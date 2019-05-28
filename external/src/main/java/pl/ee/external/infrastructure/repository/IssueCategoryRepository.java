package pl.ee.external.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ee.common.model.IssueCategoryEntity;

@Repository
public interface IssueCategoryRepository extends JpaRepository<IssueCategoryEntity, Long> {
}
