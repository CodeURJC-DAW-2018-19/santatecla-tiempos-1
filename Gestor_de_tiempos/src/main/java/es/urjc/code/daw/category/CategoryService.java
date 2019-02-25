package es.urjc.code.daw.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category findOneByName(String name) {
		return repository.findByName(name);
	}
	public Category findOne(Long id) {
		return repository.findOne(id);
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public void save(Category book) {
		repository.save(book);
	}

	public void delete(long idCategory) {
		repository.delete(idCategory);
	}
}
