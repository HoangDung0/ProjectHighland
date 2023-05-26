package hoangdung.springboot.projecthighlands.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userID;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Date dayOfBirth;

    private UserRole role;

    private boolean sex;

//    private String listAddressesID;

    private Date createDate;

    private boolean activated;

    public enum UserRole{
        NO_ROLE, CUSTOMER, STAFF, ADMIN
    }

}
