package project.freehelp.webchat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.freehelp.common.SettingSession;
import project.freehelp.common.entity.UserInfo;
import project.freehelp.common.service.UserInfoService;
import project.freehelp.common.service.UserService;
import project.master.fw.sh.common.AbstractController;
import project.master.fw.sh.common.SmsEntry;
import project.master.fw.sh.common.SmsFactory;
import project.master.user.User;

@Controller
@Scope("prototype")
@RequestMapping("/register/")
public class RegisterController extends AbstractController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SmsFactory smsFactory;
	@Autowired
	private SettingSession settingSession;

	@RequestMapping(value = "reg_{number}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(request.getPathInfo(), getParams(request));
		return mav;
	}

	@RequestMapping(value = "post/{next}", method = RequestMethod.POST)
	public ModelAndView post(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {

		String verificationcode = request.getParameter("verificationcode");
		String phone = request.getParameter("phone");
		SmsEntry sms = smsFactory.getAndRemoveCode(phone);
		if (null != sms && !verificationcode.equals(sms.getCode()))
			return exceptionPage("重复注册", null);
		try {
			User user = fillObject(new User(), getParams(request));
			user.setId(user.generatedValue());
			userService.save(user);
			userInfoService.save(new UserInfo(user.getId()));
			try {
				Integer.parseInt(next);
			} catch (Exception e) {
				return new ModelAndView("..../" + next).addObject("user", user);
			}
			return new ModelAndView("reg_" + next).addObject("user", user);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Object postJ(HttpServletRequest request, HttpServletResponse response) {
		String verificationcode = request.getParameter("verificationcode");
		String phone = request.getParameter("phone");
		SmsEntry sms = smsFactory.getAndRemoveCode(phone);
		if (null != sms && !verificationcode.equals(sms.getCode()))
			return fail("重置注册", null);
		try {
			User user = fillObject(new User(), getParams(request));
			user.setId(user.generatedValue());
			userService.save(user);
			userInfoService.save(new UserInfo(user.getId()));
			return success(user);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@RequestMapping(value = "put/{next}", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {
		try {
			User user = fillObject(new User(), getParams(request));
			userService.update(user);
			return new ModelAndView("reg_" + next).addObject("user", user);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Object put(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			User user = fillObject(new User(), getParams(request));
			userService.update(user);
			return success(user);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			User user = userService.getByPk(id);
			return success(user);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@RequestMapping(value = "get/{id}/{next}", method = RequestMethod.GET)
	public ModelAndView getj(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id, @PathVariable("next") String next) {
		try {
			User user = userService.getByPk(id);
			return new ModelAndView("reg_" + next).addObject("user", user);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@RequestMapping(value = "list/{next}")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {
		try {
			Map<String, Object> map = getParams(request);
			int page = map.containsKey("page") ? Integer.valueOf(map.remove("page").toString()) : -1;
			int size = map.containsKey("size") ? Integer.valueOf(map.remove("size").toString()) : -1;
			List<User> list = userService.getList(map, true, page, size);
			return new ModelAndView("reg_" + next).addObject("userList", list);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "listj")
	public Object listj(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> map = getParams(request);
			int page = map.containsKey("page") ? Integer.valueOf(map.remove("page").toString()) : -1;
			int size = map.containsKey("size") ? Integer.valueOf(map.remove("size").toString()) : -1;
			List<User> list = userService.getList(map, true, page, size);
			return success(list);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			userService.delete(new User(id));
			return success();
		} catch (Throwable e) {
			return fail(e);
		}
	}

	/************************************ 业务 *****************************************/

	@ResponseBody
	@RequestMapping(value = "checkUser")
	public Object checkUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			return success(userService.getCheckedUserName(getParams(request)));
		} catch (Throwable e) {
			return fail(e);
		}
	}

	// 验证码
	@ResponseBody
	@RequestMapping(value = "verificationcode")
	public Object verificationcode(HttpServletRequest request, HttpServletResponse response) {
		String sendCode = request.getParameter("sendCode");// 发送验证码
		String verificationcode = request.getParameter("verificationcode");
		String phone = request.getParameter("phone");
		// 验证
		SmsEntry sms = smsFactory.getCode(phone);
		if (null == sendCode && null != sms)
			if (verificationcode.equals(smsFactory.getCode(phone).getCode())) {
				return success();
			} else
				return fail();
		else {
			try {
				smsFactory.sendCode(phone, "【自游帮】#code#(动态验证码)，请在5分钟内填写。", "#code#");
				if (null == sendCode)
					return fail();
				return success();
			} catch (Throwable e) {
				return fail(e);
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Object login(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = userService.getlogin(getParams(request));
			if (null != user) {
				UserInfo userInfo = userInfoService.getByPk(user.generatedValue());
				// 设置Session
				settingSession.SettionSession(request, user, userInfo);
				return success();
			}
			return fail("登录失败", null);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}
	// @RequestMapping(value = "reg_{number}", method = { RequestMethod.GET, RequestMethod.POST })
	// public ModelAndView reg_2(HttpServletRequest request, HttpServletResponse response) {
	// ModelAndView mav = new ModelAndView(request.getPathInfo(), getParams(request));
	// return mav;
	// }

}
