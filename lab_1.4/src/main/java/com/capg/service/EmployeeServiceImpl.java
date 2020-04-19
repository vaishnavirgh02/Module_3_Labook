package com.capg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.dao.IEmployeeDao;
import com.capg.entities.Employee;

@Component
public class EmployeeServiceImpl implements IEmployeeService {
	   private IEmployeeDao dao;

	    public IEmployeeDao getDao(){
	        return dao;
	    }

	    @Autowired
	    public void setDao(IEmployeeDao dao){
	        this.dao=dao;
	    }
	    
	    @Override
	    public Employee fetchById(int id) {
	    	Employee e=dao.fetchById(id);
	    	return e;
	    }

}
