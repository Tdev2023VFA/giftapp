package com.swcat.giftapp.DAO.comments;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.ws.rs.GET;

import com.swcat.giftapp.DAO.MailService;
import com.swcat.giftapp.Entities.giftpackComments;
import com.swcat.giftapp.Entities.orderProcess;
import com.swcat.giftapp.Jersey.MailConfig;
import com.swcat.giftapp.JpaRepo.giftpackCommentsRepo;
import com.swcat.giftapp.JpaRepo.orderProcessRepo;
import com.swcat.giftapp.Models.delivery.orderProcessModel;
import com.swcat.giftapp.Models.gPackComments.MailCommentModel;
import com.swcat.giftapp.Models.gPackComments.gPackCommentsModel;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class commentService {
    @Autowired
    private  giftpackCommentsRepo commentsRepo;

    @Autowired
    private MailService mailService;


    public void sendEmailResloveComment(MailCommentModel mailCommentModel) throws MailException
    {
        try{
            mailService.sendEmailResloveComment(mailCommentModel);
        }
        catch(MessagingException exception)
        {
            exception.printStackTrace();
        }
    }

    public void addPackCommentsModel(gPackCommentsModel giftpackCommentModel)
    {
        giftpackComments giftpackComments=ConvertgiftpackCommentsModelTogiftpackComments(giftpackCommentModel);
        commentsRepo.save(giftpackComments);
    }
    
    public gPackCommentsModel findgPackCommentsModelByPackID(int giftpackId)
    {
        giftpackComments giftpackComments=commentsRepo.findByGiftpackId(giftpackId);
        gPackCommentsModel commentsModel=ConvertgiftpackCommentsgPackCommentsModel(giftpackComments);
        return commentsModel;

    }
    public List<gPackCommentsModel> listCommentsByUname(String uname)
    {
        List<giftpackComments> giftpackComments=commentsRepo.findByUname(uname);
        List<gPackCommentsModel> gPackCommentsModels=giftpackComments.stream()
                            .map(giftpackComment->ConvertgiftpackCommentsgPackCommentsModel(giftpackComment))
                            .collect(Collectors.toList());
        return gPackCommentsModels;

    }



    public void deletePackComments(int giftpackId)
    {
        commentsRepo.deleteById(giftpackId);
    }

    public void updatePackComments(gPackCommentsModel giftpackCommentModel)
    {
        giftpackComments giftpackComments=ConvertgiftpackCommentsModelTogiftpackComments(giftpackCommentModel);
        commentsRepo.save(giftpackComments);
    }

    public boolean existComments(int commentId)
    {
        return commentsRepo.existsById(commentId);
    }


    public List<gPackCommentsModel> listComments()
    {
        List<giftpackComments> giftpackComments=commentsRepo.findAll();
        List<gPackCommentsModel> gPackCommentsModels=giftpackComments.stream()
                            .map(giftpackComment->ConvertgiftpackCommentsgPackCommentsModel(giftpackComment))
                            .collect(Collectors.toList());
        return gPackCommentsModels;

    }

    public gPackCommentsModel ConvertgiftpackCommentsgPackCommentsModel(giftpackComments giftpackComments)
    {
        gPackCommentsModel commentsModel=new gPackCommentsModel(giftpackComments.getCid(), giftpackComments.getGiftpackId(), giftpackComments.getUname(), giftpackComments.getContent());
        return commentsModel;
    }
    public giftpackComments ConvertgiftpackCommentsModelTogiftpackComments(gPackCommentsModel gPackCommentsModel)
    {
        giftpackComments giftpackComments=new giftpackComments(gPackCommentsModel.getCid(), gPackCommentsModel.getGiftpackId(), gPackCommentsModel.getUname(), gPackCommentsModel.getContent());
        return giftpackComments;
    }
}
