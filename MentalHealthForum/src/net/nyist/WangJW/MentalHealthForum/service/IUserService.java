package net.nyist.WangJW.MentalHealthForum.service;

import net.nyist.WangJW.MentalHealthForum.domain.User;

public interface IUserService {

	User login(User model);

	void save(User model);

}
