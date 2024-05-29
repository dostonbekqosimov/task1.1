package com.example.task11.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
       String language = request.getParameter("lang");
       if(language == null || language.isEmpty()){
           return Locale.forLanguageTag("uzb");
       }
       Locale locale = Locale.forLanguageTag(language);
       if(LanguageConfig.LOCALES.contains(locale)) {
           return locale;
       }
       return Locale.forLanguageTag("uzb");
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
