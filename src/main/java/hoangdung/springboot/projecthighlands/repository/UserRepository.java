package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDto, String> {

//    @Query("select user from User user where user.userName = ?1")
    List<UserDto> findUsersByUserNameContainingIgnoreCase(String userName);
}
