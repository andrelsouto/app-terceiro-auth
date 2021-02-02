package br.com.andre.appterceiro.filters;

import br.com.andre.appterceiro.client.AutenticacaoClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestAuthFilter implements Filter {

    private static final String AUTORIZACAO_HEADER = "Authorization";
    public static final String CNPJ_HEADER = "x-cnpj";

    private final AutenticacaoClient autenticacaoClient;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try {

            String token = req.getHeader(AUTORIZACAO_HEADER);
            if (Objects.nonNull(token)){

                ResponseEntity responseEntity = autenticacaoClient.podeSeguir(token);
                String cnpj = responseEntity.getHeaders().get(CNPJ_HEADER).get(0);
                log.info("CNPJ Header Response is: " + cnpj);

                req.setAttribute(CNPJ_HEADER, cnpj);

                filterChain.doFilter(servletRequest, servletResponse);
            } else {

                tratarResponse(resp, HttpStatus.UNAUTHORIZED.value());
            }
        } catch (FeignException e) {

            tratarResponse(resp, e.status());
        }
    }

    private void tratarResponse(HttpServletResponse resp, int statusResponse) throws IOException {

        resp.setStatus(statusResponse);
        resp.getWriter().write("Nao autorizado");
        resp.flushBuffer();
    }
}
