package com.thinksys.mongo.service;

import com.thinksys.mongo.util.JsonResponse;

public interface PersonService {

	
	 public JsonResponse save();
	 public JsonResponse findAllPerson();
	 public JsonResponse update(int id);
	 public JsonResponse delete(int id,String classname);
	 
	 
}
