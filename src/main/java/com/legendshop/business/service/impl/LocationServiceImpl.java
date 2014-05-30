package com.legendshop.business.service.impl;

import com.legendshop.business.dao.LocationDao;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.Area;
import com.legendshop.model.entity.City;
import com.legendshop.model.entity.Province;
import com.legendshop.spi.service.LocationService;
import com.legendshop.spi.service.impl.AbstractService;
import java.util.List;

public class LocationServiceImpl extends AbstractService
  implements LocationService
{
  private LocationDao locationDao;

  public List<KeyValueEntity> loadProvinces()
  {
    return this.locationDao.getProvincesList();
  }

  public List<KeyValueEntity> loadCities(Integer provinceid)
  {
    return this.locationDao.getCitiesList(provinceid);
  }

  public List<KeyValueEntity> loadAreas(Integer cityid)
  {
    return this.locationDao.getAreaList(cityid);
  }

  public List<Province> getAllProvince()
  {
    return this.locationDao.getAllProvince();
  }

  public void setLocationDao(LocationDao locationDao)
  {
    this.locationDao = locationDao;
  }

  public List<City> getCity(Integer provinceid)
  {
    return this.locationDao.getCity(provinceid);
  }

  public List<Area> getArea(Integer cityid)
  {
    return this.locationDao.getArea(cityid);
  }

  public Province getProvinceById(Integer id)
  {
    return this.locationDao.getProvinceById(id);
  }

  public void deleteProvince(Integer provinceid)
  {
    this.locationDao.deleteProvince(provinceid);
  }

  public void deleteCity(Integer cityid)
  {
    this.locationDao.deleteCity(cityid);
  }

  public void deleteArea(Integer areaid)
  {
    this.locationDao.deleteArea(areaid);
  }

  public void saveProvince(Province province)
  {
    this.locationDao.saveProvince(province);
  }

  public City getCityById(Integer id)
  {
    return this.locationDao.getCityById(id);
  }

  public Area getAreaById(Integer id)
  {
    return this.locationDao.getAreaById(id);
  }

  public void updateProvince(Province province)
  {
    this.locationDao.updateProvince(province);
  }

  public void updateCity(City city)
  {
    this.locationDao.updateCity(city);
  }

  public void updateArea(Area area)
  {
    this.locationDao.updateArea(area);
  }

  public void saveCity(City city)
  {
    this.locationDao.saveCity(city);
  }

  public void saveArea(Area area)
  {
    this.locationDao.saveArea(area);
  }
}