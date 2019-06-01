package pl.ee.common.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

/*
    QueueEntity - Kolejka
 */


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "queue", schema = "public", catalog = "queue")
public class QueueEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, name= "short_name")
  private String shortName;

  @Column(name = "access_role", nullable = false)
  private String accessRole;

  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "queueEntity")
  private List<IssueEntity> issueEntities;

  @Column(name = "field_of_study_id", insertable = false, updatable = false)
  private Long fieldOfStudyId;

  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(name = "field_of_study_id")
  private FieldOfStudyEntity fieldOfStudyEntity;

}
