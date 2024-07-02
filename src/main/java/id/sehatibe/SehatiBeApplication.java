package id.sehatibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"id.sehatibe.controller", "id.sehatibe.service", "id.sehatibe.repository"})
public class SehatiBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SehatiBeApplication.class, args);
    }

}
