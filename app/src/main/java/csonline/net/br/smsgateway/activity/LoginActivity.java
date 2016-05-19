package csonline.net.br.smsgateway.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import csonline.net.br.smsgateway.R;
import csonline.net.br.smsgateway.model.LoginVO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Christian Mulato
 */
public class LoginActivity extends AbstractAsyncActivity implements OnClickListener {

    protected static final String TAG = LoginActivity.class.getSimpleName();
    private static final String PREF_NAME = "LoginActivity";
    private static final String LOGGER = "LogCat";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String REMEMBER_PASSWORD = "true";
    private static final String HTTP_PARAMETER = "POST";
    private static final String ENCODING = "UTF-8";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Log.i(LOGGER, getClass() + ": Verificando a conexão com a Internet.");
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // ***************************************
    // Remember Password method
    // ***************************************
    private void rememberPassword() {

        boolean validate = false;

        EditText editText;
        CheckBox checkBox;

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String spLogin = sp.getString(USERNAME, "");
        String spPassword = sp.getString(PASSWORD, "");
        String spCheckBok = sp.getString(REMEMBER_PASSWORD, "");

        if ((spCheckBok != null) && (!spCheckBok.equals(""))) {
            validate = true;
        } else {
            validate = false;
        }

        if (validate) {
            if (spCheckBok.equals("true")) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spLogin != null) && (!spLogin.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            if ((spPassword != null) && (!spPassword.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            editText = (EditText) findViewById(R.id.username);
            editText.setText(spLogin.toString().trim());
            editText = (EditText) findViewById(R.id.password);
            editText.setText(spPassword.toString().trim());
            checkBox = (CheckBox) findViewById(R.id.check_box_remember_password);
            checkBox.setChecked(true);
            Log.i(LOGGER, getClass() + ": Usuário e senha recuperados da memória.");
        }
    }

    // ***************************************
    // Activity methods
    // ***************************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rememberPassword();
        // Initiate the request to the protected service
        final Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(this);
    }

    // ***************************************
    // OnClickListener event
    // ***************************************
    public void onClick(View v) {
        if (isNetworkAvailable()) {
            new FetchSecuredResourceTask().execute();
        } else {
            Toast.makeText(this, getString(R.string.fail_msg_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    // ***************************************
    // Private methods
    // ***************************************
    private void displayResponse(boolean loginOK, boolean server_offline) {
        if (loginOK) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            if (server_offline) {
                Toast.makeText(this, getString(R.string.fail_msg_server_offline), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.fail_msg_login), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class FetchSecuredResourceTask extends AsyncTask<Void, Void, LoginVO> {

        private boolean loginOK;
        private boolean server_offline;
        private boolean isChecked;
        private String username;
        private String password;

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
            // build the message object
            EditText editText = (EditText) findViewById(R.id.username);
            username = editText.getText().toString();
            editText = (EditText) findViewById(R.id.password);
            password = editText.getText().toString();
            CheckBox checkBoxRememberLogin = (CheckBox) findViewById(R.id.check_box_remember_password);
            if (checkBoxRememberLogin.isChecked()) {
                isChecked = true;
            }
        }

        /**
         * Verify that username and password are configured correctly.
         *
         * @return
         */
        protected boolean checkUserPassword() {
            boolean check = false;
            boolean validate = false;

            if ((username != null) && (password != null)) {
                validate = true;
            } else {
                validate = false;
            }

            if (validate) {
                if ((!username.equals("")) && (!password.equals(""))) {
                    username = username.trim();
                    password = password.trim();
                    check = true;
                }
            }

            return check;
        }

        /**
         * HTTP Basic Authentication
         * @param login
         * @return
         */
        protected boolean authorization(LoginVO login) {
            final String strURL = getString(R.string.base_uri) + "/auth/";
            boolean auth = false;
            int status = 0;
            String username = login.getLogin();
            String password = login.getPassword();
            HttpURLConnection connection = null;
            try {
                URL url = new URL(strURL);
                String authHeader = username + ":" + password;
                byte[] message = authHeader.getBytes(ENCODING);
                String encoding = Base64.encodeToString(message, Base64.DEFAULT);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(HTTP_PARAMETER);
                connection.setDoOutput(true);
                connection.setRequestProperty("Authorization", "Basic " + encoding);
                if (connection.getResponseCode() > 0) {
                    status = connection.getResponseCode();
                }
            } catch (MalformedURLException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            if (status == 0) {
                Log.i(LOGGER, getClass() + ": Usuário não autenticado!");
            } else {
                if (status != 401) {
                    if ((status >= 500) && (status < 600)) {
                        server_offline = true;
                        Log.i(LOGGER, getClass() + ": Usuário não autenticado!");
                    } else {
                        auth = true;
                        Log.i(LOGGER, getClass() + ": Usuário autenticado com sucesso!");
                    }
                } else {
                    Log.i(LOGGER, getClass() + ": Usuário não autenticado!");
                }
            }
            return auth;
        }

        @Override
        protected LoginVO doInBackground(Void... params) {
            LoginVO login = new LoginVO();
            if (checkUserPassword()) {
                login = new LoginVO(username, password);
                loginOK = authorization(login);
                if (loginOK) {
                    if (isChecked) {
                        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString(USERNAME, username);
                        editor.putString(PASSWORD, password);
                        editor.putString(REMEMBER_PASSWORD, "true");
                        editor.commit();
                        Log.i(LOGGER, getClass() + ": Usuário e senha salvos na memória.");
                    } else {
                        getSharedPreferences(PREF_NAME, 0).edit().clear().apply();
                        Log.i(LOGGER, getClass() + ": Usuário e senha apagados da memória.");
                    }
                }
            }
            return login;
        }

        @Override
        protected void onPostExecute(LoginVO result) {
            dismissProgressDialog();
            displayResponse(loginOK, server_offline);
        }
    }
}