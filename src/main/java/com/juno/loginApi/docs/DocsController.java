package com.juno.loginApi.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {
    @GetMapping("/docs")
    public String docs(){
        return "/docs/api.html";
    }
}
