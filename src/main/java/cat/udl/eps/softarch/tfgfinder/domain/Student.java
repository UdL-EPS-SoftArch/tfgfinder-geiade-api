package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@Entity
@DiscriminatorValue("Student")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    @NotEmpty
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters long")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "The surname must be between 2 and 50 characters long")
    private String surname;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "The DNI must have 8 digits followed by a letter")
    private String dni;

    @NotEmpty
    @Size(min = 5, max = 100, message = "The address must be between 5 and 100 characters long")
    private String address;

    @NotEmpty
    @Size(min = 2, max = 50, message = "The municipality must be between 2 and 50 characters long")
    private String municipality;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{5}$", message = "The postal code must contain exactly 5 digits")
    private String postalCode;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$", message = "The phone number must contain exactly 9 digits")
    private String phoneNumber;

    @NotEmpty
    @Size(min = 2, max = 100, message = "The degree name must be between 2 and 100 characters long")
    private String degree;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_STUDENT");
    }
}
