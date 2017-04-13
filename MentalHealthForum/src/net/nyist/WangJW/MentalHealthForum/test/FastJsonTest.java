package net.nyist.WangJW.MentalHealthForum.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class FastJsonTest {

	@Test
	public void test() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("k", "vvv");
		System.out.println(jsonObject.toJSONString());
	}

}
