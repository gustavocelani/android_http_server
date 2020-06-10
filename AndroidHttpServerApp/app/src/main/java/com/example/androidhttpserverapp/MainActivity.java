package com.example.androidhttpserverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidhttpserverapp.controller.OutputConsoleController;
import com.example.androidhttpserverapp.controller.ServerController;
import com.example.androidhttpserverapp.utils.Constants;
import com.example.androidhttpserverapp.utils.Utils;

import java.io.IOException;

/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {

    /** ServerController */
    private ServerController mServerController;

    /** Layout Components */
    private Button mStartStopButton;
    private EditText mPortEditText;
    private TextView mStatusTextView;

    /** OutputConsoleController */
    private OutputConsoleController mOutputConsoleController;

    /**
     * onCreate
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUiElements();
    }

    /**
     * onDestroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mServerController != null) {
            stopServer();
        }
    }

    /**
     * Initialize UI Elements
     */
    private void initializeUiElements() {
        mStartStopButton = findViewById(R.id.startStopButton);
        mStartStopButton.setOnClickListener(startStopButtonClickListener);

        mPortEditText = findViewById(R.id.portEditText);
        mStatusTextView = findViewById(R.id.statusTextView);

        mOutputConsoleController = OutputConsoleController.getInstance();
        mOutputConsoleController.setConsoleTextView((TextView) findViewById(R.id.consoleTextView));

        updateServerStatus();
    }

    /**
     * startStopButtonClickListener
     */
    private View.OnClickListener startStopButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mServerController != null && mServerController.isAlive()) {
                mOutputConsoleController.concat("Stopping server");
                stopServer();
            } else {
                mOutputConsoleController.concat("Attempt to start server");
                startServer();
            }

            updateServerStatus();
        }
    };

    /**
     * updateServerStatus
     */
    private void updateServerStatus() {
        if (mServerController != null && mServerController.isAlive()) {
            mStartStopButton.setText(R.string.stop_server);
            mStartStopButton.setBackgroundColor(Color.RED);
            mStatusTextView.setText(String.format(Constants.SERVER_STATUS_UP_FORMAT, Utils.getIpAccess(this), mServerController.getListeningPort()));
            mStatusTextView.setTextColor(Color.GREEN);
        } else {
            mStartStopButton.setText(R.string.start_server);
            mStartStopButton.setBackgroundColor(Color.GREEN);
            mStatusTextView.setText(Constants.SERVER_STATUS_DOWN_FORMAT);
            mStatusTextView.setTextColor(Color.RED);
        }
    }

    /**
     * startServer
     */
    private void startServer() {
        if (mPortEditText == null || mPortEditText.getText() == null) {
            mOutputConsoleController.concat(Constants.INVALID_PORT_ERROR_MESSAGE);
            return;
        }

        int port = ServerController.validateServerPort(mPortEditText.getText().toString());
        if (port < 0) {
            mOutputConsoleController.concat(Constants.INVALID_PORT_ERROR_MESSAGE);
            return;
        }

        try {
            mServerController = new ServerController(port);
            mServerController.start();
            mOutputConsoleController.concat("Server started");
        } catch (IOException e) {
            e.printStackTrace();
            mOutputConsoleController.concat("Fail to start server\n" + e.getMessage());
        }
    }

    /**
     * stopServer
     */
    private void stopServer() {
        mServerController.closeAllConnections();
        mServerController.stop();
        mServerController = null;
        mOutputConsoleController.concat("Server stopped");
    }
}