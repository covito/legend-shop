package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.model.entity.Area;
import com.legendshop.model.entity.City;
import com.legendshop.model.entity.DistrictEntity;
import com.legendshop.model.entity.Province;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.LocationService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/system/district"})
public class DistrictController extends BaseController
{

  @Autowired
  private LocationService locationService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.DISTRICT_LIST_PAGE);
  }

  @RequestMapping({"/queryProvince"})
  public String queryProvince(HttpServletRequest request, HttpServletResponse response)
  {
    List provinceList = this.locationService.getAllProvince();
    request.setAttribute("provinceList", provinceList);
    String path = PathResolver.getPath(request, response, BackPage.PROVINCE_CONTENT_LIST_PAGE);
    return path;
  }

  @RequestMapping({"/queryCities/{provinceid}"})
  public String queryCities(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Integer provinceid)
  {
    List cityList = this.locationService.getCity(provinceid);
    request.setAttribute("cityList", cityList);
    String path = PathResolver.getPath(request, response, BackPage.CITY_CONTENT_LIST_PAGE);
    return path;
  }

  @RequestMapping({"/queryAreas/{cityid}"})
  public String queryAreas(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Integer cityid)
  {
    List areaList = this.locationService.getArea(cityid);
    request.setAttribute("areaList", areaList);
    String path = PathResolver.getPath(request, response, BackPage.AREA_CONTENT_LIST_PAGE);
    return path;
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Province province) {
    if ((AppUtils.isBlank(province.getProvince())) || (AppUtils.isBlank(province.getProvinceid())))
      throw new BusinessException("province name or Provinceid can not be empty ");

    if (province.getId() != null)
      this.locationService.updateProvince(province);
    else {
      this.locationService.saveProvince(province);
    }

    DistrictEntity entity = new DistrictEntity();
    entity.setProvinceid(province.getId());
    request.setAttribute("entity", entity);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    String path = PathResolver.getPath(request, response, FowardPage.DISTRICT_LIST_PAGE);
    return path;
  }

  @RequestMapping({"/savecity"})
  public String saveCity(HttpServletRequest request, HttpServletResponse response, City city) {
    if ((AppUtils.isBlank(city.getCity())) || (AppUtils.isBlank(city.getProvinceid())))
      throw new BusinessException("City name or cityid can not be empty ");

    if (city.getId() != null)
      this.locationService.updateCity(city);
    else
      this.locationService.saveCity(city);

    DistrictEntity entity = new DistrictEntity();
    entity.setCityid(city.getId());
    entity.setProvinceid(city.getProvinceid());
    request.setAttribute("entity", entity);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    String path = PathResolver.getPath(request, response, FowardPage.DISTRICT_LIST_PAGE);
    return path;
  }

  @RequestMapping({"/savearea"})
  public String saveArea(HttpServletRequest request, HttpServletResponse response, Area area) {
    if ((AppUtils.isBlank(area.getArea())) || (AppUtils.isBlank(area.getAreaid())))
      throw new BusinessException("Area name or areaid can not be empty ");

    if (area.getId() != null)
      this.locationService.updateArea(area);
    else
      this.locationService.saveArea(area);

    City city = this.locationService.getCityById(area.getCityid());
    DistrictEntity entity = new DistrictEntity();
    entity.setProvinceid(city.getProvinceid());
    entity.setCityid(area.getCityid());
    entity.setAreaid(area.getId());
    request.setAttribute("entity", entity);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    String path = PathResolver.getPath(request, response, FowardPage.DISTRICT_LIST_PAGE);
    return path; }

  @RequestMapping({"/deleteprovince/{provinceid}"})
  @ResponseBody
  public String deleteProvince(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer provinceid) {
    this.locationService.deleteProvince(provinceid);
    DistrictEntity entity = new DistrictEntity();
    entity.setProvinceid(provinceid);
    request.setAttribute("entity", entity);
    return "OK";
  }

  @RequestMapping({"/deleteprovince"})
  @ResponseBody
  public String deleteMultiProvince(HttpServletRequest request, HttpServletResponse response, String ids)
  {
    List idList = JSONUtil.getArray(ids, Integer.class);
    if (AppUtils.isNotBlank(idList))
      for (Iterator localIterator = idList.iterator(); localIterator.hasNext(); ) { Integer provinceid = (Integer)localIterator.next();
        this.locationService.deleteProvince(provinceid);
      }

    return "OK"; }

  @RequestMapping({"/deletecity/{cityid}"})
  @ResponseBody
  public String deleteCity(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer cityid) {
    this.locationService.deleteCity(cityid);
    DistrictEntity entity = new DistrictEntity();
    entity.setCityid(cityid);
    request.setAttribute("entity", entity);
    return "OK";
  }

  @RequestMapping({"/deletecity"})
  @ResponseBody
  public String deleteMultiCity(HttpServletRequest request, HttpServletResponse response, String ids)
  {
    List idList = JSONUtil.getArray(ids, Integer.class);
    if (AppUtils.isNotBlank(idList))
      for (Iterator localIterator = idList.iterator(); localIterator.hasNext(); ) { Integer cityid = (Integer)localIterator.next();
        this.locationService.deleteCity(cityid);
      }

    return "OK"; }

  @RequestMapping({"/deletearea/{areaid}"})
  @ResponseBody
  public String deleteArea(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer areaid) {
    Area area = this.locationService.getAreaById(areaid);
    if (AppUtils.isNotBlank(area)) {
      City city = this.locationService.getCityById(area.getCityid());
      this.locationService.deleteArea(areaid);
      DistrictEntity entity = new DistrictEntity();
      entity.setProvinceid(city.getProvinceid());
      entity.setCityid(city.getId());
      entity.setAreaid(areaid);
      request.setAttribute("entity", entity);
      return "OK";
    }
    return "fail";
  }

  @RequestMapping({"/deletearea"})
  @ResponseBody
  public String deleteMultiArea(HttpServletRequest request, HttpServletResponse response, String ids) {
    List idList = JSONUtil.getArray(ids, Integer.class);
    if (AppUtils.isNotBlank(idList))
      for (Iterator localIterator = idList.iterator(); localIterator.hasNext(); ) { Integer areaid = (Integer)localIterator.next();
        this.locationService.deleteArea(areaid);
      }

    return "OK";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
    Province province = this.locationService.getProvinceById(id);
    request.setAttribute("province", province);
    return PathResolver.getPath(request, response, BackPage.RPOVINCE_PAGE);
  }

  @RequestMapping({"/addprovince"})
  public String addProvince(HttpServletRequest request, HttpServletResponse response) {
    return PathResolver.getPath(request, response, BackPage.RPOVINCE_PAGE);
  }

  @RequestMapping({"/update/{id}"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
    Province province = this.locationService.getProvinceById(id);
    this.locationService.updateProvince(province);
    return PathResolver.getPath(request, response, BackPage.DISTRICT_LIST_PAGE);
  }

  @RequestMapping({"/loadcity/{id}"})
  public String loadcity(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
    City city = this.locationService.getCityById(id);
    request.setAttribute("city", city);
    if (city != null) {
      Province province = this.locationService.getProvinceById(city.getProvinceid());
      request.setAttribute("province", province);
    }
    return PathResolver.getPath(request, response, BackPage.CITY_PAGE);
  }

  @RequestMapping({"/addcity/{provinceid}"})
  public String addCity(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer provinceid) {
    if (provinceid != null) {
      Province province = this.locationService.getProvinceById(provinceid);
      request.setAttribute("province", province);
    }
    return PathResolver.getPath(request, response, BackPage.CITY_PAGE);
  }

  @RequestMapping({"/updatecity/{id}"})
  public String updatecity(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
    City city = this.locationService.getCityById(id);
    this.locationService.updateCity(city);
    return PathResolver.getPath(request, response, BackPage.DISTRICT_LIST_PAGE);
  }

  @RequestMapping({"/loadarea/{id}"})
  public String loadarea(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
    Area area = this.locationService.getAreaById(id);
    if (area != null) {
      City city = this.locationService.getCityById(area.getCityid());
      request.setAttribute("city", city);
      Province province = this.locationService.getProvinceById(city.getProvinceid());
      request.setAttribute("province", province);
    }

    request.setAttribute("area", area);
    return PathResolver.getPath(request, response, BackPage.AREA_PAGE);
  }

  @RequestMapping({"/addarea/{cityid}"})
  public String addArea(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer cityid) {
    if (cityid != null) {
      City city = this.locationService.getCityById(cityid);
      request.setAttribute("city", city);
      Province province = this.locationService.getProvinceById(city.getProvinceid());
      request.setAttribute("province", province);
    }
    return PathResolver.getPath(request, response, BackPage.AREA_PAGE);
  }

  @RequestMapping({"/updatearea/{id}"})
  public String updatearea(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
    Area area = this.locationService.getAreaById(id);
    this.locationService.updateArea(area);
    return PathResolver.getPath(request, response, BackPage.DISTRICT_LIST_PAGE);
  }
}