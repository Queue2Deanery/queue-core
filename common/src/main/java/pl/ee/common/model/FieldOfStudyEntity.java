package pl.ee.common.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
    FieldOfStudyEntity - Nazwa wydziału
    np Eletrotechnika, AiR, Informatyka
    Zawiera nazwę oraz kolejkę, do której będą zapisywać się studenci danego wydziału
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "field_of_study", schema = "public", catalog = "queue")
public class FieldOfStudyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private String faculty;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, name="short_name")
  private String shortName;

  @Column(name = "queue_id", insertable = false, updatable = false)
  private Long queueId;

  @EqualsAndHashCode.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "queue_id")
  private QueueEntity queueEntity;

  @Column(name = "access_role", nullable = false)
  private String accessRole;
}
