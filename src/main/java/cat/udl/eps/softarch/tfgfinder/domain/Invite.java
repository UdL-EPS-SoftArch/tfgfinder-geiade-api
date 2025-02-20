package cat.udl.eps.softarch.tfgfinder.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)

public class Invite extends UriEntity<Long>  {

    @Id
    private Long id;

    @NotNull
    private ZonedDateTime when;

    @NotBlank
    private String status;

}
