package es.urjc.code.daw.controllers;


import es.urjc.code.daw.repositories.*;
import es.urjc.code.daw.user.Category;
import es.urjc.code.daw.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataBaseLoader {

    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;

    @PostConstruct
    public void init(){
    	//USER
        User user1 =  new User("administrador", "administrador@gmail.com", "1234" , "ROLE_ADMIN");
        User user2 =  new User("Silvia", "silvia@gmail.com", "1234" , "ROLE_STUDENT");
        User user3 =  new User("Miguel", "miguel@gmail.com", "1234" , "ROLE_STUDENT");
        User user4 =  new User("Noelia", "noelia@gmail.com", "1234" , "ROLE_STUDENT");
        if(userRepository.findByEmail("administrador@gmail.com")==null)
        	user1=userRepository.save(user1);
        user1=userRepository.findByEmail("administrador@gmail.com");
        if(userRepository.findByEmail("silvia@gmail.com")==null)
	        user2=userRepository.save(user2);
        user2=userRepository.findByEmail("silvia@gmail.com");
        if(userRepository.findByEmail("miguel@gmail.com")==null)
	        user3=userRepository.save(user3);
        user3=userRepository.findByEmail("miguel@gmail.com");
        if(userRepository.findByEmail("noelia@gmail.com")==null)
	        user4=userRepository.save(user4);
        user4=userRepository.findByEmail("noelia@gmail.com");    
        //CATEGORY
        Category category1 = new Category("Categoria1");
        Category category2 = new Category("Categoria2");
        Category category3 = new Category("Categoria3");
        Category category4 = new Category("Categoria4");

        if (categoryRepository.findByName("Categoria1")==null) {
        	category1=categoryRepository.save(category1);
        }
        category1=categoryRepository.findByName("Categoria1");
        
        if (categoryRepository.findByName("Categoria2")==null) {
        	category2=categoryRepository.save(category2);
        }
        category2=categoryRepository.findByName("Categoria2");
       if (categoryRepository.findByName("Categoria3")==null) {
        	category3=categoryRepository.save(category3);
        }
        category3=categoryRepository.findByName("Categoria3");
        if (categoryRepository.findByName("Categoria4")==null) {
        	category4=categoryRepository.save(category4);
        }
        category4=categoryRepository.findByName("Categoria4");

    }
}