package com.legendshop.test;

import com.legendshop.model.entity.Province;
import com.legendshop.util.JSONUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONUtilTest
{
  public static void main(String[] args)
  {
    Province province = new Province();
    province.setId(Integer.valueOf(1));
    province.setProvince("province");
    province.setProvinceid("provinceid");
    String json1 = JSONUtil.getJson(province);
    System.out.println("1. " + json1);
    Map map = JSONUtil.getMap(json1);
    Province result = (Province)JSONUtil.getObject(json1, Province.class);
    System.out.println("1. 1" + map);
    System.out.println("1. 2" + result);
    List list = new ArrayList();
    list.add(province);
    String json2 = JSONUtil.getJson(list);
    System.out.println("2. " + json2);
    List list2 = JSONUtil.getArray(json2, Province.class);
    System.out.println("2. 2 " + list2);
  }
}