package com.api.blogapi.teste.context;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

@RestController
@RequestMapping("/api/contexto")
public class ContextoController {

    @GetMapping
    public ContextoResponseDto contexto(Locale locale) {
        Locale localeCompleto = locale;
        ZoneId zoneId = timezonePara(localeCompleto);
        LocalDateTime agora = LocalDateTime.now(zoneId);

        DateTimeFormatter formatoData = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(localeCompleto);

        NumberFormat numero = NumberFormat.getNumberInstance(localeCompleto);
        numero.setMinimumFractionDigits(2);
        numero.setMaximumFractionDigits(2);
        numero.setCurrency(Currency.getInstance(moedaPara(localeCompleto)));

        NumberFormat moeda = NumberFormat.getCurrencyInstance(localeCompleto);
        moeda.setCurrency(Currency.getInstance(moedaPara(localeCompleto)));

        return new ContextoResponseDto(
                localeCompleto.toLanguageTag(),
                zoneId.getId(),
                agora.format(formatoData),
                moeda.format(1250.90),
                numero.format(1250.90)
        );
    }

    private String moedaPara(Locale locale) {
        return switch (locale.getCountry()){
            case "US" -> "USD";
            case "ES" -> "EUR";
            default -> "BRL";
        };
    }

    private ZoneId timezonePara(Locale locale) {
        return switch (locale.getCountry()){
            case "US" -> ZoneId.of("America/New_York");
            case "ES" -> ZoneId.of("Europe/Madrid");
            default -> ZoneId.of("America/Sao_Paulo");
        };
    }
}
