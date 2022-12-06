package cloud.autotests.allure.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:login.properties")
public interface LoginConfig extends Config {
    String username();

    String password();

    String token();
}