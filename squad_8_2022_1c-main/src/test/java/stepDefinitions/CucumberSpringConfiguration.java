package stepDefinitions;

import com.aninfo.recursos.Application;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
