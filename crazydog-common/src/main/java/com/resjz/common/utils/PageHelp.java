package com.resjz.common.utils;

import java.util.ArrayList;
import java.util.List;

public class PageHelp {
    public static PageR page(Object object,int currentPage,int pageSize){
        PageR page=new PageR();
        List<Object> list=(List<Object>)object;
        List<Object> pageObjects=new ArrayList<>();
        if (list!=null||list.size()>0){
            int index=currentPage>1?(currentPage-1)*pageSize:0;
            for (int i=0;i<pageSize&&i<list.size()-index;i++){
                Object o=list.get(i+index);
                pageObjects.add(o);
            }
        }
        page.setCurrentPage(currentPage);
        page.setObject(pageObjects);
        page.setPageSize(pageSize);
        page.setTotalCount(list.size());
        return page;
    }


}


