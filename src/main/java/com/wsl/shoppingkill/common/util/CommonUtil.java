package com.wsl.shoppingkill.common.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wsl
 */
public class CommonUtil {

    /**手机号码正则(弱规则，只保证格式)
     */
    static final String PHONE = "^(1[3-9])\\d{9}$";

    /**
     * 把以逗号分隔的ids字符串，转为list，因为用的比较频繁，所以抽一个工具类出来
     */
    public static List<Integer> covertIdStr(String ids) {
        if (StringUtils.isBlank(ids)) {
            //空的时候返回一个空的List，避免NPE异常
            return new ArrayList<>();
        }

        return Arrays.stream(StringUtils.split(ids.replace("，", ",").replace("[", "").replace("]", ""), ","))
                //trim一下
                .map(StringUtils::trim)
                //过滤掉不能转成整数数的串
                .filter(StringUtils::isNumeric)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * 上面那个函数的反函数，把idList转为字符串,逗号拼接的形式
     */
    public static String covertIdList2Str(Collection<Integer> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return "";
        }
        return StringUtils.join(idList, ",");
    }

    /**
     * 上面那个函数的虫子啊，把stringList转为字符串,逗号拼接的形式
     */
    public static String covertStringList2Str(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return "";
        }
        return StringUtils.join(idList, ",");
    }



    public static List<String> covertStr2List(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        return Arrays.stream(StringUtils.split(str.replace("，", ","), ","))
                //trim一下
                .map(StringUtils::trim)
                //过滤掉不能转成整数数的串
                .filter(StringUtils::isNumeric)
                .map(String::toString).collect(Collectors.toList());
    }

    public static List<String> justCovertStr2List(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        return Arrays.stream(StringUtils.split(str.replace("，", ","), ","))
                //trim一下
                .map(StringUtils::trim)
                .map(String::toString).collect(Collectors.toList());
    }

    /**
     * 替换用户名隐藏敏感信息以用于展示
     *
     * @param userName 不能包含特殊字符，只能是汉字、数字、字母
     * @return String: 处理结果
     */
    public static String replaceUserName(String userName) {

        if (StringUtils.isBlank(userName)) {
            return "";
        }
        if (userName.length() < 3) {
            return userName.charAt(0) + "****";
        }
        if (userName.matches(PHONE)) {
            return userName.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return userName.charAt(0) + "****" + userName.charAt(userName.length() - 1);
    }

    public static String replaceCommentUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return "";
        }
        final int length = userName.length();
        if (length == 1) {
            return userName;
        }
        if (length == 2) {
            if (isEmojiCharacter(userName.codePointAt(0))) {
                return userName;
            }
            return userName.charAt(0) + "****";
        }
        if (length == 4 && isEmojiCharacter(userName.codePointAt(0)) && isEmojiCharacter(userName.codePointAt(length - 1))) {
            return userName.substring(0, 2) + "****";
        }
        Pattern p = Pattern.compile(PHONE);
        Matcher m = p.matcher(userName);
        if (m.matches()) {
            //电话号码
            return userName.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        String start;
        if (isEmojiCharacter(userName.codePointAt(0))) {
            start = userName.substring(0, 2);
            if (start.contains("️")) {
                start = "*";
            }
        } else {
            start = userName.substring(0, 1);
        }
        String end;
        if (isEmojiCharacter(userName.codePointAt(length - 1))) {
            if (userName.length() >= 4) {
                end = userName.substring(length - 4, length);
            } else {
                end = userName.substring(length - 2, length);
            }

            if (end.contains("️")) {
                end = "*";
            }
        } else {
            end = userName.substring(length - 1, length);
        }
        return start + "****" + end;
    }

    private static boolean isEmojiCharacter(int codePoint) {
        // 杂项符号与符号字体
        return (codePoint >= 0x2600 && codePoint <= 0x27BF)
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x2000 && codePoint <= 0x200F)
                || (codePoint >= 0x2028 && codePoint <= 0x202F)
                || codePoint == 0x205F
                || (codePoint >= 0x2065 && codePoint <= 0x206F)
                /* 标点符号占用区域 */
                // 字母符号
                || (codePoint >= 0x2100 && codePoint <= 0x214F)
                // 各种技术符号
                || (codePoint >= 0x2300 && codePoint <= 0x23FF)
                // 箭头A
                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)
                // 箭头B
                || (codePoint >= 0x2900 && codePoint <= 0x297F)
                // 中文符号
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)
                // 高低位替代符保留区域
                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)
                // 私有保留区域
                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)
                // 变异选择器
                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)
                // Plane在第二平面以上的，char都不可以存，全部都转
                || codePoint >= 0x10000;
    }

    /**
     * 比较字符串集合是否包含某个字符串 ，不区分大小写
     *
     * @param list:字符串集合
     * @param compareStr：字符串
     * @return bool结果
     */
    public static boolean containIgnoreCase(List<String> list, String compareStr) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (String s : list) {
            if (StringUtils.equalsIgnoreCase(s, compareStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把价格格式化成2位小数
     *
     * @param price ：价格
     * @return String
     */
    public static String priceTextWith00(Double price) {
        if (price == null) {
            return "0.00";
        }
        return String.format("%.2f", price);
    }

    /**
     * 把价格格式化成2位小数
     *
     * @param price ：价格
     * @return String
     */
    public static String priceTextWith00(BigDecimal price) {
        if (price == null) {
            return "0.00";
        }
        return String.format("%.2f", price);
    }


}
