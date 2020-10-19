package com.resjz.common.service.zmadmin.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.resjz.common.dao.zmadmin.entity.*;
import com.resjz.common.dao.zmadmin.dao.FHousesDao;
import com.resjz.common.service.vo.FHouseArticleVo;
import com.resjz.common.service.zmadmin.*;
import com.resjz.common.utils.PageUtils;
import com.resjz.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("fHousesService")
public class FHousesServiceImpl extends ServiceImpl<FHousesDao, FHousesEntity> implements FHousesService {
    @Autowired
    private FAreaService fAreaService;

    @Autowired
    private FHouseTypeImagesService fHouseTypeImagesService;
    @Autowired
    private FArticleTypeService fArticleTypeService;

    @Autowired
    private FHouseArticleService fHouseArticleService;

    @Autowired
    private FHouseCommentService fHouseCommentService;

    @Autowired
    private FCommonAttrValueService fCommonAttrValueService;


    @Autowired
    private FHouseSkuService fHouseSkuService;

    @Autowired
    private FHouseImagesService fHouseImagesService;

    @Autowired
    private FHouseimgSortService fHouseimgSortService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FHousesEntity> page = this.selectPage(
                new Query<FHousesEntity>(params).getPage(),
                new EntityWrapper<FHousesEntity>()
        );

