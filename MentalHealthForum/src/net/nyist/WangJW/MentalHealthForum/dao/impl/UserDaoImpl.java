package net.nyist.WangJW.MentalHealthForum.dao.impl;

import org.springframework.stereotype.Repository;

import net.nyist.WangJW.MentalHealthForum.dao.IUserDao;
import net.nyist.WangJW.MentalHealthForum.dao.base.BaseDaoImpl;
import net.nyist.WangJW.MentalHealthForum.domain.PageBean;
import net.nyist.WangJW.MentalHealthForum.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {


}
