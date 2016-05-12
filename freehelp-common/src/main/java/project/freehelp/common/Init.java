package project.freehelp.common;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import project.freehelp.common.entity.Dictionary;
import project.freehelp.common.service.DictionaryService;
import project.master.fw.sh.common.ObjectFill;

public class Init {

	@Autowired
	private DictionaryService dictionaryService;

	@PostConstruct
	public void init() {
		// 字典
		ObjectFill objectFill = new ObjectFill();
		try {
			List<Dictionary> list = AttrObjectReadXml.NewInstance().padding(Thread.currentThread().getContextClassLoader().getResourceAsStream("/dict.xml"), Dictionary.class);
			if (null != list)
				for (Dictionary d : list) {
					if (null == dictionaryService.getByPk(d.getId()))
						dictionaryService.executeUpdate("save", objectFill.objectToMap(d), false, false);
					else
						dictionaryService.update(d);
				}
			// for (int i = 0; i < 200; i++)
			// dictionaryService.save(new Dictionary(i));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
