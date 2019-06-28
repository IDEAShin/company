package com.employee.util;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBHelper {
	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql:///company?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	Connection conn = null;
	QueryRunner runner = null;

	static {
		DbUtils.loadDriver(DRIVERCLASS);
	}


	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConnection() {

		try {
			return conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("===============获取连接失败");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 * @param conn
	 */
	public void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有列表
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List execQuery(String sql, Class clazz, Object... params) {
		conn = this.getConnection();
		runner = new QueryRunner();
		List list = null;
		try {
			list = (List)runner.query(conn, sql, new BeanListHandler<>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConn(conn);
		}
		return list;
	}

	/**
	 * 查询单个数据
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object execQueryOne(String sql, Class clazz, Object... params) {
		conn = this.getConnection();
		runner = new QueryRunner();
		Object obj = null;
		try {
			obj = runner.query(conn, sql, new BeanHandler<>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConn(conn);
		}
		return obj;
	}

	/**
	 * 增删改数据
	 * @param sql
	 * @param params
	 */
	public boolean execUpdate(String sql, Object... params) {
		boolean result = true;
		conn = this.getConnection();
		runner = new QueryRunner();
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			this.closeConn(conn);
		}
		return result;
	}

	/**
	 * 增删改数据:事务
	 * @param sql
	 * @param params
	 */
	public void execUpdateRou(Connection conn, String sql, Object... params) {
		runner = new QueryRunner();
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询是否存在id
	 * @param sql
	 * @return
	 */
	public int getCount(String sql) {
		conn = this.getConnection();
		runner = new QueryRunner();
		int empCount = 0;
		try {
			empCount = runner.query(conn,sql, new ScalarHandler<Long>()).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empCount;
	}
}
