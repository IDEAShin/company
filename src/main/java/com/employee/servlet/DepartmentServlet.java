package com.employee.servlet;

import com.employee.po.Department;
import com.employee.service.DepartmentService;
import com.employee.service.Impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DepartmentServlet extends HttpServlet {
    private DepartmentService departmentService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取列表
        if ("/departmentList.do".equals(req.getServletPath())) {
            String pageStr = req.getParameter("page");//当前页码
            int page = 1;//页码默认为1
            if(pageStr != null && (!"".equals(pageStr))) {
                page = Integer.valueOf(pageStr);
            }

            List<Department> DLIST = departmentService.selectPageAll(page, 5);//分页查询
            int count =	departmentService.getCount();//雇员总数
            int last = count % 5 == 0 ? (count / 5) : ((count / 5) +1);//总页数

            req.setAttribute("last", last);
            req.setAttribute("page", page);
            req.setAttribute("DLIST", DLIST);
            req.getRequestDispatcher("/departmentList.jsp").forward(req, resp);
        }

        //插入
        if ("/departmentAdd.do".equals(req.getServletPath())) {
            String deptName = req.getParameter("deptName");
            Department department = new Department();
            department.setDeptName(deptName);
            boolean result = departmentService.insert(department);
            if (result == true) {
                req.setAttribute("msg", "插入成功");
            } else if (result == false) {
                req.setAttribute("msg", "插入失败");
            }
            req.getRequestDispatcher("/departmentList.do").forward(req, resp);
        }

        //更新
        if ("/departmentUpdate.do".equals(req.getServletPath())) {
            Integer deptNo = Integer.valueOf(req.getParameter("deptNo"));
            String deptName = req.getParameter("deptName");
            Department department = new Department();
            department.setDeptNo(deptNo);
            department.setDeptName(deptName);
            departmentService.update(department);
            resp.sendRedirect("/company/departmentList.do");
        }

        //删除
        if ("/departmentDelete.do".equals(req.getServletPath())) {
            Integer deptNo = Integer.valueOf(req.getParameter("deptNo"));
            departmentService.delete(deptNo);
            resp.sendRedirect("/company/departmentList.do");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        departmentService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        departmentService = new DepartmentServiceImpl();
    }
}
