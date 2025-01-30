    import java.util.Properties;    
    import javax.mail.*;    
    import javax.mail.internet.*;    
    class Mailer{  
        static String from="parthpatidar183@gmail.com";
        static String pwd="odzj wxwg phph arhc";
        public static void send(String to,String sub,String msg){  
              //Get properties object    
              Properties props = new Properties(); 
              props.put("mail.smtp.ssl.enable", "true");
props.put("mail.smtp.ssl.protocols", "TLSv1.2");
/*
              props.put("mail.smtp.host", "smtp.gmail.com");    
              props.put("mail.smtp.socketFactory.port", "465");    
              props.put("mail.smtp.socketFactory.class",    
                        "javax.net.ssl.SSLSocketFactory");    
              props.put("mail.smtp.auth", "true");    
              props.put("mail.smtp.port", "465");  
*/


props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "465");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.ssl.enable", "true");
props.put("mail.smtp.ssl.required", "true");
props.put("mail.smtp.ssl.protocols", "TLSv1.2");
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

              //get Session   
              Session session;
            session = Session.getDefaultInstance(props,    
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from,pwd);
                        }    
                    });
              //compose message    
              try {    
               MimeMessage message = new MimeMessage(session);    
               message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
               message.setSubject(sub);    
               message.setText(msg);    
               //send message  
               Transport.send(message);    
               System.out.println("message sent successfully");    
              } catch (MessagingException e) {throw new RuntimeException("Exception fron mailer page"+e);}    
          
              
          
    }  
      
    }    