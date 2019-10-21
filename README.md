# 一个基于redisson的分布式锁插件  仅供简单的学习参考
### 1.配置
默认单机模式，读取spring的reids设置，可以自行配置RedissonClient 
1. SingleServerConfig 单机模式
2. SentinelServersConfig 哨兵模式
3. ClusterServersConfig 集群模式
4. MasterSlaveServersConfig 主从模式

未完待续...
### 2.使用方式
在需要的方法上使用@RedisLock注解，需要配置lockKey，如果不配置，所有使用@RedisLock注解的方法共用一把锁
1. 直接获得锁
    一直阻塞线程直到获得锁，默认不会自动释放锁，可以设置quitTime>0启用超时自动释放锁
2. 尝试等待获得锁
    配置isTry=true开启等待机制，waitTime为等待获得锁的时长，在此时间内没有获得锁抛出异常
### 3.目前测试
1. 使用了@RedisLock分布式锁之后QPS只有无锁的1/3的性能 目前没有测试使用ZK的QPS

---

特别感谢[小柒2012](https://gitee.com/52itstyle/spring-boot-seckill)给的秒杀案例启发

