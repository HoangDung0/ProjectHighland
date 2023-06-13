package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import hoangdung.springboot.projecthighlands.model.dto.AddressesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressesRepository extends JpaRepository<AddressesDto, String> {

    @Query("select address from AddressesDto address where address.userDto.userID = ?1" )
    List<Tranformable> getListAddressesByUserID(String id);


}