package com.resjz.web.bean;

import java.util.HashMap;
import java.util.Map;

public class Json  extends HashMap<String, Object> {
        private static final long serialVersionUID = 1L;

        public Json() {
            put("code", 0);
            put("msg", "success");
        }

        public static Json error() {
            return error(500, "未知异常，请联系管理员");
        }

        public static Json error(String msg) {
            return error(500, msg);
        }

        public static Json error(int code, String msg) {
            Json r = new Json();
            r.put("code", code);
            r.put("msg", msg);
            return r;
        }

        public static Json ok(String msg) {
            Json r = new Json();
            r.put("msg", msg);
            return r;
        }

        public static Json ok(Map<String, Object> map) {
            Json r = new Json();
            r.putAll(map);
            return r;
        }

        public static Json ok() {
            return new Json();
        }

        @Override
        public Json put(String key, Object value) {
            super.put(key, value);
            return this;
        }


}
