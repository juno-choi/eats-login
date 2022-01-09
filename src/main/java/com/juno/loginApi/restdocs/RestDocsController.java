package com.juno.loginApi.restdocs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping("/docs")
    public String restDocs(){
        return "/docs/api.html";
    }
}
