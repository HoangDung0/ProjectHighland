package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseEntity implements Transformable {

    private String id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private LocalDate dayOfBirth;

    private String role;

    private boolean sex;

    private LocalDate createDate;

    private boolean activated;


    public static UserResponseEntity fromUser(User dao)  {
//        List<AddressesDto> addressesDtoList = AddressesService.convertListAddressesIDToListAddresses(dao.getListAddressesID());
        return  UserResponseEntity.builder()
                .id(dao.getId())
                .userName(dao.getUserName())
                .password(dao.getPassword())
                .phone(dao.getPhone())
                .email(dao.getEmail())
                .dayOfBirth(dao.getDayOfBirth())
                .role(dao.getRole().toString())
                .sex(dao.isSex())
                .createDate(dao.getCreateDate())
                .activated(dao.isActivated())
                .build();
    }



}
