package project.freehelp.common.dao;

import project.freehelp.common.entity.Dictionary;
import project.master.dbmaamger.dao.BaseDao;

public interface DictionaryDao extends BaseDao<Dictionary, Integer> {

	/***
	 * 插入
	 * 
	 * @param t
	 *            对象
	 * @return
	 * @throws Throwable
	 */
	public Dictionary replicate(Dictionary t) throws Throwable;
}