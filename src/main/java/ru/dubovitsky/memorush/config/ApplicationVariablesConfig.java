package ru.dubovitsky.memorush.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter @Setter
@Configuration
public class ApplicationVariablesConfig {

    @Value("${application.cors.allowedOriginsArray}")
    private String allowedOriginsArray;

    @Value("${application.dictionary.name}")
    private String initialDictionaryName;

    @Value("${application.jwt.securityKey}")
    private String securityKey;

    @Value("${application.jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${application.admin.name}")
    private String initAdminName;

    @Value("${application.admin.password}")
    private String initAdminPassword;

}

