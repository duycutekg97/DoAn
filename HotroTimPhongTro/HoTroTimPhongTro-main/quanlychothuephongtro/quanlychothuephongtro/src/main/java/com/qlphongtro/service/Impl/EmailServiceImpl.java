/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.service.Impl;

import com.qlphongtro.service.EmailService;
import com.qlphongtro.service.UserService;
import freemarker.template.Configuration;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author HOME
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender sender;
    @Autowired
    public SimpleMailMessage template;
    @Autowired
    private UserService userService;
    @Autowired
    private Configuration freemarkerConfiguration;

    @Override
    public void sendSimpleMessageDiemTB(String fileNameTMP,ArrayList<String> emailsTMP) {

        String from = "2051010290thien@ou.edu.vn";
//        ArrayList<String> emails = new ArrayList<String>();
//        emails.add("nino02022002@gmail.com");
//                emails.add("nino07052002@gmail.com");
        ArrayList<String> emails = emailsTMP;

        InternetAddress dests[] = new InternetAddress[emails.size()];
        for (int i = 0; i < emails.size(); i++) {
            try {
                dests[i] = new InternetAddress(emails.get(i).trim().toLowerCase());
            } catch (AddressException ex) {
                Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String fileName = fileNameTMP;
        String home = System.getProperty("user.home");
        File file = new File(home + "/Downloads/" + fileName + ".pdf");
        final String username = "2051010290thien@ou.edu.vn";//change accordingly
        final String password = "0344142375";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(recipientAddress));
            message.setRecipients(Message.RecipientType.TO,
                    dests);

            // Set Subject: header field
            message.setSubject("Điểm nè các em");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Danh sách điểm của các khóa luận");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = file.toString();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("DiemTB.pdf");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendHtmlMessage(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            sender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendEmail(InternetAddress[] to, String subject, Map<String, Object> model) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setFrom("AccommodationSearchSupport");
                String text = geFreeMarkerTemplateContent(model);
                helper.setText(text, true);
            }
        };
        sender.send(preparator);
    }

    @Override
    public String geFreeMarkerTemplateContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("email-follower.jsp"), model));
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail";
    }
}
