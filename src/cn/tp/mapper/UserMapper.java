package cn.tp.mapper;

import cn.tp.pojo.User;

public interface UserMapper {
	//根据用户名查找用户信息
	public User findByName(String username);
	//注册添加此用户
	public int add(User user);

}
