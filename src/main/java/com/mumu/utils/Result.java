package com.mumu.utils;

import java.io.Serializable;
import java.util.Map;
/**
 * 该类是所有请求返回的类，返回前需要转成json
 * code是状态码，0表示失败，1表示成功
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
	
}
