package net.nyist.WangJW.MentalHealthForum.domain;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class ResultObject implements Serializable{
	private static final long serialVersionUID = 1L;
	private JSONObject jsonObject;
	public ResultObject(){
		jsonObject = new JSONObject();
	}
	public void setStatus(int status){
		jsonObject.put("status", status);
	}
	public void setMsg(String msg){
		jsonObject.put("msg", msg);
	}
	public Integer getStatus(){
		return (Integer) jsonObject.get("status");
	}
	public String getMsg(){
		return jsonObject.getString("msg");
	}
	@Override
	public String toString() {
		return jsonObject.toJSONString();
	}
}