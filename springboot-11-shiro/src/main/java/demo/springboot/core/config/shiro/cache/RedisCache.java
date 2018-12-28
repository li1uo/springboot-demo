package demo.springboot.core.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * 实现shiro cache接口 方法
 * @author LILUO
 * @date 2018/6/29
 */
@Component
public class RedisCache<K,V> implements Cache<K,V> {

    /**shiro缓存前缀**/
    public final static String KEY_PREFIX = "shiro:cache:";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public V get(K k) throws CacheException {
        Object object = redisTemplate.opsForValue().get(KEY_PREFIX + k.toString());
        if (object != null){
            return (V)object;
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForValue().set(KEY_PREFIX + k.toString(),v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        redisTemplate.delete(KEY_PREFIX + k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(redisTemplate.keys(KEY_PREFIX + "*"));
    }

    @Override
    public int size() {
        return redisTemplate.keys(KEY_PREFIX + "*").size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(KEY_PREFIX + "*");
    }

    @Override
    public Collection<V> values() {
        return redisTemplate.opsForValue().multiGet(redisTemplate.keys(KEY_PREFIX + "*"));
    }
}
