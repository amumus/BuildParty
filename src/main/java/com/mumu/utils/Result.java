package com.mumu.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 该类是所有请求返回的类，返回前需要转成json
 * code是状态码，0表示失败，1表示成功，请求服务200为成功，500为服务出错。
 * date用来储存数据，存放message、id等返回信息
 * @author mumu
 *
 */
public class Result implements Serializable{
	public int code;
	public Map data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Map getData() {
		return data;
	}
	public void setData(Map data) {
		this.data = data;
	}
	public static Result success(){
		Result r = new Result();
		r.setCode(200);
		Map m = new HashMap();
		m.put("message", "success");
		r.setData(m);
		return r;
	}
	public static Result error() {
		Result r = new Result();
		r.setCode(500);
		Map m = new HashMap();
		m.put("message", "error");
		r.setData(m);
		return null;
	}
}
