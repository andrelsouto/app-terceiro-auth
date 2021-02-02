package br.com.andre.appterceiro.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${autenticacao-service.nome}", url = "${autenticacao-service.url}")
public interface AutenticacaoClient {

    @PostMapping("/api/autenticacao/autenticado")
    ResponseEntity podeSeguir(@RequestHeader("Authorization") String jwtToken);

}
