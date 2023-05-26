package hoangdung.springboot.projecthighlands.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagRequestEntity {

    private String tagName;

    private String tagColor;

    private String textDescription;
}
