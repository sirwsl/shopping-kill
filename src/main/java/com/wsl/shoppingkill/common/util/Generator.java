package com.wsl.shoppingkill.common.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;

/**
 * <p>
 * 代码生成器
 * </p>
 *
 * @author macbookpro
 */
public class Generator {

    /**
     * 需要生成的表名
     */
    private static final String[] TABLE_NAME = {
            "t_user", "t_types", "t_subscription_history", "t_subscription"
            , "t_sku", "t_order", "t_loggers", "t_limit_list", "t_goods",
            "t_cart", "t_appraisal", "t_after_sales", "t_advertise", "t_admin",
            "t_address", "t_activity_limit", "t_activity"
    };


    /**
     * 使用的数据源
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = getDevCoach();

    /**
     * 表前缀
     */
    private static final String TABLE_PREFIX = "t_";

    private static final String PACKAGE_NAME = "com.wsl.kill";


    public static void main(String[] args) {
        generateByTables();
    }


    private static void generateByTables() {
        GlobalConfig globalConfig = new GlobalConfig();
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityColumnConstant(true)
                .setTablePrefix(TABLE_PREFIX)
                .setNaming(NamingStrategy.underline_to_camel)
                .setLogicDeleteFieldName("del_flag")
                .setTableFillList(new ArrayList<TableFill>() {{
                    add(new TableFill("create_time", FieldFill.INSERT));
                    add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
                }})
                .setInclude(TABLE_NAME);
        String srcPath = "src" + File.separator + "main" + File.separator + "java";
        globalConfig.setActiveRecord(true)
                .setAuthor(System.getProperty("user.name"))
                .setOutputDir(srcPath)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setBaseColumnList(false)
                .setKotlin(false)
                .setActiveRecord(true)
                .setFileOverride(true)
                .setServiceName("%sService");
        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(DATA_SOURCE_CONFIG)
                .setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig()
                        .setParent(PACKAGE_NAME)
                        .setController("controller")
                        .setEntity("entity")
                        .setModuleName("api")
                ).execute();
    }


    private static DataSourceConfig getDevCoach() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/shoppingkill?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&rewriteBatchedStatements=true");
        return dsc;
    }

}
