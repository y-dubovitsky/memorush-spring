package ru.dubovitsky.flashcardsspring.security.facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.dubovitsky.flashcardsspring.security.dto.response.SuccessfulAuthenticationResponse;

public class SuccessfulAuthenticationFacade {

    public static String successfulAuthenticationResponseToJsonString(
            SuccessfulAuthenticationResponse successfulAuthenticationResponse
    ) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String tokenVerifierFilterResponse
                = gson.toJson(successfulAuthenticationResponse);

        return tokenVerifierFilterResponse;
    }

}
