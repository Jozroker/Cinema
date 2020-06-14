package com.cinema.point;

import com.cinema.point.service.SeanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointApplicationTests {

    @Autowired
    SeanceService seanceService;

    @Test
    void contextLoads() {
        System.out.println(seanceService.findById(1L));
    }

}
