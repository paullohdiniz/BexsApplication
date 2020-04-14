package br.com.bexs.travel.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bexs.travel.route.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long>{

}
