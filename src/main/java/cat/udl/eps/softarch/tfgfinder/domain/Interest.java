package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Interest extends UriEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat
	private ZonedDateTime createdDate;

	public enum Status {
		PENDING,
		ACCEPTED,
		REJECTED
	}

	@Enumerated (EnumType.STRING)
	private Status status;

	// Relations

	@ManyToOne
	private User user;

	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	private Proposal proposal;
}
