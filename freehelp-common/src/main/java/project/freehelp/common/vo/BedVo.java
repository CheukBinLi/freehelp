package project.freehelp.common.vo;

public class BedVo extends AbstractAuthorityVo {

	private transient static final long serialVersionUID = 1L;

	private int id;
	private int count;

	public int getId() {
		return id;
	}

	public BedVo setId(int id) {
		this.id = id;
		return this;
	}

	public int getCount() {
		return count;
	}

	public BedVo setCount(int count) {
		this.count = count;
		return this;
	}

}
