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
@DiscriminatorValue("Professor")
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Director {

    @NotEmpty
    @Size(min = 2, max = 100, message = "The faculty name must be between 2 and 100 characters")
    private String faculty;

    @NotEmpty
    @Size(min = 2, max = 100, message = "The department name must be between 2 and 100 characters")
    private String department;

    @NotEmpty
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "The last name must be between 2 and 50 characters")
    private String surname;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_PROFESSOR");
    }
}
