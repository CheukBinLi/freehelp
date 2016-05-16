package project.freehelp.webchat.controller;

import java.util.ArrayList;
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
import project.freehelp.common.entity.HouseInfo;
import project.freehelp.common.service.HouseInfoService;
import project.freehelp.common.vo.ImageVo;
import project.master.fw.sh.common.AbstractController;
import project.master.fw.sh.common.JsonObjectFactory;
import project.master.fw.sh.common.UpLoadFileFactory;

@Controller
@Scope("prototype")
@RequestMapping(value = { "/system/houseInfo/" })
public class HouseInfoController extends AbstractController implements Constant, SessionType {

	@Autowired
	private HouseInfoService houseInfoService;

	@Autowired
	private UpLoadFileFactory upLoadFileFactory;

	@RequestMapping(value = { "houseInfo_{number}", "housingPublish_{number}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getPage(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView(request.getPathInfo()).addAllObjects(getParams(request));
	}

	@RequestMapping(value = "post/{next}", method = RequestMethod.POST)
	public ModelAndView post(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {
		try {
			HouseInfo houseInfo = fillObject(new HouseInfo(), getParams(request));
			houseInfoService.save(houseInfo);
			return new ModelAndView("houseInfo_" + next).addObject("houseInfo", houseInfo);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Object postJ(HttpServletRequest request, HttpServletResponse response) {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String realPath = request.getServletContext().getRealPath("/") + HOUSE_IMAGE_PATH;
		HouseInfo houseInfo = new HouseInfo(true);
		// List<String> fileList = null;
		List<ImageVo> images = null;
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multiRequest.getFileNames();
			MultipartFile multipartFile;
			String fileName = null;
			images = new ArrayList<ImageVo>();
			int count = 0;
			// StringBuffer sb = new StringBuffer();
			// fileList = new ArrayList<String>();
			try {
				while (it.hasNext()) {
					multipartFile = multiRequest.getFile(it.next());
					if (multipartFile.getSize() < 1)
						continue;
					fileName = String.format("house_%s_%s.%s", houseInfo.getId(), count++, request.getParameter("type"));
					// fileList.add(fileName);
					images.add(new ImageVo(fileName));
					upLoadFileFactory.saveFile(multipartFile.getInputStream(), realPath, fileName);
					// sb.append("{\"src\":\"").append(fileName).append("\"},");
				}
				// if (sb.length() > 2) {
				// sb.insert(0, "[");
				// sb.deleteCharAt(sb.length() - 1);
				// sb.append("]");
				// }
				if (!images.isEmpty())
					houseInfo.setImage(JsonObjectFactory.newInstance().toJson(images, false));
			} catch (Throwable e) {
				// upLoadFileFactory.deleteFile(realPath, fileList.toArray(new String[0]));
				for (ImageVo vo : images)
					upLoadFileFactory.deleteSignleFile(realPath, vo.getSrc());
				return fail(e);
			}
		}
		try {
			houseInfo = fillObject(houseInfo, getParams(request));
			houseInfoService.save(houseInfo);
			return success();
		} catch (Throwable e) {
			if (null != images) {
				// upLoadFileFactory.deleteFile(realPath, fileList.toArray(new String[0]));
				for (ImageVo vo : images)
					upLoadFileFactory.deleteSignleFile(realPath, vo.getSrc());
			}
			return fail(e);
		}
	}

	@RequestMapping(value = "put/{next}", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("next") String next) {
		try {
			HouseInfo houseInfo = fillObject(new HouseInfo(), getParams(request));
			houseInfoService.update(houseInfo);
			return new ModelAndView("houseInfo_" + next).addObject("houseInfo", houseInfo);
		} catch (Throwable e) {
			return exceptionPage(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Object put(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			HouseInfo houseInfo = fillObject(new HouseInfo(), getParams(request));
			houseInfoService.update(houseInfo);
			return success(houseInfo);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			HouseInfo houseInfo = houseInfoService.getByPk(id);
			return success(houseInfo);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@RequestMapping(value = "get/{id}/{next}", method = RequestMethod.GET)
	public ModelAndView getj(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id, @PathVariable("next") String next) {
		try {
			HouseInfo houseInfo = houseInfoService.getByPk(id);
			return new ModelAndView("houseInfo_" + next).addObject("houseInfo", houseInfo);
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
			List<HouseInfo> list = houseInfoService.getList(map, true, page, size);
			return new ModelAndView("houseInfo_" + next).addObject("houseInfoList", list);
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
			List<HouseInfo> list = houseInfoService.getList(map, true, page, size);
			return success(list);
		} catch (Throwable e) {
			return fail(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		try {
			houseInfoService.delete(new HouseInfo(id));
			return success();
		} catch (Throwable e) {
			return fail(e);
		}
	}
}
