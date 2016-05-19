package csonline.net.br.smsgateway.email;

import android.util.Log;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import csonline.net.br.smsgateway.exception.AppException;
import csonline.net.br.smsgateway.util.Parameters;

import javax.mail.MessagingException;
    
/**
 * @author Christian Mulato
 * @date   Nov/14th/2013
 */
public class SendEmail {

    private static final String LOGGER = "LogCat";

    public SendEmail(String[] recipient, String subject, String message) throws AppException {
        if (Parameters.isEmail_active()) {
            sendEmail(recipient, subject, message, false, null, null);
        }
    }

    public SendEmail(String[] recipient, String subject, String message, String pathFileAttached, String filename) throws AppException {
        if (Parameters.isEmail_active()) {
            sendEmail(recipient, subject, message, true, pathFileAttached, filename);
        }
    }

    private void sendEmail(String[] recipient, String subject, String message, boolean attachment, String pathFileAttached, String filename) throws AppException {

        String msg;
        String username = Parameters.getSmtp_username();
        String password = Parameters.getSmtp_password();
        String protocolo = Parameters.getSmtp_protocol();
        String server = Parameters.getSmtp_server_host();
        String port = Parameters.getSmtp_server_port();
        boolean ssl = Parameters.isSmtp_ssl();
        String authentication = String.valueOf(Parameters.isSmtp_auth());
        boolean debug = Parameters.isSmtp_debug();
        String format = Parameters.getEmail_format();

        if ((protocolo==null) || (protocolo.equals(""))) {
            msg = "Informe protocolo do servidor p/ envio do email!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((server==null) || (server.equals(""))) {
            msg = "Informe endereço SMTP do servidor!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((port==null) || (port.equals(""))) {
            msg = "Informe a porta do servidor SMTP!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((authentication==null) || (authentication.equals(""))) {
            msg = "Informe se o servidor SMTP utiliza autenticação!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((username==null) || (username.equals(""))) {
            msg = "Informe a conta do usuário do servidor SMTP!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((password==null) || (password.equals(""))) {
            msg = "Informe a senha do usuário do servidor SMTP!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((recipient==null) || (recipient.length==0)) {
            msg = "Informe a conta eletrônica p/ envio do(s) documento(s)!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((format==null) || (format.equals(""))) {
            msg = "Informe formato de e-mail p/ enviar!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        try {

            Properties properties = new Properties();

            //define protocolo de envio
            properties.put("mail.transport.protocol", String.valueOf(protocolo));
            //server SMTP
            properties.put("mail.smtp.host", server);
            //porta
            properties.put("mail.smtp.port", port);
            //para ativar autenticacao insira "true"
            properties.put("mail.smtp.auth", authentication);
            //conta que esta enviando o email
            properties.put("mail.smtp.user", username);

            if (debug) {
                properties.put("mail.debug", "true");
            }

            //mesma porta para o socket
            properties.put("mail.smtp.socketFactory.port", port);

            if (ssl) {
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.socketFactory.fallback", "false");
            }

            properties.put("mail.smtp.quitwait", "false");

            //Cria um autenticador que sera usado a seguir
            AuthenticationEmail auth = new AuthenticationEmail (username, password);

            //Session - objeto que ira realizar a conexão com o servidor
            /*Como há necessidade de autenticação é criada uma autenticacao que
             * é responsavel por solicitar e retornar o usuário e senha para
             * autenticação */
            Session session = Session.getDefaultInstance(properties, auth);

            //Habilita o LOG das ações executadas durante o envio do email
            session.setDebug(true);
            //Objeto que contém a mensagem
            MimeMessage mimeMessage = new MimeMessage(session);

            //Setando os destinatários
            InternetAddress[] addressTo = new InternetAddress[recipient.length];
            for (int i = 0; i < recipient.length; i++) {
                addressTo[i] = new InternetAddress(recipient[i]);
            }

            mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);             	
            //Setando a origem do email
            mimeMessage.setFrom(new InternetAddress(username));
            //Setando o assunto
            mimeMessage.setSubject(subject);
            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setText(message);
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            if (attachment) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(pathFileAttached + filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }
            // Send the complete message parts
            mimeMessage.setContent(multipart);
            //Objeto encarregado de enviar os dados para o email
            Transport tr;
            //define smtp para transporte
            tr = session.getTransport(protocolo); 
            /*
             *  1 - define o servidor smtp
             *  2 - seu nome de usuario
             *  3 - sua senha
             */
            tr.connect(server, username, password);
            mimeMessage.saveChanges(); // não esqueca isso
            //envio da mensagem
            tr.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            tr.close();
        } catch (MessagingException e) {
            msg = " Não foi possível enviar a mensagem! ";
            Log.e(LOGGER, getClass() + msg , e);
            throw new AppException(msg);
        } catch (Exception e) {
            msg = " Não foi possível enviar a mensagem! ";
            Log.e(LOGGER, getClass() + msg , e);
            throw new AppException(msg);
        }
    }
}
