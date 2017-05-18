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
        String getOutcome = "https://doanh.000webhostapp.com/getOutcome.php";
    }

    interface IntentExtraKey {
        String outcomeTypeJson = "OUTCOME_TYPE_JSON";
        String outcomeJson = "OUTCOME_JSON";
    }
}
