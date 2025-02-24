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
public class Student extends User {

    @NotEmpty
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String surname;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "El DNI debe tener 8 números seguidos de una letra")
    private String DNI;

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

    @NotEmpty
    @Size(min = 2, max = 100, message = "El nombre del grado debe tener entre 2 y 100 caracteres")
    private String degree;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities(){
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_STUDENT");
    }
}