package covid.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import covid.entity.Country;
import covid.entity.Continent;
import covid.entity.InfoDailyCountry;

@Component
public class RestConfiguration implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// Lister les classes dont on veut exposer les clés dans l'API REST
		config.exposeIdsFor(Country.class, Continent.class, InfoDailyCountry.class);
	}
}