package com.dogo.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("math/add/{x}/{y}")
	public int getMath(@PathVariable("x") int x, @PathVariable("y") int y) {
		return x + y;
	}
}
