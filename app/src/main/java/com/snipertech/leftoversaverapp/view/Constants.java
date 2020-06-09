package com.snipertech.leftoversaverapp.view;

public class Constants {
    public static Constants constants = null;

    public static Constants shared(){
        if (constants==null){
            constants = new Constants();
        }
        return constants;
    }

    public static String endpoint;
    public static String name;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Constants.name = name;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        Constants.endpoint = endpoint;
    }

}
