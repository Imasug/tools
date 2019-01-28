package jp.imanaga.tools.data;

import java.util.List;
import java.util.Map;

public class CamelXmlElement {

	private String name;

	private Map<String, String> attrs;

	private List<CamelXmlElement> children;

	private String text;

	private String description;

	private Map<String, String> endpoint;

	public CamelXmlElement(String name, Map<String, String> attrs, List<CamelXmlElement> children) {
		this.name = name;
		this.attrs = attrs;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	public List<CamelXmlElement> getChildren() {
		return children;
	}

	public void setChildren(List<CamelXmlElement> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Map<String, String> endpoint) {
		this.endpoint = endpoint;
	}

}
