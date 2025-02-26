package cat.udl.eps.softarch.tfgfinder.domain;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Entity
@Table(name = "interest")
@Data
@EqualsAndHashCode(callSuper = true)
public class Interest extends UriEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat
	private ZonedDateTime when;

	public enum Status {
		PENDING,
		ACCEPTED,
		REJECTED
	}

	@Enumerated (EnumType.STRING)
	private Status status;

	
	// Relations

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
