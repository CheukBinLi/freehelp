package project.freehelp.common;

public interface Constant {

	/***
	 * 临时
	 */
	final String ACCESS_UPLOAD_TEMP_PATH = "resource/temp/";

	final String UPLOAD_TEMP_PATH = "WEB-INF/resource/temp/";

	/***
	 * 身份证图
	 */
	final String ID_CARD_IMAGE_PATH = "WEB-INF/resource/upload/idcard/";

	final String ACCESS_CARD_IMAGE_PATH = "/resource/upload/idcard/";

	/***
	 * 房源环境图
	 */
	final String HOUSE_IMAGE_PATH = "WEB-INF/resource/upload/house/";

	final String ACCESS_HOUSE_IMAGE_PATH = "/resource/upload/house/";

	/***
	 * 初始化
	 */
	final int STATUS_TYPE_INIT = 0;
	/***
	 * 等待审批
	 */
	final int STATUS_TYPE_WAIT = 1;
	/***
	 * 通过
	 */
	final int STATUS_TYPE_SUCCESS = 2;
	/***
	 * 不通过
	 */
	final int STATUS_TYPE_FAIL = -1;

}
