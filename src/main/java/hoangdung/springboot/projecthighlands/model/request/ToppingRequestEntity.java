package hoangdung.springboot.projecthighlands.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToppingRequestEntity {

    private String toppingName;

    private float price;

    private String description;

    private String thumbnailUrl;
}
