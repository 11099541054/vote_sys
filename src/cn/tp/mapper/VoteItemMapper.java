package cn.tp.mapper;

import java.util.List;

import cn.tp.pojo.VoteItem;

public interface VoteItemMapper {
	//���ͶƱ��Ϣ
	public int addItems(List<VoteItem> items);
	//����ͶƱ
	public int findItem(VoteItem item);
	//ɾ��ͶƱ��Ϣ
	public int delItem(int vsId);
}
