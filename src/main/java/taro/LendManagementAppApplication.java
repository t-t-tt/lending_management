package taro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class LendManagementAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LendManagementAppApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LendManagementAppApplication.class);
    }

	@RequestMapping(value = "nagap", method = RequestMethod.GET)
    public String getSomething() {
        return "something";
    }

}
