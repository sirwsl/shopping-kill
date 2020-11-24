package com.wsl.shoppingKill;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

/**
 * @author : WangShiLei
 * @date : 2020/11/20 9:42 下午
 **/
@SpringBootTest
public class test {

    @Test
    public void main() {

    String[] str = {"-0.85","10052555555555555555555555555555","0.085","1.00","a.12","as.asd","1","阿斯顿.asd"};
    Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*");
        for (String s : str) {
            System.out.println(pattern.matcher(s).matches());
        }


    }
}

