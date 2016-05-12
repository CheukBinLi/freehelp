package project.freehelp.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.freehelp.common.dao.DictionaryDao;
import project.freehelp.common.entity.Dictionary;
import project.freehelp.common.service.DictionaryService;
import project.master.dbmaamger.dao.BaseDao;
import project.master.dbmaamger.service.AbstractService;

@Component
public class DictionaryServiceImpl extends AbstractService<Dictionary, Integer> implements DictionaryService {

	@Autowired
	private DictionaryDao dictionaryDao;

	@Override
	public BaseDao<Dictionary, Integer> getService() {
		return dictionaryDao;
	}

	public Dictionary replicate(Dictionary t) throws Throwable {
		// TODO Auto-generated method stub
		return dictionaryDao.replicate(t);
	}

	public void save(Map<String, Object> params) throws Throwable {
		dictionaryDao.getListEntity("save", params, false, -1, -1);
	}

}
