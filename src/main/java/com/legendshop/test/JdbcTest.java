package com.legendshop.test;

import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test/applicationContext.xml" })
public class JdbcTest {

	@Resource
	private BaseJdbcDao baseJdbcDao;

	@Test
	public void update() {
		int result = this.baseJdbcDao.update("delete from user where id = ?",
				new Object[] { Integer.valueOf(2) });
		System.out.println("result = " + result);
	}

	@Test
	public void saveUser() {
		int total = 3000;
		System.out.println("JDBC开始插入数据库 total = " + total);
		long t1 = System.currentTimeMillis();

		for (int i = 0; i < total; ++i) {
			User user = new User();
			user.setUsername("username" + i);
			user.setRealname("realname" + i);
			user.setPassword("password" + i);
			user.setMemo("memo" + i);

			this.baseJdbcDao
					.updateNamed(
							"insert into user(username, realname, password, memo) values(:username, :realname, :password, :memo)",
							user);
		}
		long t2 = System.currentTimeMillis();
		System.out.println(total + "个数据插入数据库，一共用时 t2 - t1 = " + (t2 - t1));
		System.out.println("平均用时 (t2 - t1)/" + total + ",  "
				+ ((t2 - t1) / total));
	}

	@Test
	public void queryUser() {
		long count = this.baseJdbcDao.stat("select count(*) from user",
				new Object[0]);
		System.out.println("JDBC数据库中有 " + count + " 个用户数据");
		System.out.println("1. 内存为 = " + getMemery());
		User user = null;
		long total = count;
		long t1 = System.currentTimeMillis();
		for (int i = 1; i <= total; ++i) {
			user = (User) this.baseJdbcDao.get(
					"select * from user where id = ?", User.class,
					new Object[] { Integer.valueOf(i) });

			if (user == null)
				throw new RuntimeException("user is null");
		}

		long t2 = System.currentTimeMillis();
		System.out.println("2. 内存为 = " + getMemery());
		System.out.println("查找" + total + "个数据，一共用时 t2 - t1 = " + (t2 - t1));
		System.out.println("平均用时 (t2 - t1)/" + total + ",  "
				+ ((float) (t2 - t1) / (float) total));
	}

	public String getMemery() {
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		sb.append("free memory: " + format.format(freeMemory / 1024L / 1024L)
				+ "<br/>");
		sb.append("allocated memory: "
				+ format.format(allocatedMemory / 1024L / 1024L) + "<br/>");
		sb.append("max memory: " + format.format(maxMemory / 1024L / 1024L)
				+ "<br/>");
		sb.append("total free memory: "
				+ format.format((freeMemory + maxMemory - allocatedMemory) / 1024L / 1024L));
		return sb.toString();
	}

	@Test
	public void queryUserByJDBC() {
		long count = this.baseJdbcDao.stat("select count(*) from user",
				new Object[0]);
		System.out.println("JDBC数据库中有 " + count + " 个用户数据");
		System.out.println("1. 内存为 = " + getMemery());
		User user = null;
		long total = count;
		long t1 = System.currentTimeMillis();
		for (int i = 1; i <= total; ++i) {
			user = (User) this.baseJdbcDao.getJdbcTemplate().query(
					"select * from user where id = ?",
					new Object[] { Integer.valueOf(i) },
					new ResultSetExtractorImpl());

			if (user == null)
				throw new RuntimeException("user is null");
		}

		long t2 = System.currentTimeMillis();
		System.out.println("2. 内存为 = " + getMemery());
		System.out.println("查找" + total + "个数据，一共用时 t2 - t1 = " + (t2 - t1));
		System.out.println("平均用时 (t2 - t1)/" + total + ",  "
				+ ((float) (t2 - t1) / (float) total));
	}

	@Test
	public void queryUserByJDBC1() {
		long count = this.baseJdbcDao.stat("select count(*) from user",
				new Object[0]);
		System.out.println("JDBC数据库中有 " + count + " 个用户数据");
		System.out.println("1. 内存为 = " + getMemery());
		User user = null;
		long total = count;
		long t1 = System.currentTimeMillis();
		for (int i = 1; i <= total; ++i) {
			List userList = this.baseJdbcDao.getJdbcTemplate().query(
					"select * from user where id = ?",
					new Object[] { Integer.valueOf(i) }, new RowMapperUser());

			if (userList == null)
				throw new RuntimeException("user is null");
		}

		long t2 = System.currentTimeMillis();
		System.out.println("2. 内存为 = " + getMemery());
		System.out.println("查找" + total + "个数据，一共用时 t2 - t1 = " + (t2 - t1));
		System.out.println("平均用时 (t2 - t1)/" + total + ",  "
				+ ((float) (t2 - t1) / (float) total));
	}

	@Test
	public void batchUpdate() {
		List list = new ArrayList() {
		};
		this.baseJdbcDao.updateNamed(
				"update user set password = :password where id = :id", list);
	}

	@Test
	public void updateMap() {
		Map map = new HashMap() {
		};
		this.baseJdbcDao.updateNamedMap(
				"update user set password = :password where id = :id", map);
	}

	@Test
	public void batchUpdateMap() {
		List list = new ArrayList();

		list.add(new HashMap() {
		});
		list.add(new HashMap() {
		});
		list.add(new HashMap() {
		});
		this.baseJdbcDao.updateNamedMap(
				"update user set password = :password where id = :id", list);
	}

	@Test
	public void queryPage() {
		User user = (User) this.baseJdbcDao.get(
				"select * from user where id = ?", User.class,
				new Object[] { Integer.valueOf(110) });
		System.out.println(user);

		System.out.println("------------");

		String queryString = "select * from user where id > ?";
		String allCountString = "select count(*) from user where id > ?";
		SimpleSqlQuery query = new SimpleSqlQuery(User.class, queryString,
				allCountString, new Object[] { Integer.valueOf(100) });
		query.setPageSize(15);
		query.setCurPage("3");
		PageSupport ps = this.baseJdbcDao.find(query);
		List list2 = ps.getResultList();
		for (int i = 0; i < list2.size(); ++i) {
			User u = (User) list2.get(i);
			System.out.println(u);
		}
	}

	class ResultSetExtractorImpl implements ResultSetExtractor<User> {
		public User extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				user.setPassword(rs.getString("password"));
				user.setMemo(rs.getString("memo"));
			}

			return user;
		}
	}

	class RowMapperUser implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setRealname(rs.getString("realname"));
			user.setPassword(rs.getString("password"));
			user.setMemo(rs.getString("memo"));
			return user;
		}
	}
}