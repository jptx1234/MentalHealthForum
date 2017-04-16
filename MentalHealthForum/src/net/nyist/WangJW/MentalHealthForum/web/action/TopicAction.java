package net.nyist.WangJW.MentalHealthForum.web.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
		String json = JSON.toJSONString(topic,new SerializeFilter[]{filter,boardFilter});
		responseJson(json);
		
		return NONE;
	}
	
	private final Integer DEFAULT_PAGE_SIZE = 10;
	
	public String listTopicsOfBoard(){
		if (model.getBoard() == null || model.getBoard().getId() == null) {
			ServletActionContext.getResponse().setStatus(204);
			return NONE;
		}
		pageBean.setCurrentPage(Math.max(pageBean.getCurrentPage(), 1));
		pageBean.setPageSize(DEFAULT_PAGE_SIZE);
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		DetachedCriteria boardDc = dc.createCriteria("board");
		boardDc.add(Restrictions.eq("id", model.getBoard().getId()));
		topicService.pageQuery(pageBean);
		SimplePropertyPreFilter topicFilter = new SimplePropertyPreFilter(Topic.class, "id","user","title","time","repliesCount");
		SimplePropertyPreFilter userFilter = new SimplePropertyPreFilter(User.class, "username");
		responsePageJson(topicFilter,userFilter);
		
		return NONE;
	}
	
}
