package pl.ee.external.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ee.common.model.FieldOfStudyEntity;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudyEntity, Long> {
}
