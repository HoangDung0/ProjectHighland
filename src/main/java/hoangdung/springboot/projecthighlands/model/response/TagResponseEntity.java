package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dao.Tag;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseEntity implements Transformable {

    private String tagID;

    private String tagName;

    private String tagColor;

    private String textDescription;

    public static TagResponseEntity fromTag(Tag dao){
        return  TagResponseEntity.builder()
                .tagID(dao.getTagID())
                .tagName(dao.getTagName())
                .tagColor(dao.getTagColor())
                .textDescription(dao.getTextDescription())
                .build();
    }

}
