package com.thinksys.mongo.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinksys.mongo.dao.PersonDao;
import com.thinksys.mongo.model.Person;
import com.thinksys.mongo.service.PersonService;
import com.thinksys.mongo.util.JsonResponse;
import com.thinksys.mongo.util.Response;

@Service
public class PersonServiceImpl implements PersonService{

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	PersonDao personDaoImpl;


	@Override
	public JsonResponse save() {
		
		logger.info("inside save");
		
		JsonResponse jsonResponse=null;
		Person person=new Person();
		person.setName("Anuj Gangwar");
		Response response= personDaoImpl.save(person);
		if(response.getStatus()){
			jsonResponse=new JsonResponse("Success","Data successFully saved.");
			logger.info("in save,Data successFully saved.");
			
		}
		else{
			jsonResponse=new JsonResponse("Error","Failed to save data.");
			logger.info("in save,Failed to save data.");
		}
		
		return jsonResponse;
	}


	@Override
	public JsonResponse<Person> findAllPerson() {

		logger.info("inside findAllPerson");
		JsonResponse <Person> jsonResponse=null;
		try{
			Response response=personDaoImpl.findAllPerson();
			if(response.getStatus()){
				List<Person> perosnlist=(List<Person>) response.getData();
				jsonResponse=new JsonResponse<Person>("Success",perosnlist,perosnlist.size());
				logger.info("in save,Data successFully retrieved. size.[{}]",perosnlist.size());
			}
			else{
				jsonResponse=new JsonResponse<Person>("Success","No record found.");
				logger.info("in save,No record in db.");
			}
			
		}catch(Exception e){
			jsonResponse=new JsonResponse<Person>("Error",e.getMessage());
			logger.info("in save,error[{}]",e.getMessage());
			
		}
		
		return jsonResponse;
	}


	@Override
	public JsonResponse update(int id) {
		
		logger.info("inside update");
		JsonResponse <Person> jsonResponse=null;
		
		Person person=new Person();
//		person.setId(1);
//		person.setName("Ashna Gandhi");
		Response response=personDaoImpl.update(id);
		if(response.getStatus()){
			jsonResponse=new JsonResponse<Person>("Success","Record Successfuly updated !");
		}
		else{
			jsonResponse=new JsonResponse<Person>("Error","error occured.");
		}
		return jsonResponse;
	}


	@Override
	public JsonResponse delete(int id,String classname) {
		
		logger.info("inside delete");
		JsonResponse <Person> jsonResponse=null;
		try{
			Response response=personDaoImpl.delete(id,classname);
			if(response.getStatus()){
				jsonResponse=new JsonResponse<Person>("Success","Record Successfuly deleted !");
			}
			else{
				jsonResponse=new JsonResponse<Person>("Success","No Record exist with this Id.");
			}
		}catch(Exception e){
			
			e.printStackTrace();
			jsonResponse=new JsonResponse<Person>("Error",e.getMessage());
		}
		
		return jsonResponse;
	}





}
