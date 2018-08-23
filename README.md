# 一个基于redisson的分布式锁插件
### 1.配置
默认单机模式，读取spring的reids设置，可以自行配置RedissonClient 
1. SingleServerConfig 单机模式
2. SentinelServersConfig 哨兵模式
    
未完待续...
### 2.使用方式
在需要的方法上使用@RedisLock注解
1. 直接获得锁

    默认不会加锁时长过久自动释放锁，可以设置quitTime不为0启用自动释放锁
2. 尝试等待获得锁

    配置isTry=true开启等待机制，waitTime为等待获得锁的时长

### 3.思考
1. 获得锁失败直接返回null是否合理
2. 获得锁的方式方法支持自定义拓展
3. 处理与切面切解耦

---

感谢[小柒2012](https://gitee.com/52itstyle/spring-boot-seckill)给的秒杀案例启发

