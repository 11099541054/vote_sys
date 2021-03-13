package cn.tp.mapper;

import java.util.List;

import cn.tp.pojo.VoteItem;

public interface VoteItemMapper {
	//添加投票信息
	public int addItems(List<VoteItem> items);
	//根据投票
	public int findItem(VoteItem item);
	//删除投票信息
	public int delItem(int vsId);
}
