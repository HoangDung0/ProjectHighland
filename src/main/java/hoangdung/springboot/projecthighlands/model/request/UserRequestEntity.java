package hoangdung.springboot.projecthighlands.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestEntity {

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Date dayOfBirth;

    private String role;

    private boolean sex;

//    private List<AddressesResponseEntity> listAddresses;

    private Date createDate;

    private boolean activated;

}
