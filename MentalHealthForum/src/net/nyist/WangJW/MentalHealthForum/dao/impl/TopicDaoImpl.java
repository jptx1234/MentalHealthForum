package net.nyist.WangJW.MentalHealthForum.dao.impl;

import org.springframework.stereotype.Repository;

import net.nyist.WangJW.MentalHealthForum.dao.ITopicDao;
import net.nyist.WangJW.MentalHealthForum.dao.base.BaseDaoImpl;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;

@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements ITopicDao {

}
