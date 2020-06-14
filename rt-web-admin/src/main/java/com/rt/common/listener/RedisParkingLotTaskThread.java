//package com.rt.common.listener;
//
//import com.google.gson.Gson;
//import com.rt.common.utils.JedisUtil;
//import com.rt.modules.parkinglot.entity.LotDetail;
//import com.rt.modules.parkinglot.entity.Parkinglot;
//import com.rt.modules.parkinglot.service.ILotDetailService;
//import com.rt.modules.parkinglot.service.IParkinglotService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//public class RedisParkingLotTaskThread implements Runnable {
//
//    private final String PARKINGLOT_TASK_LIST = "PARKINGLOT_TASK_LIST";
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    private IParkinglotService parkinglotService;
//
//    private ILotDetailService lotDetailService;
//
//    public RedisParkingLotTaskThread(IParkinglotService parkinglotService, ILotDetailService lotDetailService) {
//        this.parkinglotService = parkinglotService;
//        this.lotDetailService = lotDetailService;
//    }
//
//    @Override
//    @Transactional
//    public void run() {
//        while (true) {
//            if (JedisUtil.hasKey(PARKINGLOT_TASK_LIST)) {
//                // redis消费操作
//                String task = JedisUtil.pop(PARKINGLOT_TASK_LIST);
//                Gson gson = new Gson();
//                Parkinglot oldParkinglot = gson.fromJson(task, Parkinglot.class);
//                // 重新查这个车场信息，防止用户中途修改
//                Parkinglot parkinglot = parkinglotService.getById(oldParkinglot.getId());
//                if (null != parkinglot.getParkingCount() && parkinglot.getParkingCount() > 0) {
//                    try {
//                        // 生成停车位
//                        ArrayList<LotDetail> lotDetails = new ArrayList<>(128);
//                        Long parkinglotId = parkinglot.getId();
//                        for (int i = 1; i < parkinglot.getParkingCount() + 1; i++) {
//                            LotDetail lotDetail = new LotDetail();
//                            lotDetail.setParkinglotId(parkinglotId);
//                            lotDetail.setIsEntered(0);
//                            lotDetail.setIsReserved(0);
//                            lotDetail.setCreateTime(LocalDateTime.now());
//                            lotDetail.setParkingCode((long) i);
//                            // 存入list集合中
//                            lotDetails.add(lotDetail);
//                        }
//
//                        boolean isSuccess = lotDetailService.saveBatch(lotDetails);
//                        if (isSuccess) {
//                            // 修改一些状态
//                            Parkinglot temParkinglot = new Parkinglot();
//                            temParkinglot.setId(parkinglotId);
//                            temParkinglot.setGenerateStatus(1);
//                            parkinglotService.updateById(temParkinglot);
//                        }
//                        logger.info("id为" + parkinglot.getId() + "的车场车位生成任务执行完成");
//
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (Exception e){
//                        logger.info("id为" + parkinglot.getId() +"生成任务执行完成失败",e);
//                        JedisUtil.push(PARKINGLOT_TASK_LIST, task);
//                    }
//                }
//
//            } else {
//                try {
//                    Thread.sleep(3000);
//                } catch (Exception e) {
//                    logger.error("线程睡眠失败", e);
//                }
//            }
//        }
//    }
//}
