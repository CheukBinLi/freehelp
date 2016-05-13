package project.freehelp.webchat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.freehelp.common.Constant;
import project.freehelp.common.SessionType;
import project.master.fw.sh.common.AbstractController;

@Controller
@Scope("prototype")
public class BaseController extends AbstractController implements Constant, SessionType {

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
		// System.out.println(request.getScheme());
		// System.out.println(request.getPathInfo());
		return new ModelAndView(request.getPathInfo());
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
