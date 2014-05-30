package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Menu
  implements BaseEntity
{
  private static final long serialVersionUID = 8596054428957749988L;
  private Long menuId;
  private String name;
  private String label;
  private String title;
  private List<String> roleNameList;
  private Integer seq;
  private String action;
  private List<String> requiredAnyFunctions;
  private String providedPlugin;
  private Long parentId;
  private Integer level;
  private Set<Menu> subMenu = new TreeSet(new MenuComparator());
  private Menu parentMenu;

  public Long getMenuId()
  {
    return this.menuId;
  }

  public void setMenuId(Long menuId)
  {
    this.menuId = menuId;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer order)
  {
    this.seq = order;
  }

  public String getAction()
  {
    return this.action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public List<String> getRequiredAnyFunctions()
  {
    return this.requiredAnyFunctions;
  }

  public void setRequiredAnyFunctions(List<String> requiredAnyFunctions)
  {
    this.requiredAnyFunctions = requiredAnyFunctions;
  }

  public void addRequiredAnyFunctions(String requiredFunction) {
    if (this.requiredAnyFunctions == null)
      this.requiredAnyFunctions = new ArrayList();

    this.requiredAnyFunctions.add(requiredFunction);
  }

  public String getProvidedPlugin()
  {
    return this.providedPlugin;
  }

  public void setProvidedPlugin(String provideredPlugin)
  {
    this.providedPlugin = provideredPlugin;
  }

  public Long getParentId()
  {
    return this.parentId;
  }

  public void setParentId(Long parentId)
  {
    this.parentId = parentId;
  }

  public Integer getLevel()
  {
    return this.level;
  }

  public void setLevel(Integer level)
  {
    this.level = level;
  }

  public Set<Menu> getSubMenu()
  {
    return this.subMenu;
  }

  public void addSubMenu(Menu menu)
  {
    if (!(this.subMenu.contains(menu)))
      this.subMenu.add(menu);
  }

  public int hashCode()
  {
    return this.menuId.hashCode();
  }

  public boolean equals(Object obj) {
    Menu menu = (Menu)obj;
    return this.menuId.equals(menu.getMenuId());
  }

  public Serializable getId() {
    return this.menuId;
  }

  public Menu getParentMenu()
  {
    return this.parentMenu;
  }

  public void setParentMenu(Menu parentMenu)
  {
    this.parentMenu = parentMenu;
  }

  public List<String> getRoleNameList()
  {
    return this.roleNameList;
  }

  public void addRoleNameList(String roleName)
  {
    if (this.roleNameList == null)
      this.roleNameList = new ArrayList();

    this.roleNameList.add(roleName);
  }
}