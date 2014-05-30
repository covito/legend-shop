package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.spi.page.BackPage;
import com.legendshop.util.handler.PluginRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/system/plugin"})
public class PluginController extends BaseController
{
  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response)
  {
    List plugins = PluginRepository.getInstance().getPlugins();
    request.setAttribute("pluginList", plugins);
    return PathResolver.getPath(request, response, BackPage.PLUGIN_LIST_PAGE);
  }

  @RequestMapping({"/turnon/{pluginId}"})
  @ResponseBody
  public String turnOn(HttpServletRequest request, HttpServletResponse response, @PathVariable String pluginId) {
    return PluginRepository.getInstance().turnOn(pluginId);
  }

  @RequestMapping({"/turnoff/{pluginId}"})
  @ResponseBody
  public String turnOff(HttpServletRequest request, HttpServletResponse response, @PathVariable String pluginId) {
    return PluginRepository.getInstance().turnOff(pluginId);
  }
}