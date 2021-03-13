package cn.tp.pojo;

public class VoteOption {
	private Integer voId;
	private String voOption;
	private Integer vsId;
	
	public VoteOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VoteOption(String voOption, Integer vsId) {
		super();
		this.voOption = voOption;
		this.vsId = vsId;
	}
	public Integer getVoId() {
		return voId;
	}
	public void setVoId(Integer voId) {
		this.voId = voId;
	}
	public String getVoOption() {
		return voOption;
	}
	public void setVoOption(String voOption) {
		this.voOption = voOption;
	}
	public Integer getVsId() {
		return vsId;
	}
	public void setVsId(Integer vsId) {
		this.vsId = vsId;
	}

	
	

}
