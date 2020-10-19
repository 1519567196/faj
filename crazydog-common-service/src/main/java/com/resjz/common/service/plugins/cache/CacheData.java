package com.resjz.common.service.plugins.cache;

import java.util.Date;

public class CacheData<T> {

    private Date storageTime;

    private T t;

    public static CacheData create(){
        return new CacheData();
    }



    private CacheData() {}

    public  CacheData(Date storageTime, T t) {
        this.storageTime = storageTime;
        this.t = t;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public CacheData storageTime (Date date){
        this.setStorageTime(date);
        return this;
    }

    public CacheData data(T t){
        this.setT(t);
        return this;
    }
}
