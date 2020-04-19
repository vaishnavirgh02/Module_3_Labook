
package com.mycompany.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class EmployeeMain {

    public static void main(String[] args) {
        //
        //ApplicationContext is the specification
        // implementation we are using is AnnotationConfigApplicationContext
        //
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //
        //registering configuration class in which configurations are kept
        //
        Class configurationClass=JavaConfig.class;
        context.register(configurationClass);
        context.refresh();
       // context.registerShutdownHook();
        //
        //fetching bean by type
        //
        Employee employee = context.getBean(Employee.class);
        employee.displayAllValues();
        context.close();
       

    }
}