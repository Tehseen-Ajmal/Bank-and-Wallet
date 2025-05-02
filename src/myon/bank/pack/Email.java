package myon.bank.pack;

import User_Online.Const;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Email {
    protected static String bank = "Bank My own 🏦";

    protected boolean send_mail(String to, String subject, String text, String name) {
        String from = Const.email_acc;
        boolean send_status = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        //zacz ugdk wmmj zmsu
        String sender = "bank.myon";
        String pass = Const.key_email;
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, pass);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
//            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, bank));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(text, "utf-8", "html");
            Transport.send(msg);
            send_status = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return send_status;
    }

}