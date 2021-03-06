package com.employee.dao;

import com.employee.po.Department;

import java.util.List;

public interface DepartmentDAO {
    boolean insert(Department d);

    boolean update(Department d);

    void delete(int id);

    List<Department> selectPageAll(int page, int count);

    List<Department> selectAll();

    int getCount();
}
