package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.User;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseEntity implements Tranformable {

    private String userID;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Date dayOfBirth;

    private String role;

    private boolean sex;

    private Date createDate;

    private boolean activated;


    public static UserResponseEntity fromUser(User dao)  {
//        List<AddressesDto> addressesDtoList = AddressesService.convertListAddressesIDToListAddresses(dao.getListAddressesID());
        return  UserResponseEntity.builder()
                .userID(dao.getUserID())
                .userName(dao.getUserName())
                .password(dao.getPassword())
                .phone(dao.getPhone())
                .email(dao.getEmail())
                .dayOfBirth(dao.getDayOfBirth())
                .role(dao.getRole().toString())
                .sex(dao.isSex())
//                .listAddressesDto(addressesDtoList)
                .createDate(dao.getCreateDate())
                .activated(dao.isActivated())
                .build();
    }



}
