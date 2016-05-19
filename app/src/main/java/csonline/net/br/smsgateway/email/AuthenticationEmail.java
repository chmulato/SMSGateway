	package csonline.net.br.smsgateway.email;

    import javax.mail.Authenticator;
    import javax.mail.PasswordAuthentication;

    /**
     * @author Christian Mulato
     * @date   May/09th/2011
     */
    class AuthenticationEmail extends Authenticator {

        public String username = null;
        public String password = null;

        public AuthenticationEmail(String user, String pwd) {
            username = user;
            password = pwd;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication (username, password);
        }
}