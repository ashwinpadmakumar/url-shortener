package com.workspace.urlshortener;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {


  @Bean
  public RedissonClient redisClient(@Value("${spring.redis.host}") String redisNodes) {
    Config config = new Config();
    config.useSingleServer()
        .setAddress(redisNodes);
    config.setCodec(new StringCodec());
    return Redisson.create(config);
  }


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
