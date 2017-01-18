package ddd.demo.api.controllers;

import ddd.demo.application.order.OrderApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private OrderApplication orderApplication;

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "你好，hello";
    }

    @GetMapping("/thread")
    @ResponseBody
    public String ThreadTest() {

        Thread thread = new Thread(() -> {
            String n = "a1233";

            int a = Integer.parseInt(n);

        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            logger.error(e.getMessage(), e);
        });

        thread.setDaemon(true);
        thread.start();


        return "ok";

    }

}
