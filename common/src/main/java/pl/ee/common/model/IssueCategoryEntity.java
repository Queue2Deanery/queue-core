package pl.ee.common.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
    IssueCategoryEntity - Kategoria sprawy
    Kategoria sprawy wybierana przez każdego oczekującego w kolejce
    Różne kategorie będą definiowane przez Dziekanat
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue_category", schema = "public", catalog = "queue")
public class IssueCategoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "issueCategoryEntity")
  private Set<IssueEntity> issueEntities;

}
