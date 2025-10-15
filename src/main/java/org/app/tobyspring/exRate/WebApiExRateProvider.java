package org.app.tobyspring.exRate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.tobyspring.api.ApiExecutor;
import org.app.tobyspring.api.ApiTemplate;
import org.app.tobyspring.api.ErApiExtractor;
import org.app.tobyspring.api.HttpClientApiExecutor;
import org.app.tobyspring.api.SimpleApiExecutor;
import org.app.tobyspring.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getForExRate(url, uri -> {
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

            try(HttpClient client = HttpClient.newBuilder().build()){
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, new ErApiExtractor());

    }
}
