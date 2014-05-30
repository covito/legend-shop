package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.Area;
import com.legendshop.model.entity.City;
import com.legendshop.model.entity.Province;
import java.util.List;

public abstract interface LocationDao extends BaseDao
{
  public abstract List<KeyValueEntity> getProvincesList();

  public abstract List<KeyValueEntity> getCitiesList(Integer paramInteger);

  public abstract List<KeyValueEntity> getAreaList(Integer paramInteger);

  public abstract List<Province> getAllProvince();

  public abstract List<City> getCity(Integer paramInteger);

  public abstract List<Area> getArea(Integer paramInteger);

  public abstract Province getProvinceById(Integer paramInteger);

  public abstract void deleteProvince(Integer paramInteger);

  public abstract void deleteCity(Integer paramInteger);

  public abstract void deleteArea(Integer paramInteger);

  public abstract void saveProvince(Province paramProvince);

  public abstract void saveCity(City paramCity);

  public abstract void saveArea(Area paramArea);

  public abstract City getCityById(Integer paramInteger);

  public abstract Area getAreaById(Integer paramInteger);

  public abstract void updateProvince(Province paramProvince);

  public abstract void updateCity(City paramCity);

  public abstract void updateArea(Area paramArea);
}