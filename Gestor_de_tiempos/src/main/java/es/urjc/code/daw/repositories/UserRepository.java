package es.urjc.code.daw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.models.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);

	Optional<User> findByIdUser(long idUser);
}