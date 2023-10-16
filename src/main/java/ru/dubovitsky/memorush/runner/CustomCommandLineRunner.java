package ru.dubovitsky.memorush.runner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dubovitsky.memorush.model.Dictionary;
import ru.dubovitsky.memorush.model.DictionaryItem;
import ru.dubovitsky.memorush.model.User;
import ru.dubovitsky.memorush.model.enums.RoleEnum;
import ru.dubovitsky.memorush.repository.DictionaryRepository;
import ru.dubovitsky.memorush.repository.UserRepository;
import ru.dubovitsky.memorush.security.config.JwtConfig;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DictionaryRepository dictionaryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findByUsername(jwtConfig.getInitAdminName())
                .ifPresentOrElse(
                        user -> log.info("Admin exists already"),
                        this::createAdminUserOnStartup);
        dictionaryRepository.findByName("initial")
                .ifPresentOrElse(
                        (u) -> log.info(""),
                        this::createInitialDictionary);
    }

    private void createAdminUserOnStartup() {
        User admin = User.builder()
                .username(jwtConfig.getInitAdminName())
                .password(passwordEncoder.encode(jwtConfig.getInitAdminPassword()))
                .password2(passwordEncoder.encode(jwtConfig.getInitAdminPassword()))
                .role(RoleEnum.ADMIN)
                .build();
        userRepository.save(admin);
        log.info("Admin user created");
    }

    private void createInitialDictionary() {
        File file = new File(
                this.getClass()
                        .getClassLoader()
                        .getResource("dictionary.json")
                        .getFile()
        );
        ObjectMapper mapper = new ObjectMapper();
        DictionaryMap dictionary = null;
        try {
            dictionary = mapper.readValue(file, DictionaryMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionaryRepository.save(CustomCommandLineRunner.mapper(dictionary));
    }

    //TODO Вынести в отдельный сервис и переработать это
    private static Dictionary mapper(DictionaryMap map) {
        return Dictionary.builder()
                .name("initial")
                .dictionaryItems(map.dictionaryItems.stream().map(it -> {
                    return DictionaryItem.builder()
                            .ru(it.ru)
                            .en(it.en)
                            .tr(it.tr)
                            .build();
                }).collect(Collectors.toList())).build();
    }


}

class DictionaryMap {
    public String name;
    public Set<DictionaryItemMap> dictionaryItems;
}

class DictionaryItemMap {
    @JsonIgnore
    public Integer id;
    public String ru;
    public String en;
    public String tr;
}

