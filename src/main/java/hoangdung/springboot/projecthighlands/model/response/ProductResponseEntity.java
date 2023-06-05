package hoangdung.springboot.projecthighlands.model.response;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productID;

    private String productName;

    private String description;

    private float price;

    private String imageUrl;

    private String sizeOptionJsonString;









}
