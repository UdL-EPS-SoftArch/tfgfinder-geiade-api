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
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String surname;

    @NotEmpty
    @Size(min = 2, max = 100, message = "El cargo debe tener entre 2 y 100 caracteres")
    private String position;

    @NotEmpty
    @Size(min = 2, max = 100, message = "La organización debe tener entre 2 y 100 caracteres")
    private String organization;

    @NotEmpty
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String address;

    @NotEmpty
    @Size(min = 2, max = 50, message = "El municipio debe tener entre 2 y 50 caracteres")
    private String municipality;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{5}$", message = "El código postal debe contener exactamente 5 dígitos")
    private String postalCode;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$", message = "El número de teléfono debe contener exactamente 9 dígitos")
    private String phoneNumber;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_EXTERNAL");
    }
}