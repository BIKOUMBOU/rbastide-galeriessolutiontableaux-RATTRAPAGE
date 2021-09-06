package galerie.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import galerie.entity.Galerie;

@Component
/**
 * Configuration pour que les clés primaires soient automatiquement exposées
 * par l'API REST (pas obligatoire, mais commode).
 * Par défaut, l'API expose seulement le lien 'self'
 */
public class RestConfiguration implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// Lister les classes dont on veut exposer les clés dans l'API REST
		config.exposeIdsFor(Galerie.class);
	}
}