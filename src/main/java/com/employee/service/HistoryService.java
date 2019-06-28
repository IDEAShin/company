package com.employee.service;

import com.employee.po.History;

import java.util.List;

public interface HistoryService {

    List<History> selectAll();

    List<History> selectPageAll(int page, int count);

    List<History> search(String search);

    int getCount();
}
