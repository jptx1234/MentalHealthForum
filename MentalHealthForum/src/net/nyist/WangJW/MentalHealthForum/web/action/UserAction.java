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
	
	public String getLoginInfo(){
		User user = CommonUtils.getLoginUser();
		JSONObject jsonObject = new JSONObject();
		if (user == null) {
			jsonObject.put("status", 0);
		}else {
			jsonObject.put("status", 1);
			jsonObject.put("username", user.getUsername());
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
	
	
	
}
