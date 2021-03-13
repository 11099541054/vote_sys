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
    //添加投票信息
	@Override
	public boolean addVote(VoteSubject vs,String[] options) {
	
		int row = 0;
		sqlSession = MybatisUtil.getSqlSession();
		try {
			VoteSubjectMapper vsmapper = sqlSession.getMapper(VoteSubjectMapper.class);
			//向vote_subject中添加数据
			if (vsmapper.addSubject(vs) > 0) {
				VoteOptionMapper vomapper = sqlSession.getMapper(VoteOptionMapper.class);
				List<VoteOption> list = new ArrayList<>();
				for (String option : options) {
					//通过插入插入vote_subject中的voteSubject对象获取vdId
					/*注意：
						在<insert id="addSubject" parameterType="VoteSubject" useGeneratedKeys="true" keyProperty="vsId">
						配置了useGeneratedKeys="true" keyProperty="vsId" 属性，就可以获取到新加进去数据的id
					*/
					list.add(new VoteOption(option, vs.getVsId()));
				}
				//向vote_option表中添加数据
				row = vomapper.addOptions(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
			//插入数据发生异常，进行事务回滚操作
			sqlSession.rollback();
		}
		
		return row > 0 ;
	}
	//查询投票信息
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
			return -1;//已经参与了投票
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
			//修改投票内容
			subjectMapper.updateSubject(vs);
			//删除投票信息
			itemMapper.delItem(vs.getVsId());
			//删除投票选项
			optionMapper.delOptions(vs.getVsId());
			//添加投票选项
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
