package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.DeliveryCorpDao;
import com.legendshop.business.dao.DeliveryTypeDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.event.FireEvent;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.randing.RandomStringUtils;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.model.entity.UserRole;
import com.legendshop.model.entity.UserRoleId;
import com.legendshop.spi.constants.RegisterEnum;
import com.legendshop.spi.constants.RoleEnum;
import com.legendshop.spi.event.SendMailEvent;
import com.legendshop.spi.service.CommonUtil;
import com.legendshop.util.AppUtils;
import com.legendshop.util.MD5Util;
import com.legendshop.util.constant.ShopStatusEnum;
import com.legendshop.util.ip.IPSeeker;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.mail.MessagingException;
import org.apache.oro.text.regex.MalformedPatternException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UserDetailDaoImpl extends BaseDaoImpl implements UserDetailDao {
	private static Logger log = LoggerFactory
			.getLogger(UserDetailDaoImpl.class);
	private CommonUtil commonUtil;
	private BaseJdbcDao baseJdbcDao;
	private SubDao subDao;
	private BasketDao basketDao;
	private DeliveryTypeDao deliveryTypeDao;
	private DeliveryCorpDao deliveryCorpDao;
	private final String REGISTED_TAG = "REGISTED";
	private Map<String, List<Integer>> scaleList;

	public String saveUser(User user, UserDetail userDetail,
			ShopDetail shopDetail, boolean isOpenShop) {
		if (((Boolean) PropertiesUtil.getObject(
				SysParameterEnum.VALIDATION_FROM_MAIL, Boolean.class))
				.booleanValue()) {
			user.setEnabled("0");
			userDetail.setRegisterCode(MD5Util.Md5Password(user.getName(),
					user.getPassword()));
		}
		String userId = (String) save(user);
		userDetail.setUserId(userId);
		userDetail.setUserName(user.getName());

		saveUerDetail(userDetail, shopDetail, isOpenShop);
		return userId;
	}

	public void saveUerDetail(UserDetail userDetail, ShopDetail shopDetail,
			boolean isOpenShop) {
		if (isOpenShop) {
			saveShopDetailAndRole(userDetail, shopDetail);
		} else
			this.commonUtil.saveUserRight(userDetail.getUserId());

		save(userDetail);
	}

	public Integer saveShopDetailAndRole(UserDetail userDetail,
			ShopDetail shopDetail) {
		return saveShopDetail(userDetail, shopDetail);
	}

	public void updateShopDetail(UserDetail userDetail, ShopDetail shopDetail,
			boolean isAdmin) {
		if (isAdmin)
			saveShopDetail(userDetail, shopDetail);
	}

	public Integer saveShopDetail(UserDetail userDetail, ShopDetail shopDetail) {
		Date date = new Date();
		shopDetail.setUserName(userDetail.getUserName());
		shopDetail.setShopAddr(userDetail.getUserAdds());
		shopDetail.setModifyDate(date);
		shopDetail.setUserId(userDetail.getUserId());
		shopDetail.setRecDate(date);
		shopDetail.setVisitTimes(Integer.valueOf(0));
		shopDetail.setOffProductNum(Integer.valueOf(0));
		shopDetail.setProductNum(Integer.valueOf(0));
		shopDetail.setCommNum(Integer.valueOf(0));
		shopDetail.setCapital(Double.valueOf(0D));
		shopDetail.setCredit(Integer.valueOf(0));
		if (((Boolean) PropertiesUtil.getObject(
				SysParameterEnum.VALIDATION_ON_OPEN_SHOP, Boolean.class))
				.booleanValue()) {
			shopDetail.setStatus(ShopStatusEnum.AUDITING.value());
			this.commonUtil.saveUserRight(userDetail.getUserId());
		} else {
			shopDetail.setStatus(ShopStatusEnum.ONLINE.value());
			this.commonUtil.saveAdminRight(userDetail.getUserId());
		}

		if (shopDetail.getIp() != null) {
			shopDetail.setCreateAreaCode(IPSeeker.getInstance().getArea(
					shopDetail.getIp()));
			shopDetail.setCreateCountryCode(IPSeeker.getInstance().getCountry(
					shopDetail.getIp()));
		}
		String userName = userDetail.getUserName();
		String subPath = userName + "/shop/";
		String filename = null;

		MultipartFile idCardPicFile = shopDetail.getIdCardPicFile();
		MultipartFile trafficPicFile = shopDetail.getTrafficPicFile();

		if ((idCardPicFile != null)
				&& (idCardPicFile.getSize() > -4648543065529647104L)) {
			filename = FileProcessor.uploadFileAndCallback(idCardPicFile,
					subPath, "");
			log.info("{} 上传身份证照片文件 {} ", userDetail.getUserName(), filename);
			shopDetail.setIdCardPic(filename);
		}

		if ((trafficPicFile != null)
				&& (trafficPicFile.getSize() > -4648543065529647104L)) {
			filename = FileProcessor.uploadFileAndCallback(trafficPicFile,
					subPath, "");
			log.info("{} 营业执照照片文件 {} ", userDetail.getUserName(), filename);
			shopDetail.setTrafficPic(filename);
		}
		save(shopDetail);
		return shopDetail.getStatus();
	}

	public void updateUser(UserDetail userDetail) {
		EventHome.publishEvent(new FireEvent(userDetail,
				OperationTypeEnum.UPDATE));
		update(userDetail);
		updatePassword(userDetail);
	}

	public void updatePassword(UserDetail userDetail) {
		if (!(AppUtils.isBlank(userDetail.getPassword()))) {
			User user = (User) get(User.class, userDetail.getUserId());
			user.setPassword(MD5Util.Md5Password(userDetail.getUserName(),
					userDetail.getPassword()));
			EventHome.publishEvent(new FireEvent(user,
					OperationTypeEnum.UPDATE_PASSWORD));
			update(user);
		}
	}

	public boolean isUserExist(String userName) {
		List list = findByHQL("select 1 from User where name = ?",
				new Object[] { userName });
		return AppUtils.isNotBlank(list);
	}

	public boolean isEmailExist(String email) {
		List list = findByHQL("select 1  from UserDetail where userMail = ?",
				new Object[] { email });
		return AppUtils.isNotBlank(list);
	}

	public boolean isShopExist(String shopName) {
		List list = findByHQL("from ShopDetail where userName = ?",
				new Object[] { shopName });

		return (!(AppUtils.isBlank(list)));
	}

	public User getUser(String userId) {
		return ((User) get(User.class, userId));
	}

	public User getUserByName(String userName) {
		return ((User) findUniqueBy("from User where name = ?", User.class,
				new Object[] { userName }));
	}

	public UserDetail getUserDetailByName(String userName) {
		return ((UserDetail) findUniqueBy("from UserDetail where userName = ?",
				UserDetail.class, new Object[] { userName }));
	}

	public void setCommonUtil(CommonUtil commonUtilImpl) {
		this.commonUtil = commonUtilImpl;
	}

	public RegisterEnum getUserRegStatus(String userName, String registerCode) {
		UserDetail userDetail = getUserDetailByName(userName);
		if (userDetail == null)
			return RegisterEnum.REGISTER_NO_USER_FOUND;

		if ((registerCode != null)
				&& (registerCode.equals(userDetail.getRegisterCode()))) {
			User user = getUser(userDetail.getUserId());
			user.setEnabled("1");
			update(user);

			userDetail.setRegisterCode("REGISTED");
			update(userDetail);
			return RegisterEnum.REGISTER_SUCCESS;
		}
		if ("REGISTED".equals(userDetail.getRegisterCode()))
			return RegisterEnum.REGISTER_SUCCESS;

		return RegisterEnum.REGISTER_FAIL;
	}

	public UserDetail getUserDetail(String userName) {
		UserDetail userDetail = (UserDetail) findUniqueBy(
				"from UserDetail where userName= ?", UserDetail.class,
				new Object[] { userName });
		if (userDetail != null) {
			String qq = userDetail.getQq();
			if (AppUtils.isNotBlank(qq)) {
				String[] qqs = qq.split(",");
				List list = new ArrayList();
				for (int i = 0; i < qqs.length; ++i)
					if (AppUtils.isNotBlank(qqs[i]))
						list.add(qqs[i]);

				userDetail.setQqList(list);
			}
			ShopDetail shopDetail = (ShopDetail) findUniqueBy(
					"from ShopDetail where userId = ?", ShopDetail.class,
					new Object[] { userDetail.getUserId() });
			userDetail.setShopDetail(shopDetail);
		}

		return userDetail;
	}

	public Long getUserScore(String userName) {
		UserDetail userDetail = (UserDetail) findUniqueBy(
				"from UserDetail where userName= ?", UserDetail.class,
				new Object[] { userName });
		if ((userDetail != null) && (userDetail.getScore() != null))
			return userDetail.getScore();

		return Long.valueOf(-4648541656780374016L);
	}

	public PageSupport getUserDetailList(HqlQuery hqlQuery) {
		return find(hqlQuery);
	}

	public PageSupport getUserDetailList(SqlQuery sqlQuery) {
		return find(sqlQuery);
	}

	public String deleteUserDetail(String userId, String userName) {
		if (PropertiesUtil.getDefaultShopName().equals(userName)) {
			return "不能删除默认商城" + userName + "， 请在系统管理中修改默认商城";
		}

		List list = findByHQL("from UserRole where id.userId = ?",
				new Object[] { userId });
		boolean isAdmin = false;
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			UserRole role = (UserRole) localIterator.next();

			if (!(role.getId().getRoleId().equals(RoleEnum.ROLE_SUPERVISOR
					.value())))
				isAdmin = true;
		}

		if (isAdmin) {
			return "不能删除商家用户或者管理员用户，请先备份好数据和去掉该用户的权限再试！";
		}

		this.basketDao.deleteUserBasket(userName);

		this.subDao.deleteSub(userName);

		deleteUserItem("ls_favorite", userName);

		deleteUserItem("ls_score", userName);

		deleteShopDetail(userId, userName, false);

		deleteAll(list);

		deleteById(UserDetail.class, userId);

		User user = getUser(userId);
		if (user != null) {
			delete(user);
			EventHome
					.publishEvent(new FireEvent(user, OperationTypeEnum.DELETE));
		} else {
			log.debug("删除用户{}不存在", userName);
			return "删除用户" + userName + "不存在";
		}

		log.debug("删除用户 {},  {} 成功", userId, userName);
		return null;
	}

	public void deleteShopDetail(String userId, String userName,
			boolean deleteShopOnly) {
		if (PropertiesUtil.getDefaultShopName().equals(userName))
			return;

		if (deleteShopOnly) {
			this.commonUtil.removeAdminRight(userId);
		}

		this.basketDao.deleteBasket(userName);

		this.deliveryTypeDao.deleteDeliveryType(userName);

		this.deliveryCorpDao.deleteDeliveryCorp(userName);

		deleteUserItem("ls_shop_detail", userName);

		deleteUserItem("ls_img_file", userName);

		deleteUserItem("ls_prod_rel", userName);

		deleteUserItem("ls_usr_comm", userName);

		this.baseJdbcDao
				.update("delete from ls_tag_map where exists (select 1 from ls_tag where ls_tag_map.tag_id = ls_tag.tag_id and ls_tag.user_name = ?)",
						new Object[] { userName });
		deleteUserItem("ls_tag", userName);

		this.baseJdbcDao.update("delete from ls_league where user_id = ?",
				new Object[] { userName });
		this.baseJdbcDao.update("delete from ls_league where friend_id = ?",
				new Object[] { userName });

		this.baseJdbcDao.update(
				"delete from ls_prod_comm where owner_name = ?",
				new Object[] { userName });

		this.baseJdbcDao.update(
				"delete from ls_prod_cons where ask_user_name = ?",
				new Object[] { userName });

		deleteUserItem("ls_dyn_temp", userName);

		this.baseJdbcDao
				.update("delete from ls_group_prod where exists (select 1 from ls_prod where ls_group_prod.prod_id = ls_prod.prod_id and ls_prod.user_name = ?)",
						new Object[] { userName });

		deleteUserItem("ls_partner", userName);

		deleteUserItem("ls_prod", userName);

		deleteUserItem("ls_ns_brand", userName);

		deleteUserItem("ls_brand", userName);

		deleteUserItem("ls_index_jpg", userName);

		deleteUserItem("ls_adv", userName);

		deleteUserItem("ls_hsearch", userName);

		deleteUserItem("ls_news", userName);

		deleteUserItem("ls_news_cat", userName);

		this.baseJdbcDao
				.update("delete from ls_nsort where exists (select 1 from ls_sort sort1_ where ls_nsort.sort_id=sort1_.sort_id and sort1_.user_name= ?)",
						new Object[] { userName });

		deleteUserItem("ls_sort", userName);

		deleteUserItem("ls_pay_type", userName);

		deleteUserItem("ls_pub", userName);

		deleteUserItem("ls_extl_link", userName);

		if (AppUtils.isNotBlank(userName)) {
			FileProcessor.deleteDirectory(new File(RealPathUtil
					.getBigPicRealPath() + "/" + userName));
			deleteSmallImage(userName);
		}
	}

	private void deleteSmallImage(String userName) {
		for (Iterator localIterator = this.scaleList.keySet().iterator(); localIterator
				.hasNext();) {
			String sacle = (String) localIterator.next();
			StringBuilder sb = new StringBuilder(
					RealPathUtil.getSmallPicRealPath());
			sb.append("/").append(sacle).append("/").append(userName);
			FileProcessor.deleteDirectory(new File(sb.toString()));
		}
	}

	private void deleteUserItem(String tableName, String userName) {
		this.baseJdbcDao.update("delete from " + tableName
				+ " where user_name = ?", new Object[] { userName });
	}

	public boolean updatePassword(String userName, String mail,
			String templateFilePath) throws MalformedPatternException,
			MessagingException {
		UserDetail userDetail = (UserDetail) findUniqueBy(
				"from UserDetail n where n.userName = ? and n.userMail = ?",
				UserDetail.class, new Object[] { userName, mail });
		if (userDetail == null) {
			return false;
		}

		User user = (User) get(User.class, userDetail.getUserId());
		String newPassword = RandomStringUtils.randomNumeric(10, 6);
		user.setPassword(MD5Util.Md5Password(user.getName(), newPassword));
		update(user);

		log.info("{} 修改密码，发送通知邮件到 {}", userName, userDetail.getUserMail());

		Map values = new HashMap();

		values.put("#domainName#", PropertiesUtil.getDomainName());
		values.put("#nickName#", userDetail.getNickName());
		values.put("#userName#", userDetail.getUserName());
		values.put("#password#", newPassword);
		String text = AppUtils.convertTemplate(templateFilePath, values);
		if (PropertiesUtil.sendMail())
			EventHome.publishEvent(new SendMailEvent(userDetail.getUserMail(),
					"恭喜您，修改密码成功！", text));

		return true;
	}

	public Long getAllUserCount() {
		return ((Long) findUniqueBy("select count(*) from UserDetail",
				Long.class, new Object[0]));
	}

	public void setBaseJdbcDao(BaseJdbcDao jdbcDao) {
		this.baseJdbcDao = jdbcDao;
	}

	public void updateUser(User user) {
		update(user);
	}

	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}

	public void setDeliveryTypeDao(DeliveryTypeDao deliveryTypeDao) {
		this.deliveryTypeDao = deliveryTypeDao;
	}

	public void setDeliveryCorpDao(DeliveryCorpDao deliveryCorpDao) {
		this.deliveryCorpDao = deliveryCorpDao;
	}

	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}

	public void setScaleList(Map<String, List<Integer>> scaleList) {
		this.scaleList = scaleList;
	}
}