package net.nyist.WangJW.MentalHealthForum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nyist.WangJW.MentalHealthForum.dao.IBoardDao;
import net.nyist.WangJW.MentalHealthForum.domain.Board;
import net.nyist.WangJW.MentalHealthForum.service.IBoardService;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements IBoardService {

	@Resource
	private IBoardDao boardDao;

	@Override
	public Board findById(Long id) {
		return boardDao.findById(id);
	}
	
}
