package jp.imanaga.tools.domain;

import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.imanaga.tools.data.CamelXmlElement;
import jp.imanaga.tools.parser.CamelXmlParser;

@Component
public class CamelXmlDomain {

	@Autowired
	private CamelXmlParser camelXmlParser;

	public List<CamelXmlElement> getContents(String path) throws Exception {
		SAXParserFactory.newInstance().newSAXParser().parse(path, camelXmlParser);
		List<CamelXmlElement> routes = camelXmlParser.getRoutes();
		return routes;
	}

}
