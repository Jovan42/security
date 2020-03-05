package reservations.security.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authorization_code", schema = "sec")
@Getter
@Setter
public class AuthorizationCodeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "value", nullable = false, length = 128)
    private String value;

    @Basic
    @Column(name = "expires", nullable = false)
    private LocalDateTime expires;

    @OneToOne private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizationCodeEntity that = (AuthorizationCodeEntity) o;
        return id == that.id
                && Objects.equals(value, that.value)
                && Objects.equals(expires, that.expires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, expires);
    }
}
