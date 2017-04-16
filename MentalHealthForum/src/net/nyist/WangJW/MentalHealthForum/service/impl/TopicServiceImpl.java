package net.nyist.WangJW.MentalHealthForum.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nyist.WangJW.MentalHealthForum.dao.ITopicDao;
import net.nyist.WangJW.MentalHealthForum.domain.PageBean;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;
import net.nyist.WangJW.MentalHealthForum.service.ITopicService;

@Service("topicService")
@Transactional
public class TopicServiceImpl implements ITopicService {

	@Resource
	private ITopicDao topicDao;

	@Override
	public Topic findById(Long i) {
		return topicDao.findById(i);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		topicDao.pageQuery(pageBean);
	}

	@Override
	public Long save(Topic model) {
		topicDao.save(model);
		return model.getId();
	}

	
}
