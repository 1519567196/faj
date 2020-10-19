package com.resjz.common.service.plugins.cache;

import java.util.HashMap;
import java.util.Map;

public final class SQLCache {

    private static Map<String,String> sqlCache = new HashMap<>(4);
    static {
        //首页大型设备bigPro recommend sale comment
        sqlCache.put("bigPro","SELECT a.id,a.title,a.imgurl,a.upnum,a.views,b.num FROM zm_products a left JOIN (SELECT count(pid) num, pid FROM zm_productcommend WHERE isavail = 'Y' GROUP BY pid) b ON a.id = b.pid WHERE a.cid IN ( SELECT id FROM zm_product_cat WHERE parentid = 17) AND a.display = 0 ORDER BY a.sortnum2 LIMIT 15 ");
        //推荐产品
        sqlCache.put("recommend","select  a.id,a.title,a.imgurl,a.upnum,a.views,b.num from zmgw_new_db.zm_products a left JOIN (SELECT count(pid) num, pid FROM zmgw_new_db.zm_productcommend WHERE isavail = 'Y' GROUP BY pid ) b ON a.id = b.pid where a.display = 0  and   a.property like '1%'  order by a.sortnum2 ,id desc  limit 15 ");
        //热销产品
        sqlCache.put("sale"," select a.id,a.title,a.imgurl,a.upnum,a.views,b.num from zm_products a left join (select count(pid) num,pid from zm_productcommend where isavail = 'Y' group by pid) b on a.id = b.pid where a.display = 0 and a.property like '%4%' order by a.sortnum2 limit 15 ");
        //热评产品
        sqlCache.put("comment","select a.id,a.title,a.imgurl,a.upnum,a.views,b.num from zmgw_new_db.zm_products  a left join (select count(pid) as num,pid from zmgw_new_db.zm_productcommend group by pid  ) b on  a.id=b.pid where  a.display=0 order by b.num desc, a.sortnum2 ,id limit 15 ");

        //新闻集团要闻
        sqlCache.put("newjtyw","SELECT  id,cid,title,contents,summary,tags,imgurl,views,commend,iscomment,display,author,createtime,modifytime,upnum,downnum FROM zm_news  WHERE cid = 4 ORDER BY CreateTime DESC LIMIT 8"  );
        //新闻中煤电视台
        sqlCache.put("newzmdst","SELECT  id,cid,title,contents,summary,tags,imgurl,views,commend,iscomment,display,author,createtime,modifytime,upnum,downnum  FROM zm_news  WHERE cid = 13 ORDER BY CreateTime DESC LIMIT 9"  );
        //新闻客户来访
        sqlCache.put("newkhlf","SELECT  id,cid,title,contents,summary,tags,imgurl,views,commend,iscomment,display,author,createtime,modifytime,upnum,downnum  FROM zm_news  WHERE cid = 8 ORDER BY CreateTime DESC LIMIT 9"  );
        //新闻领导关怀
        sqlCache.put("newldgh","SELECT  id,cid,title,contents,summary,tags,imgurl,views,commend,iscomment,display,author,createtime,modifytime,upnum,downnum  FROM zm_news  WHERE cid = 7 ORDER BY CreateTime DESC LIMIT 9"  );
        //新闻行业新闻
        sqlCache.put("newhyxw","SELECT  id,cid,title,contents,summary,tags,imgurl,views,commend,iscomment,display,author,createtime,modifytime,upnum,downnum  FROM zm_news  WHERE cid = 6 ORDER BY CreateTime DESC LIMIT 9"  );
        //新闻行业新闻
        sqlCache.put("topnews","SELECT id,newid,title,imgurl,title1,imgurl1,remark1,remark2 FROM zm_newstop ORDER BY NewId DESC LIMIT 1"  );
        //留言
        sqlCache.put("leavemsg","SELECT t1.*,t2.`Name` trReplyer,t2.`MsgContent` replyContent FROM zm_leavemsg t1 INNER JOIN zm_leavemsg t2 ON t2.id=t1.`replyID` WHERE t1.`IsAvail`='Y'"  );
        //循环首页新闻
        sqlCache.put("newjtyw","SELECT  id,cid,title,contents,summary,tags,imgurl,views,commend,iscomment,display,author,createtime,modifytime,upnum,downnum FROM zm_news   WHERE 1=1  ORDER BY CreateTime DESC LIMIT 6"  );
        //置顶新闻
        sqlCache.put("topnews","SELECT id,newid,title,imgurl,title1,imgurl1,remark1,remark2 FROM zm_newstop ORDER BY NewId DESC LIMIT 1"  );

    }

    private SQLCache(){}
    public static String getSQL(String key){
        return sqlCache.get(key);
    }
}
