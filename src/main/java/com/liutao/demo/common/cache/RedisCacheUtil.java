package com.liutao.demo.common.cache;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import com.liutao.demo.common.BaseBean;
import com.liutao.demo.controller.UserController;

/**
 * redis数据操作工具
 * 
 * @param <T>
 */
@Component("redisCacheUtil")
public class RedisCacheUtil<T extends BaseBean> {
	private static final Logger log = LoggerFactory.getLogger(RedisCacheUtil.class);

	@Autowired
	RedisCacheManager redisCacheManager;

	/**
	 * 获取缓存
	 * 
	 * @param name
	 * @return
	 */
	public Cache getCache(String name) {
		log.debug(name);
		return redisCacheManager.getCache(name);
	}

	public Collection<String> getCacheNames() {
		log.debug("getCacheNames");
		return redisCacheManager.getCacheNames();
	}

	@SuppressWarnings("unchecked")
	public T get(String name, Object key) {
		log.debug(name, key);
		Cache cache = getCache(name);
		if (cache == null) {
			return null;
		}
		ValueWrapper vw = getCache(name).get(key);
		return vw == null ? null : (T) vw.get();
	}

	public void put(String name, Object key, Object value) {
		log.debug(name, key, value);
		Cache cache = getCache(name);
		if (cache == null) {
			return;
		}
		cache.put(key, value);
	}

	public ValueWrapper putIfAbsent(String name, Object key, Object value) {
		log.debug(name, key, value);
		Cache cache = getCache(name);
		if (cache == null) {
			return null;
		}
		return cache.putIfAbsent(key, value);
	}

	public void evict(String name, Object key) {
		log.debug(name, key);
		Cache cache = getCache(name);
		if (cache == null) {
			return;
		}
		cache.evict(key);
	}

	public void evictBatch(String name, Object... keys) {
		log.debug(name, keys);
		Cache cache = getCache(name);
		if (cache == null) {
			return;
		}
		for (int i = 0; i < keys.length; i++) {
			cache.evict(keys[i]);
		}
	}

	public void clear(String name) {
		log.debug(name);
		Cache cache = getCache(name);
		if (cache == null) {
			return;
		}
		cache.clear();
	}

}
