package project.freehelp.common;

import javax.servlet.http.HttpServletRequest;

import project.freehelp.common.entity.UserInfo;
import project.freehelp.common.vo.WeiXin;
import project.master.fw.sh.common.JsonObjectFactory;
import project.master.user.User;

public class SettingSession implements SessionType, Constant {

	public void SettionSession(HttpServletRequest request, User user, UserInfo userInfo) {
		request.getSession().setAttribute(USER_ID, user.getId());
		if (null != userInfo.getWeixin()) {
			WeiXin weiXin = JsonObjectFactory.newInstance().toObject(userInfo.getWeixin(), WeiXin.class, false);
			request.getSession().setAttribute(USER_NAME, weiXin.getName());
		} else
			request.getSession().setAttribute(USER_NAME, userInfo.getId());
		request.getSession().setAttribute(STEWARD, userInfo.getSteward());
		request.getSession().setAttribute(MASTER, userInfo.getMaster());
	}

	public boolean isLogin(HttpServletRequest request) {
		return null != request.getSession().getAttribute(USER_ID);
	}

	public boolean isMaster(HttpServletRequest request) {
		try {
			return STATUS_TYPE_SUCCESS == Integer.valueOf(request.getSession().getAttribute(MASTER).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isSteward(HttpServletRequest request) {
		try {
			return STATUS_TYPE_SUCCESS == Integer.valueOf(request.getSession().getAttribute(STEWARD).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}
}
