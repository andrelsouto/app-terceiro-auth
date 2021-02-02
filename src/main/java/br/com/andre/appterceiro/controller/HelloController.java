package br.com.andre.appterceiro.controller;

import br.com.andre.appterceiro.filters.RequestAuthFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/hello")
@RestController
public class HelloController {

    @GetMapping
    public ResponseEntity hello(@RequestAttribute(RequestAuthFilter.CNPJ_HEADER) String cnpj) {
        return ResponseEntity.ok("CNPJ: " + cnpj);
    }

}
