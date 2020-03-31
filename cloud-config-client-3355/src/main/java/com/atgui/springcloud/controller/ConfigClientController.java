package com.atgui.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//第一步在pom文件里面添加  actuator相关jar包
//第二步  在bootstrap.yml文件暴露端口
//@RefreshScope  动态刷新的第三步注解配置
//在github上相关分支修改 信息
//第四步   cmd窗口  curl -X POST "http://localhost:3355/actuator/refresh"
//第五步：在浏览器访问 http://localhost:3355/configInfo即可看到修改的信息
@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

}
