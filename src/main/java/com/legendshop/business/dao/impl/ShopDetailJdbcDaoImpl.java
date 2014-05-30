package com.legendshop.business.dao.impl;

import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ShopDetailJdbcDaoImpl extends ShopDetailDaoImpl {
	private static Logger log = LoggerFactory
			.getLogger(ShopDetailJdbcDaoImpl.class);
	private JdbcTemplate jdbcTemplate;

	public ShopDetailView getShopDetailView(String userName) {
		if (AppUtils.isBlank(userName))
			return null;

		log.debug("getSimpleInfoShopDetail userName : {}", userName);
		List list = null;
		String sql = ConfigCode.getInstance().getCode("biz.getShopDetailView");
		log.debug("getShopDetailView run sql {}, userName {} ", sql, userName);
		list = this.jdbcTemplate.query(sql, new Object[] { userName },
				new ShopDetailRowMapper());
		if (AppUtils.isNotBlank(list))
			return ((ShopDetailView) list.get(0));

		return null;
	}

	public String getShopNameByDomain(String domainName) {
		if (AppUtils.isBlank(domainName))
			return null;

		String sql = "select user_name from ls_shop_detail where domain_name = ?";
		log.debug("getShopNameByDomain run sql {}, domainName {} ", sql,
				domainName);
		List result = this.jdbcTemplate.queryForList(sql,
				new Object[] { domainName }, String.class);
		if (AppUtils.isNotBlank(result))
			return ((String) result.get(0));

		return null;
	}

	public List<ShopDetailView> getShopDetail(Long[] shopId) {
		List postIdList = new ArrayList();
		StringBuffer sb = new StringBuffer(ConfigCode.getInstance().getCode(
				"biz.getShopDetailViewList"));
		for (int i = 0; i < shopId.length - 1; ++i)
			if (shopId[i] != null) {
				sb.append("?,");
				postIdList.add(shopId[i]);
			}

		if (postIdList.size() == 0)
			return new ArrayList();

		sb.setLength(sb.length() - 1);
		sb.append(")");
		if (log.isDebugEnabled())
			log.debug("getShopDetailView run sql {}, postIdList {} ",
					sb.toString(), postIdList.toArray());

		return this.jdbcTemplate.query(sb.toString(), postIdList.toArray(),
				new ShopDetailRowMapper());
	}

	public Long getShopIdByName(String userName) {
		List result = this.jdbcTemplate
				.query("select sd.shop_id as shopId from ls_shop_detail sd where sd.user_name = ?",
						new Object[] { userName }, new RowMapper() {
							public Long mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return Long.valueOf(rs.getLong("shopId"));
							}

						});
		if (AppUtils.isBlank(result))
			return null;

		return ((Long) result.get(0));
	}

	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class ShopDetailRowMapper implements RowMapper<ShopDetailView> {
		public ShopDetailView mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ShopDetailView shopDetail = new ShopDetailView();
			shopDetail.setShopId(Long.valueOf(rs.getLong("shopId")));
			shopDetail.setUserId(rs.getString("userId"));
			shopDetail.setUserName(rs.getString("userName"));
			shopDetail.setSiteName(rs.getString("siteName"));
			shopDetail.setShopAddr(rs.getString("shopAddr"));
			shopDetail.setBankCard(rs.getString("bankCard"));
			shopDetail.setPayee(rs.getString("payee"));
			shopDetail.setCode(rs.getString("code"));
			shopDetail.setPostAddr(rs.getString("postAddr"));
			shopDetail.setRecipient(rs.getString("recipient"));
			shopDetail.setStatus(Integer.valueOf(rs.getInt("status")));
			shopDetail.setVisitTimes(Integer.valueOf(rs.getInt("visitTimes")));
			shopDetail.setProductNum(Integer.valueOf(rs.getInt("productNum")));
			shopDetail.setCommNum(Integer.valueOf(rs.getInt("commNum")));
			shopDetail.setOffProductNum(Integer.valueOf(rs
					.getInt("offProductNum")));
			shopDetail.setModifyDate(rs.getDate("modifyDate"));
			shopDetail.setRecDate(rs.getDate("recDate"));
			shopDetail.setBriefDesc(rs.getString("briefDesc"));
			shopDetail.setDetailDesc(rs.getString("detailDesc"));
			shopDetail.setShopPic(rs.getString("shopPic"));
			shopDetail.setFrontEndLanguage(rs.getString("frontEndLanguage"));
			shopDetail.setBackEndLanguage(rs.getString("backEndLanguage"));
			shopDetail.setGradeId(Integer.valueOf(rs.getInt("gradeId")));
			shopDetail.setType(Integer.valueOf(rs.getInt("type")));
			shopDetail.setIdCardPic(rs.getString("idCardPic"));
			shopDetail.setTrafficPic(rs.getString("trafficPic"));
			shopDetail.setIdCardNum(rs.getString("idCardNum"));
			shopDetail.setCreateCountryCode(rs.getString("createCountryCode"));
			shopDetail.setCreateAreaCode(rs.getString("createAreaCode"));
			shopDetail.setProvinceid(Integer.valueOf(rs.getInt("provinceid")));
			shopDetail.setCityid(Integer.valueOf(rs.getInt("cityid")));
			shopDetail.setAreaid(Integer.valueOf(rs.getInt("areaid")));
			shopDetail.setProvince(rs.getString("province"));
			shopDetail.setCity(rs.getString("city"));
			shopDetail.setArea(rs.getString("area"));
			shopDetail.setUserTel(rs.getString("userTel"));
			shopDetail.setNickName(rs.getString("nickName"));
			shopDetail.setUserMobile(rs.getString("userMobile"));
			shopDetail.setQq(rs.getString("qq"));
			shopDetail.setBankCard(rs.getString("msnNumber"));
			shopDetail.setUserPostcode(rs.getString("userPostcode"));
			shopDetail.setFax(rs.getString("fax"));
			shopDetail.setFrontEndStyle(rs.getString("frontEndStyle"));
			shopDetail.setBackEndStyle(rs.getString("backEndStyle"));
			shopDetail.setFrontEndTemplet(rs.getString("frontEndTemplet"));
			shopDetail.setBackEndTemplet(rs.getString("backEndTemplet"));
			if (shopDetail.getQq() != null) {
				String[] qqs = shopDetail.getQq().split(",");
				List qqList = new ArrayList(qqs.length);
				for (int i = 0; i < qqs.length; ++i)
					if ((qqs[i] != null) && (qqs[i].length() > 0))
						qqList.add(qqs[i]);

				shopDetail.setQqList(qqList);
			}
			shopDetail.setDomainName(rs.getString("domainName"));
			shopDetail.setIcpInfo(rs.getString("icpInfo"));
			shopDetail.setLogoPic(rs.getString("logoPic"));
			return shopDetail;
		}
	}
}