package com.resjz.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class CheckCodeUtil {
    /**
     * 校验验证码
     * @param request
     * @param session
     * @param checkCode
     * @return
     */
    public String chckCode(HttpServletRequest request,
                      HttpSession session,
                      String checkCode){
        // 获得验证码对象
        Object cko = session.getAttribute("simpleCaptcha");
        if (cko == null) {
            request.setAttribute("errorMsg", "Please input verification code！");
            return "Please input verification code！";
        }
        String captcha = cko.toString();
        // 验证码有效时长为1分钟
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
        // 判断验证码输入是否正确
        if (StringUtils.isEmpty(checkCode) || captcha == null || !(checkCode.equalsIgnoreCase(captcha))) {
            request.setAttribute("errorMsg", "code error！");
            return "code error，Please input verification code！";
        } else if ((now.getTime()- codeTime) / 1000 / 60 > 1) {
            request.setAttribute("errorMsg", "Verification code is invalid, please re-enter ！");
            return "Verification code is invalid, please re-enter ！";
        }
        return "ok";
    }
}
