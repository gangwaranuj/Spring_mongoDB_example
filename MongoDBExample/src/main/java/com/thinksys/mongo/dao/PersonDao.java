package com.thinksys.mongo.dao;

import com.thinksys.mongo.model.Person;
import com.thinksys.mongo.util.Response;

public interface PersonDao {


	public Response save(Person person);
	public Response findAllPerson();
	public Response delete(int id,String classname);
	public Response update(int id);


}
