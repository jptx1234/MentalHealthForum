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
import net.nyist.WangJW.MentalHealthForum.service.IBoardService;
import net.nyist.WangJW.MentalHealthForum.service.ITopicService;
import net.nyist.WangJW.MentalHealthForum.service.IUserService;

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
	@Resource
	private IBoardService boardService;
	@Resource
	private IUserService userService;
	
	@Test
	public void test2(){
		Topic topic = topicService.findById(1L);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Topic.class);
		
		System.out.println(JSONObject.toJSONString(topic,filter));
		
	}
	
	@Test
	public void test3(){
		Board board = boardService.findById(1L);
		User user = userService.findById(1L);
		for (int i = 0; i < 10000; i++) {
			Topic topic = new Topic();
			topic.setBoard(board);
			topic.setTitle("第"+i+"条帖子");
			topic.setUser(user);
			topic.setContent("");
			topicService.save(topic);
		}
	}

}
