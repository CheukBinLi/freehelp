package project.freehelp.common;

import javax.servlet.http.HttpServletRequest;

import project.freehelp.common.entity.UserInfo;
import project.master.user.User;

public class SettingSession implements SessionType {

	public void SettionSession(HttpServletRequest request, User user, UserInfo userInfo) {
		request.getSession().setAttribute(USER_ID, user.getId());
		request.getSession().setAttribute(USER_NAME, user.getPhone());
		request.getSession().setAttribute(STEWARD, userInfo.getSteward());
		request.getSession().setAttribute(MASTER, userInfo.getMaster());
	}

	public boolean isLogin(HttpServletRequest request) {
		return null != request.getSession().getAttribute(USER_ID);
	}

	public boolean isMaster(HttpServletRequest request) {
		try {
			return 2 == Integer.valueOf(request.getSession().getAttribute(MASTER).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isSteward(HttpServletRequest request) {
		try {
			return 2 == Integer.valueOf(request.getSession().getAttribute(STEWARD).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}
}
