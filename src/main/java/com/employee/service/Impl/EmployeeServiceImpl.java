package com.employee.service.Impl;

import com.employee.dao.EmployeeDAO;
import com.employee.dao.HistoryDAO;
import com.employee.dao.impl.EmployeeDAOImpl;
import com.employee.dao.impl.HistoryDAOImpl;
import com.employee.po.Employee;
import com.employee.po.History;
import com.employee.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDao;
    private HistoryDAO historyDao;

    public EmployeeServiceImpl() {
        employeeDao = new EmployeeDAOImpl();
        historyDao = new HistoryDAOImpl();
    }

    @Override
    public boolean insert(Employee e, History h) {
        String empNo = "000001";
        List<Employee> list = employeeDao.selectAll();
        if (!list.isEmpty()) {
            int empCount = Integer.valueOf(list.get(list.size() - 1).getEmpNo()) + 1;
            if (empCount < 10) {
                empNo = "00000" + empCount;
            } else if (empCount > 9 && empCount <100) {
                empNo = "0000" + empCount;
            } else if (empCount > 99 && empCount <1000) {
                empNo = "000" + empCount;
            } else if (empCount > 999 && empCount <10000) {
                empNo = "00" + empCount;
            }else{
                empNo = "0" + empCount;
            }
        }
        e.setEmpNo(empNo);
        h.setEmpNo(empNo);
        boolean result = employeeDao.insert(e, h);
        return result;
    }

    /**
     * 变更
     * @param e
     * @param h
     */
    @Override
    public void updateEmp(Employee e, History h) {
        e.setState(1);
        employeeDao.update(e, h);
    }

    /**
     * 离职
     * @param h
     */
    @Override
    public void leaveEmp(Employee e, History h) {
        e.setState(2);
        employeeDao.leaveEmp(e, h);
    }

    /**
     * 减员
     * @param e
     */
    @Override
    public void deleteEmp(Employee e, History h) {
        e.setState(3);
        employeeDao.leaveEmp(e, h);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public List<Employee> selectPageAll(int page, int count) {
        return employeeDao.selectPageAll(page, count);
    }

    @Override
    public Employee selectOne(String empNo) {
        return employeeDao.selectOne(empNo);
    }

    @Override
    public int getCount() {
        return employeeDao.getCount();
    }
}
