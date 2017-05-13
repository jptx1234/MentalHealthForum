package net.nyist.WangJW.MentalHealthForum.web.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import net.nyist.WangJW.MentalHealthForum.service.IUserService;
import net.nyist.WangJW.MentalHealthForum.utils.CommonUtils;
import net.nyist.WangJW.MentalHealthForum.web.action.base.BaseAction;

@Controller("topicAction")
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	static{
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
	}
	
	@Resource
	private ITopicService topicService;
	@Resource
	private IUserService userService;
	
	
	public String findTopicById(){
		Topic topic = topicService.findById(model.getId());
		if (topic.getAnonymous() == null || topic.getAnonymous()) {
			topic.setUser(User.AnonymousUser);
		}
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
		dc.add(Restrictions.eq("status", (short)0));
		DetachedCriteria boardDc = dc.createCriteria("board");
		boardDc.add(Restrictions.eq("id", model.getBoard().getId()));
		topicService.pageQuery(pageBean);
		for (Object topicObject : pageBean.getRows()) {
			Topic topic = (Topic)topicObject;
			if (topic.getAnonymous() == null || topic.getAnonymous()) {
				topic.setUser(User.AnonymousUser);
			}
		}
		SimplePropertyPreFilter topicFilter = new SimplePropertyPreFilter(Topic.class, "id","user","title","time","repliesCount","label");
		SimplePropertyPreFilter userFilter = new SimplePropertyPreFilter(User.class, "username");
		responsePageJson(topicFilter,userFilter);
		
		return NONE;
	}
	
	public String saveTopic(){
		User user = CommonUtils.getLoginUser();
		if (user == null) {
			responseResultObject(0, "用户尚未登录");
			return NONE;
		}
		user = userService.findById(user.getId());
		if (user.getStatus() == 0) {
			responseResultObject(0, "用户被封禁，无法发帖");
			return NONE;
		}
		if (StringUtils.isBlank(model.getTitle())) {
			responseResultObject(0, "标题不能为空");
			return NONE;
		}
		if (StringUtils.isBlank(model.getContent())) {
			responseResultObject(0, "内容不能为空");
			return NONE;
		}
		if (model.getBoard() == null || model.getBoard().getId() == null) {
			responseResultObject(0, "获取板块信息失败，请刷新网页后重新发帖");
			return NONE;
		}
		User author = userService.findById(user.getId());
		model.setUser(author);
		Long topicId = topicService.save(model);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("msg", "发帖成功，即将跳转到此帖子页面");
		jsonObject.put("topicId", topicId);
		responseJson(jsonObject.toJSONString());
		
		return NONE;
	}
	
	public String deleteTopic(){
		User user = CommonUtils.getLoginUser();
		if (user != null) {
			user = userService.findById(user.getId());
		}
		if (user == null || !user.getIsAdmin() || user.getStatus() == 0) {
			responseResultObject(0, "无权删帖");
			return NONE;
		}
		if (model.getId() == null) {
			responseResultObject(0, "删帖参数传递失败");
			return NONE;
		}
		try {
			topicService.delete(model.getId());
			responseResultObject(1, "主题帖删除成功");
		} catch (Exception e) {
			responseResultObject(0, "删帖时出现异常");
		}
		
		return NONE;
	}
	
	public String addLabel(){
		User user = CommonUtils.getLoginUser();
		if (user != null) {
			user = userService.findById(user.getId());
		}
		if (user == null || !user.getIsAdmin() || user.getStatus() == 0) {
			responseResultObject(0, "无操作权限");
			return NONE;
		}
		if (model.getId() == null) {
			responseResultObject(0, "帖子参数传递失败");
			return NONE;
		}
		try {
			topicService.addLabel(model.getId(),model.getLabel());
			responseResultObject(1, "成功把帖子设置为："+model.getLabel());
		} catch (Exception e) {
			responseResultObject(0, "操作时出现异常");
		}
		
		return NONE;
	}
	
}
