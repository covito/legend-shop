package com.legendshop.core.security;

import com.legendshop.core.security.model.UserDetail;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.UserEntity;
import com.legendshop.util.AppUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthServiceImpl
  implements AuthService
{
  Logger _$2 = LoggerFactory.getLogger(AuthServiceImpl.class);
  private org.springframework.jdbc.core.JdbcTemplate _$1;

  public UserDetails loadUserByUsername(String paramString)
    throws UsernameNotFoundException, DataAccessException
  {
    UserEntity localUserEntity = null;
    try
    {
      localUserEntity = _$4(paramString);
    }
    catch (Exception localException)
    {
      this._$2.error("can not load user by user name {}, exception message {}", paramString, localException.getMessage());
    }
    if (this._$2.isDebugEnabled())
      this._$2.debug("getUserByName calling with name {}, result {}", paramString, localUserEntity);
    if (AppUtils.isBlank(localUserEntity))
      return null;
    List localList1 = _$3(localUserEntity.getId());
    if (AppUtils.isBlank(localList1))
      throw new UsernameNotFoundException("User has no GrantedAuthority");
    List localList2 = _$5(localUserEntity.getId());
    UserDetail localUserDetail = new UserDetail(paramString, localUserEntity.getPassword(), _$6(localUserEntity.getEnabled()), true, true, true, localList1, localList2, localUserEntity.getId());
    return localUserDetail;
  }

  @Cacheable({"GrantedFunction"})
  public Collection<GrantedFunction> getFunctionsByRoles(Collection<? extends GrantedAuthority> paramCollection)
  {
    this._$2.debug("getFunctionsByRoles calling {}", paramCollection);
    if (null == paramCollection)
      throw new IllegalArgumentException("Granted Roles cannot be null");
    HashSet localHashSet = new HashSet();
    Iterator localIterator1 = paramCollection.iterator();
    while (localIterator1.hasNext())
    {
      GrantedAuthority localGrantedAuthority = (GrantedAuthority)localIterator1.next();
      Role localRole = _$2(localGrantedAuthority.getAuthority());
      if (localRole != null)
      {
        List localList = localRole.getFunctions();
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          Function localFunction = (Function)localIterator2.next();
          localHashSet.add(new GrantedFunctionImpl(localFunction.getName()));
        }
      }
    }
    return localHashSet;
  }

  private boolean _$6(String paramString)
  {
    return ("1".endsWith(paramString));
  }

  private List<GrantedFunction> _$5(String paramString)
  {
    String str = "select DISTINCT f.name from ls_usr_role ur ,ls_role r,ls_perm p, ls_func f where r.enabled = '1' and ur.user_id= ? and ur.role_id=r.id and r.id=p.role_id and p.function_id=f.id";
    this._$2.debug("findFunctionsByUser,run sql {}, userId {}", str, paramString);
    return this._$1.query(str, new Object[] { paramString }, new IIIlIlIllllIIIIl());
  }

  private UserEntity _$4(String paramString)
  {
    String str = "select * from ls_user where enabled = '1' and name = ?";
    this._$2.debug("findUserByName, run sql {}, name {}", str, paramString);
    return ((UserEntity)this._$1.queryForObject(str, new Object[] { paramString }, new llIlIlIllllIIIIl()));
  }

  private List<GrantedAuthority> _$3(String paramString)
  {
    String str = "select distinct r.name from ls_usr_role ur ,ls_role r where r.enabled ='1' and ur.user_id= ? and ur.role_id=r.id";
    this._$2.debug("findRolesByUser,run sql {}, userId {}", str, paramString);
    return this._$1.query(str, new Object[] { paramString }, new IlIlIlIllllIIIIl());
  }

  private Role _$2(String paramString)
  {
    this._$2.debug("getgrantedAuthority calling {}", paramString);
    List localList = _$1(paramString);
    if (AppUtils.isBlank(localList))
    {
      this._$2.warn("authority {} can not get Role", paramString);
      return null;
    }
    Role localRole = (Role)localList.iterator().next();
    if (localRole != null)
    {
      localRole.setFunctions(_$1(localRole));
      return localRole;
    }
    return null;
  }

  private List<Role> _$1(String paramString)
  {
    String str = "select * from ls_role where enabled = '1' and name = ?";
    this._$2.debug("findRoleByName run sql {}, authority {}", paramString);
    return this._$1.query(str, new Object[] { paramString }, new lIlIlIlIlllIIIIl());
  }

  private List<Function> _$1(Role paramRole)
  {
    String str = "select f.* from ls_perm p ,ls_func f where p.role_id= ? and p.function_id=f.id";
    this._$2.debug("findFunctionsByRole,run sql {}, role {}", str, paramRole.getName());
    return this._$1.query(str, new Object[] { paramRole.getId() }, new IIlIlIlIlllIIIIl());
  }

  public void setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate paramJdbcTemplate)
  {
    this._$1 = paramJdbcTemplate;
  }
}