package com.employee.service.Impl;

import com.employee.dao.DepartmentDAO;
import com.employee.dao.impl.DepartmentDAOImpl;
import com.employee.po.Department;
import com.employee.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDao; //使用DAO而不是DAOImpl，上溯造型

    public DepartmentServiceImpl() {
        departmentDao = new DepartmentDAOImpl();
    }

    @Override
    public boolean insert(Department d) {
        List<Department> list = departmentDao.selectAll();
        int deptNo = 1;
        if (!list.isEmpty()) {
            deptNo = list.get(list.size() - 1).getDeptNo() + 1;
        }
        d.setDeptNo(deptNo);
        boolean result = departmentDao.insert(d);
        return result;
    }

    @Override
    public boolean update(Department d) {
        boolean result = departmentDao.update(d);
        return result;
    }

    @Override
    public void delete(int id) {
        departmentDao.delete(id);
    }

    @Override
    public List<Department> selectPageAll(int page, int count) {
        return departmentDao.selectPageAll(page, count);
    }

    @Override
    public List<Department> selectAll() {
        return departmentDao.selectAll();
    }

    @Override
    public int getCount() {
        return departmentDao.getCount();
    }
}
