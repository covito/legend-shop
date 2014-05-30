package com.legendshop.core.dao.support;

import java.util.ArrayList;
import org.hibernate.criterion.Criterion;

public class CriterionList extends ArrayList<Criterion>
{
  public final Criterion getParas(int paramInt)
  {
    return ((Criterion)super.get(paramInt));
  }

  public final void addPara(int paramInt, Criterion paramCriterion)
  {
    super.add(paramInt, paramCriterion);
  }

  public final void addPara(Criterion paramCriterion)
  {
    super.add(paramCriterion);
  }

  public final int indexofPara(Criterion paramCriterion)
  {
    return super.indexOf(paramCriterion);
  }

  public final void removePara(int paramInt)
  {
    super.remove(paramInt);
  }
}