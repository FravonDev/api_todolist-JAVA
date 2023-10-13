package br.com.flavioadriano.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.flavioadriano.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // toda classe que é gerenciada pelo spring precisa ter essa anotação
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // executar alguma ação como barrar a requisição ou permitir a requisição

        var serveletPath = request.getServletPath();
        System.out.println("doFilterInternal" + serveletPath);

        if (serveletPath.startsWith("/tasks/")) {
            System.out.println("doFilterInternal: " + serveletPath);
            // pegar usuario e senha
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();
            System.out.println("authEncoded: " + authEncoded);

            // validar usuario
            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecoded);
            System.out.println("authDecoded: " + authString);

            String[] credentials = authString.split(":");
            var username = credentials[0];
            var password = credentials[1];
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            // validar senha

            // se tudo estiver ok, permitir a requisição
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
                return;
            } else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(),
                        user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}