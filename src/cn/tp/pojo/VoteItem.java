package cn.tp.pojo;

public class VoteItem {
	private Integer viId;
	private Integer vsId;
	private Integer voId;
	private Integer vuId;
	
	
	public VoteItem() {
		super();
	}
	
	public VoteItem(Integer vsId, Integer vuId) {
		super();
		this.vsId = vsId;
		this.vuId = vuId;
	}
	
	public VoteItem(Integer vsId, Integer voId, Integer vuId) {
		super();
		this.vsId = vsId;
		this.voId = voId;
		this.vuId = vuId;
	}

	public Integer getViId() {
		return viId;
	}
	public void setViId(Integer viId) {
		this.viId = viId;
	}
	public Integer getVsId() {
		return vsId;
	}
	public void setVsId(Integer vsId) {
		this.vsId = vsId;
	}
	public Integer getVoId() {
		return voId;
	}
	public void setVoId(Integer voId) {
		this.voId = voId;
	}
	public Integer getVuId() {
		return vuId;
	}
	public void setVuId(Integer vuId) {
		this.vuId = vuId;
	}
	
	

}
