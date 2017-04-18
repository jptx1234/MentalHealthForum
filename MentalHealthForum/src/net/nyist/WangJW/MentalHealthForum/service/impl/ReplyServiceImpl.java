package net.nyist.WangJW.MentalHealthForum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nyist.WangJW.MentalHealthForum.dao.IReplyDao;
import net.nyist.WangJW.MentalHealthForum.domain.Reply;
import net.nyist.WangJW.MentalHealthForum.service.IReplyService;

@Service("replyService")
@Transactional
public class ReplyServiceImpl implements IReplyService {

	@Resource
	private IReplyDao replyDao;

	@Override
	public Long save(Reply model) {
		replyDao.save(model);
		return model.getId();
	}

	@Override
	public void delete(Long id) {
		Reply reply = replyDao.findById(id);
		reply.setStatus((short) 1);
	}
	
	
}
