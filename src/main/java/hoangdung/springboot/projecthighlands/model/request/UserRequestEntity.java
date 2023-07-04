package hoangdung.springboot.projecthighlands.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestEntity {

    @NotBlank
    @Length(min = 6)
//    @Pattern(regexp="^[a-zA-Z0-9]$",message="No special characters")
    private String userName;

    @NotBlank
//    @Length(min = 6, max = 18)
//    @Pattern(regexp="^[a-zA-Z0-9]$",message="No special characters")
    private String password;

    //    @Pattern(regexp = "^(\\+84|0)\\d{9}$")
    private String phone;

    @Email
    private String email;

    private LocalDate dayOfBirth;

    private String role;

    private boolean sex;

//    private List<AddressesResponseEntity> listAddresses;

    private LocalDate createDate;

    private boolean activated;

}
