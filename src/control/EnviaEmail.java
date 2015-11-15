/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author USER
 */
public class EnviaEmail {
        public static void enviaMail(String to, String text) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
       

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("sistema.escolar.bj@gmail.com", "jaSwUwuje3");
                        }
                    }
            );
//            
            MimeMessage msg = new MimeMessage(session);

            if (to.contains(";")) {
                String[] emails = to.split(";");
                Address[] addresses = new Address[emails.length];
                for (int i = 0; i < emails.length; i++) {
                    addresses[i] = new InternetAddress(emails[i]);
                }
                msg.addRecipients(Message.RecipientType.TO, (Address[]) addresses);
            } else {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            msg.setFrom(new InternetAddress("sistema.escolarbj@gmail.com"));
            msg.setSubject("Sistema Escolar Benito Juarez");
            msg.setContent("<font size='12' color='blue'>BENITO JUAREZ .</font><br/>" + text + "", "text/html; charset=utf-8");
            System.out.println("Enviando email");
            Transport.send(msg);
        } catch (NoSuchProviderException ex) {
           System.out.println(ex.getCause().toString());
        } catch (MessagingException ex) {
            System.out.println(ex.getCause().toString());
        }
        System.out.println("Mail enviado correctamente");
    }
}
