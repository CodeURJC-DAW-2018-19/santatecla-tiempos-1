package es.urjc.code.daw.controllers;


import es.urjc.code.daw.user.User;
import es.urjc.code.daw.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataBaseLoader {

    @Autowired private UserRepository userRepository;
    @PostConstruct
    public void init(){

        //Para poder guardar los datos primero se crea el objeto "user1" de la clase 1 y se inicializa con los valores de cada campo.
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
        
        
        
        
        
       
    }
}