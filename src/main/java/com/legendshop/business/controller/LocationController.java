package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.spi.service.LocationService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationController extends BaseController
{

  @Autowired
  private LocationService locationService;

  @RequestMapping({"/common/loadProvinces"})
  @ResponseBody
  public List<KeyValueEntity> loadProvincesEntity(HttpServletRequest request, HttpServletResponse response)
  {
    return this.locationService.loadProvinces();
  }

  @RequestMapping({"/common/loadCities/{provinceid}"})
  @ResponseBody
  public List<KeyValueEntity> loadCities(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer provinceid)
  {
    return this.locationService.loadCities(provinceid);
  }

  @RequestMapping({"/common/loadAreas/{cityid}"})
  @ResponseBody
  public List<KeyValueEntity> loadAreas(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer cityid)
  {
    return this.locationService.loadAreas(cityid);
  }
}