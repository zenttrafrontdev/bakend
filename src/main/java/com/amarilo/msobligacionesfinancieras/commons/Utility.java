package com.amarilo.msobligacionesfinancieras.commons;

import com.amarilo.msobligacionesfinancieras.exception.BusinessException;

import java.time.LocalDate;
import java.time.Period;

public class Utility {

    private static final Long MAX_PERCENTAGE_VALUE = 100000000L;

    private Utility() {
        throw new IllegalStateException("Utility class");
    }

    public static Long validateNumericField(String fieldName, String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException ex) {
            throw new BusinessException(String.format("El valor del campo %s debe ser númerico", fieldName));
        }
    }

    public static void validatePercentageField(String fieldName, Long value) {
        if (value > MAX_PERCENTAGE_VALUE) {
            throw new BusinessException(String.format("El valor del campo %s no debe ser mayor a 100", fieldName));
        }
    }

    public static Integer convertStringDecimalNumberToInteger(String decimalNumber) {
        return Integer.valueOf(decimalNumber.replace(".", ""));
    }

    public static String convertIntegerToStringDecimalNumber(Integer decimalNumber) {
        var stringDecimalNumber = String.valueOf(decimalNumber);
        StringBuilder sb = new StringBuilder();
        sb.append(stringDecimalNumber.substring(0, stringDecimalNumber.length() - 2));
        sb.append(".");
        sb.append(stringDecimalNumber.substring(stringDecimalNumber.length() - 2, stringDecimalNumber.length()));
        return sb.toString();
    }

    public static long daysNumberBetweenTwoDates(LocalDate initialDate, LocalDate endDate) {
        return Period.between(initialDate, endDate).getDays() + 1L;
    }
}
