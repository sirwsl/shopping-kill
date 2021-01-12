package com.wsl.shoppingkill.serviceImpl;

import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
class ActivityServiceImplTest {

    @Resource
    private ActivityService activityService;

    @Test
    void getActivityFuture() {
        activityService.getActivityFuture().forEach(System.err::println);
    }

    @Test
    void entity() {
        KillGoodsVO killAvtivityVO = new KillGoodsVO();
        System.out.println("killAvtivityVO = " + killAvtivityVO);
        killAvtivityVO.setStartTime(LocalDateTime.now());
        killAvtivityVO.setName("213");
        killAvtivityVO.setImgUrl("234");
        killAvtivityVO.setId(1L);
        System.err.println(killAvtivityVO);
    }
}