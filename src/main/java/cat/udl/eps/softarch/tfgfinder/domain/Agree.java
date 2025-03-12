package cat.udl.eps.softarch.tfgfinder.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Data
public class Agree extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat
    private ZonedDateTime agreeTime;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    private Status status;

}