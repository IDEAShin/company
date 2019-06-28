package com.employee.servlet;

import com.employee.po.Department;
import com.employee.po.Employee;
import com.employee.po.History;
import com.employee.service.DepartmentService;
import com.employee.service.EmployeeService;
import com.employee.service.Impl.DepartmentServiceImpl;
import com.employee.service.Impl.EmployeeServiceImpl;
import com.employee.util.CheckUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private CheckUtil checkUtil;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取列表
        if ("/employeeList.do".equals(req.getServletPath())) {
            String pageStr = req.getParameter("page");//当前页码
            int page = 1;//页码默认为1
            if(pageStr != null && (!"".equals(pageStr))) {
                page = Integer.valueOf(pageStr);
            }

            List<Employee> ELIST = employeeService.selectPageAll(page, 5);//分页查询
            int count =	employeeService.getCount();//雇员总数
            int last = count % 5 == 0 ? (count / 5) : ((count / 5) +1);//总页数
            req.setAttribute("last", last);
            req.setAttribute("page", page);
            req.setAttribute("ELIST", ELIST);
            req.getRequestDispatcher("/employeeList.jsp").forward(req, resp);
        }

        //访问插入
        if ("/employeeToAdd.do".equals(req.getServletPath())) {
            List<Department> DLIST = departmentService.selectAll();
            req.setAttribute("DLIST", DLIST);
            req.getRequestDispatcher("/employeeAdd.jsp").forward(req, resp);
        }

        //插入
        if ("/employeeAdd.do".equals(req.getServletPath())) {
            Integer deptNo = Integer.valueOf(req.getParameter("deptNo"));
            String empName = req.getParameter("empName");
            String empSex = req.getParameter("empSex");
            Date entryDate = null;
            try {
                entryDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("entryDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String empPhone = req.getParameter("empPhone");
            String empAddr = req.getParameter("empAddr");
            String sal = req.getParameter("salary");

            if (!checkUtil.isPoNumber(empPhone) || !checkUtil.isPoNumber(sal)) {
                req.setAttribute("deptNo", deptNo);
                req.setAttribute("empName", empName);
                req.setAttribute("empSex", empSex);
                req.setAttribute("entryDate", entryDate);
                req.setAttribute("empPhone", empPhone);
                req.setAttribute("empAddr", empAddr);
                req.setAttribute("mes", "3");
                List<Department> DLIST = departmentService.selectAll();
                req.setAttribute("DLIST", DLIST);
                req.getRequestDispatcher("/employeeAdd.jsp").forward(req, resp);
            } else {
                Integer salary = Integer.valueOf(sal);
                String changeReason = "入职";
                Date changeDate = new Date();

                Employee employee = new Employee();
                employee.setDeptNo(deptNo);
                employee.setEmpName(empName);
                employee.setEmpSex(empSex);
                employee.setEntryDate(entryDate);
                employee.setEmpPhone(empPhone);
                employee.setEmpAddr(empAddr);
                employee.setSalary(salary);

                History history = new History();

                history.setDeptNo(deptNo);
                history.setSalary(salary);
                history.setChangeDate(changeDate);
                history.setChangeReason(changeReason);

                boolean result = employeeService.insert(employee, history);
                if (result == true) {
                    req.setAttribute("msg", "插入成功");
                } else if (result == false) {
                    req.setAttribute("msg", "插入失败");
                }
                req.getRequestDispatcher("/employeeList.do").forward(req, resp);
            }
        }

        //访问更新
        if ("/employeeToUpdate.do".equals(req.getServletPath())) {
            String mes = req.getParameter("mes");
            String empNo = req.getParameter("empNo");
            Employee EMP = employeeService.selectOne(empNo);
            List<Department> DLIST = departmentService.selectAll();
            req.setAttribute("EMP", EMP);
            req.setAttribute("mes", mes);
            req.setAttribute("DLIST",DLIST);
            req.getRequestDispatcher("/employeeUpdate.jsp").forward(req, resp);
                      }
        //更新
        if ("/employeeUpdate.do".equals(req.getServletPath())) {
            if (!checkUtil.isPoNumber(req.getParameter("empPhone")) || !checkUtil.isPoNumber(req.getParameter("salary"))) {
                String empNo = req.getParameter("empNo");

                resp.sendRedirect("/company/employeeToUpdate.do?empNo=" + empNo + "&mes=3");
            } else {
                String empNo = req.getParameter("empNo");
                Integer deptNo = Integer.valueOf(req.getParameter("deptNo"));
                String empName = req.getParameter("empName");
                String empSex = req.getParameter("empSex");
                Date entryDate = null;
                try {
                    entryDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("entryDate"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String empPhone = req.getParameter("empPhone");
                String empAddr = req.getParameter("empAddr");
                Integer salary = Integer.valueOf(req.getParameter("salary"));
                String changeReason = req.getParameter("changeReason");
                Date changeDate = new Date();

                Employee employee = new Employee();
                employee.setEmpNo(empNo);
                employee.setDeptNo(deptNo);
                employee.setEmpName(empName);
                employee.setEmpSex(empSex);
                employee.setEntryDate(entryDate);
                employee.setEmpPhone(empPhone);
                employee.setEmpAddr(empAddr);
                employee.setSalary(salary);

                History history = new History();
                history.setEmpNo(empNo);
                history.setDeptNo(deptNo);
                history.setSalary(salary);
                history.setChangeDate(changeDate);
                history.setChangeReason(changeReason);

                employeeService.updateEmp(employee, history);
                resp.sendRedirect("/company/employeeList.do");
            }
        }

        //访问离职
        if ("/employeeToLeave.do".equals(req.getServletPath())) {
            String empNo = req.getParameter("empNo");
            List<Department> DLIST = null;
            Employee EMP = null;
            EMP = employeeService.selectOne(empNo);
            req.setAttribute("EMP", EMP);
            req.getRequestDispatcher("/employeeLeave.jsp").forward(req, resp);
        }
        //离职
        if ("/employeeLeave.do".equals(req.getServletPath())) {
            String empNo =req.getParameter("empNo");
            Integer deptNo = Integer.valueOf(req.getParameter("deptNo"));
            Integer salary = Integer.valueOf(req.getParameter("salary"));
            String dimissionReason = req.getParameter("dimissionReason");
            Date dimissionDate = new Date();
            Date leaveDate = dimissionDate;

            Employee employee = new Employee();
            employee.setEmpNo(empNo);
            employee.setLeaveDate(leaveDate);

            History history = new History();
            history.setEmpNo(empNo);
            history.setDeptNo(deptNo);
            history.setSalary(salary);
            history.setDimissionDate(dimissionDate);
            history.setDimissionReason(dimissionReason);

            employeeService.leaveEmp(employee, history);
            resp.sendRedirect("/company/employeeList.do");
        }

        //减员
        if ("/employeeDelete.do".equals(req.getServletPath())) {
            String empNo =req.getParameter("empNo");
            Integer deptNo = Integer.valueOf(req.getParameter("deptNo"));
            Integer salary = Integer.valueOf(req.getParameter("salary"));
            String dimissionReason = "减员";
            Date dimissionDate = new Date();
            Date leaveDate = dimissionDate;

            Employee employee = new Employee();
            employee.setEmpNo(empNo);
            employee.setLeaveDate(leaveDate);

            History history = new History();
            history.setEmpNo(empNo);
            history.setDeptNo(deptNo);
            history.setSalary(salary);
            history.setDimissionDate(dimissionDate);
            history.setDimissionReason(dimissionReason);
            employeeService.deleteEmp(employee, history);
            resp.sendRedirect("/company/employeeList.do");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        employeeService = null;
        departmentService = null;
        checkUtil = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = new EmployeeServiceImpl();
        departmentService = new DepartmentServiceImpl();
        checkUtil = new CheckUtil();
    }

}
