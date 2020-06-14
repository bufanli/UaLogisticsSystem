//package com.rt.common.listener;
//
//import com.rt.modules.parkinglot.service.ILotDetailService;
//import com.rt.modules.parkinglot.service.IParkinglotService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisParkingLotTaskListener implements ApplicationRunner {
//
//    @Autowired
//    private IParkinglotService parkinglotService;
//
//    @Autowired
//    private ILotDetailService lotDetailService;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        RedisParkingLotTaskThread redisParkingLotTaskThread = new RedisParkingLotTaskThread(parkinglotService, lotDetailService);
//        redisParkingLotTaskThread.run();
//    }
//}
