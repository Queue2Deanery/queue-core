package pl.ee.authentication.domain.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="revoked_jwt_token", schema="public")
public class RevokedJwtTokenEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, name = "student_index")
  private String studentIndex;

  @Builder.Default
  @Column(nullable = false, name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(nullable = false, name = "jwt_token", unique = true)
  private String jwtToken;

  @Column(nullable = false, name = "ip_address")
  private String ipAddress;
}
