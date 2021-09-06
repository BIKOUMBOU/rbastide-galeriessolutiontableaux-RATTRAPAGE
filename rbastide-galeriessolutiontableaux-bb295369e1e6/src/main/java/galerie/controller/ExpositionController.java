package galerie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import galerie.dao.ExpositionRepository;
import galerie.dto.CaPourExposition;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/exposition")
public class ExpositionController {

    private final ExpositionRepository dao;

    public ExpositionController(ExpositionRepository dao) {
        this.dao = dao;
    }


    @GetMapping(path = "chiffreAffaire")
    public @ResponseBody
    List<CaPourExposition> listeCA() {
        // This returns a JSON or XML with the categories
        return dao.listeCA();
    }
}
