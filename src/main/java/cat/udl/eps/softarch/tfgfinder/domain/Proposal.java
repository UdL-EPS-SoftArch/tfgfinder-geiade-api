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

    @ManyToOne
    private User user; // Reference to the User entity

    @ManyToOne
    private Student student;

    @ManyToOne
    private Category category;

    // Mandatory once accepted: at least one professor
    @ManyToOne
    private Professor professor;

    // Optional Co-Director (Director/External/Professor)
    @ManyToOne
    private Director coDirector;

}