        for (FHousesEntity f : page.getRecords()) {
            FAreaEntity fAreaEntity = fAreaService.selectById(f.getTownid());
            String areaName = fAreaEntity == null ? "" :
                    fAreaEntity.getAreaParentId() == null ? fAreaEntity.getAreaName() :
                            fAreaService.selectById(fAreaEntity.getAreaParentId()) == null ? fAreaEntity.getAreaName() :
                                    fAreaService.selectById(fAreaEntity.getAreaParentId()).getAreaParentId() == null ?
                                            fAreaService.selectById(fAreaEntity.getAreaParentId()).getAreaName() + fAreaEntity.getAreaName() :
                                            fAreaService.selectById(fAreaService.selectById(fAreaEntity.getAreaParentId()).getAreaParentId()) == null ?
                                                    fAreaService.selectById(fAreaEntity.getAreaParentId()).getAreaName() + fAreaEntity.getAreaName() :
                                                    fAreaService.selectById(fAreaService.selectById(fAreaEntity.getAreaParentId()).getAreaParentId()).getAreaName() +
                                                            fAreaService.selectById(fAreaEntity.getAreaParentId()).getAreaName() + fAreaEntity.getAreaName();

            f.setTownName(areaName);
        }


        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {
        /**
         * 封装不能直接传递的参数
         */
        List<Integer> listAreas = null;
        String fHousesSearch = null;
        if (params.get("listAreas") != null) {
            listAreas = (List<Integer>) params.get("listAreas");
        }
        if (params.get("fHousesSearch") != null && !params.get("fHousesSearch").toString().equals("")) {
            fHousesSearch = params.get("fHousesSearch").toString();
        }
/**
 * 先不查规格参数
 */
        List<FHousesEntity> listReady = this.selectList(

                new EntityWrapper<FHousesEntity>()
                        .eq(params.get("fHousesSaleStu") != null, "sale_status", params.get("fHousesSaleStu"))
                        .in(listAreas != null, "areaid", listAreas)
                        .like(fHousesSearch != null, "title", fHousesSearch)
                        .orderBy(params.get("orderBy").toString(), false)
        );
        List<FHousesEntity> list = new ArrayList<>();
        /**
         * 根据实际需求封装到list
         */

        for (FHousesEntity f : listReady) {
            /**
             * 封装综合分数
             */
            FHousesEntity score = getScore(f);
            f = score;
            Map<String, Object> info = f.getInfo();
            /**
             * 封装楼盘销售状态
             */
            if (f.getSaleStatus() == 1) {
                info.put("saleStatus", "在售");

            } else if (f.getSaleStatus() == 2) {
                info.put("saleStatus", "售罄");
            } else if (f.getSaleStatus() == 3) {
                info.put("saleStatus", "待售");
            }
            /**
             * 封装楼盘户型和户型面积
             */

            List<FHouseSkuEntity> fHouseSkuEntities2 = fHouseSkuService.selectList(new EntityWrapper<FHouseSkuEntity>()
                    .eq("house_id", f.getItemid())
                    .eq("attr_id", 1)
            );
            if (fHouseSkuEntities2.size() > 0) {
                StringBuffer fhouseType = new StringBuffer("");
                int i = 1;
                for (FHouseSkuEntity fs : fHouseSkuEntities2) {

                    FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(fs.getAttrValueId());
                    if (fCommonAttrValueEntity != null) {
                        if (i == fHouseSkuEntities2.size()) {
                            fhouseType.append(fCommonAttrValueEntity.getTitle());
                        } else {
                            fhouseType.append(fCommonAttrValueEntity.getTitle() + '/');
                        }

                    }


                    i++;
                }
                info.put("HhouseType", fhouseType.append("－" + f.getHtypeFloorSpace()));

            }

            /**
             * 封装标签
             */
            if (f.getTags() != null) {
                List<Object> tags = getTags(f.getTags());
                info.put("tags", tags);
            }


            /**
             * 封装楼盘类型
             */
            List<String> houseType = getHouseType(f.getItemid());
            info.put("houseType", houseType);
            /**
             * 封装地区+详细地址
             */
            String address = getAddress(f);
            info.put("areaAddress", address);


            /**
             * 筛选规格参数得出楼盘
             */
            if (params.get("fHousesSelAttrV") != null && !params.get("fHousesSelAttrV").toString().equals("")) {

                Object[] fHousesSelAttrVS = (Object[]) params.get("fHousesSelAttrV");
                if (fHousesSelAttrVS.length > 0) {
                    List<Integer> listIn = new ArrayList<>();
                    for (Object o : fHousesSelAttrVS) {
                        String[] split = o.toString().split(",");
                        if (split.length > 0) {
                            listIn.add(Integer.parseInt(split[1]));
                        }


                    }
                    EntityWrapper<FHouseSkuEntity> wrapper = new EntityWrapper<>();
                    wrapper.eq("house_id", f.getItemid());
                    wrapper.in("attr_value_id", listIn);
                    List<FHouseSkuEntity> fHouseSkuEntities = fHouseSkuService.selectList(wrapper);
                    if (fHouseSkuEntities.size() == fHousesSelAttrVS.length) {
                        list.add(f);
                    }
                } else {
                    list.add(f);
                }

            } else {
                list.add(f);
            }

            f.setInfo(info);
        }


        return PageUtils.page(list, params.get("page") == null || params.get("page").toString().equals("") ? 1 : Integer.valueOf(params.get("page").toString()), 1);
    }


    public FHousesEntity getScore(FHousesEntity f) {

        List<FHouseCommentEntity> scores = fHouseCommentService.selectList(new EntityWrapper<FHouseCommentEntity>()
                .eq("houseid", f.getItemid())
                .ne("is_hot", 2)
        );
        Double score = 0.0;
        int i = 0;
        for (FHouseCommentEntity fc : scores) {
            i++;
            score = score + fc.getMainScore();
        }

        f.setScore(i == 0 ? 0 : (score / i));
//        f.setScore(4.4);   //测试用
        Map<String, Object> map = new HashMap<>();
        map.put("commentNum", i);
        f.setInfo(map);


        return f;
    }

