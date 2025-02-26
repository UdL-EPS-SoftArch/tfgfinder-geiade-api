package cat.udl.eps.softarch.tfgfinder.domain;

import java.util.Collection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@EqualsAndHashCode(callSuper=true)
@Data

public class Proposal extends UriEntity<Long> {

    @Id
    @GeneratedValue()
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

    @ManyToOne
    private User user; // Reference to the User entity

    // Relationship with Directors (0 to 2)
    @ManyToMany
    @JoinTable(
            name = "proposal_directors",
            joinColumns = @JoinColumn(name = "proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    @Size(max = 2, message = "A proposal can have at most 2 directors")
    private List<Director> directedByDirectors;

    // Relationship with Professors (0 to 2)
    @ManyToMany
    @JoinTable(
            name = "proposal_professors",
            joinColumns = @JoinColumn(name = "proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @Size(max = 2, message = "A proposal can have at most 2 professors")
    private List<Professor> directedByProfessors;

    // Relationship with External (0 to 1)
    @ManyToOne
    @JoinColumn(name = "external_id")
    private External directedByExternal;

}