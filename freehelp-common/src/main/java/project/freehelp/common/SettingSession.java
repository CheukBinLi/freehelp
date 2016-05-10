package project.freehelp.common;

import javax.servlet.http.HttpServletRequest;

import project.freehelp.common.entity.UserInfo;
import project.master.user.User;

public class SettingSession implements SessionType {

	public void SettionSession(HttpServletRequest request, User user, UserInfo userInfo) {
		request.getSession().setAttribute("userID", user.getId());
		request.getSession().setAttribute("userName", user.getPhone());
		request.getSession().setAttribute("userName", user.getPhone());
	}

	public boolean isLogin(HttpServletRequest request) {
		return null != request.getSession().getAttribute(USER_ID);
	}
}
