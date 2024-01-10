package com.manoj.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Manoj!";
    }

    @RequestMapping("hello-html")
    @ResponseBody
    public String sayHelloHtml(){
        return """
                <html>
                <head>
                    <title> Hello title is changed </title>
                </head>
                <body>
                  Html Page!!!!
                </body>
                
                </html>
                """;
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){
        return "sayHello";
    }
}
