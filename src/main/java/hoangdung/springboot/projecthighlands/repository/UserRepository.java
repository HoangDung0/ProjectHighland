package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    @Query("select user from User user where user.userName = ?1")
    List<User> findUsersByUserNameContainingIgnoreCase(String userName);
}
