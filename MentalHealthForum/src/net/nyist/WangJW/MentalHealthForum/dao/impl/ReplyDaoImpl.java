package net.nyist.WangJW.MentalHealthForum.dao.impl;

import org.springframework.stereotype.Repository;

import net.nyist.WangJW.MentalHealthForum.dao.IReplyDao;
import net.nyist.WangJW.MentalHealthForum.dao.base.BaseDaoImpl;
import net.nyist.WangJW.MentalHealthForum.domain.Reply;

@Repository("replyDao")
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements IReplyDao {

}
