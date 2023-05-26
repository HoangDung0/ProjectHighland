package hoangdung.springboot.projecthighlands.model.dto;

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
@Table(name = "tbl_tag")
public class TagDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tagID;

    private String tagName;

    private String tagColor;

    private String textDescription;
}
