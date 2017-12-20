package com.thinksys.mongo.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thinksys.mongo.dao.PersonDao;
import com.thinksys.mongo.model.Customers;
import com.thinksys.mongo.model.Person;
import com.thinksys.mongo.util.Response;

@Repository
public class PersonDaoImpl  implements PersonDao{


	private static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("test");
	private static EntityManager entityManager=null;

	@Override
	public Response save(Person person) {

		Response response =new Response();
		if (person != null) {
			
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(person);
			entityManager.getTransaction().commit();
			entityManager.close();
			response.setStatus(true);
		}
		return response;
	}



	@Override
	public Response findAllPerson() {

		Response response=new Response();
		List<Customers> list = new ArrayList<Customers>();
		entityManager = entityManagerFactory.createEntityManager();
		Query q = entityManager.createQuery("from "+Customers.class.getName());
		list = q.getResultList();
		if(!list.isEmpty()){
			response.setData(list);
			response.setStatus(true);
		}
		else{
			response.setStatus(false);
		}
		return response;
	}
	
	@Override
	public Response delete(int id,String classname){
		
		
		Response res = new Response();
		List<Person> list = new ArrayList<Person>();
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery("from "+classname +" person where person.id ="+id);
		list = q.getResultList();
		if(list.size()>0){
			
			entityManager.remove((Person)list.get(0));
			entityManager.getTransaction().commit();
			entityManager.close();
			res.setStatus(true);
		}
		
		else{
			res.setStatus(false);
			
		}
		return res;
		
	}
	
	
	
	@Override
	public Response update(int id){
		
		Response res = new Response();
		List<Person> list = new ArrayList<Person>();
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery("from "+Person.class.getName() +" person where person.id ="+id);
		list = q.getResultList();
		if(list.size()>0){
			
			Person p=(Person)list.get(0);
			p.setName("Ashna Gandhi");
			entityManager.merge(p);
			entityManager.getTransaction().commit();
			entityManager.close();
			res.setStatus(true);
		}
		
		else{
			res.setStatus(false);
			
		}
		return res;	}
}
