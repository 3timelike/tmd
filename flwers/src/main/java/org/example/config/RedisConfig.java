package org.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
       @Bean
       public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory){
              RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
              redisTemplate.setConnectionFactory(connectionFactory);
              //设置key序列化
              redisTemplate.setKeySerializer(new StringRedisSerializer());
              //设置value序列化
              redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
              redisTemplate.setHashKeySerializer(new StringRedisSerializer());
              redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
              redisTemplate.afterPropertiesSet();
              return redisTemplate;
       }
//       在第一种配置中，使用了 FastJsonRedisSerializer 来序列化和反序列化 value，同时使用 StringRedisSerializer 来序列化和反序列化 key 和 hash 的 key。这意味着所有的 value 都会被序列化成 JSON 格式存储在 Redis 中。
//
//       而在第二种配置中，使用了 GenericJackson2JsonRedisSerializer 来序列化和反序列化 value，同时也使用了 StringRedisSerializer 来序列化和反序列化 key 和 hash 的 key。这里同样使用了 JSON 格式来存储 value。
//
//       因此，这两种配置的区别在于使用了不同的 value 序列化器。FastJsonRedisSerializer 使用的是 FastJson 序列化库，而 GenericJackson2JsonRedisSerializer 则使用的是 Jackson 序列化库。这两个库在序列化和反序列化方面有一些细微的差异，例如对日期格式、对象引用等的处理方式可能会有所不同。
//
//       一般来说，选择序列化器时可以根据具体的需求和场景来决定。FastJson 在性能上有一些优势，而 Jackson 则在通用性和稳定性上更为广泛使用
}
