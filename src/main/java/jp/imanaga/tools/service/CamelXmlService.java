package jp.imanaga.tools.service;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jp.imanaga.tools.data.CamelXmlElement;
import jp.imanaga.tools.domain.CamelXmlDomain;
import jp.imanaga.tools.processor.CamelXmlProcessor;

@Service
public class CamelXmlService {

	@Autowired
	private CamelXmlProcessor camelXmlProcessor;

	@Autowired
	private CamelXmlDomain camelXmlDomain;

	/**
	 * テキスト出力
	 * 
	 * @param path
	 */
	public void generateDoc(String path) {

		String fileName = FilenameUtils.getName(path);

		try (FileOutputStream fileOutputStream = new FileOutputStream("./output/" + fileName + ".txt");
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) {

			Velocity.setProperty("file.resource.loader.path", "./template");
			Velocity.init();

			VelocityContext velocityContext = new VelocityContext();
			List<CamelXmlElement> routes = camelXmlProcessor.process(camelXmlDomain.getContents(path));
			velocityContext.put("routes", routes);

			Template template = Velocity.getTemplate("CamelTemplate.vm", "UTF-8");
			template.merge(velocityContext, bufferedWriter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Excelファイル出力
	 * 
	 * @param path
	 */
	public void generateXls(String path) {

		String fileName = FilenameUtils.getName(path);

		try (InputStream is = new FileInputStream("./template/" + "CamelTemplate.xls");
				OutputStream os = new FileOutputStream("./output/" + fileName + ".xls");) {

			Context context = new Context();
			List<CamelXmlElement> routes = camelXmlProcessor.process(camelXmlDomain.getContents(path));
			context.putVar("routes", routes);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			System.out.println(objectMapper.writeValueAsString(routes));

			JxlsHelper.getInstance().processTemplate(is, os, context);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
