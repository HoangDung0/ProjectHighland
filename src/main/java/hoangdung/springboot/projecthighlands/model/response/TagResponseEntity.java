package hoangdung.springboot.projecthighlands.model.response;

import hoangdung.springboot.projecthighlands.model.dto.TagDto;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseEntity implements Tranformable {

    private String tagID;

    private String tagName;

    private String tagColor;

    private String textDescription;

    public static TagResponseEntity fromTagDto(TagDto dto){
        return  TagResponseEntity.builder()
                .tagID(dto.getTagID())
                .tagName(dto.getTagName())
                .tagColor(dto.getTagColor())
                .textDescription(dto.getTextDescription())
                .build();
    }

}
