package cn.tp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.tp.mapper.VoteItemMapper;
import cn.tp.mapper.VoteMapper;
import cn.tp.mapper.VoteOptionMapper;
import cn.tp.mapper.VoteSubjectMapper;
import cn.tp.pojo.OptionBean;
import cn.tp.pojo.VoteBean;
import cn.tp.pojo.VoteItem;
import cn.tp.pojo.VoteOption;
import cn.tp.pojo.VoteSubject;
import cn.tp.service.VoteService;
import cn.tp.utils.MybatisUtil;

public class VoteServiceImpl implements VoteService {
	private SqlSession sqlSession;
    //���ͶƱ��Ϣ
	@Override
	public boolean addVote(VoteSubject vs,String[] options) {
	
		int row = 0;
		sqlSession = MybatisUtil.getSqlSession();
		try {
			VoteSubjectMapper vsmapper = sqlSession.getMapper(VoteSubjectMapper.class);
			//��vote_subject���������
			if (vsmapper.addSubject(vs) > 0) {
				VoteOptionMapper vomapper = sqlSession.getMapper(VoteOptionMapper.class);
				List<VoteOption> list = new ArrayList<>();
				for (String option : options) {
					//ͨ���������vote_subject�е�voteSubject�����ȡvdId
					/*ע�⣺
						��<insert id="addSubject" parameterType="VoteSubject" useGeneratedKeys="true" keyProperty="vsId">
						������useGeneratedKeys="true" keyProperty="vsId" ���ԣ��Ϳ��Ի�ȡ���¼ӽ�ȥ���ݵ�id
					*/
					list.add(new VoteOption(option, vs.getVsId()));
				}
				//��vote_option�����������
				row = vomapper.addOptions(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
			//�������ݷ����쳣����������ع�����
			sqlSession.rollback();
		}
		
		return row > 0 ;
	}
	//��ѯͶƱ��Ϣ
	@Override
	public List<VoteBean> finVotes() {
		sqlSession = MybatisUtil.getSqlSession();
		VoteMapper mapper = sqlSession.getMapper(VoteMapper.class);
		return mapper.findVote();
	}
	@Override
	public VoteSubject showSubject(Integer vsId) {
		sqlSession = MybatisUtil.getSqlSession();
		VoteSubjectMapper mapper = sqlSession.getMapper(VoteSubjectMapper.class);
		return mapper.findById(vsId);
	}
	@Override
	public boolean findByTitle(String vsTitle) {
		sqlSession = MybatisUtil.getSqlSession();
		VoteSubjectMapper mapper = sqlSession.getMapper(VoteSubjectMapper.class);
		return mapper.findByTitle(vsTitle)> 0;
	}
	@Override
	public int vote(Integer vsId,  String[] voIds,Integer vuId) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		VoteItemMapper mapper = sqlSession.getMapper(VoteItemMapper.class);
		VoteItem item = new VoteItem(vsId,vuId);
		if(mapper.findItem(item)==0) {
			List<VoteItem> items = new ArrayList<>();
			for(int i=0;i<voIds.length;i++) {
				items.add(new VoteItem(vsId,Integer.parseInt(voIds[i]),vuId));
			}
			return mapper.addItems(items);
		}else {
			return -1;//�Ѿ�������ͶƱ
		}
		
		
	}
	@Override
	public List<OptionBean> view(Integer vsId) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		VoteMapper mapper = sqlSession.getMapper(VoteMapper.class);
		List<OptionBean> optionBeans = mapper.findOption(vsId);
		int total = 0;
		for(OptionBean ob:optionBeans) {
			total+=ob.getOptionAmount();
		}
		for(OptionBean ob:optionBeans) {
			if(total != 0) {
				ob.setPercent(ob.getOptionAmount()*100.0/total);
			}
		}
		return optionBeans;
	}
	@Override
	public int edit(VoteSubject vs, String[] options) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		VoteSubjectMapper subjectMapper = sqlSession.getMapper(VoteSubjectMapper.class);
		VoteItemMapper itemMapper = sqlSession.getMapper(VoteItemMapper.class);
		VoteOptionMapper optionMapper = sqlSession.getMapper(VoteOptionMapper.class);
		try {
			//�޸�ͶƱ����
			subjectMapper.updateSubject(vs);
			//ɾ��ͶƱ��Ϣ
			itemMapper.delItem(vs.getVsId());
			//ɾ��ͶƱѡ��
			optionMapper.delOptions(vs.getVsId());
			//���ͶƱѡ��
			List<VoteOption> list = new ArrayList<>();
			for (String option : options) {
				list.add(new VoteOption(option, vs.getVsId()));
			}
			return  optionMapper.addOptions(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		return 0;
	}
	@Override
	public List<VoteBean> query(String vsTitle) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtil.getSqlSession();
		VoteMapper mapper = sqlSession.getMapper(VoteMapper.class);
		return mapper.findByTitle(vsTitle);
	}
    
}
