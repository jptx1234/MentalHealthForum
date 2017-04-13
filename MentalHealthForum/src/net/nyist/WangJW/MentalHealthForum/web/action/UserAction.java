package net.nyist.WangJW.MentalHealthForum.web.action;

import javax.annotation.Resource;

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
		responseJson(resultObject);
		
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
	
	
	
	
}
