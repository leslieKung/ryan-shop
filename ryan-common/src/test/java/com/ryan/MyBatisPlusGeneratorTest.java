package com.ryan;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGeneratorTest {

    public static void main(String[] args) {
        // Global config
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false) // 是否开启 AR 模式
                .setAuthor("Ryan") // 作者
                .setOutputDir("F:\\JAVAProjects\\out\\ryan-shop\\")   // 生成路径
                .setFileOverride(true) // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略\
                .setDateType(DateType.ONLY_DATE)
                .setServiceName("%sService") // 设置生成的service接口的名字的首字母是否为I
                .setEntityName("%sDO") // 设置生成的实体类名字结尾DO
                .setBaseResultMap(true) //生成基本的resultMap
                .setBaseColumnList(true); //生成基本的SQL片段

        // DataSource
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/ryan-user?useSSL=false&serverTimeZone=UTC")
                .setUsername("root")
                .setPassword("123456");

        // Strategy  config
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true) // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setEntityLombokModel(true) // lombok
                .setRestControllerStyle( true) // restful api风格控制器
                .setInclude("user", "user_address"); // 生成的表

        // Package
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.ryan")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("model")
                .setXml("mapper");

        // Generate
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        autoGenerator.execute();
        System.out.println("生成完毕");
    }
}
