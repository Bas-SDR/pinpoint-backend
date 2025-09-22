package org.basr.pinpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PinpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinpointApplication.class, args);
    }

}
