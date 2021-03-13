package cn.tp.service;

import cn.tp.pojo.User;

public interface UserService {

	public User login(User user);
	public boolean register(User user);
	public User findByName(String username);

}
