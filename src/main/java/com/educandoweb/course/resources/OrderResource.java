package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Order> create(@RequestBody Order orderRequest, UriComponentsBuilder uriBuilder) {
		Order orderResponse = service.create(orderRequest);
		URI uri = uriBuilder.path("orders/{id}").buildAndExpand(orderResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(orderResponse);
	}
	
	
}
