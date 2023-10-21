package ru.dubovitsky.memorush.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dubovitsky.memorush.config.ApplicationVariablesConfig;
import ru.dubovitsky.memorush.dto.bidirectional.DictionaryDto;
import ru.dubovitsky.memorush.facade.DictionaryFacade;
import ru.dubovitsky.memorush.model.User;
import ru.dubovitsky.memorush.model.enums.RoleEnum;
import ru.dubovitsky.memorush.repository.DictionaryRepository;
import ru.dubovitsky.memorush.repository.UserRepository;
import ru.dubovitsky.memorush.utils.ResourcesUtils;


import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DictionaryRepository dictionaryRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationVariablesConfig applicationVariablesConfig;

    @Override
    public void run(String... args) {
        userRepository.findByUsername(applicationVariablesConfig.getInitAdminName())
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
                .username(applicationVariablesConfig.getInitAdminName())
                .password(passwordEncoder.encode(applicationVariablesConfig.getInitAdminPassword()))
                .password2(passwordEncoder.encode(applicationVariablesConfig.getInitAdminPassword()))
                .role(RoleEnum.ADMIN)
                .build();
        userRepository.save(admin);
        log.info("Admin user created");
    }


    private void createInitialDictionary() {
        File file = ResourcesUtils.getResourceFile(applicationVariablesConfig.getInitialDictionaryName());
        ObjectMapper mapper = new ObjectMapper();
        DictionaryDto dictionaryDto = null;
        try {
            dictionaryDto = mapper.readValue(file, DictionaryDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionaryRepository.save(DictionaryFacade.dictionaryDtoToDictionary(dictionaryDto));
        log.info("Initial Dictionary created");
    }
}


