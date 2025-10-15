package org.app.tobyspring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.tobyspring.exRate.ExRateData;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class ErApiExtractor implements ExRateExtractor {
    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
