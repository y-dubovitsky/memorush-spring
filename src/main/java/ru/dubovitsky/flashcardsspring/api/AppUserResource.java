package ru.dubovitsky.flashcardsspring.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dubovitsky.flashcardsspring.model.AppUser;
import ru.dubovitsky.flashcardsspring.model.Role;
import ru.dubovitsky.flashcardsspring.service.AppUserService;

import java.net.URI;
import java.util.List;

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