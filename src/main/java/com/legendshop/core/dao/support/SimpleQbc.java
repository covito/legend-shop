package com.legendshop.core.dao.support;

import com.legendshop.util.AppUtils;
import java.io.Serializable;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class SimpleQbc implements Serializable {
	private String _$4 = null;
	private int _$3 = 15;
	private CriterionList _$2;
	private DetachedCriteria _$1;

	public SimpleQbc() {
	}

	public SimpleQbc(Class paramClass) {
		this._$2 = new CriterionList();
		this._$1 = DetachedCriteria.forClass(paramClass);
	}

	public SimpleQbc(Class paramClass, String paramString) {
		this._$4 = paramString;
		this._$2 = new CriterionList();
		this._$1 = DetachedCriteria.forClass(paramClass);
	}

	public CriterionList getCriterionList() {
		return this._$2;
	}

	public void setCriterionList(CriterionList paramCriterionList) {
		this._$2 = paramCriterionList;
	}

	public String getCurPage() {
		return this._$4;
	}

	public void setCurPage(String paramString) {
		this._$4 = paramString;
	}

	public DetachedCriteria getDetachedCriteria() {
		return this._$1;
	}

	public void setDetachedCriteria(DetachedCriteria paramDetachedCriteria) {
		this._$1 = paramDetachedCriteria;
	}

	public void add(Criterion paramCriterion) {
		this._$1.add(paramCriterion);
	}

	public void add() {
		for (int i = 0; i < getCriterionList().size(); ++i)
			add(getCriterionList().getParas(i));
	}

	public void createAlias(String paramString) {
		this._$1.createCriteria(paramString);
	}

	public void createAlias(String paramString1, String paramString2) {
		this._$1.createCriteria(paramString1, paramString2);
	}

	public Criterion and(SimpleQbc paramSimpleQbc, int paramInt1, int paramInt2) {
		return Restrictions.and(
				paramSimpleQbc.getCriterionList().getParas(paramInt1),
				paramSimpleQbc.getCriterionList().getParas(paramInt2));
	}

	public Criterion and(Criterion paramCriterion, SimpleQbc paramSimpleQbc,
			int paramInt) {
		return Restrictions.and(paramCriterion, paramSimpleQbc
				.getCriterionList().getParas(paramInt));
	}

	public Criterion and(Criterion paramCriterion1, Criterion paramCriterion2) {
		return Restrictions.and(paramCriterion1, paramCriterion2);
	}

	public Criterion or(SimpleQbc paramSimpleQbc, int paramInt1, int paramInt2) {
		return Restrictions.or(
				paramSimpleQbc.getCriterionList().getParas(paramInt1),
				paramSimpleQbc.getCriterionList().getParas(paramInt2));
	}

	public Criterion or(Criterion paramCriterion, SimpleQbc paramSimpleQbc,
			int paramInt) {
		return Restrictions.or(paramCriterion, paramSimpleQbc
				.getCriterionList().getParas(paramInt));
	}

	public void or(Criterion paramCriterion1, Criterion paramCriterion2) {
		this._$1.add(Restrictions.or(paramCriterion1, paramCriterion2));
	}

	public void addOrder(String paramString1, String paramString2) {
		if ("asc".equals(paramString1))
			this._$1.addOrder(Order.asc(paramString2));
		else
			this._$1.addOrder(Order.desc(paramString2));
	}

	public void eq(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.eq(paramString, paramObject));
	}

	public void isnull(String paramString) {
		this._$2.addPara(Restrictions.isNull(paramString));
	}

	public void isNotNull(String paramString) {
		this._$2.addPara(Restrictions.isNotNull(paramString));
	}

	public void not(Criterion paramCriterion) {
		this._$2.addPara(Restrictions.not(paramCriterion));
	}

	public void notEq(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.or(
				Restrictions.gt(paramString, paramObject),
				Restrictions.lt(paramString, paramObject)));
	}

	public void like(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.like(paramString, paramObject));
	}

	public void gt(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.gt(paramString, paramObject));
	}

	public void lt(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.lt(paramString, paramObject));
	}

	public void le(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.le(paramString, paramObject));
	}

	public void ge(String paramString, Object paramObject) {
		this._$2.addPara(Restrictions.ge(paramString, paramObject));
	}

	public void ilike(String paramString, Object[] paramArrayOfObject) {
		this._$2.addPara(Restrictions.in(paramString, paramArrayOfObject));
	}

	public void between(String paramString, Object paramObject1,
			Object paramObject2) {
		Criterion localObject = null;
		if ((!(AppUtils.isBlank(paramObject1)))
				&& (!(AppUtils.isBlank(paramObject2))))
			localObject = Restrictions.between(paramString, paramObject1,
					paramObject2);
		else if (!(AppUtils.isBlank(paramObject1)))
			localObject = Restrictions.ge(paramString, paramObject1);
		else if (!(AppUtils.isBlank(paramObject2)))
			localObject = Restrictions.le(paramString, paramObject2);
		this._$2.add(localObject);
	}

	public int getPageSize() {
		return this._$3;
	}

	public void setPageSize(int paramInt) {
		this._$3 = paramInt;
	}

	public String toString() {
		return super.toString();
	}
}