package jp.imanaga.tools.data;

import java.util.List;
import java.util.Map;

public class XmlElement {

	private String name;

	private Map<String, String> attrs;

	private List<XmlElement> children;

	private String text;

	private String description;

	public XmlElement(String name, Map<String, String> attrs, List<XmlElement> children) {
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

	public List<XmlElement> getChildren() {
		return children;
	}

	public void setChildren(List<XmlElement> children) {
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

}
