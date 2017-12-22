package com.liutao.demo.common.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis数据操作工具
 * 
 * @param <T>
 */
@Component("redisCacheUtil")
public class RedisCacheUtil<T> {

	@Autowired
	@Qualifier("redisTemplate")
	public RedisTemplate redisTemplate;

	/**
	 * 缓存基本对象
	 * 
	 * @param key
	 * @param value
	 * @param <T>
	 * @return
	 */
	public <T> ValueOperations<String, T> cacheObject(String key, T value) {
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		operations.set(key, value);
		return operations;
	}

	/**
	 * 获取基本缓存对象
	 * 
	 * @param key
	 * @param <T>
	 * @return
	 */
	public <T> T getObject(String key) {
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}

	/**
	 * 缓存list
	 * 
	 * @param key
	 * @param list
	 * @param <T>
	 * @return
	 */
	public <T> ListOperations<String, T> cacheList(String key, List<T> list) {

		ListOperations<String, T> listOperations = redisTemplate.opsForList();
		if (null != list && !list.isEmpty()) {
			int len = list.size();
			for (int i = 0; i < len; i++) {
				T t = list.get(i);
				listOperations.rightPush(key, t);
			}
		}
		return listOperations;
	}

	/**
	 * 获取list数据
	 * 
	 * @param key
	 * @param <T>
	 * @return
	 */
	public synchronized <T> List<T> getList(String key) {

		List<T> dataList = new ArrayList<>();
		ListOperations<String, T> listOperations = redisTemplate.opsForList();
		long len = listOperations.size(key);
		for (int i = 0; i < len; i++) {
			dataList.add(listOperations.leftPop(key));
		}
		return dataList;
	}

	/**
	 * 缓存set
	 * 
	 * @param key
	 * @param dataSet
	 * @param <T>
	 * @return
	 */
	public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {

		BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
		Iterator<T> it = dataSet.iterator();
		while (it.hasNext()) {
			setOperations.add(it.next());
		}
		return setOperations;
	}

	/**
	 * 获取set数据
	 * 
	 * @param key
	 * @return
	 */
	public Set<T> getSet(String key) {

		Set<T> dataSet = new HashSet<>();
		BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
		Long size = operation.size();
		for (int i = 0; i < size; i++) {
			dataSet.add(operation.pop());
		}
		return dataSet;
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 * @return
	 */
	public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {

		HashOperations hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<String, T> entry : dataMap.entrySet()) {
				/*
				 * System.out.println("Key = " + entry.getKey() + ", Value = " +
				 * entry.getValue());
				 */
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @return
	 */
	public <T> Map<String, T> getCacheMap(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 * @return
	 */
	public <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap) {
		HashOperations hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
				/*
				 * System.out.println("Key = " + entry.getKey() + ", Value = " +
				 * entry.getValue());
				 */
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @return
	 */
	public <T> Map<Integer, T> getCacheIntegerMap(String key) {
		Map<Integer, T> map = redisTemplate.opsForHash().entries(key);
		return map;
	}
	
	public void evict(String key) {
		
	}

}
