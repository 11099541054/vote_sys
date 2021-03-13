package cn.tp.service;

import java.util.List;

import cn.tp.pojo.OptionBean;
import cn.tp.pojo.VoteBean;
import cn.tp.pojo.VoteSubject;

public interface VoteService {
	public boolean addVote(VoteSubject vs,String[] options);
	public List<VoteBean> finVotes();
	public VoteSubject showSubject(Integer vsId);
	public boolean findByTitle(String subjectTitle);
	public int vote(Integer vsId, String[] voIds,Integer vuId);
	public List<OptionBean> view(Integer vsId);
	public int edit(VoteSubject vs,String[] options);
	//¸ù¾Ýtitle²éÑ¯
	public  List<VoteBean> query(String vsTitle);
}
