package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ProductCommentDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.event.FireEvent;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.model.entity.ProductCommentCategory;
import com.legendshop.util.AppUtils;
import com.legendshop.util.Arith;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ProductCommentDaoImpl extends BaseDaoImpl implements
		ProductCommentDao {
	private JdbcTemplate jdbcTemplate;
	private SubDao subDao;
	private static Logger log = LoggerFactory
			.getLogger(ProductCommentDaoImpl.class);

	public void deleteProductComment(Long prodId, String userName) {
		exeByHQL(
				"delete from ProductComment where prodId = ? and ownerName = ?",
				new Object[] { prodId, userName });
	}

	public void saveProductComment(ProductComment productComment) {
		save(productComment);
	}

	public void updateProductComment(ProductComment productComment) {
		EventHome.publishEvent(new FireEvent(productComment,
				OperationTypeEnum.UPDATE));
		update(productComment);
	}

	public void deleteProductCommentById(Long id) {
		ProductComment productComment = (ProductComment) get(
				ProductComment.class, id);
		if (productComment != null) {
			EventHome.publishEvent(new FireEvent(productComment,
					OperationTypeEnum.DELETE));
			delete(productComment);
		}
	}

	public String validateComment(Long prodId, String userName) {
		String level = (String) PropertiesUtil.getObject(
				SysParameterEnum.COMMENT_LEVEL, String.class);
		if ("LOGONED_COMMENT".equals(level)) {
			if (!(AppUtils.isBlank(userName)))
				return "NOLOGON";
		}
		if ("BUYED_COMMENT".equals(level)) {
			if (AppUtils.isBlank(userName))
				return "NOLOGON";

			if (this.subDao.isUserOrderProduct(prodId, userName))
				return "NOBUYED";
		}
		if ("NO_COMMENT".equals(level))
			return "NOCOMMENT";

		label79: return null;
	}

	public ProductCommentCategory initProductCommentCategory(Long prodId) {
		ProductCommentCategory pcc = new ProductCommentCategory();
		String sql = "select score, count(*) as result from ls_prod_comm where  prod_id = ? group by score";
		log.debug("initProductCommentCategory run sql {}, prodId = {}", sql,
				prodId);
		List result = this.jdbcTemplate.query(sql, new Object[] { prodId },
				new ProductCommentCategoryRowMapper());
		if (AppUtils.isNotBlank(result)) {
			for (Iterator localIterator = result.iterator(); localIterator
					.hasNext();) {
				ScoreCategory scoreCategory = (ScoreCategory) localIterator
						.next();
				if ((AppUtils.isBlank(scoreCategory.getSocre()))
						|| (scoreCategory.getSocre().intValue() == 0)) {
					pcc.setMedium(pcc.getMedium()
							+ scoreCategory.getResult().intValue());
				}

				if (scoreCategory.getSocre().intValue() >= 4) {
					pcc.setHigh(pcc.getHigh()
							+ scoreCategory.getResult().intValue());
				}
				if (scoreCategory.getSocre().intValue() >= 3) {
					pcc.setMedium(pcc.getMedium()
							+ scoreCategory.getResult().intValue());
				}
				label204: pcc.setLow(pcc.getLow()
						+ scoreCategory.getResult().intValue());
			}

		}

		int total = pcc.getHigh() + pcc.getMedium() + pcc.getLow();
		if (total > 0) {
			pcc.setTotal(total);
			pcc.setHighRate(Arith.div(pcc.getHigh(), total));
			pcc.setMediumRate(Arith.div(pcc.getMedium(), total));
			pcc.setLowRate(Arith.div(pcc.getLow(), total));
		}
		pcc.setProdId(prodId);
		return pcc;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}

	class ProductCommentCategoryRowMapper implements
			RowMapper<ProductCommentDaoImpl.ScoreCategory> {
		public ProductCommentDaoImpl.ScoreCategory mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ProductCommentDaoImpl.ScoreCategory sc = new ProductCommentDaoImpl.ScoreCategory();
			sc.setSocre(Integer.valueOf(rs.getInt("score")));
			sc.setResult(Integer.valueOf(rs.getInt("result")));
			return sc;
		}
	}

	class ScoreCategory implements Serializable {
		private static final long serialVersionUID = 8821497685050946872L;
		private Integer socre;
		private Integer result;

		public Integer getSocre() {
			return this.socre;
		}

		public void setSocre(Integer socre) {
			this.socre = socre;
		}

		public Integer getResult() {
			return this.result;
		}

		public void setResult(Integer result) {
			this.result = result;
		}
	}
}