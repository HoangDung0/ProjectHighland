package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.ToppingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRepository extends JpaRepository<ToppingDto, String> {

    List<ToppingDto> findToppingsByToppingNameContainingIgnoreCase(String toppingName);


}
