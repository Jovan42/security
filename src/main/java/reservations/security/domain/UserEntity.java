package reservations.security.domain;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "sec")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return id == that.id
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
