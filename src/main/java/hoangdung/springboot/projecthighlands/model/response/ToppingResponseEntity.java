package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Topping;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToppingResponseEntity implements Transformable {

    private String id;

    private String toppingName;

    private float price;

    private String description;

    private String thumbnailUrl;

    public static ToppingResponseEntity fromTopping(Topping dao){
        return  ToppingResponseEntity.builder()
                .id(dao.getId())
                .toppingName(dao.getToppingName())
                .price(dao.getPrice())
                .description(dao.getDescription())
                .thumbnailUrl(dao.getThumbnailUrl())
                .build();
    }

}
