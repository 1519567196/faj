package com.resjz.modules.zmadmin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resjz.common.utils.Constant;
import com.resjz.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上传
 *
 */

//新闻上传图片 使用
@RestController
@RequestMapping("/upload")
public class ImgController {
   /* @Value("${file.path}")
    public  String filePath;*/

    @Value("${upload.savePath}")
    private  String savePath;

    @Value("${upload.pathNginx}")
    private  String pathNginx;


    //富文本图片上传地址
    @Value("${upload.fwbImgPath}")
    private  String fwbImgPath;

    //富文本视频上传地址
    @Value("${upload.fwbVideoPath}")
    private  String fwbVideoPath;

    private PrintWriter writer = null;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @ResponseBody
    @RequestMapping("/imgUpload")
    public R imgUpload(MultipartHttpServletRequest request, @RequestParam("filePage") String filePage,
                       HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        String fileAdd = request.getRequestURI();
        if(fileMap.size()<=0){
            return  R.error("没有文件数据！！");
        }

        Boolean iserr = false;
        String errmag ="";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile mf = entity.getValue();
            // 获得原始文件名
            String fileName = mf.getOriginalFilename();
            String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(fileName.toLowerCase());
            if (!matcher.find()) {
                iserr=true;
                errmag="图片格式不正确，请上传 jpeg,jpg,gif,bmp,png格式图片";
                break;
            }
            if (mf.getSize() > 1024 * 1024 * 3) {
                iserr=true;
                errmag="图片最大3M！";
                break;
            }

        }
        if(iserr){
            return  R.error(errmag);
        }
        List<Map<String,String>> backdata =new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
            String uuidname = UUID.randomUUID().toString();
            String upload =  savePath+""+filePage;
            if (filePage.equals("house")) {
                upload = savePath+"sqEditor";
            }else if (filePage.equals("programme")||filePage.equals("material")||filePage.equals("fitup")){
                upload = savePath+"sqEditor/"+filePage;
            }
//            System.out.println("90-----"+upload);
//            String webPath = "/upload/"+filePage;
            String webPath =  ""+filePage;
            String   imgUrl=null;
            if (filePage.equals("house")) {
                webPath = pathNginx+"/sqEditor";
                imgUrl= "/"+Constant.HOUSE_IMG;
            }else   if (filePage.equals("programme")||filePage.equals("material")||filePage.equals("fitup")) {
                webPath = pathNginx+"/sqEditor/"+filePage;
                imgUrl= "/"+Constant.HOUSE_IMG+"/"+filePage;
            }
//            System.out.println("90-----"+webPath);
            // 时间文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            upload += "/" + sdf.format(new Date())+"/";
            webPath += "/" + sdf.format(new Date())+"/";
            imgUrl += "/" + sdf.format(new Date())+"/";
//            System.out.println("98-----"+upload);
//            System.out.println("98-----"+webPath);
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            File uploadFile = new File(upload);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File file = new File(upload+uuidname+fileType);
            mf.transferTo(file);
            Map<String,String> mad = new HashMap<>();
            String realurl=webPath+uuidname+fileType;
            String weburls=webPath+uuidname+fileType;
//            System.out.println("realurl=="+realurl);
            System.out.println("weburls=="+weburls);
            mad.put("key",entity.getKey());
            mad.put("url",webPath+uuidname+fileType);
            mad.put("imgUrl",imgUrl+uuidname+fileType);
            mad.put("urls",webPath+uuidname+fileType);
            mad.put("upload",upload+uuidname+fileType);
            backdata.add(mad);
        }
        return R.ok().put("urls",backdata);

    }


    //文件上传
    @ResponseBody
    @RequestMapping("/fileUpload")
    public R fileUpload(MultipartHttpServletRequest request, @RequestParam("filePage") String filePage,
                       HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if(fileMap.size()<=0){
            return  R.error("没有文件数据！！");
        }

        Boolean iserr = false;
        String errmag ="";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile mf = entity.getValue();
            // 获得原始文件名
          /*  String fileName = mf.getOriginalFilename();
            String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(fileName.toLowerCase());
            if (!matcher.find()) {
                iserr=true;
                errmag="图片格式不正确，请上传 jpeg,jpg,gif,bmp,png格式图片";
                break;
            }*/
            if (mf.getSize() > 1024 * 1024 * 100) {
                iserr=true;
                errmag="文件最大100M！";
                break;
            }

        }
        if(iserr){
            return  R.error(errmag);
        }
        List<Map<String,String>> backdata =new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
            String uuidname = UUID.randomUUID().toString();
            String upload = savePath+"/file";//ResourceUtils.getURL("classpath:").getPath()+"/statics/upload";
            String webPath = "/upload/file";
            // 时间文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            upload += "/" + sdf.format(new Date())+"/";
            webPath += "/" + sdf.format(new Date())+"/";
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            File uploadFile = new File(upload);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File file = new File(upload+uuidname+fileType);
            mf.transferTo(file);
            Map<String,String> mad = new HashMap<>();
            mad.put("key",entity.getKey());
            mad.put("url",webPath+uuidname+fileType);
            backdata.add(mad);
        }
        return R.ok().put("urls",backdata);

    }

    //音频上传
    @ResponseBody
    @RequestMapping("/musicUpload")
    public R musicUpload(MultipartHttpServletRequest request, @RequestParam("filePage") String filePage,
                        HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if(fileMap.size()<=0){
            return  R.error("没有文件数据！！");
        }

        Boolean iserr = false;
        String errmag ="";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile mf = entity.getValue();
            // 获得原始文件名
           String fileName = mf.getOriginalFilename();
            String reg = ".+(.MP3|.mp3)$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(fileName.toLowerCase());
            if (!matcher.find()) {
                iserr=true;
                errmag="音频格式不正确，请上传 mp3格式图片";
                break;
            }
            if (mf.getSize() > 1024 * 1024 * 50) {
                iserr=true;
                errmag="音频最大50M！";
                break;
            }

        }
        if(iserr){
            return  R.error(errmag);
        }
        List<Map<String,String>> backdata =new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
            String uuidname = UUID.randomUUID().toString();
            String upload = savePath+"/"+filePage;
            String webPath = "/upload/"+filePage;
            // 时间文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            upload += "/" + sdf.format(new Date())+"/";
            webPath += "/" + sdf.format(new Date())+"/";
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            File uploadFile = new File(upload);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File file = new File(upload+uuidname+fileType);
            mf.transferTo(file);
            Map<String,String> mad = new HashMap<>();
            mad.put("key",entity.getKey());
            mad.put("url",webPath+uuidname+fileType);
            backdata.add(mad);
        }
        return R.ok().put("urls",backdata);

    }

    //视频上传
    @ResponseBody
    @RequestMapping("/videoUpload")
    public R videoUpload(MultipartHttpServletRequest request, @RequestParam("filePage") String filePage,
                         HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if(fileMap.size()<=0){
            return  R.error("没有文件数据！！");
        }

        Boolean iserr = false;
        String errmag ="";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile mf = entity.getValue();
            // 获得原始文件名
         /*   String fileName = mf.getOriginalFilename();
            String reg = ".+(.MP4|.mp4|.)$";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(fileName.toLowerCase());
            if (!matcher.find()) {
                iserr=true;
                errmag="图片格式不正确，请上传 jpeg,jpg,gif,bmp,png格式图片";
                break;
            }*/
            if (mf.getSize() > 1024 * 1024 * 100) {
                iserr=true;
                errmag="视频最大100M！";
                break;
            }

        }
        if(iserr){
            return  R.error(errmag);
        }
        List<Map<String,String>> backdata =new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
            String uuidname = UUID.randomUUID().toString();
            String upload = savePath+"/"+filePage;
            String webPath = "/upload/"+filePage;
            // 时间文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            upload += "/" + sdf.format(new Date())+"/";
            webPath += "/" + sdf.format(new Date())+"/";
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            File uploadFile = new File(upload);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File file = new File(upload+uuidname+fileType);
            mf.transferTo(file);
            Map<String,String> mad = new HashMap<>();
            mad.put("key",entity.getKey());
            mad.put("url",webPath+uuidname+fileType);
            backdata.add(mad);
        }
        return R.ok().put("urls",backdata);

    }
