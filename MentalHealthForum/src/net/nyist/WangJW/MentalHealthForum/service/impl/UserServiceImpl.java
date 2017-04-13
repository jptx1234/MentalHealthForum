package net.nyist.WangJW.MentalHealthForum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nyist.WangJW.MentalHealthForum.dao.IUserDao;
import net.nyist.WangJW.MentalHealthForum.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	
	
}
