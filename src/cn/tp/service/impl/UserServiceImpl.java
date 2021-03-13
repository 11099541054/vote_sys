 package cn.tp.service.impl;

import org.apache.ibatis.session.SqlSession;

import cn.tp.mapper.UserMapper;
import cn.tp.pojo.User;
import cn.tp.service.UserService;
import cn.tp.utils.MybatisUtil;

public class UserServiceImpl implements UserService {
	private SqlSession sqlSession;
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User userDB = userMapper.findByName(user.getUsername());
		if(userDB != null) {
			if(!user.getPassword().equals(userDB.getPassword())) {
				userDB.setPassword(null);
			}
			return userDB;
		}else {
			return userDB;
		}	
	}
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		return userMapper.add(user) > 0;
	}
	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		return userMapper.findByName(username);
	}
}
