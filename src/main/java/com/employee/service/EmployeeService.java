package com.employee.service;

import com.employee.po.Employee;
import com.employee.po.History;

import java.util.List;

public interface EmployeeService {
    boolean insert(Employee e, History h);

    void updateEmp(Employee e, History h);

    void leaveEmp(Employee e, History h);

    void deleteEmp(Employee e, History h);

    List<Employee> selectAll();

    List<Employee> selectPageAll(int page, int count);

    Employee selectOne(String empNo);

    int getCount();
}
