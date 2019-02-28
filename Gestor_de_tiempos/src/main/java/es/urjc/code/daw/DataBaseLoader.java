package es.urjc.code.daw;


import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import es.urjc.code.daw.interval.Interval;
import es.urjc.code.daw.interval.IntervalRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataBaseLoader {
	
    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private IntervalRepository intervalRepository;

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
        Category[] cats={new Category("Categoria1"),
        new Category("Categoria2"),
        new Category("Categoria3"),
        new Category("Categoria4"),
        new Category("Categoria5"),
        new Category("Categoria6"),
        new Category("Categoria7"),
        new Category("Categoria8"),
        new Category("Categoria9"),
        new Category("Categoria10"),
        new Category("Categoria11"),
        new Category("Categoria12"),
        new Category("Categoria13"),
        new Category("Categoria14"),
        new Category("Categoria15"),
        new Category("Categoria16"),
        new Category("Categoria17"),
        new Category("Categoria18"),
        new Category("Categoria19"),
        new Category("Categoria20"),
        new Category("Categoria21"),
        new Category("Categoria22"),
        new Category("Categoria23"),
        new Category("Categoria24")};
        
        for(int i=0;i<cats.length;i++){
        	if (categoryRepository.findByName(cats[i].getName())==null)
        		categoryRepository.save(cats[i]);
        }
        //INTERVAL
        Interval i1 = new Interval("Intervalo1","23/12/1989","23/11/1994");
        Interval i2 = new Interval("Intervalo2","2/10/1999","3/11/1995");

        if (intervalRepository.findByName("Intervalo1")==null) {
        	i1=intervalRepository.save(i1);
        }
        i1=intervalRepository.findByName("Intervalo1");
        
        if (intervalRepository.findByName("Intervalo2")==null) {
        	i2=intervalRepository.save(i2);
        }
        i2=intervalRepository.findByName("Intervalo2");
    }
}