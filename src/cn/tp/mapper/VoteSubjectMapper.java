package cn.tp.mapper;

import cn.tp.pojo.VoteSubject;

public interface VoteSubjectMapper {
	public int findByTitle(String vsTitle);
	public int addSubject(VoteSubject vSubject);
	public VoteSubject findById(Integer vsId);
	public int updateSubject(VoteSubject vSubject);
}
