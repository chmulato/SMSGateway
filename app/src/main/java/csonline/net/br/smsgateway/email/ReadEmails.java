package csonline.net.br.smsgateway.email;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import csonline.net.br.smsgateway.exception.AppException;
import csonline.net.br.smsgateway.model.EmailVO;
import csonline.net.br.smsgateway.util.Parameters;
import csonline.net.br.smsgateway.util.Tools;

import org.jsoup.Jsoup;

import java.io.IOException;

public class ReadEmails {

    private static final String LOGGER = "LogCat";

    private static List<EmailVO> listAllEmails;

    private static boolean deleteMessages;

    public static void setDeleteMessages(boolean deleteMessages) {
        ReadEmails.deleteMessages = deleteMessages;
    }

    public static void setListAllEmails(List<EmailVO> listAllEmails) {
        ReadEmails.listAllEmails = listAllEmails;
    }

    public static List<EmailVO> getListAllEmails() {
        return listAllEmails;
    }

    private static boolean isThisMessageToDelete(Message message) throws MessagingException {
        boolean validate = false;
        boolean delete = false;
        if ((message.getSubject()!=null) && (!message.getSubject().equals(""))) {
            validate = true;
        }
        if (validate) {
            validate = message.getSentDate()!=null;
        }

        if (validate) {
            String subject = message.getSubject();
            if ((getListAllEmails()!=null) && (getListAllEmails().size()>0)) {
                ListIterator<EmailVO> listIterator = getListAllEmails().listIterator();
                while (listIterator.hasNext()) {
                    EmailVO email = listIterator.next();		
                    if (email!=null) {
                        boolean equal = false;
                        if (email.getFrom()!=null) {
                            equal = true;
                        }

                        if (equal) {
                            equal = email.getData()!=null;
                        }

                        if (equal) {
                            String mobile = email.getFrom();
                            Date date = email.getData();
                            if ((subject.contains(mobile)) && (date.equals(message.getSentDate()))) {
                                String msg = "Deletar email: subject = "
                                        + message.getSubject()
                                        + " | date= "
                                        + Tools.converteDateToString(message.getSentDate(), "dd/MM/yyyy HH:mm:ss");

                                Log.i("Info: ", msg);
                                delete = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return delete;
    }

    public static void connectionAccountMail() throws AppException {
        
        String msg;
    	String username = Parameters.getImap_username();
        String password = Parameters.getImap_password();
        String protocolo = Parameters.getImap_protocol();
        String server = Parameters.getImap_server_host();
        String port = Parameters.getImap_server_port();
        boolean ssl = Parameters.isImap_ssl();
        String format = Parameters.getEmail_format();
        String folder = Parameters.getImap_folder();
        String timeout = String.valueOf(Parameters.getImap_interval());
        boolean oauth2 = Parameters.isImap_oauth2();

        if ((protocolo==null) || (protocolo.equals(""))) {
            msg = "Informe protocolo do servidor p/ recebimento de email!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((server==null) || (server.equals(""))) {
            msg = "Informe endereço IMAP do servidor!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((port==null) || (port.equals(""))) {
            msg = "Informe a porta do servidor IMAP!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((username==null) || (username.equals(""))) {
            msg = "Informe a conta do usuário do servidor IMAP!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((password==null) || (password.equals(""))) {
            msg = "Informe a senha do usuário do servidor IMAP!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((format==null) || (format.equals(""))) {
            msg = "Informe formato de e-mail p/ leitura!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }

        if ((timeout==null) || (timeout.equals(""))) {
            msg = "Informe timeout de e-mail p/ leitura!";
            Log.e("Error: ", msg);
            throw new AppException(msg);
        }
        
        try {
            Properties properties = new Properties();
            //IMAPS protocol
            properties.setProperty("mail.store.protocol", protocolo);
            //Set host address
            properties.setProperty("mail.imap.host", server);
            properties.setProperty("mail.imap.connectiontimeout", timeout);
            properties.setProperty("mail.imap.timeout", timeout);
            //Set specified port
            Log.i("Port ", port);
            properties.setProperty("mail.imap.port", port);
            if (port.equals("143")) {
                properties.setProperty("mail.imap.ssl.enable", "true");
            }
            if (port.equals("993")) {
                properties.setProperty("mail.imap.starttls.enable", "false");
                properties.setProperty("mail.imap.ssl.enable", "true");
            }
            //Using SSL
            if (ssl) {
                properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.setProperty("mail.imap.socketFactory.fallback", "false");
            }
            //Using Open Authentication XOAUTH2
            if (oauth2) {
                properties.setProperty("mail.imap.sasl.enable", "true");
                properties.setProperty("mail.imap.sasl.mechanisms", "XOAUTH2");
                properties.setProperty("mail.imap.auth.login.disable", "true");
                properties.setProperty("mail.imap.auth.plain.disable", "true");
            }
            //Cria um autenticador que sera usado a seguir
            AuthenticationEmail auth = new AuthenticationEmail (username, password);
            if (!deleteMessages) {
                Log.i("Info: ","--------------Início de Processamento de Leitura de Emails-----------------");
            } else {
                Log.i("Info: ","--------------Início de Processamento de Deletar de Emails-----------------");
            }
            //Setting IMAP session
            Session session = Session.getDefaultInstance(properties, auth);
            Log.i("Debug ","TRUE");
            Log.i("Info: ","Abrindo sessão para acessar emails ...");
            Store store = session.getStore(protocolo);
            Log.i("Info: ", "Conexão estabelecida com o servidor imap: " + server);
            //Connect to server by sending username and password.
            //Example mailServer = imap.gmail.com, username = abc, password = abc
            Log.i("Server ", server);
            Log.i("Username ", username);
            Log.i("Password ", password);
            store.connect(server, username, password);
            //Get all mails in Inbox Folder
            Folder inbox = store.getFolder(folder);
            Log.i("Total messages ", " " + store.getFolder(folder).getMessageCount());
            if (!deleteMessages) {
                inbox.open(Folder.READ_ONLY);
                readerAllMails(inbox);
            } else {
                inbox.open(Folder.READ_WRITE);
                deleteAllMails(inbox);
            }
            // Close connection 
            inbox.close(true);
            store.close();
            msg = "--------------Finalização do Processo de Leitura de Emails-----------------";
            if (!deleteMessages) {
                Log.i("Info: ", msg);
            } else {
                Log.i("Info: ", msg);
            }
        } catch (Exception e) {
            msg = "Não foi possível acessar as mensagens! ";
            Log.e(LOGGER, "Error: " + msg , e);
            throw new AppException(msg);
        }
    }

    private static void readerAllMails(Folder inbox) {
        String msg = "Leitura de emails... pasta: " + inbox.getFullName();
    	Log.i("Info: ", msg);
    	boolean loop = false;
        try {
            Message messages[] = inbox.getMessages();
            if ((messages!=null) && (messages.length > 0)) {
                msg = "Total de emails lidos: " + messages.length;
                Log.i("Info: ", msg);
                int count = 0;
                for(Message message:messages) {
                    msg = "Mensagem existente para leitura! " + (count + 1);
                    Log.i("Info: ", msg);
                    msg = "e ..., contanto.";
                    Log.i("Info: ", msg);
                    if ((message.getSubject()!=null) && (!message.getSubject().equals(""))) {
                        Log.i("Info: ", msg);
                        EmailVO vo = new EmailVO();
                        if (!loop) {
                            listAllEmails = new ArrayList<EmailVO>();
                            loop = true;
                        }
                        msg = "DATE: " + Tools.converteDateToString(message.getSentDate(), "dd/MM/yyyy HH:mm:ss");
                        Log.i("Info: ", msg);
                        vo.setData(message.getSentDate());
                        String from = message.getFrom()[0].toString();
                        msg = "FROM: " + from;
                        Log.i("Info: ", msg);
                        vo.setFrom(from);
                        msg = "SUBJECT: " + String.valueOf(message.getSubject());
                        Log.i("Info: ", msg);
                        vo.setSubject(String.valueOf(message.getSubject()));
                        String content = "";
                        if(message instanceof MimeMessage) {
                            MimeMessage m = (MimeMessage) message;
                            Object contentObject = m.getContent();
                            if(contentObject instanceof Multipart) {
                                BodyPart clearTextPart = null;
                                BodyPart htmlTextPart = null;
                                Multipart multipart = (Multipart)contentObject;
                                int mpCount = multipart.getCount();
                                for(int i=0; i<mpCount; i++) {
                                    BodyPart part =  multipart.getBodyPart(i);
                                    if(part.isMimeType("text/plain")) {
                                        clearTextPart = part;
                                        break;
                                    } else if(part.isMimeType("text/html")) {
                                        htmlTextPart = part;
                                    }
                                }
                                if(clearTextPart!=null) {
                                    content = (String) clearTextPart.getContent();
                                } else if (htmlTextPart!=null) {
                                    String html = (String) htmlTextPart.getContent();
                                    content = Jsoup.parse(html).text();
                                }
                            } else if (contentObject instanceof String) {
                                // a simple text message
                                content = (String) contentObject;
                            } else {
                                // not a mime message
                                msg = "It is not a mime part or multipart";
                                Log.e("Error: ", msg);
                                content = null;
                            }
                        }
                        msg = "CONTENT: " + String.valueOf(content);
                        Log.i("Info: ", msg);
                        vo.setContent(String.valueOf(content));
                        msg = "Carregando mensagens na lista ...";
                        Log.i("Info: ", msg);
                        listAllEmails.add(vo);
                        msg = "Mensagens carregadas corretamente!";
                        Log.i("Info: ", msg);
                    }
                    count = count + 1;
                }
                msg = "Looping leitura finalizado!";
                Log.i("Info: ", msg);
            }
        } catch (MessagingException e) {
            msg = "Não foi possível ler as mensagens! " + e.getMessage();
            Log.e("Error: ", msg);
        }  catch (IOException e) {
            msg = "Não foi possível ler as mensagens! " + e.getMessage();
            Log.e("Error: ", msg);
        }
    	if ((listAllEmails!=null) && (listAllEmails.size()>0)) {
            msg = "Ordenar lista de mensagens!";
            Log.i("Info: ", msg);
            Collections.sort(listAllEmails);
    	}
    }

    private static void deleteAllMails(Folder inbox) {
        String msg = "Deletar mensagens... pasta: " + inbox.getFullName();
    	Log.i("Info: ", msg);
        try {
            Message messages[] = inbox.getMessages();
            if ((messages!=null) && (messages.length > 0)) {
                msg = "Total de emails para verificar se será deletadas: " + messages.length;
                Log.i("Info: ", msg);
                int count = 0;
                for(Message message:messages) {
                    msg = "Mensagem existente para deletar: " + (count + 1);
                    Log.i("Info: ", msg);
                    msg = "e ..., contanto.";
                    Log.i("Info: ", msg);
                    boolean delete = isThisMessageToDelete(message);
                    msg = "Deletar mensagem selecionada? " + (delete ? "- Sim" : " - Não" );
                    Log.i("Info: ", msg);
                    if (delete) {
                        message.setFlag(Flags.Flag.DELETED, true);
                        msg = "Mensagem deletada!";
                        Log.i("Info: ", msg);
                    }
                    count = count + 1;
                }
                msg = "Looping delete finalizado!";
                Log.i("Info: ", msg);
            }
        } catch (MessagingException e) {
            msg = "Não foi possível deletar todas as mensagens! " + e.getMessage();
            Log.e("Error: ", msg);
        }
    }
}
