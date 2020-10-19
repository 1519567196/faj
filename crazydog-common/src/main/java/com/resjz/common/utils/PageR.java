package com.resjz.common.utils;

public class PageR {
    private int currentPage;//当前页
    private int pageSize;   //每页条数
    private Object object;  //分页内容
    private int totalCount;//总条数

    public PageR() {
    }
    public PageR(int currentPage, int pageSize, Object object, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.object = object;
        this.totalCount = totalCount;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Page{" + "currentPage=" + currentPage + ", pageSize=" + pageSize + ", object=" + object + ", totalCount=" + totalCount + '}';
    }
}

