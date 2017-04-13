package net.nyist.WangJW.MentalHealthForum.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nyist.WangJW.MentalHealthForum.dao.IUserDao;
import net.nyist.WangJW.MentalHealthForum.domain.User;
import net.nyist.WangJW.MentalHealthForum.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public User login(User model) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", model.getUsername()));
		dc.add(Restrictions.eq("password", model.getPassword()));
		System.out.println(model.getUsername()+"---"+model.getPassword());
		List<User> list = userDao.findByCriteria(dc);
		if (list == null || list.size() != 1) {
			return null;
		}
		return list.get(0);
	}
	
	
	
}
