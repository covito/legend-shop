package com.legendshop.model.entity;

import java.io.Serializable;

public abstract interface BaseEntity extends Serializable
{
  public abstract Serializable getId();
}