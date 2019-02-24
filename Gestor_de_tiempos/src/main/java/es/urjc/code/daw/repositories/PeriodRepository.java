package es.urjc.code.daw.repositories;

import es.urjc.code.daw.models.Category;
import es.urjc.code.daw.models.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodRepository extends JpaRepository<Period, Long> {

    Optional<Period> findByIdPeriod(long idPeriod);
}
