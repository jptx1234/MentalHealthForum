package net.nyist.WangJW.MentalHealthForum.service;

import java.util.List;

import net.nyist.WangJW.MentalHealthForum.domain.PageBean;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;

public interface ITopicService {

	Topic findById(Long i);

	void pageQuery(PageBean pageBean);

	Long save(Topic model);

}
