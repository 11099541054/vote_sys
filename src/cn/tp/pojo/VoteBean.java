package cn.tp.pojo;

public class VoteBean {
	private Integer vsId;
	private String vsTitle;
	private Integer userCount;
	private Integer optionCount;
	
	public VoteBean(String vsTitle, Integer userCount, Integer optionCount) {
		super();
		this.vsTitle = vsTitle;
		this.userCount = userCount;
		this.optionCount = optionCount;
	}
	
	public VoteBean() {
		super();
		// TODO Auto-generated constructor stub
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
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Integer getOptionCount() {
		return optionCount;
	}
	public void setOptionCount(Integer optionCount) {
		this.optionCount = optionCount;
	}
	
	
}
