package com.dwh.gyndowindback.exception;

import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.utils.ResultUtil;

import java.util.HashMap;

/**
 * @author: dwh
 **/
public class GyndowindException extends RuntimeException {

    private int code;
    private String message;
    private HashMap<String, Object> param;

    public GyndowindException(String message) {
        this.code = ResultUtil.ResponseEnum.FAIL.getType();
        this.message = message;
    }

    /***
     * 添加异常信息 键值对
     */
    public GyndowindException param(String key, Object value) {
        if (null == this.param) {
            this.param = new HashMap<>();
        }
        param.put(key, value);
        return this;
    }

    /***
     * 置空param
     */
    public GyndowindException empty() {
        this.param = new HashMap<>();
        return this;
    }

    public GyndowindException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String paramToString() {
        if (null == this.param || this.param.size() <= 0)
            return null;
        return JSONObject.toJSONString(this.param);
    }
}
