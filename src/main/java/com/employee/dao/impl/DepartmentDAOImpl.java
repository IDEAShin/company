package com.employee.dao.impl;

import com.employee.dao.DepartmentDAO;
import com.employee.po.Department;
import com.employee.util.DBHelper;

import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    private DBHelper dbHelper;
    public DepartmentDAOImpl(){
        dbHelper = new DBHelper();
    }

    @Override
    public boolean insert(Department d) {

        String sql = "insert into Dept(DEPTNO, DEPTNAME) values(?, ?)";
        boolean result = dbHelper.execUpdate(sql, d.getDeptNo(), d.getDeptName());
        return result;
    }

    @Override
    public boolean update(Department d) {
        String sql = "update Dept set DEPTNAME = ? where DEPTNO = ?";
        boolean result = dbHelper.execUpdate(sql, d.getDeptName(), d.getDeptNo());
        return result;
    }

    @Override
    public void delete(int deptNo) {
        String sql = "delete from Dept where DEPTNO=?";
        dbHelper.execUpdate(sql, deptNo);
    }

    @Override
    public List<Department> selectPageAll(int page, int count){
        String sql = "select * from Dept order by DEPTNO limit ?, ?";
        List<Department> departments = (List<Department>)dbHelper.execQuery(sql, Department.class, count*(page-1), count);
        return departments;
    }


    @Override
    public List<Department> selectAll(){
        String sql = "select * from Dept order by DEPTNO";
        List<Department> departments = (List<Department>)dbHelper.execQuery(sql, Department.class);
        return departments;
    }


    @Override
    public int getCount() {
        String sql = "select count(*) from Dept";
        int empCount = dbHelper.getCount(sql);
        return empCount;
    }

}
