package com.swcat.giftapp.DAO;

import java.net.URI;
import java.net.URISyntaxException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.swcat.giftapp.JpaRepo.accountInfoRepo;
import com.swcat.giftapp.Models.OrderUserModel;
import com.swcat.giftapp.Models.gPackComments.MailCommentModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Source code có thảm khảo 1 phần từ : https://openplanning.net/11145/spring-email
 */

@Service
public class MailService {
    
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private accountInfoRepo accountInfoRepo;

    public void sendEmailToCustomer(OrderUserModel order, String uname) throws MessagingException{
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlString = "<html>\r\n"
        + "    <head>\r\n"
        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
        + "        <title>JSP Page</title>\r\n"
        + "    </head>\r\n"
        + "    <body>\r\n"
        + "        <h1>Thank you for order my gift website!</h1>\r\n"
        + "        Your order was handled. Please check your order on website\r\n"
        + "        <a href=\"\"></a>\r\n"
        + "            <table>\r\n"
        + "                <th>\r\n"
        + "                    Order ID\r\n"
        + "                </th>\r\n"
        + "                <th>\r\n"
        + "                    Total Cost\r\n"
        + "                </th>\r\n"
        + "                <th>\r\n"
        + "                    Date order\r\n"
        + "                </th>\r\n"
        + "                 <th>\r\n"
        + "                    Date arrive\r\n"
        + "                </th>\r\n"
        + "                 <th>\r\n"
        + "                    Greeting card content\r\n"
        + "                </th>\r\n"
        + "                 <th>\r\n"
        + "                    Status\r\n"
        + "                </th>\r\n"
        + "                <tr>\r\n"
        + "                    <td>\r\n"
        +                         order.getCid()+"\r\n"
        + "                    </td>\r\n"
        + "                    <td>\r\n"
        +                         order.getCost()+"\r\n"
        + "                    </td>\r\n"
        + "                    <td>\r\n"
        +                         order.getDateOrder()+"\r\n"
        + "                    </td>\r\n"
        + "                    <td>\r\n"
        +                         order.getDateArrive()+"\r\n"
        + "                    </td>\r\n"
        + "                    <td>\r\n"
        +                         order.getGreetingCardContent()+"\r\n"
        + "                    </td>\r\n"
        + "                    <td>\r\n"
        +                         order.getStatus()+"\r\n"
        + "                    </td>\r\n"
        + "                </tr>\r\n"
        + "            </table>\r\n"
        + "    <h3>You can check your order in " + "http://tonydomain.ddns.net/api/user/order/id/"+order.getCid() + " </h3>\r\n"
        + "    </body>\r\n"
        + "</html>\r\n"
        + "";

        message.setContent(htmlString, "text/html");
        helper.setTo(findEmailByUserName(uname));
        helper.setSubject("Comfirm your order gift shop");
        this.emailSender.send(message);
    }
    public void sendEmailResloveComment(MailCommentModel mailCommentModel) throws MessagingException
    {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlString = "<html>\r\n"
        + "    <head>\r\n"
        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
        + "        <title>JSP Page</title>\r\n"
        + "    </head>\r\n"
        + "    <body>\r\n"
        + "        <h1>Thank you for comment feed back your oder gift in website!</h1>\r\n"
        + "        Store had reslove this feedback. Thank you for contribute about service \r\n"
        + "        <a href=\"\"></a>\r\n"
        + "            <table>\r\n"
        + "                <tr>\r\n"
        + "                <th>\r\n"
        + "                    Your comment\r\n"
        + "                </th>\r\n"
        + "                    <td>\r\n"
        +                         mailCommentModel.getContent()
        + "                    </td>\r\n"
        +"                  </tr>\r\n"
        + "                <tr>\r\n"
        + "                <th>\r\n"
        + "                    Response comment of store\r\n"
        + "                </th>\r\n"
        + "                    <td>\r\n"
        +                        mailCommentModel.getCommentResolve()
        + "                    </td>\r\n"
        + "                </tr>\r\n"
        + "            </table>\r\n"
        + "    \r\n"
        + "    </body>\r\n"
        + "</html>\r\n"
        + "";
        message.setContent(htmlString, "text/html");
        helper.setTo(mailCommentModel.getEmail());
        helper.setSubject("Reslove the feedback of gift package");
        this.emailSender.send(message);
    }
    public String findEmailByUserName(String uname){
        //String email = accountInfoRepo.findById(uname).get()
        //return "giakhangnguyen115@gmail.com";

        String email = accountInfoRepo.findById(uname).get().getEmail();
        return email;
    }

}
