package csonline.net.br.smsgateway.util;

/**
 * Created by Christian on 01/12/2015.
 */
public class Parameters {

    // Startup APP
    private static boolean email_active = true;

    private static boolean imap_request_delivery;

    private static String imap_server_host;

    private static String imap_server_port;

    private static String imap_protocol = "imap";

    private static boolean imap_ssl;

    private static String imap_username;

    private static String imap_password;

    private static long imap_interval;

    private static String imap_folder = "Inbox";

    private static boolean imap_debug = false;

    private static boolean imap_delete_email;

    private static boolean imap_oauth2 = false;

    //pop3 parameters
    private static boolean pop_request_delivery;

    private static String pop_server_host;

    private static String pop_server_port;

    private static String pop_protocol = "pop3";

    private static boolean pop_ssl;

    private static String pop_username;

    private static String pop_password;

    private static String pop_folder = "inbox";

    private static boolean pop_oauth2 = false;

    // Received SMS and SMTP settings
    private static boolean smtp_enabled_sms;

    private static boolean smtp_delete_sms;

    private static String smtp_server_host;

    private static String smtp_server_port;

    private static String smtp_protocol = "smtp";

    private static boolean smtp_auth;

    private static boolean smtp_ssl;

    private static String smtp_username;

    private static String smtp_password;

    private static boolean smtp_debug = false;

    private static String smtp_forward_to_email;

    private static boolean smtp_oauth2 = false;

    private static String  email_format = "text/plain";

    public static boolean isEmail_active() {
        return email_active;
    }

    public static void setEmail_active(boolean email_active) {
        Parameters.email_active = email_active;
    }

    public static boolean isImap_request_delivery() {
        return imap_request_delivery;
    }

    public static void setImap_request_delivery(boolean imap_request_delivery) {
        Parameters.imap_request_delivery = imap_request_delivery;
    }

    public static String getImap_server_host() {
        return imap_server_host;
    }

    public static void setImap_server_host(String imap_server_host) {
        Parameters.imap_server_host = imap_server_host;
    }

    public static String getImap_server_port() {
        return imap_server_port;
    }

    public static void setImap_server_port(String imap_server_port) {
        Parameters.imap_server_port = imap_server_port;
    }

    public static String getImap_protocol() {
        return imap_protocol;
    }

    public static void setImap_protocol(String imap_protocol) {
        Parameters.imap_protocol = imap_protocol;
    }

    public static boolean isImap_ssl() {
        return imap_ssl;
    }

    public static void setImap_ssl(boolean imap_ssl) {
        Parameters.imap_ssl = imap_ssl;
    }

    public static String getImap_username() {
        return imap_username;
    }

    public static void setImap_username(String imap_username) {
        Parameters.imap_username = imap_username;
    }

    public static String getImap_password() {
        return imap_password;
    }

    public static void setImap_password(String imap_password) {
        Parameters.imap_password = imap_password;
    }

    public static long getImap_interval() {
        return imap_interval;
    }

    public static void setImap_interval(long imap_interval) {
        Parameters.imap_interval = imap_interval;
    }

    public static String getImap_folder() {
        return imap_folder;
    }

    public static void setImap_folder(String imap_folder) {
        Parameters.imap_folder = imap_folder;
    }

    public static boolean isImap_debug() {
        return imap_debug;
    }

    public static void setImap_debug(boolean imap_debug) {
        Parameters.imap_debug = imap_debug;
    }

    public static boolean isImap_delete_email() {
        return imap_delete_email;
    }

    public static void setImap_delete_email(boolean imap_delete_email) {
        Parameters.imap_delete_email = imap_delete_email;
    }

    public static boolean isImap_oauth2() {
        return imap_oauth2;
    }

    public static void setImap_oauth2(boolean imap_oauth2) {
        Parameters.imap_oauth2 = imap_oauth2;
    }

    public static boolean isPop_request_delivery() {
        return pop_request_delivery;
    }

    public static void setPop_request_delivery(boolean pop_request_delivery) {
        Parameters.pop_request_delivery = pop_request_delivery;
    }

    public static String getPop_server_host() {
        return pop_server_host;
    }

