package project.freehelp.webchat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import project.freehelp.common.SessionType;
import project.freehelp.common.entity.HouseInfo;
import project.freehelp.common.service.HouseInfoService;
import project.master.fw.sh.common.AbstractController;

@Controller
@Scope("prototype")
@RequestMapping("/system/")
public class SystemController extends AbstractController implements SessionType {

	@Autowired
	private HouseInfoService houseInfoService;

	@RequestMapping("index")
	public ModelAndView anythingPath(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("master", request.getSession().getAttribute(USER_ID));
		List<HouseInfo> houseInfos = null;
		try {
			houseInfos = houseInfoService.getList(params, false, 1, 2);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
		return new ModelAndView(request.getPathInfo()).addObject("houseInfos", houseInfos);
	}

	@RequestMapping("exit")
	public ModelAndView exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView(new RedirectView("index"));
	}
}
