package com.thinksys.mongo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinksys.mongo.model.Person;
import com.thinksys.mongo.service.PersonService;
import com.thinksys.mongo.util.JsonResponse;


@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	@Autowired
	PersonService personServiceImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public JsonResponse save() {
		
		JsonResponse jsonResponse=personServiceImpl.save();
		return jsonResponse;
	}

	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public JsonResponse getAllperson() {

		JsonResponse jsonResponse=	personServiceImpl.findAllPerson();
		return jsonResponse;
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JsonResponse deleteRecord(@PathVariable int id) {

		String classname=Person.class.getName();
		JsonResponse jsonResponse=	personServiceImpl.delete(id,classname);
		return jsonResponse;
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public JsonResponse updateRecord(@PathVariable int id) {

		JsonResponse jsonResponse=	personServiceImpl.update(id);
		return jsonResponse;
	}
}
