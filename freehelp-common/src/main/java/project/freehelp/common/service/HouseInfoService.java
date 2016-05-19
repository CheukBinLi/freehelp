package project.freehelp.common.service;

import java.util.List;

import project.freehelp.common.entity.HouseInfo;
import project.master.dbmaamger.service.BaseService;

public interface HouseInfoService extends BaseService<HouseInfo, String> {
	// stewardHouseList
	List<HouseInfo> getStewardHouseList(String steward, int page, int size) throws Throwable;

}
