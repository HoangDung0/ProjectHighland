package hoangdung.springboot.projecthighlands.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
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
public class CustomerInfo implements Transformable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String customerID;

    private int point;

    private int rank;

    private String usedCouponJsonString;

    private String cardInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;
}