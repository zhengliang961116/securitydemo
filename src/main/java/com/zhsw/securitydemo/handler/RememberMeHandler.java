package com.zhsw.securitydemo.handler;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhengliang
 * @Description: 记住我处理
 * @Date: 2019/12/10 18:01
 */
@Component
public class RememberMeHandler  implements PersistentTokenRepository {
    /**
     * token有效时间30天
     */
    private static final Long TOKEN_VALID_DAYS = 15L;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 新增
     * @param token
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
    String key = generateTokenKey(token.getSeries());
    Map<String,String> map =new HashMap<>(8);
    map.put("username",token.getUsername());
    map.put("token",token.getTokenValue());
    map.put("date",token.getDate().getTime() + "");
    //放在缓存redis
    stringRedisTemplate.opsForHash().putAll(key,map);
    stringRedisTemplate.expire(key,TOKEN_VALID_DAYS, TimeUnit.DAYS);
    saveUsernameAndSeries(token.getUsername(),token.getSeries());
    }

    /**
     * 修改
     * @param series
     * @param tokenValue
     * @param date
     */
    @Override
    public void updateToken(String series, String tokenValue, Date date) {
        //调用产生key的方法
        String key = generateTokenKey(series);
        Boolean hasSeries = stringRedisTemplate.hasKey(key);
        if(hasSeries == null || !hasSeries){
            return;
        }
        Map<String,String> map = new HashMap<>(4);
        map.put("tokenValue", tokenValue);
        map.put("date", date.getTime()+"");
        stringRedisTemplate.opsForHash().putAll(key, map);
        stringRedisTemplate.expire(key, TOKEN_VALID_DAYS, TimeUnit.DAYS);
        //根据series查找username
        String username = stringRedisTemplate.opsForValue().get(generateUsernameAndSeriesKey(series));
        //保存用户名和series
        saveUsernameAndSeries(username, series);

    }
    /**
     * 删除
     * @param username
     * @return
     */
    @Override
    public void removeUserTokens(String username) {
        //根据用户名查找series
        String series = stringRedisTemplate.opsForValue().get(generateUsernameAndSeriesKey(username));
        if (series==null||series.trim().length()<=0){
            return;
        }
        stringRedisTemplate.delete(generateTokenKey(series));
        stringRedisTemplate.delete(generateUsernameAndSeriesKey(username));
        stringRedisTemplate.delete(generateUsernameAndSeriesKey(series));

    }

    /**
     * 根据seriesId查询
     * @param seriesId
     * @return
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String key = generateTokenKey(seriesId);
        Map hashKeyValues = stringRedisTemplate.opsForHash().entries(key);
        if (hashKeyValues == null) {
            return null;
        }
        Object username = hashKeyValues.get("username");
        Object tokenValue = hashKeyValues.get("tokenValue");
        Object date = hashKeyValues.get("date");
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        long timestamp = Long.valueOf(String.valueOf(date));
        Date time = new Date(timestamp);

        return new PersistentRememberMeToken(username+"", seriesId, tokenValue+"", time);
    }


    /**
     * 互相保存，便于查询
     */
    private void saveUsernameAndSeries(String username, String series){
        stringRedisTemplate.opsForValue().set(generateUsernameAndSeriesKey(username),series,TOKEN_VALID_DAYS*2, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(generateUsernameAndSeriesKey(series),username,TOKEN_VALID_DAYS*2,TimeUnit.DAYS);
    }

    /**
     * 生成token key
     */
    private String generateTokenKey(String series) {
        return "spring:security:rememberMe:token:" + series;
    }

    /**
     * 生成key
     */
    private String generateUsernameAndSeriesKey(String usernameOrSeries) {
        return "spring:security:rememberMe:"+usernameOrSeries;
    }
}
