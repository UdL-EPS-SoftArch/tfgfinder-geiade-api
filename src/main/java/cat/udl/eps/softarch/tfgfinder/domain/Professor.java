package cat.udl.eps.softarch.tfgfinder.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Director {

    @NotEmpty
    private String faculty;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_PROFESSOR");
    }
}