package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.AddressesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressesRepository extends JpaRepository<AddressesDto, String> {

    @Query("select dto from AddressesDto dto where dto.userDto.userID = ?1" )
    List<AddressesDto> getListAddressesByUserID(String id);

}