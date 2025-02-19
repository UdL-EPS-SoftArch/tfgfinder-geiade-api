package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Table(name = "Agrees")
@Data
@EqualsAndHashCode(callSuper = true)
public class Agree extends UriEntity<String> {

    @Id
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


    public String getId() { return id; }

    public void setAgree(String invite) { this.id = invite; }


    public ZonedDateTime getWhen() { return when; }

    public void setWhen(ZonedDateTime when) { this.when = when; }


    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}