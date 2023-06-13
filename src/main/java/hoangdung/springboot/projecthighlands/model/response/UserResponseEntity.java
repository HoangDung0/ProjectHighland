package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.UserDto;
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

//    private List<AddressesDto> listAddressesDto;

    private Date createDate;

    private boolean activated;


    public static UserResponseEntity fromUserDto(UserDto dto)  {
//        List<AddressesDto> addressesDtoList = AddressesService.convertListAddressesIDToListAddresses(dto.getListAddressesID());
        return  UserResponseEntity.builder()
                .userID(dto.getUserID())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .dayOfBirth(dto.getDayOfBirth())
                .role(dto.getRole().toString())
                .sex(dto.isSex())
//                .listAddressesDto(addressesDtoList)
                .createDate(dto.getCreateDate())
                .activated(dto.isActivated())
                .build();
    }



}
