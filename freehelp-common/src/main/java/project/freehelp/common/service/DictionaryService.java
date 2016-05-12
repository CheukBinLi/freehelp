package project.freehelp.common.service;

import java.util.Map;

import project.freehelp.common.entity.Dictionary;
import project.master.dbmaamger.service.BaseService;

public interface DictionaryService extends BaseService<Dictionary, Integer> {
	Dictionary replicate(Dictionary t) throws Throwable;

	void save(Map<String, Object> params) throws Throwable;
}
