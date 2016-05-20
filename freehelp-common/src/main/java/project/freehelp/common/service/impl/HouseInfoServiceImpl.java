package project.freehelp.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.freehelp.common.Constant;
import project.freehelp.common.dao.HouseInfoDao;
import project.freehelp.common.entity.HouseInfo;
import project.freehelp.common.service.HouseInfoService;
import project.master.dbmaamger.dao.BaseDao;
import project.master.dbmaamger.service.AbstractService;

@Component
public class HouseInfoServiceImpl extends AbstractService<HouseInfo, String> implements HouseInfoService, Constant {

	@Autowired
	private HouseInfoDao houseInfoDao;

	@Override
	public BaseDao<HouseInfo, String> getService() {
		return houseInfoDao;
	}

	public List<List<Object>> getStewardHouseList(String steward, int page, int size) throws Throwable {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("steward", steward);
		return houseInfoDao.getList("stewardHouseList", params, false, page, size);
	}

	public List<HouseInfo> getStewardHouseList2(String steward, int page, int size) throws Throwable {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("steward", steward);
		return houseInfoDao.getList("stewardHouseList2", params, false, page, size);
	}

	public List<HouseInfo> getNotConfirmHouseList(Map<String, Object> params, int page, int size) throws Throwable {
		return houseInfoDao.getListEntity("notConfirmHouseList", params, false, page, size);
	}

	public List<HouseInfo> getNotConfirmAndNotInStewardHouseList(Map<String, Object> params, int page, int size) throws Throwable {
		return houseInfoDao.getListEntity("noConfirmAndNotInStewardHouseList", params, false, page, size);
	}

}
