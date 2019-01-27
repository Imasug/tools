package jp.imanaga.tools.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import jp.imanaga.tools.data.XmlElement;

@Component
public class CamelXmlParser extends DefaultHandler {

	private Stack<XmlElement> caches;

	private List<XmlElement> routes;

	public List<XmlElement> getRoutes() {
		return routes;
	}

	@Override
	public void startDocument() throws SAXException {
		caches = new Stack<>();
		routes = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// 属性をMapへ変換
		Map<String, String> attrs = new LinkedHashMap<>();
		for (int i = 0; i < attributes.getLength(); i++) {
			attrs.put(attributes.getQName(i), attributes.getValue(i));
		}

		// コンポーネント情報作成
		XmlElement component = new XmlElement(qName, attrs, new ArrayList<>());

		if ("route".equals(qName)) {
			routes.add(component);
		}

		if (caches.size() > 0) {
			caches.peek().getChildren().add(component);
		}

		// キャッシュへ記録
		caches.add(component);

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		caches.peek().setText(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		caches.pop();
	}

	@Override
	public void endDocument() throws SAXException {
	}

}
