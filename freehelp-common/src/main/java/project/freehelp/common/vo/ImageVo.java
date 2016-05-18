package project.freehelp.common.vo;

public class ImageVo extends AbstractAuthorityVo {

	private transient static final long serialVersionUID = 1L;

	private String src;

	public String getSrc() {
		return src;
	}

	public ImageVo setSrc(String src) {
		this.src = src;
		return this;
	}

	public ImageVo(String src) {
		super();
		this.src = src;
	}

	public ImageVo() {
		super();
	}

}
