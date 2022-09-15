package devrabbit.personalblog;

import devrabbit.personalblog.config.annotation.DeveloperInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@DeveloperInfo(
        expertise = DeveloperInfo.Expertise.JUNIOR,
        createdBy = "Kübra Felek",
        url = "https://github.com/kubrafelek",
        email = "kbr.flk@hotmail.com"
)
public class PersonalBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalBlogApplication.class, args);
    }

}
