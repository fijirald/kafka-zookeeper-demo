package com.fijirald.kafkazookeeperdemo.controller;

import com.fijirald.kafkazookeeperdemo.zookeeper.ZKManager;
import com.fijirald.kafkazookeeperdemo.zookeeper.ZKManagerImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ZookeeperController {

    private ZKManager zkManager;

    @GetMapping("/zookeeper/get-config")
    public String getConfigValue(@RequestParam Boolean watchFlag,
                                 @RequestParam String configPath) {
        configPath = configPath.replace(",", "/");
        try {
            if (zkManager == null) zkManager = new ZKManagerImpl();
            return (String) zkManager.getZNodeData(configPath, watchFlag);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ex.getMessage();
        }
    }
}
