package com.util;

/**
 * Created by alpha on 5/18/2017.
 */

public interface Constant {

    interface EditOutcomeTypeValue {

        String id = "EDIT_OUTCOME_TYPE_ID";
        String name = "EDIT_OUTCOME_TYPE_NAME";
    }

    interface Url {

        String insertOutcomeType = "https://doanh.000webhostapp.com/insertOutcomeType.php";
        String updateOutcomeType = "https://doanh.000webhostapp.com/updateOutcomeType.php";
        String deleteOutcomeType = "https://doanh.000webhostapp.com/deleteOutcomeType.php";
        String getOutcomeType = "https://doanh.000webhostapp.com/getOutcomeType.php";
        String insertOutcome = "https://doanh.000webhostapp.com/insertOutcome.php";
        String getOutcome = "https://doanh.000webhostapp.com/getOutcome.php";
    }

    interface IntentExtraKey {
        String outcomeTypeJson = "OUTCOME_TYPE_JSON";
        String outcomeJson = "OUTCOME_JSON";
    }

    interface ServerResponse {
        String outcomeType = "outcome_type";
        String outcome = "outcome";

        interface OutcomeType {
            String name = "outcomeTypeName";
            String id = "outcomeTypeId";
        }

        interface Outcome {
            String name = "outcomeName";
            String id = "outcomeId";
            String day = "day";
            String month = "month";
            String year = "year";
            String amount = "amount";
            String note = "note";
        }
    }

    String utf8 = "UTF-8";
}
