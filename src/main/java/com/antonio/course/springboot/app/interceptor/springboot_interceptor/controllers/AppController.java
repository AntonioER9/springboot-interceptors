package com.antonio.course.springboot.app.interceptor.springboot_interceptor.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/app")
public class AppController {

  @GetMapping("/foo")
  public Map<String, String> foo() {
    return Collections.singletonMap("message", "handler foo of controller");
  }

  @GetMapping("/bar")
  public Map<String, String> bar() {
    return Collections.singletonMap("message", "handler bar of controller");
  }

  @GetMapping("/baz")
  public Map<String, String> baz() {
    return Collections.singletonMap("message", "handler baz of controller");
  }

}
