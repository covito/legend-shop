package com.legendshop.core.helper;

import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;
import com.legendshop.util.CookieUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitHistoryHelper
{
  private static Integer _$4 = Integer.valueOf(6);
  private static Integer _$3 = Integer.valueOf(259200);
  private static String _$2 = "Visited_Prod";
  private static String _$1 = "Visited_ShopDetail";

  private static LinkedList<Object> _$1(String paramString)
  {
    LinkedList localLinkedList = new LinkedList();
    if (paramString == null)
      return localLinkedList;
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    localLinkedList = new LinkedList();
    if (localStringTokenizer.hasMoreTokens())
      try
      {
        localLinkedList.add(localStringTokenizer.nextToken());
      }
      catch (Exception localException)
      {
        return new LinkedList();
      }
    return localLinkedList;
  }

  public static void visit(ProductDetail paramProductDetail, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
  {
    String str = String.valueOf(paramProductDetail.getId());
    int i = 0;
    LinkedList localLinkedList = _$1(CookieUtil.getCookieValue(paramHttpServletRequest, _$2));
    Iterator localIterator = localLinkedList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (str.equals(localObject.toString()))
        i = 1;
    }
    if (i == 0)
    {
      if (localLinkedList.size() >= _$4.intValue())
        localLinkedList.removeFirst();
      localLinkedList.addLast(str);
      CookieUtil.addCookie(paramHttpServletResponse, _$3.intValue(), _$2, AppUtils.list2String(localLinkedList));
    }
  }

  public static void visit(ShopDetailView paramShopDetailView, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
  {
    String str = paramShopDetailView.getUserName();
    int i = 0;
    LinkedList localLinkedList = _$1(CookieUtil.getCookieValue(paramHttpServletRequest, _$1));
    Iterator localIterator = localLinkedList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (str.equals(localObject.toString()))
        i = 1;
    }
    if (i == 0)
    {
      if (localLinkedList.size() >= _$4.intValue())
        localLinkedList.removeFirst();
      localLinkedList.addLast(str);
      CookieUtil.addCookie(paramHttpServletResponse, _$3.intValue(), _$1, AppUtils.list2String(localLinkedList));
    }
  }

  public static List<Object> getVisitedShopDetail(HttpServletRequest paramHttpServletRequest)
  {
    return _$1(CookieUtil.getCookieValue(paramHttpServletRequest, _$1));
  }

  public static List<Object> getVisitedProd(HttpServletRequest paramHttpServletRequest)
  {
    return _$1(CookieUtil.getCookieValue(paramHttpServletRequest, _$2));
  }
}