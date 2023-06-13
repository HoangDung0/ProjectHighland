package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.AddressesDto;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressesResponseEntity implements Tranformable {

    private String addressID;

    private String addressesName;

    private String address1;

    private String address2;

    private String address3;

    private String address4;

    private String phoneNumber;

    private String userID;

    public static AddressesResponseEntity fromAddressesDto(AddressesDto dto) {
        return  AddressesResponseEntity.builder()
                .addressID(dto.getAddressesID())
                .addressesName(dto.getAddressesName())
                .address1(dto.getAddress1())
                .address2(dto.getAddress2())
                .address3(dto.getAddress3())
                .address4(dto.getAddress4())
                .phoneNumber(dto.getPhoneNumber())
                .userID(dto.getUserDto().getUserID())
                .build();
    }

}
