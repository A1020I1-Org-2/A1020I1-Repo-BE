//package codegym.vn.config;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.Logger;
//
//@Component
//public class ScheduleTaskFixedDelay {
//
//    private static final Logger log = LoggerFactory.getLogger(ScheduleTaskFixedRate.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        log.info("Method executed at every 5 seconds", dateFormat.format(new Date()));
//    }
//
//}