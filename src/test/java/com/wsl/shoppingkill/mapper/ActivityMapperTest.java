package com.wsl.shoppingkill.mapper;

import com.wsl.shoppingkill.obj.bo.KillGoodsBO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityMapperTest {

    @Resource
    private ActivityMapper activityMapper;

    @Test
    public void getKillGoods() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        ids.add(5L);
        ids.add(6L);
        ids.add(7L);
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(format);
        System.out.println(ids.toString());
        List<KillGoodsBO> killGoods = activityMapper.getKillGoods(ids, format);
        System.out.println(killGoods.size());

        killGoods.forEach(System.out::println);
    }
}