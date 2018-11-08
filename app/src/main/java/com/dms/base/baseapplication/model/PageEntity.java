package com.dms.base.baseapplication.model;

import java.util.List;

public class PageEntity<T> {
    private int page;

    private int total;

    private List<T> list;

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }
}
