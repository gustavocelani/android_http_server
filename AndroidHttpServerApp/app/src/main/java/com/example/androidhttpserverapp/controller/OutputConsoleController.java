package com.example.androidhttpserverapp.controller;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.example.androidhttpserverapp.utils.Constants;

/**
 * OutputConsoleController
 */
public class OutputConsoleController {

    /** ConsoleTextView */
    private TextView mConsoleTextView;
    /** Singleton Instance */
    @SuppressLint("StaticFieldLeak")
    private static OutputConsoleController mOutputConsoleController;

    /**
     * Private Constructor
     */
    private OutputConsoleController() {}

    /**
     * Singleton getInstance
     * @return Singleton Instance
     */
    public static OutputConsoleController getInstance() {
        if (mOutputConsoleController == null) {
            mOutputConsoleController = new OutputConsoleController();
        }
        return mOutputConsoleController;
    }

    /**
     * setConsoleTextView
     * @param consoleTextView consoleTextView
     */
    public void setConsoleTextView(TextView consoleTextView) {
        this.mConsoleTextView = consoleTextView;
        mConsoleTextView.append(Constants.APP_HEADER);
    }

    /**
     * concat
     */
    public void concat(String text) {
        mConsoleTextView.append(String.format(Constants.CONSOLE_FORMAT, text));
    }
}
