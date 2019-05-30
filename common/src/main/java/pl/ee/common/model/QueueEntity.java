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
@Table(name="queue", schema ="public")
public class QueueEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, name= "short_name")
  private String shortName;

  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "queueEntity")
  private List<IssueEntity> issueEntities;

}