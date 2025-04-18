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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ZonedDateTime inviteDate;

    @NotBlank
    private String status;

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private User who;

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Proposal what;

}
