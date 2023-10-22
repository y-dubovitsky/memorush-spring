package ru.dubovitsky.memorush.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.dubovitsky.memorush.utils.ApplicationVariablesUtils;

@Getter @Setter
@NoArgsConstructor
@Configuration
public class ApplicationVariablesConfig {

    @Getter(AccessLevel.NONE)
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

    public String[] getAllowedOriginsArray() {
        return ApplicationVariablesUtils.splitStringToArrayByPattern(allowedOriginsArray);
    }

}

