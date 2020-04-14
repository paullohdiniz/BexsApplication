package br.com.bexs.travel.route.engine;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bexs.travel.route.model.Route;
import br.com.bexs.travel.route.service.RouteService;

@Component
public class BestRoute {


	RouteService routeService;
	List<Route> routesList = null;

	@Autowired
	public BestRoute(RouteService routeService) {
		super();
		this.routeService = routeService;
		routesList = this.routeService.findAll();
	}

	public Route myBestRoute(String origin, String destiny) {

		Route bestDirectRoute = this.routesList.stream()
				.filter(e -> (e.getOrigin().equals(origin) && e.getDestine().equals(destiny)))
				.min(Comparator.comparing(Route::getCost))
				.get();

		List<Route> secudaryRoute = this.routesList.stream()
				.filter(e -> (e.getOrigin().equals(origin) && !e.getDestine().equals(destiny)) && e.getCost() < bestDirectRoute.getCost())
				.collect(Collectors.toList());

		if (secudaryRoute.isEmpty())
			return  bestDirectRoute;

		return bestRecursivePath(bestDirectRoute, secudaryRoute);

	}

	private Route bestRecursivePath(Route bestDirectRoute, List<Route> secudaryRoute) {

		List<Route> routeList = null;
		for (Route routeAux : secudaryRoute) {
			Route r = this.routesList.stream()
					.filter(e -> e.getOrigin().equals(routeAux.getDestine())
							&& e.getDestine().equals(bestDirectRoute.getDestine())
							&& e.getCost() + routeAux.getCost() < bestDirectRoute.getCost())
					.min(Comparator.comparing(Route::getCost))
					.get()
					;

			routeList.add(r);
			System.out.println(r);
		}

		Optional<Route> routeMin = routeList.stream().min(Comparator.comparing(Route::getCost));
		return routeMin.get();
	}

}

/*routes.stream()
		.forEach(origi -> routes.stream()
		        .filter(route -> route.hasOrigin(origin) && route.hasDestine(destiny)));*/

		/*routes.stream()
				.forEach(e -> e.hasOrigin(origin) && e.hasDestine(destiny));*/


