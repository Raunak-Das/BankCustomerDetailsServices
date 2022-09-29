package gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		
		/*return builder.routes()
				.route(r->r.path("/credit/**")
				.uri("http://localhost:8383/"))
				.route(r->r.path("/loan/**")
				.uri("http://localhost:8181/"))
				.build();*/
		
		return builder.routes()
				.route(r->r.path("/bank/**")
				.uri("http://localhost:8858/"))
				.route(r->r.path("/aadhar/**")
				.uri("http://localhost:8856/"))
				.route(r->r.path("/pan/**")
				.uri("http://localhost:8854/"))
				.build();
	}

}
