package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.LocationDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.Area;
import com.legendshop.model.entity.City;
import com.legendshop.model.entity.Province;
import java.util.Iterator;
import java.util.List;

public class LocationDaoImpl extends BaseDaoImpl implements LocationDao {
	private BaseJdbcDao baseJdbcDao;

	public List<KeyValueEntity> getProvincesList() {
		return this.baseJdbcDao.query(
				" select id as 'key' ,province as 'value' from ls_provinces ",
				KeyValueEntity.class, new Object[0]);
	}

	public List<KeyValueEntity> getCitiesList(Integer provinceid) {
		return this.baseJdbcDao
				.query("select id as 'key' ,city as 'value' from ls_cities where provinceid=? ",
						KeyValueEntity.class, new Object[] { provinceid });
	}

	public List<KeyValueEntity> getAreaList(Integer cityid) {
		List result = this.baseJdbcDao
				.query(" select id as 'key' ,area as 'value' from ls_areas where cityid=?",
						KeyValueEntity.class, new Object[] { cityid });
		return result;
	}

	public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao) {
		this.baseJdbcDao = baseJdbcDao;
	}

	public List<Province> getAllProvince() {
		List list = this.baseJdbcDao.query("select * from ls_provinces",
				Province.class, new Object[0]);
		return list;
	}

	public List<City> getCity(Integer provinceid) {
		List list = this.baseJdbcDao.query(
				"select * from ls_cities where provinceid=?", City.class,
				new Object[] { provinceid });
		return list;
	}

	public List<Area> getArea(Integer cityid) {
		List list = this.baseJdbcDao.query(
				"select * from ls_areas where cityid=?", Area.class,
				new Object[] { cityid });
		return list;
	}

	public Province getProvinceById(Integer id) {
		List province = this.baseJdbcDao.query(
				"select * from ls_provinces where id=?", Province.class,
				new Object[] { id });
		Iterator localIterator = province.iterator();
		if (localIterator.hasNext()) {
			Province p = (Province) localIterator.next();
			return p;
		}
		return null;
	}

	public void deleteProvince(Integer id) {
		int result = this.baseJdbcDao
				.update("delete from ls_areas where cityid in (select id from ls_cities where provinceid = ?)",
						new Object[] { id });

		result = this.baseJdbcDao.update(
				"delete from ls_cities where provinceid = ?",
				new Object[] { id });

		result = this.baseJdbcDao.update("delete from ls_provinces where id=?",
				new Object[] { id });
	}

	public void deleteCity(Integer id) {
		this.baseJdbcDao.update("delete from ls_areas where cityid  = ?",
				new Object[] { id });

		this.baseJdbcDao.update("delete from ls_cities where id=?",
				new Object[] { id });
	}

	public void deleteArea(Integer id) {
		this.baseJdbcDao.update("delete from ls_areas where id=?",
				new Object[] { id });
	}

	public void saveProvince(Province province) {
		save(province);
	}

	public void saveCity(City city) {
		save(city);
	}

	public void saveArea(Area area) {
		save(area);
	}

	public City getCityById(Integer id) {
		return ((City) this.baseJdbcDao.get(
				"select * from ls_cities where id=?", City.class,
				new Object[] { id }));
	}

	public Area getAreaById(Integer id) {
		return ((Area) this.baseJdbcDao.get(
				"select * from ls_areas where id=?", Area.class,
				new Object[] { id }));
	}

	public void updateProvince(Province province) {
		this.baseJdbcDao
				.update("update ls_provinces set province= ? ,provinceid= ? where id= ? ",
						new Object[] { province.getProvince(),
								province.getProvinceid(), province.getId() });
	}

	public void updateCity(City city) {
		this.baseJdbcDao
				.update("update ls_cities set city=?,cityid=? where id=? ",
						new Object[] { city.getCity(), city.getCityid(),
								city.getId() });
	}

	public void updateArea(Area area) {
		this.baseJdbcDao
				.update("update ls_areas set area=?,areaid=? where id=? ",
						new Object[] { area.getArea(), area.getAreaid(),
								area.getId() });
	}
}