package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.ToppingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToppingResponseEntity {

    private String toppingID;

    private String toppingName;

    private float price;

    private String description;

    private String thumbnailUrl;

    public static ToppingResponseEntity fromToppingDto(ToppingDto dto){
        return  ToppingResponseEntity.builder()
                .toppingID(dto.getToppingID())
                .toppingName(dto.getToppingName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .thumbnailUrl(dto.getThumbnailUrl())
                .build();
    }

}
