package com.employee.dao;

import com.employee.po.History;

import java.util.List;

public interface HistoryDAO {

    List<History> selectAll();

    List<History> selectPageAll(int page, int count);

    List<History> search(String search);

    int getCount();
}
