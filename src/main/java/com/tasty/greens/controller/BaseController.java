package com.tasty.greens.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

	@GetMapping(path = "health")
	public String healthCheck() {
		return "instagrocer-inventory-service up and running";
	}
}
