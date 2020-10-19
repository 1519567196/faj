package com.resjz.modules.zmadmin.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 匿名服务
 */
//@RestController
public class AnnoController {

    //@Value("${file.path}")
    private String filePath;

    /**
     * 回显上传图片
     */
    //@RequestMapping("/proUpload/**")
    public void renderPicture(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        uri = uri.replace(request.getContextPath(),"");
        String path = filePath + uri;
        try(InputStream in = new FileInputStream(path)) {
            byte[] bytes = IOUtils.toByteArray(in);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            try {
                response.sendRedirect("https://img.zcool.cn/community/015a465698b54432f87574be965625.png@2o.png");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
