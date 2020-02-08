package com.tianjs.chat_room.vo;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianjs.chat_room.enums.ErrorCodeEnum;

public class ResponseResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 417546153847635139L;

	private ResponseResult() {
	}

	public static ResponseResult systemError() {
		return error(ErrorCodeEnum.systemError);
	}

	public static ResponseResult error(ErrorCodeEnum errorCodeEnum) {
		return error("", errorCodeEnum);
	}

	public static ResponseResult error(String prefix, ErrorCodeEnum errorCodeEnum) {
		ResponseResult r = new ResponseResult();
		r.put("code", errorCodeEnum.getValue());
		r.put("msg", prefix + errorCodeEnum.getMessage());
		r.put("data", null);
		return r;
	}
	public static ResponseResult error(ErrorCodeEnum errorCodeEnum,String suffix) {
		ResponseResult r = new ResponseResult();
		r.put("code", errorCodeEnum.getValue());
		r.put("msg", errorCodeEnum.getMessage() + suffix);
		r.put("data", null);
		return r;
	}
	public static ResponseResult error(String message) {
		ResponseResult r = new ResponseResult();
		r.put("code", 9999);
		r.put("msg", message);
		r.put("data", null);
		return r;
	}

	public static ResponseResult ok() {
		return ok(null);
	}
	
	public static boolean isOk(ResponseResult responseResult) {
		if("0".equals(responseResult.get("code").toString())) {
			return true;
		}
		return false;
	}

	public static ResponseResult ok(Object data) {
		ResponseResult r = new ResponseResult();
		r.put("code", 0);
		r.put("data", data);
		r.put("msg", null);
		return r;
	}

	@Override
	public ResponseResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

}
