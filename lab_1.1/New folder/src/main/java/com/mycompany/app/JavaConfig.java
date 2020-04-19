package com.mycompany.app;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.mycompany.app")
@PropertySource("classpath:values.properties")
public class JavaConfig {
@Bean
public Employee employee() {
	Employee employee=new Employee();
	return employee;
}


}
