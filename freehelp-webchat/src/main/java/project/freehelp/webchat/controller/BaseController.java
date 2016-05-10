package project.freehelp.webchat.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import project.freehelp.common.Constant;
import project.freehelp.common.SessionType;
import project.master.fw.sh.common.UpLoadFileFactory;

@Controller
@Scope("prototype")
public class BaseController implements Constant, SessionType {

	@Autowired
	private UpLoadFileFactory upLoadFileFactory;

	/***
	 * 
	 * @param request
	 * @param response
	 * @param business
	 *            业务名
	 * @param process
	 *            流程序号
	 * @return
	 */
	// @RequestMapping("**/{business}_{process}")
	// public ModelAndView businessStream(HttpServletRequest request, HttpServletResponse response, @PathVariable("business") String business, @PathVariable("process") String process) {
	// return new ModelAndView(request.getPathInfo());
	// }

	/***
	 * 
	 * @param request
	 * @param response
	 * @param path
	 *            所有路径
	 * @return
	 */
	@RequestMapping("**/index")
	public ModelAndView anythingPath(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getScheme());
		System.out.println(request.getPathInfo());
		return new ModelAndView(request.getPathInfo());
	}

	@RequestMapping(value = "/system/upload/idcard", method = RequestMethod.POST)
	public ModelAndView uploadIdCard(HttpServletRequest request, HttpServletResponse response) {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String realPath = request.getRealPath("/") + UPLOAD_TEMP_PATH;
		// idcard_sessionID_p.jpg (前:p , 背:b)
		MultipartFile mf=null;
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> map = multiRequest.getFileMap();
			System.out.println(map.size());
			for (Entry<String, MultipartFile> en : map.entrySet()) {
				System.out.println(en.getKey());
				mf = en.getValue();
			}
			Iterator<String> it = multiRequest.getFileNames();
			MultipartFile multipartFile;
			String fileName = null;
			int count = 0;
			while (it.hasNext()) {
				fileName = String.format("idcard_%s_%s.%s", request.getSession().getAttribute(USER_ID), count++, request.getParameter("type"));
				multipartFile = multiRequest.getFile(it.next());
				// fileName = multipartFile.getName();
				// tempFiel = new File(realPath + fileName);
				// try {
				// multipartFile.transferTo(tempFiel);
				// } catch (IllegalStateException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				try {
					upLoadFileFactory.saveFile(multipartFile.getInputStream(), realPath, fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// ModelAndView mav = new ModelAndView("forward:/system/userInfo/authentication_2");
		// ModelAndView mav = new ModelAndView(new RedirectView("/system/userInfo/authentication_2"));
		// System.out.println(request.getScheme());
		// System.out.println(request.getPathInfo());
		// return new ModelAndView(request.getPathInfo());
		// try {
		// response.getOutputStream().write(("<div id=\"msg\">{\"code\":\"1\",\"src\":\"" + ACCESS_UPLOAD_TEMP_PATH + fileName + "\"}</div>").getBytes());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return null;
	}

	@RequestMapping(value = "/a/b/{id}", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		System.out.println("get");

		return null;
	}

	@RequestMapping(value = "/a/b/{id}", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		System.out.println("DELETE");
		return null;
	}

}
