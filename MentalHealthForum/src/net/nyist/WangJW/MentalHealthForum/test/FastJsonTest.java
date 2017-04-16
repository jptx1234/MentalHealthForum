package net.nyist.WangJW.MentalHealthForum.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import net.nyist.WangJW.MentalHealthForum.domain.Board;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;
import net.nyist.WangJW.MentalHealthForum.domain.User;
import net.nyist.WangJW.MentalHealthForum.service.ITopicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FastJsonTest {

	@Test
	public void test() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("k", "vvv");
		System.out.println(jsonObject.toJSONString());
	}
	
	@Resource
	private ITopicService topicService;
	
	@Test
	public void test2(){
		Topic topic = topicService.findById(1L);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Topic.class);
		
		System.out.println(JSONObject.toJSONString(topic,filter));
		
	}
	
	@Test
	public void test3(){
	}

}
