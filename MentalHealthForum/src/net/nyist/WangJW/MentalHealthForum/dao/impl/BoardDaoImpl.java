package net.nyist.WangJW.MentalHealthForum.dao.impl;

import org.springframework.stereotype.Repository;

import net.nyist.WangJW.MentalHealthForum.dao.IBoardDao;
import net.nyist.WangJW.MentalHealthForum.dao.base.BaseDaoImpl;
import net.nyist.WangJW.MentalHealthForum.domain.Board;

@Repository("boardDao")
public class BoardDaoImpl extends BaseDaoImpl<Board> implements IBoardDao {

}
