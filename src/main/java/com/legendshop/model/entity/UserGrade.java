package com.legendshop.model.entity;

public class UserGrade
  implements BaseEntity
{
  private static final long serialVersionUID = -7775238608218891693L;
  private Integer gradeId;
  private String name;
  private Integer score;
  private String memo;

  public Integer getId()
  {
    return this.gradeId;
  }

  public Integer getGradeId()
  {
    return this.gradeId;
  }

  public void setGradeId(Integer gradeId)
  {
    this.gradeId = gradeId;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Integer getScore()
  {
    return this.score;
  }

  public void setScore(Integer score)
  {
    this.score = score;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String memo)
  {
    this.memo = memo;
  }
}