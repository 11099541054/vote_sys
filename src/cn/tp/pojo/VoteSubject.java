package cn.tp.pojo;

import java.util.List;

public class VoteSubject {
	private Integer vsId;
	private String vsTitle;
	private Integer vsType;
	private List<VoteOption> options;
	public VoteSubject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VoteSubject(String vsTitle, Integer vsType) {
		super();
		this.vsTitle = vsTitle;
		this.vsType = vsType;
	}
	
	
	public VoteSubject(Integer vsId, String vsTitle, Integer vsType) {
		super();
		this.vsId = vsId;
		this.vsTitle = vsTitle;
		this.vsType = vsType;
	}
	public Integer getVsId() {
		return vsId;
	}
	public void setVsId(Integer vsId) {
		this.vsId = vsId;
	}
	public String getVsTitle() {
		return vsTitle;
	}
	public void setVsTitle(String vsTitle) {
		this.vsTitle = vsTitle;
	}
	public Integer getVsType() {
		return vsType;
	}
	public void setVsType(Integer vsType) {
		this.vsType = vsType;
	}
	public List<VoteOption> getOptions() {
		return options;
	}
	public void setOptions(List<VoteOption> options) {
		this.options = options;
	}
	
}
