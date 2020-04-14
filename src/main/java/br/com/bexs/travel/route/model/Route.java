package br.com.bexs.travel.route.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String origin;
	private String destine;
	private Integer cost;
	
	
	public Route(String origin, String destine, Integer cost) {
		super();
		this.origin = origin;
		this.destine = destine;
		this.cost = cost;
	}
	
	private Route() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestine() {
		return destine;
	}
	public void setDestine(String destine) {
		this.destine = destine;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String toString(){
		return this.getOrigin() + " - " +this.getDestine();
	}
	
}
