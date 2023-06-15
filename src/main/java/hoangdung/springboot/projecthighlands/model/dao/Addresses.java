package hoangdung.springboot.projecthighlands.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
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
@Table(name = "tbl_addresses")
public class Addresses implements Tranformable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String addressesID;

    private String addressesName;

    private String address1;

    private String address2;

    private String address3;

    private String address4;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

}
