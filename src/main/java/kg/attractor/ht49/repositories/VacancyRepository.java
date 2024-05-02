package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
}
