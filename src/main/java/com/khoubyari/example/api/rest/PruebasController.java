package com.khoubyari.example.api.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebasController {

	@RequestMapping("hello/{name}")
	public String hello(@PathVariable String name) {
		return "こんにちは " + name + "さん ";
	}
	
	@RequestMapping("hola")
	public String hola() {
		return "hOLA mUNDO";
	}
}