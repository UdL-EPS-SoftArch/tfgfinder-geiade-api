package cat.udl.eps.softarch.tfgfinder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class External extends Director {

    @NotEmpty
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "The last name must be between 2 and 50 characters")
    private String surname;

    @NotEmpty
    @Size(min = 2, max = 100, message = "The position must be between 2 and 100 characters")
    private String position;

    @NotEmpty
    @Size(min = 2, max = 100, message = "The organization must be between 2 and 100 characters")
    private String organization;

    @NotEmpty
    @Size(min = 5, max = 100, message = "The address must be between 5 and 100 characters")
    private String address;

    @NotEmpty
    @Size(min = 2, max = 50, message = "The municipality must be between 2 and 50 characters")
    private String municipality;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{5}$", message = "The postal code must contain exactly 5 digits")
    private String postalCode;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$", message = "The phone number must contain exactly 9 digits")
    private String phoneNumber;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_EXTERNAL");
    }
}
