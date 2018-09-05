package com.hcl.studentmanagement.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.studentmanagement.service.StudentServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	StudentServices service;
	
	
	@RequestMapping("/add")
	public ArrayList<String> addUser(@RequestParam(required = false,defaultValue="0") int id,@RequestParam String fname,
			@RequestParam String lname, @RequestParam String email,
			@RequestParam String password) {
		ArrayList<String> obj = new ArrayList<>();
		try {
			Map<String, Object> params=new HashMap<>();
			params.put("id", id);
			params.put("fname", fname);
			params.put("lname", lname);
			params.put("email", email);
			params.put("password", password);
			obj.add("status");
			service.create(params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch");
		}
		System.out.println(obj.toString());
		return obj;
	}

	@RequestMapping("/search")
	public List<?> searchUser(@RequestParam(required = false, defaultValue = "0") int id) {
		
		try {
			
			Map<String, Object> params=new HashMap<>();
			params.put("id", id);
			return service.select(params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch");
			return null;
		}
	}

	
	@RequestMapping("/bulkInsert")
	public String bulkInsert(@RequestParam(required = false, defaultValue = "false") boolean committable) {
		
		try {
			
			Map<String, Object> params=new HashMap<>();
			params.put("committable", committable);
			return service.bulkInsert(committable);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("catch");
			return null;
		}
	}
}
