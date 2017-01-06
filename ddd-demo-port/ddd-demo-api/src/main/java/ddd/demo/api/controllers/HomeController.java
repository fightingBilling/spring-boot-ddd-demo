package ddd.demo.api.controllers;

import ddd.demo.application.order.OrderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OrderApplication orderApplication;

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "你好，hello";
    }
}
