package com.cxl;

import io.lettuce.core.KeyValue;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRedis {
    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;

    @Before
    public void init() {
        redisClient = RedisClient.create("redis://localhost");
        System.out.println("------init-----");
    }

    @Test
    public void insertString() {
        connection = redisClient.connect();
        String value = connection.sync().set("b", "b");//设置key对应的值为string类型的value,返回1表示成功，0失败
        System.out.println("values is " + value);
        Boolean setnx = connection.sync().setnx("b", "b");//设置key对应的值为string类型的value,返回1表示成功，0失败，如果key已经存在，返回0 。nx 是not exist的意思
        System.out.println(setnx);
        String b = connection.sync().get("b");//获取key对应的string值,如果key不存在返回nil
        System.out.println(b);

        List<KeyValue<String, String>> mget = connection.sync().mget("b", "c", "a");//一次获取多个key的值，如果对应key不存在，则对应返回nil。
        for (KeyValue<String, String> list : mget) {
            System.out.println(list);
        }
        Map map = new HashMap();
        map.put("aa", "xx");
        map.put("ba", "xx");
        String mset = connection.sync().mset(map);//一次设置多个key的值，成功返回1表示所有的值都设置了，失败返回0表示没有任何值被设置
        System.out.println(mset);
        Long a = connection.sync().incr("a");//对key的值做加加操作,并返回新的值。注意incr一个不是int的value会返回错误，incr一个不存在的key，则设置key为1

        System.out.println(a);
    }

    @Test
    public void updateString() {
        connection = redisClient.connect();
        String set = connection.sync().set("a", "b");
        System.out.println("value is " + set);

        String a = connection.sync().get("a");
        System.out.println("value is " + a);
    }

    @Test
    public void delString() {
        connection = redisClient.connect();
        connection.sync().del("a");
        System.out.println("删除成功");
        String a = connection.sync().get("a");
        System.out.println("value is " + a);
    }

    @Test
    public void addList() {
        connection = redisClient.connect();
        Long a = connection.sync().lpush("a", "1", "2", "3", "4", "5");//在key对应list的头部添加字符串元素，返回1表示成功，0表示key存在且不是list类型
//        1.rpush key string同上，在尾部添加

//        2.llen key返回key对应list的长度，key不存在返回0,如果key对应类型不是list返回错误

//        3.lrange key start end返回指定区间内的元素，下标从0开始，负值表示从后面计算，-1表示倒数第一个元素 ，key不存在返回空列表

//        4.ltrim key start end截取list，保留指定区间内元素，成功返回1，key不存在返回错误
//        5.lset key index value设置list中指定下标的元素值，成功返回1，key或者下标不存在返回错误
//        6.lrem key count value从key对应list中删除count个和value相同的元素。count为0时候删除全部
//        7.lpop key从list的头部删除元素，并返回删除元素。如果key对应list不存在或者是空返回nil，如果key对应值不是list返回错误
//        8.rpop同上，但是从尾部删除
        System.out.println("add is " + a);
        List<String> a1 = connection.sync().lrange("a", 0, a);
        for (String item : a1) {
            System.out.println("value is " + item);
        }
    }

    @Test
    public void updateList() {
        connection = redisClient.connect();
        String a = connection.sync().lset("a", 1, "11");
        System.out.println(a);
        List<String> a1 = connection.sync().lrange("a", 0, -1);
        for (String aa : a1) {
            System.out.println("value is " + aa);
        }
    }

    @Test
    public void delList() {
        connection = redisClient.connect();
        KeyValue<String, String> a = connection.sync().blpop(0, "a");
        System.out.println("删除：" + a);

        List<String> a1 = connection.sync().lrange("a", 0, -1);

        for (String item : a1) {
            System.out.println(item);
        }

    }

    /**
     * sadd key member 添加一个string元素到,key对应的set集合中，成功返回1,如果元素以及在集合中返回0,key对应的set不存在返回错误
     * srem key member 从key对应set中移除给定元素，成功返回1，如果member在集合中不存在或者key不存在返回0，如果key对应的不是set类型的值返回错误
     * spop key 删除并返回key对应set中随机的一个元素,如果set是空或者key不存在返回nil
     * srandmember key 同spop，随机取set中的一个元素，但是不删除元素
     * smove srckey dstkey member 从srckey对应set中移除member并添加到dstkey对应set中，整个操作是原子的。成功返回1,如果member在srckey中不存在返回0，如果key不是set类型返回错误
     * scard key 返回set的元素个数，如果set是空或者key不存在返回0
     * sismember key member 判断member是否在set中，存在返回1，0表示不存在或者key不存在
     * sinter key1 key2...keyN 返回所有给定key的交集
     * sinterstore dstkey key1...keyN 同sinter，但是会同时将交集存到dstkey下
     * sunion key1 key2...keyN 返回所有给定key的并集
     * sunionstore dstkey key1...keyN 同sunion，并同时保存并集到dstkey下
     * sdiff key1 key2...keyN 返回所有给定key的差集
     * sdiffstore dstkey key1...keyN 同sdiff，并同时保存差集到dstkey下
     * smembers key 返回key对应set的所有元素，结果是无序的
     */
    @Test
    public void insertSet() {
        connection = redisClient.connect();
        connection.sync().srem("c", "");
    }

    /**
     * zadd key score member 添加元素到集合，元素在集合中存在则更新对应score
     * zrem key member 删除指定元素，1表示成功，如果元素不存在返回0
     * zincrby key incr member 增加对应member的score值，然后移动元素并保持skip list有序。返回更新后的score值
     * zrank key member 返回指定元素在集合中的排名（下标，非score）,集合中元素是按score从小到大排序的
     * zrevrank key member 同上,但是集合中元素是按score从大到小排序
     * zrange key start end 类似lrange操作从集合中取指定区间的元素。返回的是有序结果
     * zrevrange key start end 同上，返回结果是按score逆序的
     * zrangebyscore key min max 返回集合中score在给定区间的元素
     * zcount key min max 返回集合中score在给定区间的数量
     * zcard key 返回集合中元素个数
     * zscore key element  返回给定元素对应的score
     * zremrangebyrank key min max 删除集合中排名在给定区间的元素
     * zremrangebyscore key min max 删除集合中score在给定区间的元素
     */
    @Test
    public void insertSortSet() {
        connection = redisClient.connect();

        connection.sync().zadd("cc", 1, "10");
        connection.sync().zadd("cc", 2, "11");
        connection.sync().zadd("cc", 15, "12");
        connection.sync().zadd("cc", 43, "14");
        connection.sync().zadd("cc", 18, "18");
        List<String> cc = connection.sync().zrevrange("cc", 0, -1);
        for (String c:cc){
            System.out.println(c);
        }

    }

    /**
     * hset key field value设置hash field为指定值，如果key不存在，则先创建
     * hget key field 获取指定的hash field
     * hmget key filed1....fieldN 获取全部指定的hash filed
     * hmset key filed1 value1 ... filedN valueN 同时设置hash的多个field
     * hincrby key field integer 将指定的hash filed 加上给定值
     * hexists key field 测试指定field是否存在
     * hdel key field 删除指定的hash field
     * hlen key 返回指定hash的field数量
     * hkeys key 返回hash的所有field
     * hvals key 返回hash的所有value
     * hgetall 返回hash的所有filed和value
     */
    @Test
    public void insertHash(){
        connection=redisClient.connect();
        Boolean hset = connection.sync().hset("ccc", "11", "vv");
        System.out.println(hset);
        String cc = connection.sync().hget("ccc", "11");
        System.out.println(cc);

    }
    @After
    public void destroy() {
        connection.close();
    }
}
