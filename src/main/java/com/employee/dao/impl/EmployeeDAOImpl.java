package com.employee.dao.impl;

import com.employee.dao.EmployeeDAO;
import com.employee.po.Employee;
import com.employee.po.History;
import com.employee.util.DBHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private DBHelper dbHelper;
    public EmployeeDAOImpl(){
        dbHelper = new DBHelper();
    }

    @Override
    public boolean insert(Employee e, History h) {
        Connection conn = dbHelper.getConnection();
        boolean result = true;
        try {
            conn.setAutoCommit(false);
            String sql1 = "insert into Employee(EMPNO, DEPTNO, EMPNAME, EMPSEX, ENTRYDATE, EMPPHONE, EMPADDR, SALARY) values(?, ?, ?, ?, ?, ?, ?, ?)";
            dbHelper.execUpdateRou(conn, sql1,
                    e.getEmpNo(),
                    e.getDeptNo(),
                    e.getEmpName(),
                    e.getEmpSex(),
                    new Date(e.getEntryDate().getTime()),
                    e.getEmpPhone(),
                    e.getEmpAddr(),
                    e.getSalary()
            );
            String sql2 = "insert into history(EMPNO, DEPTNO, SALARY, CHANGEDATE, CHANGEREASON) values(?, ?, ?, ?, ?)";
            dbHelper.execUpdateRou(conn, sql2,
                    h.getEmpNo(),
                    h.getDeptNo(),
                    h.getSalary(),
                    new Date(h.getChangeDate().getTime()),
                    h.getChangeReason()
            );
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
                result = false;
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
        return result;
    }

        @Override
        public boolean update(Employee e, History h) {
            Connection conn = dbHelper.getConnection();
            boolean result = true;
            try {
                conn.setAutoCommit(false);
                String sql1 = "update Employee set DEPTNO = ?, EMPNAME = ?, EMPSEX = ?, ENTRYDATE = ?, EMPPHONE = ?, EMPADDR = ?, SALARY = ? where EMPNO = ?";
                dbHelper.execUpdateRou(conn, sql1,
                        e.getDeptNo(),
                        e.getEmpName(),
                        e.getEmpSex(),
                        new Date(e.getEntryDate().getTime()),
                        e.getEmpPhone(),
                        e.getEmpAddr(),
                        e.getSalary(),
                        e.getEmpNo()
                );
                String sql2 = "insert into history(EMPNO, DEPTNO, SALARY, CHANGEDATE, CHANGEREASON) values(?, ?, ?, ?, ?)";
                dbHelper.execUpdateRou(conn, sql2,
                        h.getEmpNo(),
                        h.getDeptNo(),
                        h.getSalary(),
                        new Date(h.getChangeDate().getTime()),
                        h.getChangeReason()
                );
                conn.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    conn.rollback();
                    result = false;
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            return result;
        }

    @Override
    public boolean leaveEmp(Employee e, History h) {
        Connection conn = dbHelper.getConnection();
        boolean result = true;
        try {
            conn.setAutoCommit(false);
            String sql1 = "update Employee set STATE = ?, LEAVEDATE = ? where EMPNO = ?";
            dbHelper.execUpdateRou(conn, sql1,
                    e.getState(),
                    new Date(e.getLeaveDate().getTime()),
                    e.getEmpNo()
            );
            String sql2 = "insert into history(EMPNO, DEPTNO, SALARY, DIMISSIONDATE, DIMISSIONREASON) values(?, ?, ?, ?, ?)";
            dbHelper.execUpdateRou(conn, sql2,
                    h.getEmpNo(),
                    h.getDeptNo(),
                    h.getSalary(),
                    new Date(h.getDimissionDate().getTime()),
                    h.getDimissionReason()
            );
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
                result = false;
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<Employee> selectAll() {
        String sql = "select *," +
                " (select DEPTNAME FROM dept where dept.DEPTNO=employee.DEPTNO) AS DEPTNAME " +
                " from Employee order by EMPNO";
        List<Employee> employees = (List<Employee>)dbHelper.execQuery(sql, Employee.class);
        return employees;
    }

    @Override
    public List<Employee> selectPageAll(int page, int count) {
        String sql = "select *," +
                " (select DEPTNAME FROM dept where dept.DEPTNO=employee.DEPTNO) AS DEPTNAME " +
                " from employee where state != 3 limit ?, ?";
        List<Employee> employees = (List<Employee>)dbHelper.execQuery(sql, Employee.class, count*(page-1), count);
        return employees;
    }

    @Override
    public Employee selectOne(String empNo) {
        String sql = "select * from Employee where EMPNO = ?";
        Employee employee = (Employee) dbHelper.execQueryOne(sql, Employee.class,empNo);
        return employee;
    }

    @Override
    public int getCount() {
        String sql = "select count(*) from Employee";
        int empCount = dbHelper.getCount(sql);
        return empCount;
    }

}
