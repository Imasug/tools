package jp.imanaga.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import jp.imanaga.tools.service.CamelXmlService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		// TODO 引数チェック

		SpringApplication app = new SpringApplication(Application.class);
		app.setWebApplicationType(WebApplicationType.NONE);

		try (ConfigurableApplicationContext ctx = app.run(args);) {
			// TODO Apache Commons CLI、Spring Shellを検討
			ctx.getBean(CamelXmlService.class).generateDoc(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
