package project.freehelp.common.vo;

public class WeiXin extends AbstractAuthorityVo {

	private transient static final long serialVersionUID = 1L;

	private String name;
	private String head;

	public String getName() {
		return name;
	}

	public WeiXin setName(String name) {
		this.name = name;
		return this;
	}

	public String getHead() {
		return head;
	}

	public WeiXin setHead(String head) {
		this.head = head;
		return this;
	}

}
