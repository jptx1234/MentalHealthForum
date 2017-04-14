package net.nyist.WangJW.MentalHealthForum.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.struts2.ServletActionContext;

import net.nyist.WangJW.MentalHealthForum.domain.User;

public class CommonUtils {
	
	public static User getLoginUser(){
		return  (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
	
	public static String sha256(String text){
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("sha-256").digest(
					text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有sha256这个算法！");
		}
		String sha256code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - sha256code.length(); i++) {
			sha256code = "0" + sha256code;
		}
		return sha256code;
	}
	
}
