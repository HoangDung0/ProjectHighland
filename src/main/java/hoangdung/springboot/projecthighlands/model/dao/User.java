package hoangdung.springboot.projecthighlands.model.dao;

import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class User implements Transformable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "userID")
    private String id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private LocalDate dayOfBirth;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean sex;

    private LocalDate createDate;

    private boolean activated;

    public enum UserRole{
        NO_ROLE, CUSTOMER, STAFF, ADMIN
    }

}
