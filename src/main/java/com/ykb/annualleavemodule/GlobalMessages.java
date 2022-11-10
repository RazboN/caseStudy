package com.ykb.annualleavemodule;

import java.util.Locale;
import java.util.ResourceBundle;

public class GlobalMessages {
    private static Locale loc;
    public static String getLocaleMessage(String messageKey, String lang){
        if(lang.equals("en"))
            loc = Locale.US;
        else
            loc = Locale.JAPAN;

        return ResourceBundle.getBundle("messages", loc).getString(messageKey);
    }
}
