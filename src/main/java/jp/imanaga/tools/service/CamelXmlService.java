package jp.imanaga.tools.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.imanaga.tools.data.XmlElement;
import jp.imanaga.tools.domain.CamelXmlDomain;
import jp.imanaga.tools.processor.CamelXmlProcessor;

@Service
public class CamelXmlService {

	@Autowired
	private CamelXmlProcessor camelXmlProcessor;

	@Autowired
	private CamelXmlDomain camelXmlDomain;

	public void generateDoc(String path) {

		// TODO pathからファイル名を抽出
		String fileName = "test.txt";

		try (FileOutputStream fileOutputStream = new FileOutputStream("./output/" + fileName);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) {

			Velocity.setProperty("file.resource.loader.path", "./template");
			Velocity.init();

			VelocityContext velocityContext = new VelocityContext();
			List<XmlElement> routes = camelXmlProcessor.process(camelXmlDomain.getContents(path));
			velocityContext.put("routes", routes);

			Template template = Velocity.getTemplate("doc.vm", "UTF-8");
			template.merge(velocityContext, bufferedWriter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
