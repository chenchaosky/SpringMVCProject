package demo.Email;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService{
//    private static Logger logger = Logger.getLogger(EmailServiceImpl.class);

    private String excelPath = "D:/SourceCode/Java_SourceCode/SpringDemo/";
    
    @Resource
    private JavaMailSender javaMailSender;
    
    @Resource
    private SimpleMailMessage simpleMailMessage;
    
    @Override
    public void emailManage(){
        MailModel mail = new MailModel();
        //subject
        mail.setSubject("Inventory"); 
        
        //attachment
        Map<String, String> attachments = new HashMap<String, String>();
        attachments.put("test.txt",excelPath+"test.txt");
        mail.setAttachments(attachments);
        
        //context
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>Hello£¡<br />");
        builder.append("&nbsp&nbsp&nbsp&nbsp Attachement is the inventory.<br />");
        builder.append("&nbsp&nbsp&nbsp&nbsp Personal information:<br />");
        builder.append("</body></html>");
        String content = builder.toString();
        
        mail.setContent(content);
        
        try {
			sendEmail(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }



    /**
     * Sending Email
     * 
     * @author chenchao
     * @date 2016-5-9 ÉÏÎç11:18:21
     * @throws Exception
     */
    @Override
    public void sendEmail(MailModel mail) throws Exception  {
        //Creating Email type messages
        MimeMessage message = javaMailSender.createMimeMessage();
        
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            // Configuring sender's email details
            if (mail.getEmailFrom()!=null) {
                messageHelper.setFrom(mail.getEmailFrom());
            } else {
                messageHelper.setFrom(simpleMailMessage.getFrom());
            }
            
            //Configuring receiver's email details
            if (mail.getToEmails()!=null) {
                String[] toEmailArray = mail.getToEmails().split(";");
                List<String> toEmailList = new ArrayList<String>();
                if (null == toEmailArray || toEmailArray.length <= 0) {
                    throw new Exception("The receiver should not be null");
                } else {
                    for (String s : toEmailArray) {
                        if (s!=null&&!s.equals("")) {
                            toEmailList.add(s);
                        }
                    }
                    if (null == toEmailList || toEmailList.size() <= 0) {
                        throw new Exception("The receiver should not be null");
                    } else {
                        toEmailArray = new String[toEmailList.size()];
                        for (int i = 0; i < toEmailList.size(); i++) {
                            toEmailArray[i] = toEmailList.get(i);
                        }
                    }
                }
                messageHelper.setTo(toEmailArray);
            } else {
                messageHelper.setTo(simpleMailMessage.getTo());
            }
            
            //Email's subject
            if (mail.getSubject()!=null) {
                messageHelper.setSubject(mail.getSubject());
            } else {
                
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }
            
            //true refers HTML email
            messageHelper.setText(mail.getContent(), true);
            
            //Adding pictures
            if (null != mail.getPictures()) {
                for (Iterator<Map.Entry<String, String>> it = mail.getPictures().entrySet()
                        .iterator(); it.hasNext();) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("Confirm every picture's ID and Address.");
                    }
                    
                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("Picture" + filePath + "does not exist£¡");
                    }
                    
                    FileSystemResource img = new FileSystemResource(file);
                    messageHelper.addInline(cid, img);
                }
            }
            
            //Adding attachments
            if (null != mail.getAttachments()) {
                for (Iterator<Map.Entry<String, String>> it = mail.getAttachments()
                        .entrySet().iterator(); it.hasNext();) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("Confirm every attachment's ID and Address.");
                    }
                    
                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("attachment" + filePath + "does not exist.");
                    }
                    
                    FileSystemResource fileResource = new FileSystemResource(file);
                    messageHelper.addAttachment(cid, fileResource);
                }
            }
            messageHelper.setSentDate(new Date());
            //Sending Email
            javaMailSender.send(message);
            System.out.println("Send email successfully");
//            logger.info("------------send email successfully----------");
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
