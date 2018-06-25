package com.s5.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s5")
public class S5Controller {
	
	@RequestMapping("/boot2")
	public String hello(@RequestParam String name) {
		return "Boot2 "+name;
	}

}
