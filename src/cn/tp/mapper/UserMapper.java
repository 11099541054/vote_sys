package cn.tp.mapper;

import cn.tp.pojo.User;

public interface UserMapper {
	//�����û��������û���Ϣ
	public User findByName(String username);
	//ע����Ӵ��û�
	public int add(User user);

}
