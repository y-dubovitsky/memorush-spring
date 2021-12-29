package ru.dubovitsky.flashcardsspring.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.dubovitsky.flashcardsspring.security.filter.request.UsernameAndPasswordAuthRequest;
import ru.dubovitsky.flashcardsspring.security.filter.response.TokenVerifierFilterResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager1) {
        this.authenticationManager = authenticationManager1;
    }

    //TODO Вынести в отдельный конфиг
    private final String key = "ru.dubovitsky.flashcardsspring.security.filter.JwtUsernameAndPasswordAuthFilter@1997e7b2, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@c0ee2c9";

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthRequest req = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    req.getUsername(),
                    req.getPassword(),
                    null
            );

            return authenticationManager.authenticate(authentication); //! FIXME
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(28)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(this.getJsonTokenVerifierFilterResponse("Bearer " + token, authResult.getName()));
        out.flush();
    }

    private String getJsonTokenVerifierFilterResponse(String token, String username) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String tokenVerifierFilterResponse
                = gson.toJson(new TokenVerifierFilterResponse(token, username));

        return tokenVerifierFilterResponse;
    }
}
