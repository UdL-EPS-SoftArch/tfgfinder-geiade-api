package cat.udl.eps.softarch.tfgfinder.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Table(name = "agrees")
@Data
@EqualsAndHashCode(callSuper = true)
public class Agree extends UriEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @DateTimeFormat
    private ZonedDateTime when;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    private Status status;

}