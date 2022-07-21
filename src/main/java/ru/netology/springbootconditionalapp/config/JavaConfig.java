package ru.netology.springbootconditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springbootconditionalapp.profile.DevProfile;
import ru.netology.springbootconditionalapp.profile.ProductionProfile;
import ru.netology.springbootconditionalapp.profile.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "true", matchIfMissing = true)
    //default bean
    public SystemProfile devProfile() {
        System.out.println("Bean DevProfile has been created successfully");
        return new DevProfile();
    }


    @ConditionalOnProperty(prefix = "netology", name = "profile.dev", havingValue = "false")
    @Bean
    public SystemProfile productionProfile() {
        System.out.println("Bean ProductionProfile has been created successfully");
        return new ProductionProfile();
    }

}
