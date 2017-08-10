# ssm-redis

## v1.0 简单的搭建了ssm的框架

## v1.1 添加缓存

### 简单的使用代码进行redis缓存，即在查询的时候先在service层从redis缓存中获取数据。
* 如果不存在，则再经过dao层从数据库中获取，最后将查询到的数据缓存到redis中；
* 如果存在，直接从redis缓存中读取，并交给controller层。


#### xml配置
```xml
<bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
  <property name="maxTotal" value="${redis.maxTotal}"></property>
  <property name="maxIdle" value="${redis.maxIdle}"></property>
  <property name="testOnBorrow" value="${redis.testOnBorrow}"></property>  
  <property name="maxWaitMillis" value="${redis.maxWaitMillis}"></property>
</bean>
```

```xml
<!-- jedis客户端单机版 -->  
<bean id="redisClient" class="redis.clients.jedis.JedisPool">
  <constructor-arg name="poolConfig" ref="poolConfig"></constructor-arg>
  <constructor-arg name="host" value="${redis.hostName}"></constructor-arg>
  <constructor-arg name="port" value="${redis.port}"></constructor-arg>
</bean>
```

```xml
<!-- jedis集群版配置 -->  
<bean id="" class="redis.clients.jedis.JedisCluster">
  <constructor-arg name="poolConfig" ref="poolConfig"></constructor-arg>
  <constructor-arg name="nodes">
    <set>
      <bean class="redis.clients.jedis.HostAndPort">
        <constructor-arg name="host" value="127.0.0.1"></constructor-arg>
        <constructor-arg name="port" value="7001"></constructor-arg>
      </bean>
      <bean class="redis.clients.jedis.HostAndPort">
        <constructor-arg name="host" value="127.0.0.1"></constructor-arg>
        <constructor-arg name="port" value="7002"></constructor-arg>
      </bean>
      <bean class="redis.clients.jedis.HostAndPort">
        <constructor-arg name="host" value="127.0.0.1"></constructor-arg>
        <constructor-arg name="port" value="7003"></constructor-arg>
      </bean>
      <bean class="redis.clients.jedis.HostAndPort">
        <constructor-arg name="host" value="127.0.0.1"></constructor-arg>
        <constructor-arg name="port" value="7004"></constructor-arg>
      </bean>
      <bean class="redis.clients.jedis.HostAndPort">
        <constructor-arg name="host" value="127.0.0.1"></constructor-arg>
        <constructor-arg name="port" value="7005"></constructor-arg>
      </bean>
    </set>
  </constructor-arg>
</bean>
```


#### java调用
```java
//service层查询时代码
@Override
public List<User> list() {
  // TODO Auto-generated method stub
  try {
    String json = jedisClient.hget("user", "list");
    if (StringUtils.isNotBlank(json)) {
      List<User> users = JsonUtils.jsonToList(json, User.class);
      return users;
    }
  } catch (Exception e) {
    e.printStackTrace();
  }

  List<User> list = userMapper.list();

  try {
    jedisClient.hset("user", "list", JsonUtils.objectToJson(list));
  } catch (Exception e) {
    e.printStackTrace();
  }
  return list;
}
```
