package com.legendshop.core.dao.support;

import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.util.AppUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CriteriaQuery extends AbstractQuery implements Serializable {
	private static final long serialVersionUID = -1464383406564081554L;
	private CriterionList _$3;
	private DetachedCriteria _$2;
	private List<Order> _$1;

	public CriteriaQuery() {
	}

	public CriteriaQuery(Class paramClass) {
		this._$3 = new CriterionList();
		this._$2 = DetachedCriteria.forClass(paramClass);
	}

	public CriteriaQuery(Class paramClass, String paramString1,
			String paramString2, PageProviderEnum paramPageProviderEnum) {
		if (paramString2 != null)
			this.myaction = paramString2;
		setCurPage(paramString1);
		this._$3 = new CriterionList();
		this._$2 = DetachedCriteria.forClass(paramClass);
		this.pageProvider = paramPageProviderEnum;
	}

	public CriteriaQuery(Class paramClass, String paramString1,
			String paramString2) {
		this(paramClass, paramString1, paramString2,
				PageProviderEnum.PAGE_PROVIDER);
	}

	public CriteriaQuery(Class paramClass, String paramString) {
		this(paramClass, paramString, null, PageProviderEnum.PAGE_PROVIDER);
	}

	public CriteriaQuery(Class paramClass, String paramString,
			PageProviderEnum paramPageProviderEnum) {
		this(paramClass, paramString, null, paramPageProviderEnum);
	}

	public CriterionList getCriterionList() {
		return this._$3;
	}

	public void setCriterionList(CriterionList paramCriterionList) {
		this._$3 = paramCriterionList;
	}

	public DetachedCriteria getDetachedCriteria() {
		return this._$2;
	}

	public void setDetachedCriteria(DetachedCriteria paramDetachedCriteria) {
		this._$2 = paramDetachedCriteria;
	}

	public void add(Criterion paramCriterion) {
		this._$2.add(paramCriterion);
	}

	public void add() {
		for (int i = 0; i < getCriterionList().size(); ++i)
			add(getCriterionList().getParas(i));
	}

	public void createAlias(String paramString) {
		this._$2.createCriteria(paramString);
	}

	public void createAlias(String paramString1, String paramString2) {
		this._$2.createCriteria(paramString1, paramString2);
	}

	public Criterion and(CriteriaQuery paramCriteriaQuery, int paramInt1,
			int paramInt2) {
		return Restrictions.and(
				paramCriteriaQuery.getCriterionList().getParas(paramInt1),
				paramCriteriaQuery.getCriterionList().getParas(paramInt2));
	}

	public Criterion and(Criterion paramCriterion,
			CriteriaQuery paramCriteriaQuery, int paramInt) {
		return Restrictions.and(paramCriterion, paramCriteriaQuery
				.getCriterionList().getParas(paramInt));
	}

	public Criterion and(Criterion paramCriterion1, Criterion paramCriterion2) {
		return Restrictions.and(paramCriterion1, paramCriterion2);
	}

	public Criterion or(CriteriaQuery paramCriteriaQuery, int paramInt1,
			int paramInt2) {
		return Restrictions.or(
				paramCriteriaQuery.getCriterionList().getParas(paramInt1),
				paramCriteriaQuery.getCriterionList().getParas(paramInt2));
	}

	public Criterion or(Criterion paramCriterion,
			CriteriaQuery paramCriteriaQuery, int paramInt) {
		return Restrictions.or(paramCriterion, paramCriteriaQuery
				.getCriterionList().getParas(paramInt));
	}

	public void or(Criterion paramCriterion1, Criterion paramCriterion2) {
		this._$2.add(Restrictions.or(paramCriterion1, paramCriterion2));
	}

	public void addOrder(String paramString1, String paramString2) {
		if (this._$1 == null)
			this._$1 = new ArrayList();
		if ("asc".equals(paramString1))
			this._$1.add(Order.asc(paramString2));
		else
			this._$1.add(Order.desc(paramString2));
	}

	public void eq(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.eq(paramString, paramObject));
	}

	public void notEq(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.or(
					Restrictions.gt(paramString, paramObject),
					Restrictions.lt(paramString, paramObject)));
	}

	public void like(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.like(paramString, paramObject));
	}

	public void gt(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.gt(paramString, paramObject));
	}

	public void lt(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.lt(paramString, paramObject));
	}

	public void le(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.le(paramString, paramObject));
	}

	public void ge(String paramString, Object paramObject) {
		if (AppUtils.isNotBlank(paramObject))
			this._$3.addPara(Restrictions.ge(paramString, paramObject));
	}

	public void ilike(String paramString, Object[] paramArrayOfObject) {
		if (AppUtils.isNotBlank(paramArrayOfObject))
			this._$3.addPara(Restrictions.in(paramString, paramArrayOfObject));
	}

	public void between(String paramString, Object paramObject1,
			Object paramObject2) {
		Criterion localObject = null;
		if ((AppUtils.isNotBlank(paramObject1))
				&& (AppUtils.isNotBlank(paramObject2)))
			localObject = Restrictions.between(paramString, paramObject1,
					paramObject2);
		else if (AppUtils.isNotBlank(paramObject1))
			localObject = Restrictions.ge(paramString, paramObject1);
		else if (AppUtils.isNotBlank(paramObject2))
			localObject = Restrictions.le(paramString, paramObject2);
		this._$3.add(localObject);
	}

	public List<Order> getOrderList() {
		return this._$1;
	}
}