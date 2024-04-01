package py.com.progweb.prueba.utils;

import py.com.progweb.prueba.model.Customer;
import py.com.progweb.prueba.model.PointsHeader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailUtils {

    private static final Logger LOGGER = Logger.getLogger(EmailUtils.class.getName());

    private static Session session;

    public static Session getSession() {
        if (session == null) {
            String user = System.getenv("user");
            String pass = System.getenv("pass");

            // Create a static session object that can be reused across requests
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.office365.com");
            properties.setProperty("mail.smtp.port", "587");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");


            session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });
        }
        return session;
    }

    public static void enviarCorreo(String from, String to, String subject, String body) {
        new Thread(() -> {
            enviarCorreoThread(from, to, subject, body);
        }).start();
    }

    public static void enviarCorreoThread(String from, String to, String subject, String body) {
        Session session = getSession();
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Correo enviado " + message);
    }

    public static String getCuerpoEmail(Customer cliente, PointsHeader pointsHeader, int remainingBalance) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String retorno = "Hola " + cliente.getFirstName() + " " + cliente.getLastName() + ",<br><br>" +
                "<br><br> Se registró un uso de puntos el " + formatter.format(pointsHeader.getUsageDate()) +
                "<br><b>Concepto:</b> " + pointsHeader.getPointUseConcept().getDescription() +
                "<br><b>Puntos usados:</b> " + pointsHeader.getPointsUsed() +
                "<br><br>Actualmente le quedan <b>" + remainingBalance + "</b> puntos";
        return "<html><body>" + retorno + "</body></html>";
    }
}