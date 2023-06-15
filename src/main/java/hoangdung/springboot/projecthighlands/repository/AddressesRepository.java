package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import hoangdung.springboot.projecthighlands.model.dao.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, String> {

    @Query("select address from Addresses address where address.user.userID = ?1" )
    List<Tranformable> getListAddressesByUserID(String id);


}