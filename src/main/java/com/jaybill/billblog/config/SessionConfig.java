package com.jaybill.billblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * 共享sessio的配置
 * @author jaybill
 *
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1200)
public class SessionConfig {
}
