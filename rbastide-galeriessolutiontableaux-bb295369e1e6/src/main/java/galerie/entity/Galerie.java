package galerie.entity;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Galerie {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;
   
    @NonNull
    private String adresse;
    
    @OneToMany(mappedBy = "organisateur")
    List<Exposition> evenements = new LinkedList<>();
    
    public float CAannuel(int annee) {
        float result = 0.0f;
        for (Exposition evenement : evenements)
            if (evenement.getDebut().getYear() == annee)
                result += evenement.CA();
        return result;
        // Peut s'écrire en utilisant l'API Stream 
        // cf. https://www.baeldung.com/java-stream-filter-lambda
        /*
        return evenements.stream()
                .filter( expo -> expo.getDebut().getYear() == annee) // On filtre sur l'annee
                .map(expo -> expo.CA()) // On garde le CA
                .reduce(0f, Float::sum); // On additionne
        */
    }
}
