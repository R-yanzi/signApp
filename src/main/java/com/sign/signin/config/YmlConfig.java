package com.sign.signin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class YmlConfig {
    public static String url = "src/main/resources/config/application.yml";

    public static boolean changeRatio() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));


        return true;
    }
}
