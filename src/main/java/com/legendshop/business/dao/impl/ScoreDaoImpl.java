package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ScoreDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Score;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.constants.SubStatusEnum;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class ScoreDaoImpl extends SubCommonDaoImpl
  implements ScoreDao
{
  private static Logger log = LoggerFactory.getLogger(ScoreDaoImpl.class);
  private UserDetailDao userDetailDao;
  private Long moneyPerScore = Long.valueOf(-4648543787084152831L);
  private Double scorePerMoney = Double.valueOf(0.10000000000000001D);
  private final String CREDIT_SCORE = "C";
  private final String DEBIT_SCORE = "D";

  public void saveScore(Sub sub)
  {
    log.debug("addScore UserName = {},Score ={} ", sub.getUserName(), sub.getScore());

    if ((sub == null) || (sub.getTotal().doubleValue() <= 0D) || (sub.getScoreId() != null) || 
      (!(((Boolean)PropertiesUtil.getObject(SysParameterEnum.USE_SCORE, Boolean.class)).booleanValue())))
      return;

    UserDetail userDetail = this.userDetailDao.getUserDetailByName(sub.getUserName());
    Long score = userDetail.getScore();
    if (score == null)
      score = Long.valueOf(-4648549920297451520L);

    Long core = calScore(sub.getTotal(), "C");
    userDetail.setScore(Long.valueOf(score.longValue() + core.longValue()));
    update(userDetail);
    save(makeScore(sub, core, "C"));
  }

  public Map<String, Object> deleteScore(Sub sub, Long avaibleScore)
  {
    if ((sub == null) || (avaibleScore == null) || (avaibleScore.longValue() <= -4648543065529647104L) || 
      (!(((Boolean)PropertiesUtil.getObject(SysParameterEnum.USE_SCORE, Boolean.class)).booleanValue())))
      return null;

    Map map = new HashMap();
    UserDetail userDetail = this.userDetailDao.getUserDetailByName(sub.getUserName());
    Long orginScore = userDetail.getScore();
    if (orginScore == null)
      orginScore = Long.valueOf(-4648549920297451520L);

    if (orginScore.longValue() - avaibleScore.longValue() < -4648542773471870976L) {
      throw new BusinessException("Not enough score", "605");
    }

    Long requiredScore = calRequiredScore(sub.getTotal());
    Long usedScore = null;

    if (requiredScore.longValue() > avaibleScore.longValue()) {
      userDetail.setScore(Long.valueOf(orginScore.longValue() - avaibleScore.longValue()));
      usedScore = avaibleScore;
      saveSubHistory(sub, SubStatusEnum.DEBIT_SCORE.value());
      sub.setActualTotal(sub.getTotal());
      sub.setTotal(Double.valueOf(sub.getTotal().doubleValue() - calMoney(avaibleScore).doubleValue()));
    } else {
      saveSubHistory(sub, SubStatusEnum.DEBIT_SCORE.value());

      sub.setActualTotal(sub.getTotal());
      sub.setTotal(Double.valueOf(0D));
      sub.setStatus(OrderStatusEnum.PADYED.value());
      sub.setUpdateDate(new Date());
      userDetail.setScore(Long.valueOf(avaibleScore.longValue() - requiredScore.longValue()));
      usedScore = requiredScore;
    }

    map.put("userScore", userDetail.getScore());
    update(userDetail);
    map.put("subTotal", sub.getTotal());
    Long scoreId = (Long)save(makeScore(sub, usedScore, "D"));
    sub.setScoreId(scoreId);
    sub.setScore(usedScore);
    update(sub);
    return map;
  }

  private Long calRequiredScore(Double total)
  {
    return Long.valueOf(Double.valueOf(Math.ceil(total.doubleValue() / this.scorePerMoney.doubleValue())).longValue());
  }

  public Long calScore(Double total, String scoreType)
  {
    if ("C".equals(scoreType))
    {
      return Long.valueOf(Double.valueOf(Math.floor(this.moneyPerScore.longValue() * total.doubleValue())).longValue());
    }

    return Long.valueOf(Double.valueOf(Math.ceil(this.moneyPerScore.longValue() * total.doubleValue())).longValue());
  }

  private Score makeScore(Sub sub, Long score, String scoreType)
  {
    Score entity = new Score();
    entity.setRecDate(new Date());
    entity.setScore(score);
    entity.setScoreType(scoreType);
    entity.setSubId(sub.getSubId());
    entity.setUserName(sub.getUserName());
    return entity;
  }

  public Double calMoney(Long score)
  {
    return Double.valueOf(this.scorePerMoney.doubleValue() * score.longValue());
  }

  @Required
  public void setMoneyPerScore(Long moneyPerScore)
  {
    this.moneyPerScore = moneyPerScore;
  }

  @Required
  public void setScorePerMoney(Double scorePerMoney)
  {
    this.scorePerMoney = scorePerMoney;
  }

  @Required
  public void setUserDetailDao(UserDetailDao userDetailDaoImpl)
  {
    this.userDetailDao = userDetailDaoImpl;
  }
}