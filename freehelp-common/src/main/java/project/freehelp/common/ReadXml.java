package project.freehelp.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import project.freehelp.common.entity.Dictionary;

public class ReadXml extends DefaultHandler {
	//	static ReadXml instance = new ReadXml();

	protected static final Map<Class<?>, Map<String, Field>> CACHE = new ConcurrentHashMap<Class<?>, Map<String, Field>>();

	private List<Object> resultObject;

	private Map<String, Field> current;

	private Object unit;

	LinkedList<String> link = new LinkedList<String>();

	protected static ReadXml NewInstance() {
		return new ReadXml();
	}

	public <T> List<T> padding(InputStream in, Class<T> obj) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		current = CACHE.get(obj);
		if (null == current)
			current = scanClass(obj);
		link.clear();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		ReadXml handler = this;
		InputSource is = new InputSource(in);
		is.setEncoding("utf-8");
		parser.parse(is, handler);

		T t = (T) resultObject;
		return null;
	}

	public Map<String, Field> scanClass(Class<?> c) {
		current = new HashMap<String, Field>();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			current.put(f.getName(), f);
		}
		CACHE.put(c, current);
		return current;
	}

	static class Node {
		private String tagName;
		private Field field;
		private Object obj;

		public String getTagName() {
			return tagName;
		}

		public Node setTagName(String tagName) {
			this.tagName = tagName;
			return this;
		}

		public Object getObj() {
			return obj;
		}

		public Node setObj(Object obj) {
			this.obj = obj;
			return this;
		}

		public Field getField() {
			return field;
		}

		public Node setField(Field field) {
			this.field = field;
			return this;
		}

		public Node(String tagName, Field field, Object obj) {
			super();
			this.tagName = tagName;
			this.field = field;
			this.obj = obj;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		link.addLast(qName);
		System.out.println("s" + qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(link.removeLast()))
			unit = null;
		//		System.out.println("e" + qName);
		//		System.out.println("e:" + qName.equals(link.removeLast()));
		System.out.println(qName + ":" + link.removeLast());
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//		try {
		//			if (null == unit)
		//				unit = unit.getClass().newInstance();
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("dict.xml");
		new ReadXml().padding(in, Dictionary.class);
	}
}
