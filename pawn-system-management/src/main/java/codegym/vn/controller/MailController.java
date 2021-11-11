//package codegym.vn.controller;
//
//import codegym.vn.service.ContractService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Component
//@EnableScheduling
//public class MailController {
//    @Autowired
//     JavaMailSender emailSender;
//    @Autowired
//     ContractService contractService;
//    @Scheduled(cron = " 0 50 20 ? * * ")
//    public void setEmail(){
//        List<String> listEmailOneTime =contractService.getAllEmailToSend();
//        Set<String> listMail = new HashSet<>();
//        listMail.addAll(listEmailOneTime);
//        if (!(listMail.size()==0)){
//            String [] array =listMail.toArray(new String[0]);
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(array);
//            message.setSubject("[Thông báo] Hợp đồng Của Bạn Sắp Hết Hạn ");
//            this.emailSender.send(message);
//        }else{
//            System.out.println("Have not email to send!");
//
//        }
//    }
//}

//import org.springframework.data.jpa.repository.Query;
//
//@Query(value = "select email from contract join customer on customer.customer_id = contract.customer_id  " +
//        "where DATE(contract.end_date)  = curdate()+1;",nativeQuery = true)
//    List<String> getAllEmailToSend();
//
//private void sendSimpleEmail(Contract contract) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(contract.getCustomer().getEmail());
//        message.setSubject("[Thông báo] Hợp đồng Của Bạn Sắp Hết Hạn");
//        message.setText("Chào, khách hàng " + contract.getCustomer().getName() + "\n"
//        + "Hợp Đồng của bạn sắp hết hạn, mã hợp đồng là " + contract.getContractId() + ".");
//
//        javaMailSender.send(message);
//        }
//
//@Override
//public List<String> getAllEmailToSend() {
//        return contractRepository.getAllEmailToSend();
//        }
