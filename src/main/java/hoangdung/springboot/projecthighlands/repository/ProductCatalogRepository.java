package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dto.ProductCatalogDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalogDto, String> {

    List<ProductCatalogDto> findProductCatalogByProductCatalogNameContainingIgnoreCase(String productCatalogName);


}
