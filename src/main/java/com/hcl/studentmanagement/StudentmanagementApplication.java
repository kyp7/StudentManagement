package com.hcl.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;  

@SpringBootApplication
@EnableJpaAuditing
public class StudentmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);
		/*StandardServiceRegistry ssr =  new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		
		SessionFactory factory = meta.getSessionFactoryBuilder().build();  
		Session session = factory.openSession();  
		Transaction t = session.beginTransaction();
		
		Student s1 = new Student();
		//s1.setId(1);
		s1.setFirstName("Kairav");
		s1.setLastName("Pate");
		s1.setEmail("k@gmai.com");
		s1.setPassword("ab1234");
		
		session.delete(s1);
		t.commit();
		System.out.println("Saved succesfully");
		factory.close();
		session.close();*/
	}
	
}
