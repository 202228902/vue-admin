package com.galaxy.empvue;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * MyBatis-Plus 代码生成器配置
 */
public class MyBatisPlusGeneratorConfig {

    /**
     * 执行代码生成
     */
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setDataSource(new DataSourceConfig()
                .setUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("1234")
                .setDriverName("com.mysql.cj.jdbc.Driver"));
        autoGenerator.setGlobalConfig(new GlobalConfig()
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                .setAuthor("duGalaxy")
                .setOpen(false));
        autoGenerator.setPackageInfo(new PackageConfig()
                .setParent("com.galaxy.empvue")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setEntity("entity"));
        autoGenerator.setStrategy(new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //表名
                .setInclude("user")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setLogicDeleteFieldName("deleted")
                .setVersionFieldName("version")
                .setControllerMappingHyphenStyle(true));
        autoGenerator.execute();
    }
}