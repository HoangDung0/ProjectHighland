package hoangdung.springboot.projecthighlands.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressesRequestEntity {

    private String addressesName;

    private String address1;

    private String address2;

    private String address3;

    private String address4;

    private String phoneNumber;

    private String userID;


}
