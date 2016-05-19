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
public class PopActivity extends AppCompatActivity implements OnClickListener {

    private static final String PREF_NAME = "PopActivity";
    private static final String LOGGER = "LogCat";
    private static final String POP_REQUEST_DELIVERY = "pop_request_delivery";
    private static final String POP_SERVER_HOST = "pop_server_host";
    private static final String POP_SERVER_PORT = "pop_server_port";
    private static final String POP_SSL = "pop_ssl";
    private static final String POP_USERNAME = "pop_username";
    private static final String POP_PASSWORD = "pop_password";
    private static final String POP_FOLDER = "pop_folder";
    private static final String POP_OAUTH2 = "pop_oath2";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    /**
     * Getter POP parameters
     * @return
     */
    private boolean getPopParameters() {

        boolean parameters = false;
        boolean validate = false;

        EditText editText;
        CheckBox checkBox;

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String spPopRequestDelivery = sp.getString(POP_REQUEST_DELIVERY, "");
        String spPopServerHost = sp.getString(POP_SERVER_HOST, "");
        String spPopServerPort = sp.getString(POP_SERVER_PORT, "");
        String spPopSSL = sp.getString(POP_SSL, "");
        String spPopUsername = sp.getString(POP_USERNAME, "");
        String spPopPassword = sp.getString(POP_PASSWORD, "");
        String spPopFolder = sp.getString(POP_FOLDER, "");
        String spPopOauth2 = sp.getString(POP_OAUTH2, "");

        if ((spPopRequestDelivery != null) && (!spPopRequestDelivery.equals(""))) {
            validate = true;
        } else {
            validate = false;
        }

        if (validate) {
            if ((spPopServerHost != null) && (!spPopServerHost.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPopServerPort != null) && (!spPopServerPort.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPopSSL != null) && (!spPopSSL.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPopUsername != null) && (!spPopUsername.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPopPassword != null) && (!spPopPassword.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPopFolder != null) && (!spPopFolder.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPopOauth2 != null) && (!spPopOauth2.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            // request a delivery report
            checkBox = (CheckBox) findViewById(R.id.check_box_pop_request_delivery);
            checkBox.setChecked(Boolean.valueOf(spPopRequestDelivery.toString()));
            //pop server host
            editText = (EditText) findViewById(R.id.edit_pop_server_host);
            editText.setText(spPopServerHost.toString().trim());
            //pop server port
            editText = (EditText) findViewById(R.id.edit_pop_server_port);
            editText.setText(spPopServerPort.toString().trim());
            //pop SSL
            checkBox = (CheckBox) findViewById(R.id.check_box_pop_SSL);
            checkBox.setChecked(Boolean.valueOf(spPopSSL.toString()));
            //pop username
            editText = (EditText) findViewById(R.id.edit_pop_username);
            editText.setText(spPopUsername.toString().trim());
            //pop password
            editText = (EditText) findViewById(R.id.edit_pop_password);
            editText.setText(spPopPassword.toString().trim());
            //pop folder
            editText = (EditText) findViewById(R.id.edit_pop_folder);
            editText.setText(spPopFolder.toString().trim());
            //pop open atuthentication version 2.0
            checkBox = (CheckBox) findViewById(R.id.check_box_pop_oauth2);
            checkBox.setChecked(Boolean.valueOf(spPopOauth2.toString()));
            parameters = true;
        }
        if (parameters) {
            Log.i(LOGGER, getClass() + ": Parâmetros POP recuperados da memória.");
        }
        return parameters;
    }

    /**
     * Setter POP parameters
     * @return
     */
    private boolean setPopParameters() {
        boolean checkingData = false;
        EditText editText;
        CheckBox checkBox;

        boolean checkBoxPopRequestDelivery = false;
        String strPopServerHost = "";
        String strPopServerPort = "";
        boolean checkBoxPopSSL = false;
        String strPopUsername = "";
        String strPopPassword = "";
        String strPopFolder = "";
        boolean checkBoxPopOAuth2 = false;

        try {
            checkBox = (CheckBox) findViewById(R.id.check_box_pop_request_delivery);
            if (checkBox.isChecked()) {
                checkBoxPopRequestDelivery = true;
            }
            editText = (EditText) findViewById(R.id.edit_pop_server_host);
            strPopServerHost = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_pop_server_port);
            strPopServerPort = editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_pop_SSL);
            if (checkBox.isChecked()) {
                checkBoxPopSSL = true;
            }
            editText = (EditText) findViewById(R.id.edit_pop_username);
            strPopUsername =  editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_pop_password);
            strPopPassword =  editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_pop_folder);
            strPopFolder =  editText.getText().toString();
            checkBox = (CheckBox) findViewById(R.id.check_box_pop_oauth2);
            if (checkBox.isChecked()) {
                checkBoxPopOAuth2 = true;
            }
            Integer.valueOf(strPopServerPort);
            checkingData = true;
        } catch (Exception e) {
            Log.e(LOGGER, getClass() + " : Falha ao validar parâmetros!");
        }
        if (checkingData) {
            sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            editor = sp.edit();
            if (checkBoxPopRequestDelivery) {
                editor.putString(POP_REQUEST_DELIVERY, "true");
            } else {
                editor.putString(POP_REQUEST_DELIVERY, "false");
            }
            editor.putString(POP_SERVER_HOST, strPopServerHost);
            editor.putString(POP_SERVER_PORT, strPopServerPort);
            if (checkBoxPopSSL) {
                editor.putString(POP_SSL, "true");
            } else {
                editor.putString(POP_SSL, "false");
            }
            editor.putString(POP_USERNAME, strPopUsername);
            editor.putString(POP_PASSWORD, strPopPassword);
            editor.putString(POP_FOLDER, strPopFolder);
            if (checkBoxPopOAuth2) {
                editor.putString(POP_OAUTH2, "true");
            } else {
                editor.putString(POP_OAUTH2, "false");
            }
            editor.commit();
            Log.i(LOGGER, getClass() + ": Parâmetros POP salvos na memória.");
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
        setContentView(R.layout.activity_pop);
        getter = getPopParameters();
        // Initiate the request to the protected service
        final Button submitButton = (Button) findViewById(R.id.pop_button_submit);
        submitButton.setOnClickListener(this);
    }

    // ***************************************
    // OnClickListener event
    // ***************************************
    public void onClick(View v) {
        boolean setter = setPopParameters();
        if (setter) {
            Intent intent = new Intent(PopActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.fail_msg_empty_data), Toast.LENGTH_SHORT).show();
        }
    }
}