    public static void setPop_server_host(String pop_server_host) {
        Parameters.pop_server_host = pop_server_host;
    }

    public static String getPop_server_port() {
        return pop_server_port;
    }

    public static void setPop_server_port(String pop_server_port) {
        Parameters.pop_server_port = pop_server_port;
    }

    public static String getPop_protocol() {
        return pop_protocol;
    }

    public static void setPop_protocol(String pop_protocol) {
        Parameters.pop_protocol = pop_protocol;
    }

    public static boolean isPop_ssl() {
        return pop_ssl;
    }

    public static void setPop_ssl(boolean pop_ssl) {
        Parameters.pop_ssl = pop_ssl;
    }

    public static String getPop_username() {
        return pop_username;
    }

    public static void setPop_username(String pop_username) {
        Parameters.pop_username = pop_username;
    }

    public static String getPop_password() {
        return pop_password;
    }

    public static void setPop_password(String pop_password) {
        Parameters.pop_password = pop_password;
    }

    public static String getPop_folder() {
        return pop_folder;
    }

    public static void setPop_folder(String pop_folder) {
        Parameters.pop_folder = pop_folder;
    }

    public static boolean isPop_oauth2() {
        return pop_oauth2;
    }

    public static void setPop_oauth2(boolean pop_oauth2) {
        Parameters.pop_oauth2 = pop_oauth2;
    }

    public static boolean isSmtp_enabled_sms() {
        return smtp_enabled_sms;
    }

    public static void setSmtp_enabled_sms(boolean smtp_enabled_sms) {
        Parameters.smtp_enabled_sms = smtp_enabled_sms;
    }

    public static boolean isSmtp_delete_sms() {
        return smtp_delete_sms;
    }

    public static void setSmtp_delete_sms(boolean smtp_delete_sms) {
        Parameters.smtp_delete_sms = smtp_delete_sms;
    }

    public static String getSmtp_server_host() {
        return smtp_server_host;
    }

    public static void setSmtp_server_host(String smtp_server_host) {
        Parameters.smtp_server_host = smtp_server_host;
    }

    public static String getSmtp_server_port() {
        return smtp_server_port;
    }

    public static void setSmtp_server_port(String smtp_server_port) {
        Parameters.smtp_server_port = smtp_server_port;
    }

    public static String getSmtp_protocol() {
        return smtp_protocol;
    }

    public static void setSmtp_protocol(String smtp_protocol) {
        Parameters.smtp_protocol = smtp_protocol;
    }

    public static boolean isSmtp_auth() {
        return smtp_auth;
    }

    public static void setSmtp_auth(boolean smtp_auth) {
        Parameters.smtp_auth = smtp_auth;
    }

    public static boolean isSmtp_ssl() {
        return smtp_ssl;
    }

    public static void setSmtp_ssl(boolean smtp_ssl) {
        Parameters.smtp_ssl = smtp_ssl;
    }

    public static String getSmtp_username() {
        return smtp_username;
    }

    public static void setSmtp_username(String smtp_username) {
        Parameters.smtp_username = smtp_username;
    }

    public static String getSmtp_password() {
        return smtp_password;
    }

    public static void setSmtp_password(String smtp_password) {
        Parameters.smtp_password = smtp_password;
    }

    public static boolean isSmtp_debug() {
        return smtp_debug;
    }

    public static void setSmtp_debug(boolean smtp_debug) {
        Parameters.smtp_debug = smtp_debug;
    }

    public static String getSmtp_forward_to_email() {
        return smtp_forward_to_email;
    }

    public static void setSmtp_forward_to_email(String smtp_forward_to_email) {
        Parameters.smtp_forward_to_email = smtp_forward_to_email;
    }

    public static boolean isSmtp_oauth2() {
        return smtp_oauth2;
    }

    public static void setSmtp_oauth2(boolean smtp_oauth2) {
        Parameters.smtp_oauth2 = smtp_oauth2;
    }

    public static String getEmail_format() {
        return email_format;
    }

    public static void setEmail_format(String email_format) {
        Parameters.email_format = email_format;
    }
}