    public static Object[] oneClear(Object[] arr) {
        Set set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && !arr[i].toString().equals("")) {
                set.add(arr[i]);
            }

        }
        return set.toArray();
    }

    @Override
    public FHousesEntity getIndex(Map<String, Object> params) {
        if (params.get("houseId") != null) {
            FHousesEntity fHousesEntity = this.selectById(Integer.valueOf(params.get("houseId").toString()));
            /*  添加浏览量*/
            try {
                fHousesEntity.setViews(fHousesEntity.getViews() + 1);
                this.updateById(fHousesEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Map<String, Object> info = new HashMap<>();
            if (fHousesEntity != null) {
                /**
                 * 封装销售状态
                 */
                if (fHousesEntity.getSaleStatus() == 1) {
                    info.put("saleStatus", "在售");

                } else if (fHousesEntity.getSaleStatus() == 2) {
                    info.put("saleStatus", "售罄");
                } else if (fHousesEntity.getSaleStatus() == 3) {
                    info.put("saleStatus", "待售");
                }


                /**
                 * 封装楼盘类型
                 */
                List<String> houseType = getHouseType(fHousesEntity.getItemid());
                info.put("houseType", houseType);

                /**
                 * 封装标签
                 */
                if (fHousesEntity.getTags() != null) {
                    List<Object> tags = getTags(fHousesEntity.getTags());
                    info.put("tags", tags);
                }

                /**
                 * 封装地区+详细地址
                 */
                String address = getAddress(fHousesEntity);
                info.put("areaAddress", address);

                /**
                 * 封装楼盘相册
                 */
                List<Map<String, String>> hImges = new ArrayList<>();
                List<FHouseimgSortEntity> fHouseimgSortEntities =
                        fHouseimgSortService.selectList(new EntityWrapper<FHouseimgSortEntity>()
                                .eq("ia_type", 1)
                        );
                for (FHouseimgSortEntity fs : fHouseimgSortEntities) {
                    List<FHouseImagesEntity> fHouseImagesEntities =
                            fHouseImagesService.selectList(new EntityWrapper<FHouseImagesEntity>()
                                    .eq("house_id", fHousesEntity.getItemid())
                                    .eq("sort_id", fs.getIaId())
                                    .orderBy("rank", false)
                            );
                    if (fHouseImagesEntities.size() > 0) {
                        Map<String, String> map = new HashMap<>();
                        map.put("imgSort", fs.getImageAlbumName());
                        map.put("imgCount", String.valueOf(fHouseImagesEntities.size()));
                        map.put("image", fHouseImagesEntities.get(0).getImage());

                        hImges.add(map);

                    }


                }

                info.put("hImges", hImges);

                /*   封装楼盘动态    */

                List<FHouseArticleEntity> articleList = getArticleList(fHousesEntity, params);
                info.put("articleList", articleList);


                /* 封装楼盘户型相册*/
//封装相册和相册图片数量
                List<Map<String, String>> htImges = new ArrayList<>();
                List<FHouseTypeImagesEntity> htImgeContent = new ArrayList<>();
                List<FHouseimgSortEntity> fHouseTypeimgSortEntities =
                        fHouseimgSortService.selectList(new EntityWrapper<FHouseimgSortEntity>()
                                .eq("ia_type", 2)
                        );
                if (fHouseTypeimgSortEntities.size() > 0) {
                    for (FHouseimgSortEntity fs : fHouseTypeimgSortEntities) {
                        List<FHouseTypeImagesEntity> fHouseTypeImagesEntities =
                                fHouseTypeImagesService.selectList(new EntityWrapper<FHouseTypeImagesEntity>()
                                        .eq("house_id", fHousesEntity.getItemid())
                                        .eq("sort_id", fs.getIaId())
                                        .orderBy("rank", false));

                        if (fHouseTypeImagesEntities.size() > 0) {
                            Map<String, String> map = new HashMap<>();
                            map.put("sortName", fs.getImageAlbumName());
                            map.put("imgCount", String.valueOf(fHouseTypeImagesEntities.size()));
                            htImges.add(map);
//                            htImgeContent.add(fHouseTypeImagesEntities);
                            htImgeContent.addAll(fHouseTypeImagesEntities);
                        }


                    }
                    info.put("htImges", htImges);
                    info.put("htImgeContent", htImgeContent.size()<=4?htImgeContent:htImgeContent.subList(0,4));


                }


                fHousesEntity.setInfo(info);
            }
            return fHousesEntity;
        } else {

            return null;
        }

    }

    public List<FHouseArticleEntity> getArticleList(FHousesEntity fHousesEntity, Map<String, Object> params) {
        List<FHouseArticleEntity> fHouseArticleEntities =
                fHouseArticleService.selectList(new EntityWrapper<FHouseArticleEntity>()
                                .eq("house_id", fHousesEntity.getItemid())
                                .orderBy("views", false)
//                                .last("limit 3")
                );
        List<FHouseArticleEntity> articleList = new ArrayList<>();
        if (fHouseArticleEntities.size() > 0) {
            for (FHouseArticleEntity f : fHouseArticleEntities) {

                FArticleTypeEntity fArticleTypeEntity = fArticleTypeService.selectById(f.getArticleTypeId());
                f.setArticleTypeName(fArticleTypeEntity.getTypeName());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String format = sdf.format(f.getAddtime());
                f.setAddDate(format);


                if (params.get("areaId1") != null && f.getAreaid() != null) {
                    int areaId1 = Integer.parseInt(params.get("areaId1").toString());
                    FAreaEntity fAreaEntity = fAreaService.selectById(f.getAreaid());
                    if (areaId1 == f.getAreaid() || areaId1 == fAreaEntity.getAreaParentId()) {
                        articleList.add(f);
                    }

                } else {
                    articleList.add(f);
                }


            }
        }

        return articleList;
    }

    public String getAddress(FHousesEntity fHousesEntity) {
        if (fHousesEntity.getTownid() != null) {
            FAreaEntity fAreaEntity = fAreaService.selectById(fHousesEntity.getTownid());
            String areaName = fAreaEntity.getAreaName() == null ? "" : fAreaEntity.getAreaName();
            if (fHousesEntity.getAddress() != null) {
                return "[" + areaName + "]" + fHousesEntity.getAddress();

//                info.put("areaAddress", );
            } else {
                return "[" + areaName + "]";

            }

        } else {
            if (fHousesEntity.getAddress() != null) {
                return fHousesEntity.getAddress();

//                info.put("areaAddress", );
            } else {
                return "";

            }
        }
    }

    public List<Object> getTags(String str) {
        String[] split1 = str.split(";");
        if (split1.length > 0) {
            Object[] objects = oneClear(split1);
            List<Object> objects1 = Arrays.asList(objects);

            if (objects.length > 0) {
                if (objects1.size() <= 3) {
//                    info.put("tags", objects);
                    return objects1;
                } else {
//                    info.put("tags", objects1.subList(0, 3));
                    return objects1.subList(0, 3);
                }

            }
        }

        return null;
    }

    public List<String> getHouseType(Integer houseId) {
        List<FHouseSkuEntity> fHouseSkuEntities1 = fHouseSkuService.selectList(new EntityWrapper<FHouseSkuEntity>()
                .eq("house_id", houseId)
                .eq("attr_id", 2)
        );
        List<String> houseType = new ArrayList<>();
        if (fHouseSkuEntities1.size() > 0) {

            for (FHouseSkuEntity fs : fHouseSkuEntities1) {
                FCommonAttrValueEntity fCommonAttrValueEntity = fCommonAttrValueService.selectById(fs.getAttrValueId());
                if (fCommonAttrValueEntity != null) {
                    houseType.add(fCommonAttrValueEntity.getTitle());

                }

            }

        }

        return houseType;
    }

    @Override
    public List<FHousesEntity> query() {
        List<FHousesEntity> fHousesEntities = this.selectList(null);

        return fHousesEntities;
    }
}