//富文本上传
    @ResponseBody
    @RequestMapping("/fwbUpload")
    public void fwbUpload(MultipartHttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        String fileAdd = request.getRequestURI();
        String url = "";
        Boolean iserr = false;
        String errmag ="";
        //返回内容防止中文乱码
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        writer = response.getWriter();
        if(fileMap.size()<=0){
            writer.println(objectMapper.writeValueAsString(getError("没有文件数据！！")));
        }

        //音视频默认传入视频文件夹下
        String filePage="fwbVideoPath";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile mf = entity.getValue();
            // 获得原始文件名

            String fileName = mf.getOriginalFilename();
            String regImges = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
            String regVideo= ".+(.AVI|.avi|.MP4|.mp4|.FLV|.flv|.RMVB|.rmvb|.3GP|.3gp)$";

            Pattern patternImg = Pattern.compile(regImges);
            Matcher matcherImg = patternImg.matcher(fileName.toLowerCase());
            Pattern patternVideo = Pattern.compile(regVideo);
            Matcher matcherVideo = patternVideo.matcher(fileName.toLowerCase());
            //如果是图片
            if (matcherImg.find()) {
                 filePage="fwbImgPath";
                break;
            }
            //如果是视频
            if (matcherVideo.find()) {
                 filePage="fwbVideoPath";
                break;
            }
            //大小 50m
            if (mf.getSize() > 1024 * 1024 * 50) {
                iserr=true;
                writer.println(objectMapper.writeValueAsString(getError("文件大小超过50M ！")));
                return;
            }
        }
        List<Map<String,String>> backdata =new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
            String uuidname = UUID.randomUUID().toString();
            String upload = savePath+"/textimage/"+filePage;
//            String webPath = "/upload/"+filePage;
            String webPath = "/textimage/"+filePage;
            // 时间文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            upload += "/" + sdf.format(new Date())+"/";
            webPath += "/" + sdf.format(new Date())+"/";
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            File uploadFile = new File(upload);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File file = new File(upload+uuidname+fileType);
            mf.transferTo(file);
            url=pathNginx+webPath+uuidname+fileType;
        }
        System.out.println("ddddddd=="+url);
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("error", 0);
        msg.put("url", url);
        writer.println(objectMapper.writeValueAsString(msg));
    }
    private Map<String, Object> getError(String message) {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("error", 1);
        msg.put("message", message);
        return msg;
    }


    @ResponseBody
    @RequestMapping("/deleteFile")
    public R deleteFile(@RequestParam("delUrl") String delUrl) {
        String imgurl = "G:/fanganjia/statics//sqEditor/20200914/e863b3f5-bb69-4577-924f-6a11b61205eb.png";
//        System.out.println(imgurl+"         nnnn");
        File serverFile = new File(delUrl);
        if (serverFile.exists() && serverFile.isFile()
                && serverFile.delete() == true) {
            return new R();

        }
        return R.error();

    }


}
