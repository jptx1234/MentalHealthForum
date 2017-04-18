package net.nyist.WangJW.MentalHealthForum.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		DetachedCriteria dc = DetachedCriteria.forClass(Topic.class);
		dc.add(Restrictions.eq("id", i)); 
		dc.add(Restrictions.eq("status", (short)0));
		List<Topic> list = topicDao.findByCriteria(dc);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
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

	@Override
	public void delete(Long id) {
		Topic topic = topicDao.findById(id);
		topic.setStatus((short) 1);
	}

	@Override
	public void addLabel(Long id, String label) {
		Topic topic = topicDao.findById(id);
		topic.setLabel(label);
	}

	
}
