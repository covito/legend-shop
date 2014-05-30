package com.legendshop.model.visit;

import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class VisitHistory
{
  private Integer maxLength = Integer.valueOf(6);
  private final LinkedList<VisitItem> visitedProd = new LinkedList();
  private final LinkedList<VisitItem> visitedShop = new LinkedList();

  private void addvisitedProduct(VisitItem item)
  {
    boolean find = false;
    for (Iterator localIterator = this.visitedProd.iterator(); localIterator.hasNext(); ) { VisitItem visitItem = (VisitItem)localIterator.next();
      if (!(visitItem.getId().equals(item.getId()))) 
       find = true;
    }

    if (!(find)) {
      if (this.visitedProd.size() >= this.maxLength.intValue())
        this.visitedProd.removeFirst();

      this.visitedProd.addLast(item);
    }
  }

  private void addvisitedShop(VisitItem item)
  {
    boolean find = false;
    for (Iterator localIterator = this.visitedShop.iterator(); localIterator.hasNext(); ) { VisitItem visitItem = (VisitItem)localIterator.next();
      if (!(visitItem.getId().equals(item.getId())))
      find = true;
    }

    if (!(find)) {
      if (this.visitedShop.size() >= this.maxLength.intValue())
        this.visitedShop.removeFirst();

      this.visitedShop.addLast(item);
    }
  }

  public void visit(ProductDetail prod)
  {
    VisitItem item = new VisitItem();
    item.setName(prod.getName());
    item.setTitle(prod.getKeyWord());
    item.setId(String.valueOf(prod.getProdId()));
    item.setDate(new Date());
    item.setPic(prod.getPic());
    addvisitedProduct(item);
  }

  public void visit(ShopDetailView shopDetail)
  {
    VisitItem item = new VisitItem();
    item.setName(shopDetail.getUserName());
    item.setTitle(shopDetail.getBriefDesc());
    item.setId(shopDetail.getUserId());
    item.setDate(new Date());
    addvisitedShop(item);
  }

  public LinkedList<VisitItem> getVisitProdList()
  {
    return this.visitedProd;
  }

  public LinkedList<VisitItem> getVisitShopList()
  {
    return this.visitedShop;
  }

  public Integer getMaxLength()
  {
    return this.maxLength;
  }

  public void setMaxLength(Integer maxLength)
  {
    this.maxLength = maxLength;
  }

  public String toString()
  {
    VisitItem item;
    StringBuffer sb = new StringBuffer();
    sb.append("Visit Product: [ ");
    for (Iterator localIterator = this.visitedProd.iterator(); localIterator.hasNext(); ) { item = (VisitItem)localIterator.next();
      sb.append(item.getName()).append(", ");
    }
    sb.append("]");
    sb.append("\nVisit Shop: [ ");
    for (Iterator localIterator = this.visitedShop.iterator(); localIterator.hasNext(); ) { item = (VisitItem)localIterator.next();
      sb.append(item.getName()).append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  public LinkedList<VisitItem> getVisitedProd()
  {
    return this.visitedProd;
  }

  public LinkedList<VisitItem> getVisitedShop()
  {
    return this.visitedShop;
  }
}