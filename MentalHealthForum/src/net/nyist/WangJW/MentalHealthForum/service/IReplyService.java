package net.nyist.WangJW.MentalHealthForum.service;

import net.nyist.WangJW.MentalHealthForum.domain.Reply;

public interface IReplyService {

	Long save(Reply model);

	void delete(Long id);

}
