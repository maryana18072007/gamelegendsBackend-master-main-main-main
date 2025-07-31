package br.gamelegends.gamelegends.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator {

    public static boolean isValidDate(String date) {
        if (date == null || date.isEmpty()) {
            return false; // ou lance uma exceção, dependendo do seu tratamento de erro
        }

        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
