package com.wsl.shoppingKill.common.component.snowflake;

import org.springframework.stereotype.Component;

/**
 * 描述: Twitter的分布式自增ID雪花算法snowflake (Java版)
 *
 * @author yanpenglei
 * 2018-03-13 12:37
 **/
@Component
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;


     //每一部分占用的位数
    /**
     *序列号占用的位数
     */

    private final static long SEQUENCE_BIT = 12;
    /**
     *机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    /**
     *数据中心占用的位数
     */
    private final static long DATACENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     *数据中心
     */

    private long datacenterId;
    /**
     * 机器标识
     */
    private long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastStamp = -1L;

    public SnowFlake(){
        this.datacenterId = 2;
        this.machineId = 3;
    }

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }

    }

    /**
     * 产生下一个ID
     *
     * @return long
     */
    public synchronized long nextId() {
        long currStmp = getNewest();
        if (currStmp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStmp;

        //时间戳部分
        return (currStmp - START_STMP) << TIMESTMP_LEFT
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewest();
        while (mill <= lastStamp) {
            mill = getNewest();
        }
        return mill;
    }

    private long getNewest() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(1, 1);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println(snowFlake.nextId());
        }

        System.out.println(System.currentTimeMillis() - start);


    }
}