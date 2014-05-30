package com.legendshop.core.security.dao;

import com.legendshop.core.security.model.Resource;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

public class ResourcesDao
{
  private JdbcTemplate _$2;
  private final Logger _$1 = LoggerFactory.getLogger(ResourcesDao.class);

  public ResourcesDao()
  {
    System.out.println("加载ResourcesDao..." + this._$2);
  }

  public List<Resource> findAll()
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      List localList = this._$2.queryForList("select * from ls_resource");
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Map localMap = (Map)localIterator.next();
        Resource localResource = new Resource();
        localResource.setId(Integer.valueOf(localMap.get("id").toString()));
        localResource.setName(localMap.get("name").toString());
        localResource.setResType(localMap.get("res_type").toString());
        localResource.setResString(localMap.get("res_string").toString());
        localResource.setDescn(localMap.get("descn").toString());
        localArrayList.add(localResource);
      }
      return localArrayList;
    }
    catch (RuntimeException localRuntimeException)
    {
      this._$1.error("find all resource failed " + localRuntimeException);
      throw localRuntimeException;
    }
  }

  public Collection<ConfigAttribute> loadRoleByResource(String paramString)
  {
    String str;
    try
    {
      str = "select ro.name as role,re.res_string as url from ls_role ro join ls_resc_role rr on ro.id=rr.role_id join ls_resource re on re.id=rr.resc_id where re.res_string='" + paramString + "'";
      List localList = this._$2.queryForList(str);
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Map localMap = (Map)localIterator.next();
        SecurityConfig localSecurityConfig = new SecurityConfig(localMap.get("role").toString());
        localArrayList.add(localSecurityConfig);
      }
      return localArrayList;
    }
    catch (RuntimeException localRuntimeException)
    {
      this._$1.error("find roles by url failed " + localRuntimeException);
      throw localRuntimeException;
    }
  }
}