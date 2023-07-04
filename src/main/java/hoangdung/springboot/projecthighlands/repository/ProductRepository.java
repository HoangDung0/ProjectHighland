package hoangdung.springboot.projecthighlands.repository;

import hoangdung.springboot.projecthighlands.model.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

//    @Query("select product from Product product where product.id = ?1")
//    List<Product> searchProductByProductName(String name);

    List<Product> getProductsByProductNameIgnoreCase(String name);

}
