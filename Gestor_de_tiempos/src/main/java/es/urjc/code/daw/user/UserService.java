package es.urjc.code.daw.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public User findOne(String name) {
		return repository.findByEmail(name);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public void save(User user) {
		repository.save(user);
	}
/*
	public void delete(long id) {
		repository.deleteById(id);
	}*/
}
