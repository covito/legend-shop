package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ConstTableDao;
import com.legendshop.core.tag.TableCache;
import com.legendshop.model.entity.ConstTable;
import com.legendshop.model.entity.ConstTableId;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapCodeTablesCache
  implements TableCache
{
  private static Logger log = LoggerFactory.getLogger(MapCodeTablesCache.class);
  private Map<String, Map<String, String>> codeTables = new HashMap();
  private ConstTableDao constTableDao;

  public Map<String, Map<String, String>> getCodeTables()
  {
    return this.codeTables;
  }

  public void setCodeTables(Map<String, Map<String, String>> codeTables)
  {
    this.codeTables = codeTables;
  }

  public Map<String, String> getCodeTable(String beanName)
  {
    if ((beanName == null) || (beanName.trim().length() == 0))
      return null;

    Map table = (Map)this.codeTables.get(beanName);
    return table;
  }

  public void initCodeTablesCache()
  {
    List list = this.constTableDao.loadAllConstTable();
    for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { ConstTable constTable = (ConstTable)localIterator.next();
      String type = constTable.getId().getType();
      Map items = (Map)this.codeTables.get(type);
      if (items == null)
        items = new LinkedHashMap();

      items.put(constTable.getId().getKey(), constTable.getValue());
      this.codeTables.put(type, items);
    }

    log.info("codeTables size = {}", Integer.valueOf(this.codeTables.size()));
  }

  public void setConstTableDao(ConstTableDao constTableDao) {
    this.constTableDao = constTableDao;
  }
}