package net.nyist.WangJW.MentalHealthForum.service;

import net.nyist.WangJW.MentalHealthForum.domain.PageBean;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;

public interface ITopicService {

	Topic findById(Long i);

	void pageQuery(PageBean pageBean);

	Long save(Topic model);

	void delete(Long id);

	void addLabel(Long id, String label);

}
