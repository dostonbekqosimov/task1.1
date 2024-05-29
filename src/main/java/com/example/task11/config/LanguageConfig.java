package com.example.task11.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LanguageConfig {
    public static final List<Locale> LOCALES = Arrays.asList(
            new Locale("uz"), // changed to "uz" to match ISO 639-1 codes
            new Locale("ru"),
            new Locale("en")
    );
}