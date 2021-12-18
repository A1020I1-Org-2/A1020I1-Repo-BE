package codegym.vn.service.impl;

import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.dto.ContractDto;
import codegym.vn.dto.EditContract;
import codegym.vn.entity.*;
import codegym.vn.repository.*;

import codegym.vn.entity.ContractDTO;
import codegym.vn.entity.Employee;
import codegym.vn.entity.StatusContract;
import codegym.vn.repository.ContractRepository;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.repository.EmployeeRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.ParseException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StatusReponsitory statusReponsitory;
    @Autowired
    private TypeProductRepository typeProductRepository;
    @Autowired
    private TypeContractRepository typeContractRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JavaMailSender emailSender;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Page<Contract> getContractList(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public Page<Contract> getAllPawn(Pageable pageable) {
        return contractRepository.getItemWarehouse(pageable);
    }

    @Override
    public Page<Contract> searchContract(String customer, String productName, String statusContract,
                                         String typeContract, Date startDateFrom, Date endDateTo,
                                         Pageable pageable) {
        return contractRepository.searchContractTest(customer, productName, statusContract, typeContract, startDateFrom, endDateTo, pageable);
    }


    @Override
    public Contract findById(String id) {
        return this.contractRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contract> contractListTop10() {
        return this.contractRepository.findTop10ByOrderByStartDateDesc();
    }

    @Override
    public void contractUpdate(EditContract editContract) {
        Contract contract = this.contractRepository.getById(editContract.getContractID());

        Customer customer = this.customerRepository.getById(editContract.getCustomerID());

        StatusContract statusContract = this.statusReponsitory.getById(editContract.getStatusTypeID());

        TypeProduct typeProduct = this.typeProductRepository.getById(editContract.getProductTypeID());

        customer.setName(editContract.getCustomerName());
        contract.setCustomer(customer);
        contract.setTypeProduct(typeProduct);
        contract.setProductName(editContract.getProductName());
        contract.setStartDate(editContract.getStartDate());
        contract.setEndDate(editContract.getEndDate());
        contract.setStatusContract(statusContract);

        this.contractRepository.save(contract);

    }

    @Override
    public List<Contract> contractListTop10Search(String name) {
        System.out.println();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(name);
            Date date1 = (Date)date.clone();
            date.setHours(0);
            date1.setHours(23);
            return this.contractRepository.findAllByStartDate(date, date1);
        } catch (ParseException e) {
            return this.contractRepository.searchListTop10(name);
        }
    }


    @Override
    public void contractDelete(String id) {
        this.contractRepository.deleteById(id);
    }

    @Override
    public List<StatusContract> getAllStatus() {
        return this.statusReponsitory.findAll();
    }

    @Override
    public List<TypeContract> getAllTypeContract() {
        return this.typeContractRepository.findAll();
    }

    @Override
    public List<TypeProduct> getAllTypeProduct() {
        return this.typeProductRepository.findAll();
    }

   
    @Override
    public void createPawnContract(ContractDTO contractDTO)throws MessagingException {
        Customer customer = customerRepository.getById(contractDTO.getCustomerId());
        Employee employee = employeeRepository.getById(contractDTO.getEmployeeId());

        Contract contract = new Contract(
                contractDTO.getContractId(),
                contractDTO.getProductImg(),
                contractDTO.getProductName(),
                contractDTO.getInterestMoney(),
                contractDTO.getReceiveMoney(),
                contractDTO.getLoanMoney(),
                contractDTO.getLiquidationDate(),
                contractDTO.getStartDate(),
                contractDTO.getEndDate(),
                contractDTO.getQuantity(),
                contractDTO.getStatusContract(),
                contractDTO.getTypeProduct(),
                contractDTO.getTypeContract(),
                employee,
                customer
        );
        contractRepository.save(contract);

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        String htmlMsg =templateMail(contract.getProductName(),contract.getQuantity(),contract.getLoanMoney(),contract.getInterestMoney(),contract.getStartDate(),contract.getEndDate());

        message.setContent(htmlMsg, "text/html;charset=utf-8");

        helper.setTo(customer.getEmail());

        helper.setSubject("Thông tin hợp đồng cầm đồ.");

        this.emailSender.send(message);
    }

    @Override
    public Page<Customer> searchCustomer(String searchValue,Pageable pageable) {
        return customerRepository.searchCustomer(searchValue,pageable);
    }

    @Scheduled(cron = "0 0/30 19 * * *")
    private void OverdueMailing(){
        Date date = new Date();
        List<Contract> contractList = contractRepository.findContractOutOfDate(date);
        System.out.println(contractList);
    }

    private String templateMail(String nameProduct, int quantity, int loanMoney, int interestMoney, Date statDate, Date endDate){
        return "<!DOCTYPE html><html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\"><head><title></title><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><style>\n" +
                "*{box-sizing:border-box}body{margin:0;padding:0}th.column{padding:0}a[x-apple-data-detectors]{color:inherit!important;text-decoration:inherit!important}#MessageViewBody a{color:inherit;text-decoration:none}p{line-height:inherit}@media (max-width:520px){.icons-inner{text-align:center}.icons-inner td{margin:0 auto}.row-content{width:100%!important}.image_block img.big{width:auto!important}.stack .column{width:100%;display:block}}</style></head><body \n" +
                "style=\"background-color:#fff;margin:0;padding:0;-webkit-text-size-adjust:none;text-size-adjust:none\"><table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#fff\"><tbody><tr><td><table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td><table class=\"row-content stack\" \n" +
                "align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;background-color:#f2efef;color:#000\" width=\"500\"><tbody><tr><th class=\"column\" width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0;font-weight:400;text-align:left;vertical-align:top;padding-top:5px;padding-bottom:5px;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" \n" +
                "role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"width:100%;padding-right:0;padding-left:0\"><div align=\"center\" style=\"line-height:10px\"><img class=\"big\" src=\"https://d15k2d11r6t6rl.cloudfront.net/public/users/BeeFree/beefree-pyl7y8nmpci/editor_images/A1020I1%20%E1%BB%9F%20%C4%91%C3%A2y%20%C4%91%E1%BB%83%20h%E1%BB%97%20tr%E1%BB%A3%20b%E1%BA%A1n..png\" style=\"display:block;height:auto;border:0;width:500px;max-width:100%\" width=\"500\"></div></td></tr></table><table \n" +
                "class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;word-break:break-word\"><tr><td><div style=\"font-family:sans-serif\"><div style=\"font-size:12px;mso-line-height-alt:14.399999999999999px;color:#555;line-height:1.2;font-family:Arial,Helvetica Neue,Helvetica,sans-serif\"><p style=\"margin:0;font-size:12px\">Hệ thống cầm đồ A1020I1 kính chào quý khách.</p><p style=\"margin:0;font-size:12px\">\n" +
                "Chúng tôi cảm ơn bạn đã sử dụng dịch vụ của hệ thống.</p><p style=\"margin:0;font-size:12px\">Sau đây là những thông tin hợp đồng cầm đồ tại cửa hàng:</p><ul style=\"list-style-type:circle;line-height:120%;font-size:12px\"><li>Tên đồ cầm :"+nameProduct+"</li><li>Số lượng : "+quantity+"</li><li>Tiền cho vay :&nbsp;"+loanMoney+"</li><li>Tiền lãi : "+interestMoney+"</li><li>Ngày làm hợp đồng :&nbsp;"+statDate+"</li><li>Ngày kết thúc hợp đồng : "+endDate+"</li></ul><p style=\"margin:0\">Email này để thông báo về thông tin hợp đồng của quý khách.</p><p style=\"margin:0\">\n" +
                "Vui lòng không trả lời mail này.</p><p style=\"margin:0\">Lưu ý để tránh lộ thông tin quý khách không được cung cấp mail này cho người khác.</p><p style=\"margin:0\">Mọi thông tin chi tiết vui lòng liên hệ : 190088888</p></div></div></td></tr></table><table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td><div align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" \n" +
                "width=\"100%\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td class=\"divider_inner\" style=\"font-size:1px;line-height:1px;border-top:1px solid #bbb\"><span>&#8202;</span></td></tr></table></div></td></tr></table><table class=\"social_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td><table class=\"social-table\" width=\"144px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" align=\"center\" \n" +
                "style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"padding:0 2px 0 2px\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/facebook@2x.png\" width=\"32\" height=\"32\" alt=\"Facebook\" title=\"Facebook\" style=\"display:block;height:auto;border:0\"></a></td><td style=\"padding:0 2px 0 2px\"><a href=\"https://twitter.com/\" target=\"_blank\"><img \n" +
                "src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/twitter@2x.png\" width=\"32\" height=\"32\" alt=\"Twitter\" title=\"Twitter\" style=\"display:block;height:auto;border:0\"></a></td><td style=\"padding:0 2px 0 2px\"><a href=\"https://instagram.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/instagram@2x.png\" width=\"32\" height=\"32\" alt=\"Instagram\" title=\"Instagram\" \n" +
                "style=\"display:block;height:auto;border:0\"></a></td><td style=\"padding:0 2px 0 2px\"><a href=\"https://www.linkedin.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/circle-color/linkedin@2x.png\" width=\"32\" height=\"32\" alt=\"LinkedIn\" title=\"LinkedIn\" style=\"display:block;height:auto;border:0\"></a></td></tr></table></td></tr></table><table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" \n" +
                "style=\"mso-table-lspace:0;mso-table-rspace:0;word-break:break-word\"><tr><td><div style=\"font-family:sans-serif\"><div style=\"font-size:14px;mso-line-height-alt:16.8px;color:#555;line-height:1.2;font-family:Arial,Helvetica Neue,Helvetica,sans-serif\"><p style=\"margin:0;text-align:center\">About us&nbsp; |&nbsp; Contact us&nbsp; |&nbsp; Unsubscribe</p><p style=\"margin:0;text-align:center\">Copyright@2020 - email Template, All rights reserved</p></div></div></td></tr></table></th></tr></tbody></table>\n" +
                "</td></tr></tbody></table><table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td><table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0;color:#000\" width=\"500\"><tbody><tr><th class=\"column\" width=\"100%\" \n" +
                "style=\"mso-table-lspace:0;mso-table-rspace:0;font-weight:400;text-align:left;vertical-align:top;padding-top:5px;padding-bottom:5px;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"icons_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"color:#9d9d9d;font-family:inherit;font-size:15px;padding-bottom:5px;padding-top:5px;text-align:center\"><table width=\"100%\" cellpadding=\"0\" \n" +
                "cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0;mso-table-rspace:0\"><tr><td style=\"text-align:center\"><!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]--><!--[if !vml]><!--><table class=\"icons-inner\" style=\"mso-table-lspace:0;mso-table-rspace:0;display:inline-block;margin-right:-4px;padding-left:0;padding-right:0\" cellpadding=\"0\" \n" +
                "cellspacing=\"0\" role=\"presentation\"><!--<![endif]--><tr><td style=\"text-align:center;padding-top:5px;padding-bottom:5px;padding-left:5px;padding-right:6px\"><a href=\"https://www.designedwithbee.com/\"><img class=\"icon\" alt=\"Designed with BEE\" src=\"https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/53601_510656/Signature/bee.png\" height=\"32\" width=\"34\" align=\"center\" style=\"display:block;height:auto;border:0\"></a></td><td \n" +
                "style=\"font-family:Arial,Helvetica Neue,Helvetica,sans-serif;font-size:15px;color:#9d9d9d;vertical-align:middle;letter-spacing:undefined;text-align:center\"><a href=\"https://www.designedwithbee.com/\" style=\"color:#9d9d9d;text-decoration:none\">Designed with BEE</a></td></tr></table></td></tr></table></td></tr></table></th></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><!-- End --></body></html>";
    }
  
   @Override
    public Page<Contract> getLiquidationProductList(Pageable pageable) {
        return contractRepository.getLiquidationProductList(pageable);
    }

    @Override
    public void saveLiquidationContract(ContractDto contractDto) {
        Customer customer = customerRepository.getById(contractDto.getCustomerId());
        Employee employee = employeeRepository.getById(contractDto.getEmployeeId());
        Contract contract = new Contract(
                contractDto.getContractId(),
                contractDto.getProductImg(),
                contractDto.getProductName(),
                contractDto.getInterestMoney(),
                contractDto.getReceiveMoney(),
                contractDto.getLoanMoney(),
                contractDto.getLiquidationDate(),
                new Date(),
                new Date(),
                contractDto.getQuantity(),
                contractDto.getStatusContract(),
                contractDto.getTypeProduct(),
                contractDto.getTypeContract(),
                employee,
                customer
        );
        contractRepository.save(contract);
    }

    @Override
    public Page<Contract> searchLiquidationProduct(
            String productName, String typeProductName, String receiveMoney, Pageable pageable) {
        return contractRepository.searchLiquidationProduct(productName, receiveMoney, typeProductName, pageable);
    }

    @Override
    public boolean updateStatusContractPawn(String contractID) {
        Contract contract = contractRepository.findById(contractID).orElse(null);
        if (contract == null) {
            return false;
        } else {
            contract.setStatusContract(new StatusContract(3, "Close"));
            contractRepository.save(contract);
            return true;
        }
    }
    public Contract findPawnById(String id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Contract> searchPawn(String search, String typeSearch, Pageable pageable) {
        return contractRepository.searchPawn(search,typeSearch,pageable);
    }

    @Override
    public Page<Contract> getListContractOpen(String keyword, Pageable pageable) {
        return this.contractRepository.getListContractOpen(keyword, pageable);
    }

    @Override
    public boolean paymentContract(ContractDTO contractDTO) {
        Contract contract = this.contractRepository.findById(contractDTO.getContractId()).orElse(null);
        if(contract == null){
            return false;
        }
        contract.setReceiveMoney(contract.getInterestMoney());
        contract.setLiquidationDate(contractDTO.getLiquidationDate());
        this.contractRepository.save(contract);
        return true;
    }
}
