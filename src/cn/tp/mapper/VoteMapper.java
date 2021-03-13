package cn.tp.mapper;

import java.util.List;

import cn.tp.pojo.OptionBean;
import cn.tp.pojo.VoteBean;

public interface VoteMapper {
	//查询所有投票
	public List<VoteBean> findVote();
	//查询所有投票选项信息
	public List<OptionBean> findOption(Integer vsId);
	//根据条件查询
	public List<VoteBean> findByTitle(String vsTitle);
}
