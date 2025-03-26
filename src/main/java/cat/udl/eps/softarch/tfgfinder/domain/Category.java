package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@EqualsAndHashCode(callSuper=true)
@Data

public class Category extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String name;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Size(min = 50, max = 500)
    private String description;

}
