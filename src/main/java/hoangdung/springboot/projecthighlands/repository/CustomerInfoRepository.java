package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dao.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, String> {

    @Query("select customerInfo from CustomerInfo customerInfo where customerInfo.user.id = ?1")
    public CustomerInfo getCustomerInfoByUserID(String id);

}
