package hoangdung.springboot.projecthighlands.model.dto;

import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_topping")
public class ToppingDto implements Tranformable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String toppingID;

    private String toppingName;

    private float price;

    private String description;

    private String thumbnailUrl;
}
