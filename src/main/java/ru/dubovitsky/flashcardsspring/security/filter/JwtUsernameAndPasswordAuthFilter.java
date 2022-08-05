package ru.dubovitsky.flashcardsspring.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.dubovitsky.flashcardsspring.security.config.JwtConfig;
import ru.dubovitsky.flashcardsspring.security.dto.request.UsernameAndPasswordAuthRequest;
import ru.dubovitsky.flashcardsspring.security.dto.response.SuccessfulAuthenticationResponse;
import ru.dubovitsky.flashcardsspring.security.facade.SuccessfulAuthenticationFacade;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

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

    //TODO Додумать этот механизм, слишком запутанный код
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        //! Get token
        String token = createToken(authResult);
        //! Set headers
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //! Set body
        String responseBody = SuccessfulAuthenticationFacade.successfulAuthenticationResponseToJsonString(
                new SuccessfulAuthenticationResponse(token, authResult.getName())
        );
        PrintWriter out = response.getWriter();
        out.print(responseBody);
        out.flush();
    }

    //TODO Вынести в утилитный метод?
    private String createToken(Authentication authResult) {
        //! Create token
        String token = jwtConfig.getTokenPrefix() + Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(28)))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecurityKey().getBytes()))
                .compact();
        return token;
    }
}
