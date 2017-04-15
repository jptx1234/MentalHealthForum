package net.nyist.WangJW.MentalHealthForum.web.action;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import net.nyist.WangJW.MentalHealthForum.domain.Board;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;
import net.nyist.WangJW.MentalHealthForum.domain.User;
import net.nyist.WangJW.MentalHealthForum.service.ITopicService;
import net.nyist.WangJW.MentalHealthForum.web.action.base.BaseAction;

@Controller("topicAction")
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	static{
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
	}
	
	@Resource
	private ITopicService topicService;
	
	
	public String findTopicById(){
		Topic topic = topicService.findById(model.getId());
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class);
		Set<String> excludes = filter.getExcludes();
		excludes.add("replies");
		excludes.add("topics");
		SimplePropertyPreFilter boardFilter = new SimplePropertyPreFilter(Board.class,"id","name");
		String json = JSONObject.toJSONString(topic,new SerializeFilter[]{filter,boardFilter});
		responseJson(json);
		
		return NONE;
	}
	
}
