package net.nyist.WangJW.MentalHealthForum.utils;

import org.apache.struts2.ServletActionContext;

import net.nyist.WangJW.MentalHealthForum.domain.User;

public class CommonUtils {
	
	public static User getLoginUser(){
		return  (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
	
}
