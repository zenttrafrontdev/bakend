package com.amarilo.msobligacionesfinancieras.commons;

public class Constants {

    private Constants(){
        throw new IllegalStateException("Utility class");
    }

    public static final String BOGOTA_BANK_CODE = "01";
    public static final String BANCOLOMBIA_BANK_CODE = "07";
    public static final String BBVA_BANK_CODE = "13";
    public static final String DAVIVIENDA_BANK_CODE = "51";

    // JASPER REPORTS
    public static final String BOGOTA_DISBURSEMENT_JASPER_LETTER = "bogota_bank_disbursement_letter.jrxml";
    public static final String BOGOTA_DISBURSEMENT_PREOPERATIVE_JASPER_LETTER = "bogota_bank_disbursement_preoperative_letter.jrxml";
    public static final String BANCOLOMBIA_DISBURSEMENT_JASPER_LETTER = "bancolombia_bank_disbursement_letter.jrxml";
    public static final String BBVA_DISBURSEMENT_JASPER_LETTER = "bbva_bank_disbursement_letter.jrxml";
    public static final String DAVIVIENDA_DISBURSEMENT_JASPER_LETTER = "davivienda_bank_disbursement_letter.jrxml";
}
