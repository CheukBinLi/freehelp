package project.freehelp.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.freehelp.common.Constant;
import project.freehelp.common.dao.HouseStewardDao;
import project.freehelp.common.entity.HouseSteward;
import project.freehelp.common.service.HouseStewardService;
import project.master.dbmaamger.dao.BaseDao;
import project.master.dbmaamger.service.AbstractService;
import project.master.fw.sh.common.ObjectFill;

@Component
public class HouseStewardServiceImpl extends AbstractService<HouseSteward, String> implements HouseStewardService, Constant {

	@Autowired
	private HouseStewardDao houseStewardDao;

	@Override
	public BaseDao<HouseSteward, String> getService() {
		return houseStewardDao;
	}

	public HouseSteward checkSaveOrUpdate(HouseSteward houseSteward) throws Throwable {
		Map<String, Object> status = new HashMap<String, Object>();
		status.put("status", STATUS_TYPE_SUCCESS);
		status.put("house", houseSteward.getHouse());
		HouseSteward tempHouseSteward = null;
		List<?> a = houseStewardDao.getList(status, -1, -1);
		if (a.size() > 0)
			throw new Exception("查询失败");
		status.remove("status");
		status.put("steward", houseSteward.getSteward());
		a = houseStewardDao.getList(status, -1, -1);
		if (a.size() > 0) {
			tempHouseSteward = (HouseSteward) a.get(0);
			houseStewardDao.update(tempHouseSteward.setStatus(STATUS_TYPE_WAIT));
			return tempHouseSteward;
		}
		return houseStewardDao.save(houseSteward);
	}

}
