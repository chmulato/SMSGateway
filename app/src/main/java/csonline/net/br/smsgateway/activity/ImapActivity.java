package csonline.net.br.smsgateway.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import csonline.net.br.smsgateway.R;
import csonline.net.br.smsgateway.exception.AppException;

/**
 * @author Christian Mulato
 */
public class ImapActivity extends AppCompatActivity implements OnClickListener {

    private static final String PREF_NAME = "ImapActivity";
    private static final String LOGGER = "LogCat";
    private static final String IMAP_REQUEST_DELIVERY = "imap_request_delivery";
    private static final String IMAP_SERVER_HOST = "imap_server_host";
    private static final String IMAP_SERVER_PORT = "imap_server_port";
    private static final String IMAP_SSL = "imap_ssl";
    private static final String IMAP_USERNAME = "imap_username";
    private static final String IMAP_PASSWORD = "imap_password";
    private static final String IMAP_INTERVAL = "imap_interval";
    private static final String IMAP_DELETE_EMAIL = "imap_delete_email";
    private static final String IMAP_FOLDER = "imap_folder";
    private static final String IMAP_OAUTH2 = "imap_oauth2";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    /**
     * Getter IMAP parameters
     * @return
     */
    private boolean getImapParameters() {

        boolean parameters = false;
        boolean validate = false;

        EditText editText;
        CheckBox checkBox;

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String spImapRequestDelivery = sp.getString(IMAP_REQUEST_DELIVERY, "");
        String spImapServerHost = sp.getString(IMAP_SERVER_HOST, "");
        String spImapServerPort = sp.getString(IMAP_SERVER_PORT, "");
        String spImapSSL = sp.getString(IMAP_SSL, "");
        String spImapUsername = sp.getString(IMAP_USERNAME, "");
        String spImapPassword = sp.getString(IMAP_PASSWORD, "");
        String spImapInterval = sp.getString(IMAP_INTERVAL, "");
        String spDeleteEmail = sp.getString(IMAP_DELETE_EMAIL, "");
        String spImapFolder = sp.getString(IMAP_FOLDER, "");
        String spImapOAuth2 = sp.getString(IMAP_OAUTH2, "");

        if ((spImapRequestDelivery != null) && (!spImapRequestDelivery.equals(""))) {
            validate = true;
        } else {
            validate = false;
        }

        if (validate) {
            if ((spImapServerHost != null) && (!spImapServerHost.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapServerPort != null) && (!spImapServerPort.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapSSL != null) && (!spImapSSL.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapUsername != null) && (!spImapUsername.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapPassword != null) && (!spImapPassword.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapInterval != null) && (!spImapInterval.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spDeleteEmail != null) && (!spDeleteEmail.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapFolder != null) && (!spImapFolder.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spImapOAuth2 != null) && (!spImapOAuth2.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            // request a delivery report
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_request_delivery);
            checkBox.setChecked(Boolean.valueOf(spImapRequestDelivery.toString()));
            //imap server host
            editText = (EditText) findViewById(R.id.edit_imap_server_host);
            editText.setText(spImapServerHost.toString().trim());
            //imap server port
            editText = (EditText) findViewById(R.id.edit_imap_server_port);
            editText.setText(spImapServerPort.toString().trim());
            //imap SSL
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_SSL);
            checkBox.setChecked(Boolean.valueOf(spImapSSL.toString()));
            //imap username
            editText = (EditText) findViewById(R.id.edit_imap_username);
            editText.setText(spImapUsername.toString().trim());
            //imap password
            editText = (EditText) findViewById(R.id.edit_imap_password);
            editText.setText(spImapPassword.toString().trim());
            //imap poll interval (miliseconds)
            editText = (EditText) findViewById(R.id.edit_imap_interval);
            editText.setText(spImapInterval.toString().trim());
            //delete email message from email server
            //after being forwarded successfully to the GSM network
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_delete_email);
            checkBox.setChecked(Boolean.valueOf(spDeleteEmail.toString()));
            //imap folder
            editText = (EditText) findViewById(R.id.edit_imap_folder);
            editText.setText(spImapFolder.toString().trim());
            //imap open authentication version 2.0
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_oauth2);
            checkBox.setChecked(Boolean.valueOf(spImapOAuth2.toString()));
            parameters = true;
        }
        if (parameters) {
            Log.i(LOGGER, getClass() + ": Parâmetros IMAP recuperados da memória.");
        }
        return parameters;
    }

    /**
     * Setter IMAP parameters
     * @return
     */
    private boolean setImapParameters () {
        boolean checkingData = false;
        EditText editText;
        CheckBox checkBox;

        boolean checkBoxImapRequestDelivery = false;
        String strImapServerHost = "";
        String strImapServerPort = "";
        boolean checkBoxImapSSL = false;
        String strImapUsername = "";
        String strImapPassword = "";
        String strImapInterval = "";
        boolean checkBoxDeleteEmail = false;
        String strImapFolder = "";
        boolean checkBoxOAuth2 = false;

        try {
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_request_delivery);
            if (checkBox.isChecked()) {
                checkBoxImapRequestDelivery = true;
            }
            editText = (EditText) findViewById(R.id.edit_imap_server_host);
            strImapServerHost = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_imap_server_port);
            strImapServerPort = editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_SSL);
            if (checkBox.isChecked()) {
                checkBoxImapSSL = true;
            }
            editText = (EditText) findViewById(R.id.edit_imap_username);
            strImapUsername =  editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_imap_password);
            strImapPassword =  editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_imap_interval);
            strImapInterval =  editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_delete_email);
            if (checkBox.isChecked()) {
                checkBoxDeleteEmail = true;
            }
            editText = (EditText) findViewById(R.id.edit_imap_folder);
            strImapFolder =  editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_imap_oauth2);
            if (checkBox.isChecked()) {
                checkBoxOAuth2 = true;
            }
            Integer.valueOf(strImapServerPort);
            Integer.valueOf(strImapInterval);
            int interval = Integer.valueOf(strImapInterval);
            if ((interval < 2000) || (interval > 10000)) {
                throw new AppException("Interval out of range! Value is :" + interval);
            }
            checkingData = true;
        } catch (Exception e) {
            Log.e(LOGGER, getClass() + " : Falha ao validar parâmetros!");
        }
        if (checkingData) {
            sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            editor = sp.edit();
            if (checkBoxImapRequestDelivery) {
                editor.putString(IMAP_REQUEST_DELIVERY, "true");
            } else {
                editor.putString(IMAP_REQUEST_DELIVERY, "false");
            }
            editor.putString(IMAP_SERVER_HOST, strImapServerHost);
            editor.putString(IMAP_SERVER_PORT, strImapServerPort);
            if (checkBoxImapSSL) {
                editor.putString(IMAP_SSL, "true");
            } else {
                editor.putString(IMAP_SSL, "false");
            }
            editor.putString(IMAP_USERNAME, strImapUsername);
            editor.putString(IMAP_PASSWORD, strImapPassword);
            editor.putString(IMAP_INTERVAL, strImapInterval);
            if (checkBoxDeleteEmail) {
                editor.putString(IMAP_DELETE_EMAIL, "true");
            } else {
                editor.putString(IMAP_DELETE_EMAIL, "false");
            }
            editor.putString(IMAP_FOLDER, strImapFolder);
            if (checkBoxOAuth2) {
                editor.putString(IMAP_OAUTH2, "true");
            } else {
                editor.putString(IMAP_OAUTH2, "false");
            }
            editor.commit();
            Log.i(LOGGER, getClass() + ": Parâmetros IMAP salvos na memória.");
        }
        return checkingData;
    }

    // ***************************************
    // Activity methods
    // ***************************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        boolean getter = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imap);
        getter = getImapParameters();
        // Initiate the request to the protected service
        final Button submitButton = (Button) findViewById(R.id.imap_button_submit);
        submitButton.setOnClickListener(this);
    }

    // ***************************************
    // OnClickListener event
    // ***************************************
    public void onClick(View v) {
        boolean setter = setImapParameters();
        if (setter) {
            Intent intent = new Intent(ImapActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.fail_msg_empty_data), Toast.LENGTH_SHORT).show();
        }
    }
}