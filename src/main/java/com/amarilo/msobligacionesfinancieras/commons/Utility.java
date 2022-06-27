package com.amarilo.msobligacionesfinancieras.commons;

import com.amarilo.msobligacionesfinancieras.exception.BusinessException;

public class Utility {

    private Utility() {
        throw new IllegalStateException("Utility class");
    }

    public static Integer validateNumericField(String fieldName, String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            throw new BusinessException(String.format("El valor del campo %s debe ser númerico", fieldName));
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
}
