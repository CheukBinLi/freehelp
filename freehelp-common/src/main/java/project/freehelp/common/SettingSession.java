package project.freehelp.common;

import javax.servlet.http.HttpServletRequest;

import project.freehelp.common.entity.UserInfo;
import project.freehelp.common.vo.UserInfoAuthorityVo;
import project.master.fw.sh.common.JsonObjectFactory;
import project.master.user.User;

public class SettingSession implements SessionType {

	public void SettionSession(HttpServletRequest request, User user, UserInfo userInfo) {
		request.getSession().setAttribute("userID", user.getId());
		request.getSession().setAttribute("userName", user.getPhone());
		request.getSession().setAttribute("userName", user.getPhone());
		request.getSession().setAttribute("userName", user.getPhone());
		request.getSession().setAttribute("freeHelpAuthority", JsonObjectFactory.newInstance().toObject(userInfo.getAuthority(), UserInfoAuthorityVo.class, false));
	}

	public boolean isLogin(HttpServletRequest request) {
		return null != request.getSession().getAttribute(USER_ID);
	}

	public boolean isMaster(HttpServletRequest request) {
		return 2 == ((UserInfoAuthorityVo) request.getSession().getAttribute(FREE_HELP_AUTHORITY)).getMaster();
	}

	public boolean isSteward(HttpServletRequest request) {
		return 2 == ((UserInfoAuthorityVo) request.getSession().getAttribute(FREE_HELP_AUTHORITY)).getSteward();
	}
}
