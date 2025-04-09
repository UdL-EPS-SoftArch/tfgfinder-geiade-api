package cat.udl.eps.softarch.tfgfinder.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@EqualsAndHashCode(callSuper=true)
@Data

public class Proposal extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 12, max = 120)
    private String title;

    @NotBlank
    @Size(min = 50, max = 500)
    private String description;

    @NotBlank
    @Size(min = 5, max = 50)
    private String timing;

    @NotBlank
    @Size(min = 5, max = 50)
    private String speciality;

    @NotBlank
    @Size(min = 5, max = 50)
    private String kind;

    @ElementCollection
    private Collection<String> keywords;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @NotNull
    private User user; // Reference to the User entity

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne //Changed to many to one.
    @NotNull
    private Category category;

    //Create a handler to assign the proposal to the current user RoomEventHandler.java
}
