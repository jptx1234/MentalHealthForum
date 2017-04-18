package net.nyist.WangJW.MentalHealthForum.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.nyist.WangJW.MentalHealthForum.domain.User;
import net.nyist.WangJW.MentalHealthForum.service.IUserService;
import net.nyist.WangJW.MentalHealthForum.utils.CommonUtils;
import net.nyist.WangJW.MentalHealthForum.web.action.base.BaseAction;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	@Resource
	private IUserService userService;
	
	public String login(){
		User loginUser = userService.login(model);
		if (loginUser == null) {
			resultObject.setStatus(0);
			resultObject.setMsg("用户名或密码错误");
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
			resultObject.setStatus(1);
			resultObject.setMsg("登录成功，欢迎回来，"+loginUser.getUsername());
		}
		responseResultObject();
		
		return NONE;
	}
	
	public String getUserInfo(){
		try {
//			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = CommonUtils.getLoginUser();
		JSONObject jsonObject = new JSONObject();
		if (user == null) {
			jsonObject.put("status", 0);
		}else {
			jsonObject.put("status", 1);
			jsonObject.put("username", user.getUsername());
			jsonObject.put("isAdmin", user.getIsAdmin());
			jsonObject.put("id", user.getId());
		}
		responseJson(jsonObject.toJSONString());
		
		return NONE;
	}
	
	public String regist(){
		if (StringUtils.isBlank(model.getUsername())) {
			resultObject.setStatus(0);
			resultObject.setMsg("用户名不能为空");
		}else if(StringUtils.isBlank(model.getPassword())){
			resultObject.setStatus(0);
			resultObject.setMsg("密码不能为空");
		}else {
			try {
				userService.save(model);
				resultObject.setStatus(1);
				resultObject.setMsg("注册成功，请前去登录");
			} catch (Exception e) {
				resultObject.setStatus(0);
				resultObject.setMsg("注册失败，服务器异常");
			}
		}
		responseResultObject();
		
		return NONE;
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		resultObject.setStatus(1);
		responseResultObject();
		
		return NONE;
	}
	
	public String forbitUser(){
		User user = CommonUtils.getLoginUser();
		if (user == null || !user.getIsAdmin()) {
			responseResultObject(0, "无操作权限");
			return NONE;
		}
		if (model.getId() == null) {
			responseResultObject(0, "用户参数传递失败");
			return NONE;
		}
		if (user.getStatus() == 0) {
			responseResultObject(0, "您已被封禁，无法操作");
			return NONE;
		}
		try {
			userService.forbit(model.getId());
			responseResultObject(1, "成功封禁此用户");
		} catch (Exception e) {
			responseResultObject(1, "封禁时出现异常");
		}
		
		return NONE;
	}
	
	
	
}
