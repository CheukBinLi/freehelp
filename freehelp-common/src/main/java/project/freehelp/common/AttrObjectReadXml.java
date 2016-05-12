package project.freehelp.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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

@SuppressWarnings("unchecked")
public class AttrObjectReadXml extends DefaultHandler {
	// static ReadXml instance = new ReadXml();

	protected static final Map<Class<?>, Map<String, Field>> CACHE = new ConcurrentHashMap<Class<?>, Map<String, Field>>();

	private List<Object> resultObject;

	private Map<String, Field> current;

	private Class<?> unitClazz;

	protected Object getUnitInstance() throws InstantiationException, IllegalAccessException {
		return unitClazz.newInstance();
	}

	private Object unit;

	private Field objectField;

	private String unitName;

	protected static AttrObjectReadXml NewInstance() {
		return new AttrObjectReadXml();
	}

	public <T> List<T> padding(InputStream in, Class<T> obj) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		current = CACHE.get(obj);
		this.unitClazz = obj;
		if (null == current)
			current = scanClass(obj);

		unitName = obj.getSimpleName().toUpperCase();
		resultObject = new ArrayList<Object>();

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		AttrObjectReadXml handler = this;
		InputSource is = new InputSource(in);
		is.setEncoding("utf-8");
		parser.parse(is, handler);

		return (List<T>) resultObject;
	}

	protected Map<String, Field> scanClass(Class<?> c) {
		current = new HashMap<String, Field>();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			current.put(f.getName(), f);
		}
		CACHE.put(c, current);
		return current;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.toUpperCase().equals(unitName)) {
			String value;
			for (int i = 0, len = attributes.getLength(); i < len; i++) {
				value = attributes.getValue(i);
				// System.out.println(value);
				if (value.length() > 0) {
					objectField = current.get(attributes.getQName(i));
					try {
						if (null == unit)
							unit = getUnitInstance();
						objectField.set(unit, getValue(objectField.getType(), value));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			resultObject.add(unit);
			unit = null;
		}
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("dict.xml");
		List<Dictionary> list = new AttrObjectReadXml().padding(in, Dictionary.class);
		System.err.println(list.size());

	}

	private Object getValue(Class<?> c, Object data) {
		String simpleName = c.getSimpleName();
		boolean isArray = data.getClass().isArray();
		if (c.isArray()) {
			System.err.println("数组末实现");
			return null;
		} else if (simpleName.equalsIgnoreCase("String"))
			return getFirstValue(isArray, data);
		else if (simpleName.equalsIgnoreCase("boolean") || simpleName.equalsIgnoreCase("Boolean"))
			return Boolean.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("int") || simpleName.equalsIgnoreCase("Integer"))
			return Integer.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("byte"))
			return Byte.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("char") || simpleName.equalsIgnoreCase("Character"))
			return Character.valueOf(getFirstValue(isArray, data).charAt(0));
		else if (simpleName.equalsIgnoreCase("double"))
			return Double.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("long"))
			return Long.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("short"))
			return Short.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("float"))
			return Float.valueOf(getFirstValue(isArray, data));
		return data;
	}

	private String getFirstValue(boolean isArray, Object data) {
		return isArray ? ((String[]) data)[0] : data.toString();
	}
}
