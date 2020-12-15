package com.cxl.autoproperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AtuoProperties implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtuoProperties.class);
    private static final String CONFIG = "setting/config.properties";
    private Map<String, Long> configFileModifyData = new HashMap<>();
    private static Map<String, String> SYSTEM_CONFIG = new ConcurrentHashMap<>();

    public  Map<String, String> getSystemConfig() {
        return SYSTEM_CONFIG;
    }

    @Override
    public void run() {
        int checkDelay = 5 * 1000;
        LOGGER.info("启动监听配置文件");
        while (true) {
            try {
                this.loadAllConfigFiles();
                Thread.sleep(checkDelay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAllConfigFiles() {
        File file = new File(CONFIG);
        String fullPath = file.getAbsolutePath();
        Long value = configFileModifyData.get(fullPath);
        if (value == null || value != file.lastModified()) {
            LOGGER.info("加载配置文件" + file);
            loadPropertiesFile(file);
            configFileModifyData.put(fullPath, file.lastModified());
        }


    }

    private void loadPropertiesFile(File file) {
        Properties props = new Properties();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            props.load(is);
            Set<String> strings = props.stringPropertyNames();
            for (String key : strings) {
                String value = props.getProperty(key);
                LOGGER.info("load properties:" + key + "==" + value);
                SYSTEM_CONFIG.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AtuoProperties atuoProperties = new AtuoProperties();
        atuoProperties.getSystemConfig();
        Thread thread = new Thread(atuoProperties);
        thread.start();
    }
}
