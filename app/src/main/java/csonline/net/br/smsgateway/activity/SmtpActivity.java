package csonline.net.br.smsgateway.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

/**
 * @author Christian Mulato
 */
public class SmtpActivity extends AppCompatActivity implements OnClickListener {

    private static final String PREF_NAME = "SmtpActivity";
    private static final String LOGGER = "LogCat";
    private static final String SMTP_ENABLE_SMS = "smtp_enable_sms";
    private static final String SMTP_DELETE_SMS = "smtp_delete_sms";
    private static final String SMTP_SERVER_HOST = "smtp_server_host";
    private static final String SMTP_SERVER_PORT = "smtp_server_port";
    private static final String SMTP_AUTH = "smtp_auth";
    private static final String SMTP_SSL = "smtp_ssl";
    private static final String SMTP_USERNAME = "smtp_username";
    private static final String SMTP_PASSWORD = "smtp_password";
    private static final String SMTP_FORWARD_TO_EMAIL = "smtp_forward_to_email";
    private static final String SMTP_OAUTH2 = "smtp_oauth2";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    /**
     * Getter SMTP parameters
     * @return
     */
    private boolean getSmtpParameters() {
        boolean parameters = false;
        boolean validate = false;
        EditText editText;
        CheckBox checkBox;

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String spSmtpEnableSMS = sp.getString(SMTP_ENABLE_SMS, "");
        String spSmtpDeleteSMS = sp.getString(SMTP_DELETE_SMS, "");
        String spSmtpServerHost = sp.getString(SMTP_SERVER_HOST, "");
        String spSmtpServerPort = sp.getString(SMTP_SERVER_PORT, "");
        String spSmtpAuth = sp.getString(SMTP_AUTH, "");
        String spSmtpSSL = sp.getString(SMTP_SSL, "");
        String spSmtpUsername = sp.getString(SMTP_USERNAME, "");
        String spSmtpPassword = sp.getString(SMTP_PASSWORD, "");
        String spSmtpForwardToEmail = sp.getString(SMTP_FORWARD_TO_EMAIL, "");
        String spSmtpOAuth2 = sp.getString(SMTP_OAUTH2, "");

        if ((spSmtpEnableSMS != null) && (!spSmtpEnableSMS.equals(""))) {
            validate = true;
        } else {
            validate = false;
        }

        if (validate) {
            if ((spSmtpDeleteSMS != null) && (!spSmtpDeleteSMS.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpServerHost != null) && (!spSmtpServerHost.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpServerPort != null) && (!spSmtpServerPort.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpAuth != null) && (!spSmtpAuth.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpSSL != null) && (!spSmtpSSL.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpUsername != null) && (!spSmtpUsername.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpPassword != null) && (!spSmtpPassword.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpForwardToEmail != null) && (!spSmtpForwardToEmail.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spSmtpOAuth2 != null) && (!spSmtpOAuth2.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            //enable incoming SMS forwarding
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_enable_SMS);;
            checkBox.setChecked(Boolean.valueOf(spSmtpEnableSMS));
            //delete SMS message after being forwarded successfully
            //to the given SMTP server
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_delete_SMS);;
            checkBox.setChecked(Boolean.valueOf(spSmtpDeleteSMS));
            //smtp server host
            editText = (EditText) findViewById(R.id.edit_smtp_server_host);
            editText.setText(spSmtpServerHost.toString().trim());
            //smtp server port
            editText = (EditText) findViewById(R.id.edit_smtp_server_port);
            editText.setText(spSmtpServerPort.toString().trim());
            //authentication server required
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_auth);;
            checkBox.setChecked(Boolean.valueOf(spSmtpAuth));
            //ssl required
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_SSL);;
            checkBox.setChecked(Boolean.valueOf(spSmtpSSL));
            //smtp username
            editText = (EditText) findViewById(R.id.edit_smtp_username);
            editText.setText(spSmtpUsername.toString().trim());
            //smtp password
            editText = (EditText) findViewById(R.id.edit_smtp_password);
            editText.setText(spSmtpPassword.toString().trim());
            //forward to email
            editText = (EditText) findViewById(R.id.edit_smtp_forward_to_email);
            editText.setText(spSmtpForwardToEmail.toString().trim());
            //smtp open authentication version 2.0
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_oauth2);;
            checkBox.setChecked(Boolean.valueOf(spSmtpOAuth2));
            parameters = true;
        }
        if (parameters) {
            Log.i(LOGGER, getClass() + ": Parâmetros SMTP recuperados da memória.");
        }
        return parameters;
    }

    /**
     * Setter SMTP parameters
     * @return
     */
    private boolean setSmtpParameters () {
        boolean checkingData = false;
        EditText editText;
        CheckBox checkBox;

        boolean checkBoxSmtpEnableSMS = false;
        boolean checkBoxSmtpDeleteSMS = false;
        String strSmtpServerHost = "";
        String strSmtpServerPort = "";
        boolean checkBoxSmtpAuth = false;
        boolean checkBoxSmtpSSL = false;
        String strSmtpUsername = "";
        String strSmtpPassword = "";
        String strSmtpForwardToEmail = "";
        boolean checkBoxSmtpOAuth2 = false;

        try {
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_enable_SMS);
            if (checkBox.isChecked()) {
                checkBoxSmtpEnableSMS = true;
            }
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_delete_SMS);
            if (checkBox.isChecked()) {
                checkBoxSmtpDeleteSMS = true;
            }
            editText = (EditText) findViewById(R.id.edit_smtp_server_host);
            strSmtpServerHost = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_smtp_server_port);
            strSmtpServerPort = editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_SSL);
            if (checkBox.isChecked()) {
                checkBoxSmtpSSL = true;
            }
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_auth);
            if (checkBox.isChecked()) {
                checkBoxSmtpAuth = true;
            }
            editText = (EditText) findViewById(R.id.edit_smtp_username);
            strSmtpUsername =  editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_smtp_password);
            strSmtpPassword =  editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_smtp_forward_to_email);
            strSmtpForwardToEmail =  editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_smtp_oauth2);
            if (checkBox.isChecked()) {
                checkBoxSmtpOAuth2 = true;
            }
            Integer.valueOf(strSmtpServerPort);
            checkingData = true;
        } catch (Exception e) {
            Log.e(LOGGER, getClass() + " : Falha ao validar parâmetros!");
        }
        if (checkingData) {
            sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            editor = sp.edit();
            if (checkBoxSmtpEnableSMS) {
                editor.putString(SMTP_ENABLE_SMS, "true");
            } else {
                editor.putString(SMTP_ENABLE_SMS, "false");
            }
            if (checkBoxSmtpDeleteSMS) {
                editor.putString(SMTP_DELETE_SMS, "true");
            } else {
                editor.putString(SMTP_DELETE_SMS, "false");
            }
            editor.putString(SMTP_SERVER_HOST, strSmtpServerHost);
            editor.putString(SMTP_SERVER_PORT, strSmtpServerPort);
            if (checkBoxSmtpAuth) {
                editor.putString(SMTP_AUTH, "true");
            } else {
                editor.putString(SMTP_AUTH, "false");
            }
            if (checkBoxSmtpSSL) {
                editor.putString(SMTP_SSL, "true");
            } else {
                editor.putString(SMTP_SSL, "false");
            }
            editor.putString(SMTP_USERNAME, strSmtpUsername);
            editor.putString(SMTP_PASSWORD, strSmtpPassword);
            editor.putString(SMTP_FORWARD_TO_EMAIL, strSmtpForwardToEmail);
            if (checkBoxSmtpOAuth2) {
                editor.putString(SMTP_OAUTH2, "true");
            } else {
                editor.putString(SMTP_OAUTH2, "false");
            }
            editor.commit();
            Log.i(LOGGER, getClass() + ": Parâmetros SMTP salvos na memória.");
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
        setContentView(R.layout.activity_smtp);
        getter = getSmtpParameters();
        // Initiate the request to the protected service
        final Button submitButton = (Button) findViewById(R.id.smtp_button_submit);
        submitButton.setOnClickListener(this);
    }

    // ***************************************
    // OnClickListener event
    // ***************************************
    public void onClick(View v) {
        boolean setter = setSmtpParameters();
        if (setter) {
            Intent intent = new Intent(SmtpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.fail_msg_empty_data), Toast.LENGTH_SHORT).show();
        }
    }
}