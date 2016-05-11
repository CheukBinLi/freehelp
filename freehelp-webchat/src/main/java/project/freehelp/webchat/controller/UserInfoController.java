package project.freehelp.webchat.controller;

import java.io.IOException;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import project.freehelp.common.Constant;
import project.freehelp.common.SessionType;
import project.freehelp.common.entity.UserInfo;
import project.freehelp.common.service.UserInfoService;
import project.master.fw.sh.common.AbstractController;
import project.master.fw.sh.common.UpLoadFileFactory;

@Controller
@Scope("prototype")
@RequestMapping("/system/userInfo/")
public class UserInfoController extends AbstractController implements Constant, SessionType {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UpLoadFileFactory upLoadFileFactory;

	@RequestMapping(value = { "userInfo_{number}", "authentication_{number}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getPage(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		// System.out.println(request.getRealPath("/"));
		// json:
		// <#assign jsonX=json?eval />
		// <#list jsonX as item>
		// <br>${item_index}:${item.msg}
		// </#list>
		return new ModelAndView(request.getPathInfo()).addAllObjects(getParams(request))/* .addObject("json", "[{\"msg\":\"你好吗？\"},{\"msg\":\"你好吗？\"}]") */;
	}

	@RequestMapping(value = "post/{next}", method = RequestMethod.POST)
	public ModelAndView post(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {
		try {
			UserInfo userInfo = fillObject(new UserInfo(), getParams(request));
			userInfoService.save(userInfo);
			return new ModelAndView("userInfo_" + next).addObject("userInfo", userInfo);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Object postJ(HttpServletRequest request, HttpServletResponse response) {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String realPath = request.getServletContext().getRealPath("/") + ID_CARD_IMAGE_PATH;
		String id = request.getSession().getAttribute(USER_ID).toString();
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multiRequest.getFileNames();
			MultipartFile multipartFile;
			String fileName = null;
			int count = 0;
			StringBuffer sb = new StringBuffer();
			try {
				while (it.hasNext()) {
					fileName = String.format("idcard_%s_%s.%s", id, count++, request.getParameter("type"));
					multipartFile = multiRequest.getFile(it.next());
					upLoadFileFactory.saveFile(multipartFile.getInputStream(), realPath, fileName);
					sb.append("{\"src\":\"").append(fileName).append("\"},");
				}
				if (sb.length() > 2) {
					sb.insert(0, "[");
					sb.deleteCharAt(sb.length() - 1);
					sb.append("]");
				}
				// 更新
				UserInfo userInfo = userInfoService.getByPk(id);
				userInfo.setReallyName(request.getParameter("reallyName")).setIdCard(request.getParameter("idCard")).setIdCardImage(sb.length() > 2 ? sb.toString() : null).setStatus(1);
				userInfoService.update(userInfo);
				return success();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		// ModelAndView mav = new ModelAndView("forward:/system/userInfo/authentication_2");
		// ModelAndView mav = new ModelAndView(new RedirectView("/system/userInfo/authentication_2"));
		return fail("上传失败", null);
	}

	@RequestMapping(value = "put/{next}", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {
		try {
			UserInfo userInfo = fillObject(new UserInfo(), getParams(request));
			userInfoService.update(userInfo);
			return new ModelAndView("userInfo_" + next).addObject("userInfo", userInfo);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Object put(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			UserInfo userInfo = fillObject(new UserInfo(), getParams(request));
			userInfoService.update(userInfo);
			return success(userInfo);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			UserInfo userInfo = userInfoService.getByPk(id);
			return success(userInfo);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@RequestMapping(value = "get/{id}/{next}", method = RequestMethod.GET)
	public ModelAndView getj(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id, @PathVariable("next") String next) {
		try {
			UserInfo userInfo = userInfoService.getByPk(id);
			return new ModelAndView("userInfo_" + next).addObject("userInfo", userInfo);
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
			List<UserInfo> list = userInfoService.getList(map, true, page, size);
			return new ModelAndView("userInfo_" + next).addObject("userInfoList", list);
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
			List<UserInfo> list = userInfoService.getList(map, true, page, size);
			return success(list);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			userInfoService.delete(new UserInfo(id));
			return success();
		} catch (Throwable e) {
			return fail(e);
		}
	}

	/************************************ 业务 *****************************************/
}
