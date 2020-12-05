package com.wsl.shoppingkill;

import com.wsl.shoppingkill.domain.Types;
import com.wsl.shoppingkill.service.TypesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * @author : WangShiLei
 * @date : 2020/11/20 9:42 下午
 **/
@SpringBootTest
public class test {

    @Resource
    private TypesService typesService;

    @Test
    public void main() {

    String[] str = {"-0.85","10052555555555555555555555555555","0.085","1.00","a.12","as.asd","1","阿斯顿.asd"};
    Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*");
        for (String s : str) {
            System.out.println(pattern.matcher(s).matches());
        }

    }

    @Test
    public void testsss(){
        Types types = new Types();
        types.setName("测试分类111").setCreatTime(LocalDateTime.now()).setUpdateTime(LocalDateTime.now());
        System.out.println(types);
        typesService.save(types);
        System.out.println(types);
        System.err.println(types);
    }
}

