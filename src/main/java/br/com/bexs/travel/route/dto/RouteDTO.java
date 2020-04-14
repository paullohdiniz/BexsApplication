package br.com.bexs.travel.route.dto;

import br.com.bexs.travel.route.model.Route;

public class RouteDTO {
	
	private String origin;
	private String destiny;
	private Integer cost;
	
	
	
	public RouteDTO(String origem, String destiny, Integer cost) {
		super();
		this.origin = origem;
		this.destiny = destiny;
		this.cost = cost;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestiny() {
		return destiny;
	}
	public void setDestine(String destiny) {
		this.destiny = destiny;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public Route transformeToObjeto(){
	    return new Route(origin, destiny, cost);
	}

}
