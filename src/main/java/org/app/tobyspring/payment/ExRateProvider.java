package org.app.tobyspring.payment;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
    public BigDecimal getExRate(String currency) throws IOException;
}
