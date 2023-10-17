package ru.dubovitsky.memorush.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter @Setter
@Configuration
public class VariablesConfig {

    @Value("${application.cors.allowedOriginsArray}")
    private String allowedOriginsArray;

}

