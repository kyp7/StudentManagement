package com.hcl.studentmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.studentmanagement.repository.StudentRepository;

@Service
public class StudentServices {

	@Autowired
	StudentRepository repository;
	
	public List<?> select(Map<String, Object> params)
	{
		return repository.select(params);
	}
	
	public void create(Map<String, Object> params)
	{
		repository.create(params);
	}
	
	public String bulkInsert(boolean committable)
	{
		return repository.bulkInsert(repository.select(null),committable);
	}
	
}
