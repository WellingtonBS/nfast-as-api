package br.com.nfast.api;

import br.com.nfast.api.config.Config;
import br.com.nfast.api.utils.Files;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.nfast.api"})
public class Startup implements ServletContextInitializer {
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        if (args.length == 0) {
            List<String> items = new ArrayList<>();
            items.add("--server.port=8085");
            items.add("--server.file=D:\\Impulse\\Projetos\\DevImpulse\\Nfast\\nfast-as-api\\nfast-api.properties");
            args = items.toArray(new String[0]);
        }
        SpringApplication.run(Startup.class, args);
    }

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        Config.SERVER_PORT = env.getProperty("server.port");
        Config.SERVER_FILE = env.getProperty("server.file");
        context.getSessionCookieConfig().setName("nfast-api-" + Config.SERVER_PORT);

        if (Strings.isNonEmpty(Config.SERVER_FILE)) {
            Properties props = Files.loadProperties(Config.SERVER_FILE);
            Config.AUTH_MODE = props.getProperty("auth.mode");
            Config.AUTH_HOST = props.getProperty("auth.host");
            Config.AUTH_PORT = props.getProperty("auth.port");
            Config.AUTH_NAME = props.getProperty("auth.name");
            Config.AUTH_USER = props.getProperty("auth.user");
            Config.AUTH_PASS = props.getProperty("auth.pass");
            Config.APPLICATION_NAME = props.getProperty("application.name");
        }
    }

}
