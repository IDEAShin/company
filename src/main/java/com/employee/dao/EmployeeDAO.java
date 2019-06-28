package com.employee.dao;

import com.employee.po.Employee;
import com.employee.po.History;

import java.util.List;

public interface EmployeeDAO {
    boolean insert(Employee e, History h);

    boolean update(Employee e, History h);

    boolean leaveEmp(Employee e, History h);

    List<Employee> selectAll();

    List<Employee> selectPageAll(int page, int count);

    Employee selectOne(String empNo);

    int getCount();
}
