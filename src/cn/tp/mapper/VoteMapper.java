package cn.tp.mapper;

import java.util.List;

import cn.tp.pojo.OptionBean;
import cn.tp.pojo.VoteBean;

public interface VoteMapper {
	//��ѯ����ͶƱ
	public List<VoteBean> findVote();
	//��ѯ����ͶƱѡ����Ϣ
	public List<OptionBean> findOption(Integer vsId);
	//����������ѯ
	public List<VoteBean> findByTitle(String vsTitle);
}
