package com.example.mmall.util;

import com.example.mmall.config.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zyi
 * @Date: 2019/03/26
 * @Version 1.0
 * redis工具类客户端 <br>
 */

@Slf4j
public class RedisClientUtil {

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate =
            (RedisTemplate<String, Object>) ApplicationContextProvider.getBean("redisTemplate");


    /**
     * 获取值
     * @param key
     * @return
     */
    public static <T> T get(String key) {
        return (T)redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置值
     * @param key
     * @return
     */
    public static void set(String key,Object value) {
         redisTemplate.opsForValue().set(key , value);
    }


    /**
     * 通过前缀获取一个集合
     * @param pattern
     */
    public static Set<String> keys(String pattern){
       return redisTemplate.keys(pattern);
    }

    /**
     * 对键进行自增
     * @param key
     * @return
     */
    public static Long increment(String key) {
        Long value = redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    /**
     * 设置值加过期时间 秒
     * @param key
     * @return
     */
    public static void set(String key,Object value,long time) {
        redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
    }

    /**
     * 删除键 <br>
     * @param key
     */
    public static void delete(String key){

        redisTemplate.delete(key);
    }

    /**
     * 删除集合键 <br>
     * @param keys
     */
    public static void delete(Collection<String> keys){

        redisTemplate.delete(keys);
    }

    /**
     * 重置session 时间 <br>
     */
    public static void restExpire(String key){
        redisTemplate.expire( key ,30 ,  TimeUnit.MINUTES);
    }


    /**
     * 设置key的时效性只在今天有效
     * @param key
     * @return
     */
    private static Long getIncrement(String key) {
        Long value = redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.opsForValue().set(key, value, getCurrentDayTimeout(), TimeUnit.MILLISECONDS);
        return value;
    }


    private static long getCurrentDayTimeout() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time24 = cal.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        long timeout = time24 - currentTime;
        return timeout;
    }

    /**
     * 设置set集合数据
     * @param setKey
     * @param value
     */
    public static void setCollectionSet(String setKey,Object value){

        redisTemplate.opsForSet().add(setKey , value);
    }

    /**
     * 批量导入到set
     * @param setKey
     * @param list
     */
    public static void setPipelineCollectionSet(String setKey, List list){

        if(StringUtils.isEmpty(setKey)){
            log.warn("setKey is null ! ");
            return ;
        }
        if(list == null || list.size()==0){
            log.warn("list is null !");
            return ;
        }
        long startTime = System.currentTimeMillis();
        redisTemplate.executePipelined(new RedisCallback<Object>(){
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                list.forEach( value ->{
                    byte[] key  = serializer.serialize(setKey);
                    byte[] val = serializer.serialize(value.toString());
                    redisConnection.setCommands().sAdd(key,val);
                } );
                redisConnection.closePipeline();
                return null;
            }
        });
        long endTime = System.currentTimeMillis();
        log.info("Pipelined 花费时间为：{}ms" , (endTime-startTime) );
    }


    /**
     * 返回set集合数据
     * @param setKey
     */
    public static Object getCollectionSet(String setKey){
        return redisTemplate.opsForSet().members(setKey);
    }

    /**
     * 删除set
     * @param setKey
     */
    public static boolean deleteCollectionSet(String setKey){
        return redisTemplate.opsForSet().getOperations().delete(setKey);
    }

    /**
     * 检查set集合中是否存在元素
     * @param setKey
     */
    public static boolean ckechCollectionSetIsMembe(String setKey,Object value){
        return redisTemplate.opsForSet().isMember(setKey,value);
    }

    /**
     * user:1:follow it music his sports
     * user:2:follow it news ent sports
     * result: it sports
     * 计算集合的交集
     */
    public static <T> Set<T>  intersectSet(String setKey1 , String setKey2){
        Object obj = redisTemplate.opsForSet().intersect(setKey1,setKey2);
        if( obj != null){
           return (Set<T>)obj;
        }
        return null;
    }

    /**
     * user:1:follow it music his sports
     * user:2:follow it news ent sports
     * result： it music news his ent sports
     * 计算集合的并集
     */
    public static  <T> Set<T>  unionSet(String setKey1 , String setKey2){
        Object obj = redisTemplate.opsForSet().union(setKey1,setKey2);
        if(obj!= null){
            return (Set<T> )obj;
        }
        return null;
    }

    /**
     * user:1:follow it music his sports
     * user:2:follow it news ent sports
     * result: music his
     * @param setKey1
     * @param setKey2
     * @return
     * 计算集合差集
     */
    public static  <T> Set<T>  differenceSet(String setKey1 , String setKey2){
        Object obj = redisTemplate.opsForSet().difference(setKey1,setKey2);
        if ( obj !=null){
            return ( Set<T> )obj;
        }
        return null;
    }


    /**
     * 队列长度
     */
    public static Long querySize(String queryKey){
        return redisTemplate.opsForList().size(queryKey);
    }

    /**
     * 左边进入队列
     */
    public static void publisherQuery(String queryKey, Object queryValue){
        redisTemplate.opsForList().leftPush( queryKey , queryValue  );
    }

    /**
     * 获取key对应列表下面的所有数据
     * @param redisKey
     * @return
     */
    public static Object getListAll(String redisKey){
        return redisTemplate.opsForList().range(redisKey,0,-1);
    }

    /**
     * 右边队列消费
     */
    public static <T> T receiverQuery(String queryKey ){
        return  (T)redisTemplate.opsForList().rightPop( queryKey );
    }



    /**
     *  获取hash的一个值
     * @param redisKey
     * @param mapKey
     * @return
     */
    public static Object getValueByHashKey(String redisKey,String mapKey ){

        return redisTemplate.opsForHash().get(redisKey,mapKey);
    }


    /**
     * 返回map
     * @param redisKey
     * @return
     */
    public static Map<Object,Object> getHashEntries(String redisKey){

        return  redisTemplate.opsForHash().entries(redisKey);
    }

    /**
     * map put
     */
    public static void hashPut(String redisKey, String hashKey,Object hashValue){

        redisTemplate.opsForHash().put(redisKey,hashKey,hashValue);
    }

    /**
     * 移出hash的一个键值
     * @param redisKey
     * @param hashKey
     */
    public static void removeHash(String redisKey, String hashKey){
        redisTemplate.opsForHash().delete(redisKey,hashKey);
    }

    /**
     * 一次性放入一个map
     * @param redisKey
     * @param map
     */
    public static void putAll(String redisKey,Map map){

        redisTemplate.opsForHash().putAll(redisKey,map);
    }

}
