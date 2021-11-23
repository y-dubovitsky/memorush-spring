package ru.dubovitsky.flashcardsspring.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dubovitsky.flashcardsspring.model.AppUser;
import ru.dubovitsky.flashcardsspring.model.Role;
import ru.dubovitsky.flashcardsspring.service.AppUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InvalidObjectException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserResource {

    private final AppUserService appUserService;

    @GetMapping("/user/all")
    public ResponseEntity<List<AppUser>> getAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveAppUser(@RequestBody AppUser appUser) {
        AppUser savedAppUser = appUserService.saveAppUser(appUser);
        return ResponseEntity.created(getUriForEndpoint("/api/user/save")).body(savedAppUser);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveAppUser(@RequestBody Role role) {
        Role savedRole = appUserService.saveRole(role);
        return ResponseEntity.created(getUriForEndpoint("/api/role/save")).body(savedRole);
    }

    @PostMapping("/user/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody AppUserWithRoleForm form) {
        appUserService.addRoleToAppUser(form.username, form.role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("flashcards".getBytes()); //FIXME Вынести в утилиты и константы в настройки @Value
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.getAppUser(username);

                String accessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withClaim("roles", appUser.getRoles()
                                .stream()
                                .map(role -> role.getName())
                                .collect(Collectors.toList())
                        )
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withIssuedAt(new Date(System.currentTimeMillis()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap();
                tokens.put("accessToken", accessToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens); //TODO Посмотреть подробнее как работает
            } catch (Exception e) {
                log.error("Invalid refresh token");
                throw new RuntimeException("Invalid refresh token");
            }
        }
    }

    private URI getUriForEndpoint(String endpoint) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(endpoint).toUriString());
        return uri;
    }
}

@Data
class AppUserWithRoleForm { //TODO Фасад или Request?
    String username;
    String role;
}