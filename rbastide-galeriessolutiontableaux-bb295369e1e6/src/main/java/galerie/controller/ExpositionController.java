package galerie.controller;

import java.util.List;

import galerie.dao.ArtisteRepository;
import galerie.dao.GalerieRepository;
import galerie.dao.TableauRepository;
import galerie.entity.Exposition;
import galerie.dao.ExpositionRepository;
import galerie.entity.Galerie;
import galerie.entity.Tableau;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import galerie.dto.CaPourExposition;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/exposition")
public class ExpositionController {

    private final ExpositionRepository expositionDAO;
    private final GalerieRepository    galerieDAO;

    public ExpositionController(ExpositionRepository expositionDAO, GalerieRepository galerieDAO) {
        this.expositionDAO = expositionDAO;
        this.galerieDAO    = galerieDAO ;
    }


    @GetMapping(path = "chiffreAffaire")
    public @ResponseBody
    List<CaPourExposition> listeCA() {
        // This returns a JSON or XML with the categories
        return expositionDAO.listeCA();
    }


    /**
     * Affiche toutes les tableaux dans la base
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheTableaux.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesExpositions(Model model) {
        model.addAttribute("tableaux", expositionDAO.findAll());
        return "afficheExposition";
    }

    /**
     * Montre le formulaire permettant d'ajouter un tableau
     * @param Exposition initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @param model pour transmettre des informations à la vie
     * @return le nom de la vue à afficher ('formulaireTableau.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("tableau") Tableau tableau, Model model) {
        // Tableau tableau = new Tableau();
        // model.addAttribute("tableau", tableau) ;
        // On transmet la liste des artistes pour pouvoir choisir l'auteur du tableau
        model.addAttribute("galerie", galerieDAO.findAll());
        return "formulaireExposition";
    }

    /**
     * Appelé par 'formulaireCategorie.html', méthode POST
     * @param Exposition Un tableau initialisé avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des galeries
     */
    @PostMapping(path = "save")
    public String ajouteLExpositionPuisMontreLaListe(Exposition exposition, RedirectAttributes redirectInfo) {
        String message;
        //tableau.setAuteur(auteur);
        // cf. https://www.baeldung.com/spring-data-crud-repository-save
       expositionDAO.save(exposition);
        message = "L'exposition''" + exposition.getIntitule() + "' a été correctement enregistré";
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste
    }

    /**
     * Appelé par le lien 'Supprimer' dans 'afficheExposition.html'
     * @param tableau à partir de l'id du tableau transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le tableau dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des tableaux
     */
    @GetMapping(path = "delete")
    public String supprimerUneExpositionPuisMontreLaListe(@RequestParam("id") Exposition exposition, RedirectAttributes redirectInfo) {
        String message;
        try {
            expositionDAO.delete(exposition); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
            message = "L'Exposition' '" + exposition.getIntitule() + "' a bien été supprimé";
        } catch (DataIntegrityViolationException e) {
            // Par exemple, si l'exposition' est dans appartient à une galérie
            message = "Erreur : Impossible de supprimer L'exposition' '" + exposition.getIntitule() + "'";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }

}
