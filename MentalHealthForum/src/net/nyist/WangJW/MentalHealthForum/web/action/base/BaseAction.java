package net.nyist.WangJW.MentalHealthForum.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONWriter;
import org.hibernate.criterion.DetachedCriteria;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.nyist.WangJW.MentalHealthForum.domain.PageBean;
import net.nyist.WangJW.MentalHealthForum.domain.ResultObject;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;
	protected T model;
	protected PageBean pageBean;
	protected ResultObject resultObject = new ResultObject();

	
	@Override
	public T getModel() {
		return model;
	}
	
	public void setPage(int page){
		pageBean.setCurrentPage(page);
	}
	
	public void setRows(int rows){
		pageBean.setPageSize(rows);
	}
	

	@SuppressWarnings("unchecked")
	public BaseAction(){
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		try {
			Class<T> modelClass = (Class<T>) superClass.getActualTypeArguments()[0];
			model = modelClass.newInstance();
			pageBean = new PageBean();
			pageBean.setDetachedCriteria(DetachedCriteria.forClass(modelClass));
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			throw new RuntimeException("实例化 BaseAction 时泛型不正确",e);
		} catch (InstantiationException e) {
			throw new RuntimeException("实例化模型类时失败",e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("模型类无参构造无法访问", e);
		}
	}
	
	protected void responseJson(String json) {
		try {
			ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void responseResultObject() {
		try {
			String json = null;
			if (resultObject == null) {
				json = "";
			}else {
				json = resultObject.toString();
			}
			responseJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void responseResultObject(int status,String msg){
		resultObject.setStatus(status);
		resultObject.setMsg(msg);
		responseResultObject();
	}
	
	
	
	
	protected void responsePageJson(SerializeFilter... filters){
		String json = JSON.toJSONString(pageBean, filters);
		responseJson(json);
	}
	
	protected void responseJsonArray(Iterable<? extends Object> array, String... excludes){
	}
	
	protected void responseJsonObject(Object object, String... excludes){
	}
	
}
