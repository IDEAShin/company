package com.employee.dao.impl;

import com.employee.dao.HistoryDAO;
import com.employee.po.History;
import com.employee.util.DBHelper;

import java.util.List;

public class HistoryDAOImpl implements HistoryDAO {
    private DBHelper dbHelper;
    public HistoryDAOImpl(){
        dbHelper = new DBHelper();
    }

    @Override
    public List<History> selectAll() {
        String sql = "select * from history";
        List<History> histories = (List<History>)dbHelper.execQuery(sql, History.class);
        return histories;
    }


    @Override
    public List<History> selectPageAll(int page, int count) {
        String sql = "select *,(select EMPNAME FROM employee where employee.EMPNO=history.EMPNO) AS EMPNAME, " +
                 "(select DEPTNAME FROM dept where dept.DEPTNO=history.DEPTNO) AS DEPTNAME " +
                "from history limit ?, ?";
        List<History> histories = (List<History>)dbHelper.execQuery(sql, History.class, count*(page-1), count);
        return histories;
    }

    @Override
    public List<History> search(String search) {
        String sql = "select *,(select EMPNAME FROM employee where employee.EMPNO=history.EMPNO) AS EMPNAME, " +
                "(select DEPTNAME FROM dept where dept.DEPTNO=history.DEPTNO) AS DEPTNAME " +
                "from history where EMPNO in (select EMPNO from employee where EMPNAME like ?) or DEPTNO in (select DEPTNO from Dept where DEPTNAME like ?)";
        List<History> hlist = (List<History>) dbHelper.execQuery(sql, History.class, search, search);
        return hlist;
    }

    @Override
    public int getCount() {
        String sql = "select count(*) from history";
        int empCount = dbHelper.getCount(sql);
        return empCount;
    }

}
