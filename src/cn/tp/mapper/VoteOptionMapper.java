package cn.tp.mapper;

import java.util.List;


import cn.tp.pojo.VoteOption;

public interface VoteOptionMapper {
	public int addOption(VoteOption vo);
	public int addOptions(List<VoteOption> list);
	public int delOptions(int vsId);
}
