package galerie.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Artiste extends Personne {
    @NotEmpty
    private String biographie;
    
    @OneToMany(mappedBy="auteur")
    @ToString.Exclude
    private List<Tableau> oeuvres = new LinkedList<>();
}
