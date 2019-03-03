package es.urjc.code.daw.interval;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, Long> {
	Interval findByName(String name);
	Interval findByIdInterval(Long idInterval);
}
