//package com.resjz.common.service.plugins.cache;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.stream.Collectors;
//
//@Service
//public class ResultCache<T> {
//
//    public  enum CacheConfig{
//        //产品分类id及子id
//        Pro_Cat_Ids_Available(120,"Pro_Cat_Ids_Available"),
//        //产品中心侧边
//        Pro_Cat(90,"Pro_Cat"),
//        //首页产品
//        Pro_Index(150,"Pro_Index"),
//        //首页新闻
//        News_Index(70,"News_Index"),
//        //置顶新闻
//        News_Top(300,"News_Top"),
//        //首页类型
//        News_Type(60,"News_Type"),
//
//        //新闻中心侧边
//        News_Cat(90,"News_Cat"),
//        //客户留言内容
//        Leave_Msg(100,"Leave_Msg");
//
//
//
//        private int time;
//
//        private String name;
//
//        CacheConfig(int time, String name) {
//            this.time = time;
//            this.name = name;
//        }
//
//        public int getTime() {
//            return time;
//        }
//
//        public void setTime(int time) {
//            this.time = time;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
//    @Autowired
//    private ZmProductCatService zmProductCatService;
//    public static ConcurrentMap<String,CacheData> cids = new ConcurrentHashMap<>();
//
//    public static ConcurrentMap<String,CacheData> data= new ConcurrentHashMap<>();
//
//   public void putData(String key,Object value,ResultCache.CacheConfig config){
//       long time = System.currentTimeMillis()+(config.getTime()*1000);
//       Date expiretime = new Date(time);
//       data.put(config.getName()+ key, new CacheData(expiretime,value));
//   }
//
//    public CacheData<T> getData(CacheConfig pro_cat, String key) {
//
//        CacheData cacheData = data.get(pro_cat.getName()+key);
//        if(checkExpire(cacheData,pro_cat)){
//            return null;
//        }
//        return cacheData;
//    }
//
//    public List<Integer> getAllAvailable(ResultCache.CacheConfig config,int pid){
//        CacheData<List<Integer>> data = cids.get(config.getName() + pid);
//        if(!checkExpire(data,config)){
//            return data.getT();
//        }
//        ZmProductCatEntity entity = zmProductCatService.selectById(pid);
//        if(null== entity || entity.getStatus().equals("N")){
//            return Collections.emptyList();
//        }
//
//        List<ZmProductCatEntity> child = new ArrayList<>(10);
//        zmProductCatService.loopByPid(pid,child);
//        List<ZmProductCatEntity> availChild = child.parallelStream().filter(item->item.getStatus().equals("Y")).collect(Collectors.toList());
//        List<Integer> ids = availChild.stream().map(ZmProductCatEntity::getId).collect(Collectors.toList());
//        ids.add(pid);
//        return ids;
//    }
//
//
//    private boolean checkExpire(CacheData cacheData,ResultCache.CacheConfig cacheConfig){
//        if(null==cacheData){
//            return true;
//        }
//        long expire = cacheData.getStorageTime().getTime()+cacheConfig.getTime()*1000;
//        if(expire < System.currentTimeMillis()){
//            return true;
//        }
//        return false;
//    }
//}
