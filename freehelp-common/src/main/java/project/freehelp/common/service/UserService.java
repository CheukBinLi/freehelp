package project.freehelp.common.service;

import java.util.List;
import java.util.Map;

import project.master.dbmaamger.service.BaseService;
import project.master.user.User;

public interface UserService extends BaseService<User, String> {

	public List<Object[]> getListX(String queueName, Map<String, Object> params) throws Throwable;

	public int getCheckedUserName(Map<String, Object> params) throws Throwable;

	public User getlogin(Map<String, Object> params) throws Throwable;;

}
