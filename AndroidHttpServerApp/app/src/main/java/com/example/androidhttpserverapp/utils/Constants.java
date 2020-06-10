package com.example.androidhttpserverapp.utils;

/**
 * Constants
 */
public class Constants {

    /** App */
    public static final String APP_VERSION = "0.1";
    public static final String APP_TAG     = "[AndroidHttpServerApp] ";
    public static final String APP_HEADER  = "[AndroidHttpServerApp] v" + APP_VERSION + "\n\n";

    /** String Format */
    public static final String CONSOLE_FORMAT             = "$ %s\n";
    public static final String SESSION_INFO_KEY_FORMAT    = "%18s > ";
    public static final String IP_ADDRESS_FORMAT          = "%d.%d.%d.%d";
    public static final String SERVER_STATUS_UP_FORMAT    = "[Server UP] %s:%s";
    public static final String SERVER_STATUS_DOWN_FORMAT  = "[Server DOWN]";

    public static final String INVALID_PORT_ERROR_MESSAGE = "The port must be between 1024 and 9999";
}
