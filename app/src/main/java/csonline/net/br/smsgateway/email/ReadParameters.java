package csonline.net.br.smsgateway.email;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;
import csonline.net.br.smsgateway.util.Parameters;

/**
 * Created by Christian on 06/12/2015.
 */
public class ReadParameters extends ContextWrapper {

    private static final String LOGGER = "LogCat";
    private static SharedPreferences sp;
    //imap parameters
    private static final String IMAP_PREF_NAME = "ImapActivity";
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
    //pop parameters
    private static final String POP_PREF_NAME = "PopActivity";
    private static final String POP_REQUEST_DELIVERY = "pop_request_delivery";
    private static final String POP_SERVER_HOST = "pop_server_host";
    private static final String POP_SERVER_PORT = "pop_server_port";
    private static final String POP_SSL = "pop_ssl";
    private static final String POP_USERNAME = "pop_username";
    private static final String POP_PASSWORD = "pop_password";
    private static final String POP_FOLDER = "pop_folder";
    private static final String POP_OAUTH2 = "pop_oauth2";
    //smtp parameters
    private static final String SMTP_PREF_NAME = "SmtpActivity";
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
    //config read parameters
    private static final String IMAP = "IMAP";
    private static final String POP = "POP";
    private static final String SMTP = "SMTP";

    public ReadParameters(Context base, String PROTOCOL) {
        super(base);
        if ((PROTOCOL!=null) && (PROTOCOL.equals(IMAP))) {
            getImapParameters();
        }
        if ((PROTOCOL!=null) && (PROTOCOL.equals(SMTP))) {
            getSmtpParameters();
        }
        if ((PROTOCOL!=null) && (PROTOCOL.equals(POP))) {
            getPopParameters();
        }
    }

    /**
     * Getter IMAP parameters
     * @return
     */
    private boolean getImapParameters() {

        boolean parameters = false;
        boolean validate = false;

        sp = getSharedPreferences(IMAP_PREF_NAME, MODE_PRIVATE);
        String spImapRequestDelivery = sp.getString(IMAP_REQUEST_DELIVERY, "");
        String spImapServerHost = sp.getString(IMAP_SERVER_HOST, "");
        String spImapServerPort = sp.getString(IMAP_SERVER_PORT, "");
        String spImapSSL = sp.getString(IMAP_SSL, "");
        String spImapUsername = sp.getString(IMAP_USERNAME, "");
        String spImapPassword = sp.getString(IMAP_PASSWORD, "");
        String spImapInterval = sp.getString(IMAP_INTERVAL, "");
        String spImapDeleteEmail = sp.getString(IMAP_DELETE_EMAIL, "");
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
            if ((spImapDeleteEmail != null) && (!spImapDeleteEmail.equals(""))) {
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
            Parameters.setImap_request_delivery(Boolean.valueOf(spImapRequestDelivery.toString()));
            //imap server host
            Parameters.setImap_server_host(spImapServerHost.toString().trim());
            //imap server port
            Parameters.setImap_server_port(spImapServerPort.toString().trim());
            //imap SSL
            Parameters.setImap_ssl(Boolean.valueOf(spImapSSL.toString()));
            //imap username
            Parameters.setImap_username(spImapUsername.toString().trim());
            //imap password
            Parameters.setImap_password(spImapPassword.toString().trim());
            //imap poll interval (min)
            Parameters.setImap_interval(Long.valueOf(spImapInterval.toString().trim()));
            //delete email message from email server
            //after being forwarded successfully to the GSM network
            Parameters.setImap_delete_email(Boolean.valueOf(spImapDeleteEmail.toString()));
            //imap folder
            Parameters.setImap_folder(spImapFolder.toString().trim());
            //imap oauth2
            Parameters.setImap_oauth2(Boolean.valueOf(spImapOAuth2.toString()));
            parameters = true;
        }
        if (parameters) {
            Log.i(LOGGER, getClass() + ": Parâmetros IMAP recuperados da memória.");
        }
        return parameters;
    }

    /**
     * Getter POP parameters
     * @return
     */
    private boolean getPopParameters() {

        boolean parameters = false;
        boolean validate = false;

        sp = getSharedPreferences(POP_PREF_NAME, MODE_PRIVATE);
        String spPopRequestDelivery = sp.getString(POP_REQUEST_DELIVERY, "");
        String spPopServerHost = sp.getString(POP_SERVER_HOST, "");
        String spPopServerPort = sp.getString(POP_SERVER_PORT, "");
        String spPopSSL = sp.getString(POP_SSL, "");
        String spPopUsername = sp.getString(POP_USERNAME, "");
        String spPopPassword = sp.getString(POP_PASSWORD, "");
        String spPopFolder = sp.getString(POP_FOLDER, "");
        String spPopOAuth2 = sp.getString(POP_OAUTH2, "");

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
            if ((spPopOAuth2 != null) && (!spPopOAuth2.equals(""))) {
                validate = true;
            } else {
                validate = false;
            }
        }

        if (validate) {
            // request a delivery report
            Parameters.setPop_request_delivery(Boolean.valueOf(spPopRequestDelivery.toString()));
            //pop server host
            Parameters.setPop_server_host(spPopServerHost.toString().trim());
            //pop server port
            Parameters.setPop_server_port(spPopServerPort.toString().trim());
            //pop SSL
            Parameters.setPop_ssl(Boolean.valueOf(spPopSSL.toString()));
            //pop username
            Parameters.setPop_username(spPopUsername.toString().trim());
            //pop password
            Parameters.setPop_password(spPopPassword.toString().trim());
            //pop folder
            Parameters.setPop_folder(spPopFolder.toString().trim());
            //pop oauth2
            Parameters.setPop_oauth2(Boolean.valueOf(spPopOAuth2.toString()));
            parameters = true;
        }
        if (parameters) {
            Log.i(LOGGER, getClass() + ": Parâmetros POP recuperados da memória.");
        }
        return parameters;
    }

    /**
     * Getter SMTP parameters
     * @return
     */
    private boolean getSmtpParameters() {
        boolean parameters = false;
        boolean validate = false;

        sp = getSharedPreferences(SMTP_PREF_NAME, MODE_PRIVATE);
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
            Parameters.setSmtp_enabled_sms(Boolean.valueOf(spSmtpEnableSMS));
            //delete SMS message after being forwarded successfully
            //to the given SMTP server
            Parameters.setSmtp_delete_sms(Boolean.valueOf(spSmtpDeleteSMS));
            //smtp server host
            Parameters.setSmtp_server_host(spSmtpServerHost.toString().trim());
            //smtp server port
            Parameters.setSmtp_server_port(spSmtpServerPort.toString().trim());
            //autentication server required
            Parameters.setSmtp_auth(Boolean.valueOf(spSmtpAuth));
            //ssl required
            Parameters.setSmtp_ssl(Boolean.valueOf(spSmtpSSL));
            //smtp username
            Parameters.setSmtp_username(spSmtpUsername.toString().trim());
            //smtp password
            Parameters.setSmtp_password(spSmtpPassword.toString().trim());
            //forward to email
            Parameters.setSmtp_forward_to_email(spSmtpForwardToEmail.toString().trim());
            //smtp oauth2
            Parameters.setSmtp_oauth2(Boolean.valueOf(spSmtpOAuth2));
            parameters = true;
        }
        if (parameters) {
            Log.i(LOGGER, getClass() + ": Parâmetros SMTP recuperados da memória.");
        }
        return parameters;
    }

}
