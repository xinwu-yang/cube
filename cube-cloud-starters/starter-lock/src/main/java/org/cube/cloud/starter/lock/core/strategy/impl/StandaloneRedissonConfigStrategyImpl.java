package org.cube.cloud.starter.lock.core.strategy.impl;

import cn.hutool.core.util.StrUtil;
import org.cube.cloud.starter.lock.enums.GlobalConstant;
import org.cube.cloud.starter.lock.prop.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.cube.cloud.starter.lock.core.strategy.RedissonConfigStrategy;
import org.redisson.config.Config;

/**
 * 单机方式Redisson配置
 *
 * @author zyf
 * @since 2020-11-11
 */
@Slf4j
public class StandaloneRedissonConfigStrategyImpl implements RedissonConfigStrategy {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String redisAddr = GlobalConstant.REDIS_CONNECTION_PREFIX + address;
            config.useSingleServer().setAddress(redisAddr);
            config.useSingleServer().setDatabase(database);
            if (StrUtil.isNotBlank(password)) {
                config.useSingleServer().setPassword(password);
            }
            log.info("初始化Redisson单机配置,连接地址:" + address);
        } catch (Exception e) {
            log.error("单机Redisson初始化错误", e);
            e.printStackTrace();
        }
        return config;
    }
}
