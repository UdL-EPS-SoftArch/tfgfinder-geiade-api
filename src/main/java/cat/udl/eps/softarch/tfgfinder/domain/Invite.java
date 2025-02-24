package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // WHO: Qui està associat a la invitació
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private User who;

}
