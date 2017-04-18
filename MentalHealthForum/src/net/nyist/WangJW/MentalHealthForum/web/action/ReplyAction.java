package net.nyist.WangJW.MentalHealthForum.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.nyist.WangJW.MentalHealthForum.domain.Reply;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;
import net.nyist.WangJW.MentalHealthForum.domain.User;
import net.nyist.WangJW.MentalHealthForum.service.IReplyService;
import net.nyist.WangJW.MentalHealthForum.service.ITopicService;
import net.nyist.WangJW.MentalHealthForum.service.IUserService;
import net.nyist.WangJW.MentalHealthForum.utils.CommonUtils;
import net.nyist.WangJW.MentalHealthForum.web.action.base.BaseAction;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	@Resource
	private IReplyService replyService;
	@Resource
	private IUserService userService;
	@Resource
	private ITopicService topicService;
	
	public String saveReply(){
		User user = CommonUtils.getLoginUser();
		if (user == null) {
			responseResultObject(0, "用户尚未登录");
			return NONE;
		}
		user = userService.findById(user.getId());
		if (user.getStatus() == 0) {
			responseResultObject(0, "用户被封禁，无法回复");
			return NONE;
		}
		if (StringUtils.isBlank(model.getContent())) {
			responseResultObject(0, "内容不能为空");
			return NONE;
		}
		if (model.getTopic() == null || model.getTopic().getId() == null) {
			responseResultObject(0, "获取主题帖信息失败，请刷新网页后重新回复");
			return NONE;
		}
		User author = userService.findById(user.getId());
		model.setUser(author);
		Topic topic = topicService.findById(model.getTopic().getId());
		Integer floor = topic.getRepliesCount() + 2;
		System.out.println(floor);
		model.setFloor(floor);
		replyService.save(model);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("msg", "回复成功，即将重新加载本贴");
		responseJson(jsonObject.toJSONString());
		
		return NONE;
	}
	
	public String deleteReply(){
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
			replyService.delete(model.getId());
			responseResultObject(1, "删帖成功");
		} catch (Exception e) {
			responseResultObject(0, "删帖失败，请稍后重试");
		}
		return NONE;
	}
	
}
