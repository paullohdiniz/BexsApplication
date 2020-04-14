package br.com.bexs.travel.route.service;

import java.util.List;
import java.util.Optional;

import br.com.bexs.travel.route.engine.BestRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bexs.travel.route.model.Route;
import br.com.bexs.travel.route.repository.RouteRepository;

@Service
public class RouteService {

	RouteRepository routeRepository;
	
	@Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
	
	public Optional<Route> findById(Long id) {
		return routeRepository.findById(id);
	}
	
	public Route salve(Route route) {
		return routeRepository.saveAndFlush(route);
	}

	public List<Route> findAll() {
		return routeRepository.findAll();
	}

	public Route findRouteBestCost(String origin, String destiny){

		BestRoute bestRoute = new BestRoute(this);
		return bestRoute.myBestRoute(origin, destiny);
	}
}
