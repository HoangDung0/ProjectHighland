package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.CustomerInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfoDto, String> {

    @Query("select dto from CustomerInfoDto dto where dto.userDto.userID = ?1")
    public CustomerInfoDto getCustomerInfoByUserID(String id);

}
