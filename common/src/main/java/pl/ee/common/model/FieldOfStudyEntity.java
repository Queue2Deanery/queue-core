package pl.ee.common.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
@Table(name="field_of_study", schema="public")
public class FieldOfStudyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotNull
  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, name="short_name")
  private String shortName;

  @EqualsAndHashCode.Exclude
  @ManyToOne
  @NotNull
  @JoinColumn(name = "queue_id")
  private QueueEntity queueEntity;

}
