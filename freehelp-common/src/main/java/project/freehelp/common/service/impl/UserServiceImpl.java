package project.freehelp.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.freehelp.common.dao.UserDao;
import project.freehelp.common.service.UserService;
import project.master.dbmaamger.dao.BaseDao;
import project.master.dbmaamger.service.AbstractService;
import project.master.user.User;

@Component
public class UserServiceImpl extends AbstractService<User, String> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public BaseDao<User, String> getService() {
		return userDao;
	}

	public List<Object[]> getListX(String queueName, Map<String, Object> params) throws Throwable {
		return userDao.getList(queueName, params, true, -1, -1);
	}

	public int getCheckedUserName(Map<String, Object> params) throws Throwable {
		Object o;
		o = userDao.uniqueResult("checkUser", false, params);
		if (null != o) {
			return Integer.valueOf(o.toString());
		}
		throw new Exception("查询失败");
	}

	public User getlogin(Map<String, Object> params) throws Throwable {
		List<User> list = userDao.getListEntity("login", params, false, -1, -1);
		if (null != list && list.size() > 0)
			return list.get(0);
		throw new Exception("登录失败");
	}
}
