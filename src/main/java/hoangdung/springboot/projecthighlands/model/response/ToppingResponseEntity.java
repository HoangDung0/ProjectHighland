package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Topping;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToppingResponseEntity implements Tranformable {

    private String toppingID;

    private String toppingName;

    private float price;

    private String description;

    private String thumbnailUrl;

    public static ToppingResponseEntity fromTopping(Topping dao){
        return  ToppingResponseEntity.builder()
                .toppingID(dao.getToppingID())
                .toppingName(dao.getToppingName())
                .price(dao.getPrice())
                .description(dao.getDescription())
                .thumbnailUrl(dao.getThumbnailUrl())
                .build();
    }

}
