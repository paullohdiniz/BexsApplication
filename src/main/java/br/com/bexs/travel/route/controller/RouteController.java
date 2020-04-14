package br.com.bexs.travel.route.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bexs.travel.route.dto.RouteDTO;
import br.com.bexs.travel.route.model.Route;
import br.com.bexs.travel.route.service.RouteService;

@RestController
@RequestMapping(RouteController.PATH)
public class RouteController {

	public static final String PATH = "/routes";

	private static final String MSG_OK = "SUCCESSFULLY CREATED";

	@Autowired
	private RouteService routeService;

	@GetMapping()
    public List<Route> Get() {
        return routeService.findAll();
    }

	@GetMapping(path = "/{id}")
	public ResponseEntity<Route> findRouteById(@PathVariable(value = "id") long id) {

		Optional<Route> route = routeService.findById(id);

		if (route.isPresent())
			return new ResponseEntity<Route>(route.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Route> salvar(@RequestBody RouteDTO dto) {
		Route route = routeService.salve(dto.transformeToObjeto());
		return new ResponseEntity<>(route, HttpStatus.CREATED);
	}

    @PostMapping(path = "/list")
    public ResponseEntity<String> salvar(@RequestBody List<RouteDTO> dtoList) {
        for (RouteDTO rt: dtoList) {
            Route route = routeService.salve(rt.transformeToObjeto());
        }
	    return new ResponseEntity<>(MSG_OK, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/route/{origin}/{destiny}/{cost}", method = RequestMethod.POST)
	public ResponseEntity<Route> salvar(@PathVariable(value = "origin") String origin, @PathVariable(value = "destiny") String destiny, @PathVariable(value = "cost") Integer cost) {
		RouteDTO routeDTO = new RouteDTO(origin, destiny, cost);
		Route route = routeService.salve(routeDTO.transformeToObjeto());
		return new ResponseEntity<>(route, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/route/{origin}/{destiny}", method = RequestMethod.GET)
	public ResponseEntity<Route> getBestCost(@PathVariable(value = "origin") String origin, @PathVariable(value = "destiny") String destiny) {
		Route route = routeService.findRouteBestCost(origin, destiny);
		return new ResponseEntity<>(route, HttpStatus.CREATED);
	}

}
