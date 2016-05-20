package project.freehelp.common.service;

import java.util.List;
import java.util.Map;

import project.freehelp.common.entity.HouseInfo;
import project.master.dbmaamger.service.BaseService;

public interface HouseInfoService extends BaseService<HouseInfo, String> {
	// stewardHouseList
	List<List<Object>> getStewardHouseList(String steward, int page, int size) throws Throwable;

	List<HouseInfo> getStewardHouseList2(String steward, int page, int size) throws Throwable;

	List<HouseInfo> getNotConfirmHouseList(Map<String, Object> params, int page, int size) throws Throwable;

	List<HouseInfo> getNotConfirmAndNotInStewardHouseList(Map<String, Object> params, int page, int size) throws Throwable;

}
