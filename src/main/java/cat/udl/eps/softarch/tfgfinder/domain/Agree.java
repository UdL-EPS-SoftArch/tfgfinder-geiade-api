package cat.udl.eps.softarch.tfgfinder.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Table(name = "agrees")
@Data
@EqualsAndHashCode(callSuper = true)
public class Agree extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat
    @NotNull
    private ZonedDateTime when;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Proposal proposal;

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

}