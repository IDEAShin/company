package com.employee.servlet;

import com.employee.po.Department;
import com.employee.po.Employee;
import com.employee.po.History;
import com.employee.service.Impl.DepartmentServiceImpl;
import com.employee.service.Impl.EmployeeServiceImpl;
import com.employee.service.Impl.HistoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HistoryServlet extends HttpServlet {
    private HistoryServiceImpl historyService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //日志列表
        if ("/historyList.do".equals(req.getServletPath())) {
            String pageStr = req.getParameter("page");//当前页码
            int page = 1;//页码默认为1
            if(pageStr != null && (!"".equals(pageStr))) {
                page = Integer.valueOf(pageStr);
            }

            List<History> HLIST = historyService.selectPageAll(page, 5);//分页查询
            int count =	historyService.getCount();//雇员总数
            int last = count % 5 == 0 ? (count / 5) : ((count / 5) +1);//总页数
            req.setAttribute("last", last);
            req.setAttribute("page", page);

            req.setAttribute("HLIST", HLIST);
            req.getRequestDispatcher("/historyList.jsp").forward(req, resp);
        }

        //模糊查询
        if ("/historySearch.do".equals(req.getServletPath())) {
            String search = req.getParameter("search");
            List<History> HLIST = historyService.search(search);
            req.setAttribute("HLIST", HLIST);
            req.getRequestDispatcher("/historyList.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        historyService = null;
}

    @Override
    public void init() throws ServletException {
        super.init();
        historyService = new HistoryServiceImpl();
    }
}
