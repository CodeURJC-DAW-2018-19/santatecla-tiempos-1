package es.urjc.code.daw;


import es.urjc.code.daw.category.Category;
import es.urjc.code.daw.category.CategoryRepository;
import es.urjc.code.daw.interval.Interval;
import es.urjc.code.daw.interval.IntervalRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;
import es.urjc.code.daw.event.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataBaseLoader {
	
    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private IntervalRepository intervalRepository;
    @Autowired private EventRepository eventRepository;


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
        Interval padre1 = new Interval("Intervalo Padre 1","01/01/1989","01/01/1996");
        intervalRepository.save(padre1);
        Interval [] intervals = {
        		new Interval("Intervalo 1","23/12/1989","23/11/1994"),
        		new Interval("Intervalo 2","2/10/1999","3/11/1995"),
        		new Interval("Intervalo 3","23/12/1989","23/11/1994"),
        		new Interval("Intervalo 4","2/10/1999","3/11/1995"),
        		new Interval("Intervalo 5","23/12/1989","23/11/1994"),
        		new Interval("Intervalo 6","2/10/1999","3/11/1995"),
        };
        for(int i=0;i<intervals.length;i++){
        	if (intervalRepository.findByName(intervals[i].getName())==null)
                intervals[i].setParent(padre1);
        		//padre1.addChild(intervals[i]);
        		intervalRepository.save(intervals[i]);
        }
        
        Interval padre2 = new Interval("Intervalo Padre 2","01/01/2000","01/01/2005");
        intervalRepository.save(padre2);
        Interval [] intervals2 = {
        		new Interval("Intervalo 7","23/12/2001","23/11/2004"),
        		new Interval("Intervalo 8","2/10/2003","3/11/2003"),
        		new Interval("Intervalo 9","2/10/2003","3/11/2003"),
        		new Interval("Intervalo 10","2/10/2003","3/11/2003"),
        		new Interval("Intervalo 11","2/10/2003","3/11/2003"),
        		new Interval("Intervalo 12","2/10/2003","3/11/2003"),
        		new Interval("Intervalo 13","2/10/2003","3/11/2003")
        };
        for(int i=0;i<intervals2.length;i++){
        	if (intervalRepository.findByName(intervals2[i].getName())==null)
                intervals2[i].setParent(padre2);
        		//padre1.addChild(intervals[i]);
        		intervalRepository.save(intervals2[i]);
        }

        //EVENTS
        Event [] events = {new Event("Evento 1","Wiki 1", "Date 1"),
        		new Event("Evento 2","Wiki 2", "Date 2"),
        		new Event("Evento 3","Wiki 3", "Date 3"),
        		new Event("Evento 4","Wiki 4", "Date 4"),
        		new Event("Evento 5","Wiki 5", "Date 5"),
        		new Event("Evento 6","Wiki 6", "Date 6"),
        		new Event("Evento 7","Wiki 7", "Date 7"),
        		new Event("Evento 8","Wiki 7", "Date 7"),
        		new Event("Evento 9","Wiki 7", "Date 7"),
        		new Event("Evento 10","Wiki 7", "Date 7"),
        		new Event("Evento 11","Wiki 7", "Date 7"),
        		new Event("Evento 12","Wiki 7", "Date 7"),
        		new Event("Evento 13","Wiki 7", "Date 7")
        };
        for(int i=0;i<events.length;i++){
        	if (eventRepository.findByName(events[i].getName())==null)
        		eventRepository.save(events[i]);
        }
    }
}