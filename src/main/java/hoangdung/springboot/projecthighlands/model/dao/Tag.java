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
@Table(name = "tbl_tag")
public class Tag implements Tranformable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tagID;

    private String tagName;

    private String tagColor;

    private String textDescription;
}
