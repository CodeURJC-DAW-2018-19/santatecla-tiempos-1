package es.urjc.code.daw.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category findOne(String name) {
		return repository.findByName(name);
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public void save(Category book) {
		repository.save(book);
	}
/*
	public void delete(long id) {
		repository.deleteById(id);
	}*/
}
