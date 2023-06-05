package hoangdung.springboot.projecthighlands.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_customerinfo")
public class CustomerInfoDto {

    @Id
    private String customerID;

    private int point;

    private int rank;

    private String usedCouponJsonString;

    private String cardInfo;

    @OneToOne(mappedBy = "userID", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserDto userDto;
}
