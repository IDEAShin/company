package com.employee.service.Impl;

import com.employee.dao.HistoryDAO;
import com.employee.dao.impl.HistoryDAOImpl;
import com.employee.po.History;
import com.employee.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDAO historyDao;

    public HistoryServiceImpl() {
        historyDao = new HistoryDAOImpl();
    }

    @Override
    public List<History> selectAll(){
        return historyDao.selectAll();
    }

    @Override
    public List<History> selectPageAll(int page, int count) {
        return historyDao.selectPageAll(page, count);
    }

    @Override
    public List<History> search(String search) {
        search = "%" + search + "%";
        List<History> hlist = historyDao.search(search);
        return hlist;
    }

    @Override
    public int getCount() {
        return historyDao.getCount();
    }

}
