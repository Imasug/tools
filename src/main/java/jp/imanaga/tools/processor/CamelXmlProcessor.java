package jp.imanaga.tools.processor;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.imanaga.tools.data.CamelXmlElement;

@Component
public class CamelXmlProcessor {

	@Autowired
	private ResourceBundle resourceBundle;

	public List<CamelXmlElement> process(List<CamelXmlElement> input) {
		// TODO 要検討
		input.forEach(element -> {
			String key = element.getName();
			if (resourceBundle.containsKey(key)) {
				element.setName(resourceBundle.getString(key));
			}
			process(element.getChildren());
		});
		return input;
	}

}
