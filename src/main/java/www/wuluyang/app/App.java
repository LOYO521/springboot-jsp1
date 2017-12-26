package www.wuluyang.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages={"www.wuluyang.controller","www.wuluyang.service"})
@MapperScan(basePackages="www.wuluyang.mapper")
@EnableAutoConfiguration
public class App {
public static void main(String[] args) {
	SpringApplication.run(App.class, args);
}
}
