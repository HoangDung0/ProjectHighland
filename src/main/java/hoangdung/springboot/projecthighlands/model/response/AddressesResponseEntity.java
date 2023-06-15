package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Addresses;
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

    public static AddressesResponseEntity fromAddresses(Addresses dao) {
        return  AddressesResponseEntity.builder()
                .addressID(dao.getAddressesID())
                .addressesName(dao.getAddressesName())
                .address1(dao.getAddress1())
                .address2(dao.getAddress2())
                .address3(dao.getAddress3())
                .address4(dao.getAddress4())
                .phoneNumber(dao.getPhoneNumber())
                .userID(dao.getUserDto().getUserID())
                .build();
    }

}
