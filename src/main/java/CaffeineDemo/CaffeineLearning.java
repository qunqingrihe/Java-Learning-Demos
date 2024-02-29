package CaffeineDemo;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CaffeineLearning {
    //手动创建缓存
    Cache<Object, Object> cache = Caffeine.newBuilder()
            //初始数量
            .initialCapacity(10)
            //最大条数
            .maximumSize(10)
            //PS：expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
            //最后一次写操作后经过指定时间过期
            .expireAfterWrite(1, TimeUnit.SECONDS)
            //最后一次读或写操作后经过指定时间过期
            .expireAfterAccess(1, TimeUnit.SECONDS)
            //监听缓存被移除
            .removalListener((key, val, removalCause) -> { })
            //记录命中
            .recordStats()
            .build();
    LoadingCache<String,String> loadingCache = Caffeine.newBuilder()
    //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
            .refreshAfterWrite( 1, TimeUnit.SECONDS)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterAccess(1, TimeUnit.SECONDS)
            .maximumSize(10)
            .build(key->new Date().toString());
    AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
            //创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持LoadingCache
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterAccess(1, TimeUnit.SECONDS)
            .maximumSize(10)
            //根据key查询数据库里面的值
            .buildAsync(key -> {
                Thread.sleep(1000);
                return new Date().toString();
            });

    //异步缓存返回的是CompletableFuture
    CompletableFuture<String> future = asyncLoadingCache.get("1");


    LoadingCache<String, String> cache1 = Caffeine.newBuilder()
            //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterAccess(1, TimeUnit.SECONDS)
            .maximumSize(10)
            //开启记录缓存命中率等信息
            .recordStats()
            //根据key查询数据库里面的值
            .build(key -> {
                Thread.sleep(1000);
                return new Date().toString();
            });



    /*
     * hitCount :命中的次数
     * missCount:未命中次数
     * requestCount:请求次数
     * hitRate:命中率
     * missRate:丢失率
     * loadSuccessCount:成功加载新值的次数
     * loadExceptionCount:失败加载新值的次数
     * totalLoadCount:总条数
     * loadExceptionRate:失败加载新值的比率
     * totalLoadTime:全部加载时间
     * evictionCount:丢失的条数
     */


}
