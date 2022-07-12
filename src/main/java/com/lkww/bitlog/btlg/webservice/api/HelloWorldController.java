package com.lkww.bitlog.btlg.webservice.api;

import com.lkww.scaffold.application.api.HelloWorldApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class HelloWorldController{
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello World");
    }
}
