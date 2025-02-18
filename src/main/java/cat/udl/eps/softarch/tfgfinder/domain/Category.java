package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@EqualsAndHashCode(callSuper=true)
@Data

public class Category extends UriEntity<Long> {

    @Id
    @GeneratedValue()
    private Long id;


    @NotBlank
    private String name;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

}
