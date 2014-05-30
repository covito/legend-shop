package com.legendshop.test;

import java.io.PrintStream;
import java.text.NumberFormat;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test/applicationContextHibernate.xml"})
public class HibernateTest
{

  @Resource
  private HibernateDaoImpl hibernateDao;

  @Test
  public void saveUser()
  {
    int total = 1000;
    long t1 = System.currentTimeMillis();
    System.out.println("开始插入数据库 total = " + total);
    for (int i = 0; i < total; ++i) {
      User user = new User();
      user.setUsername("username" + i);
      user.setRealname("realname" + i);
      user.setPassword("password" + i);
      user.setMemo("memo" + i);
      this.hibernateDao.save(user);
    }
    long t2 = System.currentTimeMillis();
    System.out.println(total + "个数据插入数据库，一共用时 t2 - t1 = " + (t2 - t1));
    System.out.println("平均用时 (t2 - t1)/" + total + ",  " + ((t2 - t1) / total));
  }

  @Test
  public void queryUser() {
    long count = this.hibernateDao.stat("select count(*) from user", new Object[0]);
    System.out.println("Hibernate: 数据库中有 " + count + " 个用户数据");
    System.out.println("1. 内存为 = " + getMemery());
    User user = null;
    long total = count;
    long t1 = System.currentTimeMillis();
    for (long i = -4648542412694618111L; i <= total; i += -4648542447054356479L)
    {
      user = this.hibernateDao.findUser(Long.valueOf(i));

      if (user == null)
        throw new RuntimeException("user is null");
    }

    long t2 = System.currentTimeMillis();
    System.out.println("2. 内存为 = " + getMemery());
    System.out.println("查找" + total + "个数据，一共用时 t2 - t1 = " + (t2 - t1));
    System.out.println("平均用时 (t2 - t1)/" + total + ",  " + ((float)(t2 - t1) / (float)total));
  }

  public String getMemery() {
    Runtime runtime = Runtime.getRuntime();

    NumberFormat format = NumberFormat.getInstance();

    StringBuilder sb = new StringBuilder();
    long maxMemory = runtime.maxMemory();
    long allocatedMemory = runtime.totalMemory();
    long freeMemory = runtime.freeMemory();

    sb.append("free memory: " + format.format(freeMemory / 1024L / 1024L) + "<br/>");
    sb.append("allocated memory: " + format.format(allocatedMemory / 1024L / 1024L) + "<br/>");
    sb.append("max memory: " + format.format(maxMemory / 1024L / 1024L) + "<br/>");
    sb.append("total free memory: " + format.format((freeMemory + maxMemory - allocatedMemory) / 1024L / 1024L));
    return sb.toString();
  }
}