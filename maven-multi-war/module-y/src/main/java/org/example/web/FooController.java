package org.example.web;

import org.example.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FooController
{
  @Autowired
  private FooService service;

  @RequestMapping("/")
  @ResponseBody
  public String home()
  {
    return service.greet();
  }
}
