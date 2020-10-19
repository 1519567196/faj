package com.resjz.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageUtil {

    public static List<String> page(String route, int currPage, int totalPage, String keyword){
        String head = "<a href='" + route;
        String end = "</a>";
        if(currPage == 1 && totalPage == 1){
            return Arrays.asList("<a href='"+route+"0/1' class='current'>1</a>");
        }
        List<String> hrefs = new ArrayList<>(13);

        for(int i = 4;i > 0;--i){
            if(currPage - i > 0 ){
                hrefs.add(head + keyword + "/" + (currPage -i) + "'>" + (currPage - i) + end);
            }
        }
        hrefs.add(head + keyword + "/" + currPage +   "' class='current'>"+currPage+"</a>");
        for(int i = 1;i < 5 ;++i){
            if(currPage + i <= totalPage){
                hrefs.add(head + keyword + "/" + (currPage +i) + "'>" + (currPage + i) + end);
            }
        }
        if(currPage != 1){
            hrefs.add(0,head + keyword + "/" + currPage + "'>First" + end);
            hrefs.add(1,head + keyword + "/" + (currPage -1) + "'>Prev" + end);
        }
        if(currPage != totalPage){
            hrefs.add(head + keyword + "/" + (currPage +1) + "'>Next" + end);
            hrefs.add(head + keyword + "/" + totalPage + "'>Last" + end);
        }
        return  hrefs;
    }
}
