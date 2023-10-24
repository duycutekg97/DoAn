
import com.qlphongtro.service.EmailService;
import com.qlphongtro.service.Impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HOME
 */
public class run {
    @Autowired
    private static JavaMailSender emailSender;
   
    public static void main(String[] args) {
        sendSimpleMessage("nino02022002@gmail.com", "HELLO", "HELLO");
        System.out.print("OK");
        
    }
    public static void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("2051010290thien@ou.edu.vn");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }
    
}
