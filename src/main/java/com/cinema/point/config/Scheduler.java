//package com.cinema.point.config;
//
//import com.cinema.point.dto.SeanceCreationDTO;
//import com.cinema.point.service.SeanceService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//@EnableScheduling
//@Slf4j
//public class Scheduler {
//
//    private final SeanceService seanceService;
//
//    public Scheduler(SeanceService seanceService) {
//        this.seanceService = seanceService;
//    }
//
//    @Scheduled(cron = "0 0 0 * * *")
//    public void cleanSeances() {
//        log.info("cleaning seances");
//        Date tomorrow = Date.valueOf(LocalDate.now().minus(1, ChronoUnit.DAYS));
//        List<SeanceCreationDTO> deleting = seanceService.findBySeanceDateTo(tomorrow);
//        for (SeanceCreationDTO seanceCreationDTO : deleting) {
//            seanceService.deleteById(seanceCreationDTO.getId());
//        }
//    }
//}
