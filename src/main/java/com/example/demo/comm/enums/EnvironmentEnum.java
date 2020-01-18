package com.example.demo.comm.enums;

import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

public enum EnvironmentEnum {
    DEV,
    QA,
    RE;

    public static boolean isProdEnv(Environment env){

        Assert.notNull(env,"env parameter not null");

        return EnvironmentEnum.DEV.name().equalsIgnoreCase(env.getProperty("spring.profiles.active"));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
