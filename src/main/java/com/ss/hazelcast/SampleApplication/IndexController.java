package com.ss.hazelcast.SampleApplication;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;


    @GetMapping("/index")
    public String index(Model model) {

        // add `message` attribute
        model.addAttribute("message", "Thank you for visiting.");

        // return view name
        return "index";
    }
}
