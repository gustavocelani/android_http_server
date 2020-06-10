package com.example.androidhttpserverapp.controller;

import android.util.Log;

import com.example.androidhttpserverapp.utils.Constants;

import java.util.Map;
import fi.iki.elonen.NanoHTTPD;

/**
 * ServerController
 * extends NanoHTTPD
 */
public class ServerController extends NanoHTTPD {

    /** OutputConsoleController */
    private OutputConsoleController mOutputConsoleController;

    /**
     * Constructor
     * @param port port
     */
    public ServerController(int port) {
        super(port);
        mOutputConsoleController = OutputConsoleController.getInstance();
    }

    /**
     * serve
     * @param session session
     * @return HTTP Response
     */
    @Override
    public Response serve(IHTTPSession session) {
        Log.d(Constants.APP_TAG, "Formatted session info\n" + getFormattedSessionInfo(session));
        mOutputConsoleController.concat(getFormattedSessionInfo(session));

        /*
         * Here a switch case can be implemented to filter the requests using any session attributes
         */

        return newFixedLengthResponse(
                "<html><body><h1>Android HTTP Server App</h1>\n" +
                "<style>\np {\nfont-size: 16px;\nfont-family:monospace\n}\n</style>\n" +
                "<p>\n" +
                getFormattedSessionInfo(session).replaceAll("\n", "</br></br>") +
                "</p>\n" +
                "</body></html>\n");
    }

    /**
     * validateServerPort
     * @param portString portString
     * @return Validation
     */
    public static int validateServerPort(String portString) {
        int port;

        try {
            port = Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (port >= 1024 && port <= 9999) {
            return port;
        }

        return -1;
    }

    /**
     * getFormattedSessionInfo
     * @param session session
     * @return Formatted session info
     */
    private String getFormattedSessionInfo(IHTTPSession session) {
        StringBuilder sessionInfo = new StringBuilder();

        sessionInfo.append("New Request").append("\n");
        sessionInfo.append(String.format(Constants.SESSION_INFO_KEY_FORMAT, "URI")).append(session.getUri()).append("\n");
        sessionInfo.append(String.format(Constants.SESSION_INFO_KEY_FORMAT, "Method")).append(session.getMethod().toString()).append("\n");

        Map<String, String> headers = session.getHeaders();
        for (String key : headers.keySet()) {
            if (headers.get(key) != null) {
                sessionInfo.append(String.format(Constants.SESSION_INFO_KEY_FORMAT, key)).append(headers.get(key)).append("\n");
            }
        }

        Map<String, String> parameters = session.getParms();
        for (String key : parameters.keySet()) {
            if (parameters.get(key) != null) {
                sessionInfo.append(String.format(Constants.SESSION_INFO_KEY_FORMAT, key)).append(parameters.get(key)).append("\n");
            }
        }

        return sessionInfo.toString();
    }
}

