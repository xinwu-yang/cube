package org.cube.commons.base;

import cn.hutool.core.util.ObjectUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 自定义Map
 */
public class BaseMap extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public BaseMap() {
    }

    public BaseMap(Map<String, Object> map) {
        this.putAll(map);
    }

    @Override
    public BaseMap put(String key, Object value) {
        super.put(key, Optional.ofNullable(value).orElse(""));
        return this;
    }

    public BaseMap add(String key, Object value) {
        super.put(key, Optional.ofNullable(value).orElse(""));
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Object obj = super.get(key);
        if (ObjectUtil.isNotEmpty(obj)) {
            return (T) obj;
        } else {
            return null;
        }
    }

    public Boolean getBoolean(String key) {
        Object obj = super.get(key);
        if (ObjectUtil.isNotEmpty(obj)) {
            return Boolean.valueOf(obj.toString());
        } else {
            return false;
        }
    }

    public Long getLong(String key) {
        Object v = get(key);
        if (ObjectUtil.isNotEmpty(v)) {
            return Long.valueOf(v.toString());
        }
        return null;
    }

    public Long[] getLongs(String key) {
        Object v = get(key);
        if (ObjectUtil.isNotEmpty(v)) {
            return (Long[]) v;
        }
        return null;
    }

    public List<Long> getListLong(String key) {
        List<String> list = get(key);
        if (ObjectUtil.isNotEmpty(list)) {
            return list.stream().map(Long::valueOf).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public Long[] getLongIds(String key) {
        Object ids = get(key);
        if (ObjectUtil.isNotEmpty(ids)) {
            String[] strIds = ids.toString().split(",");
            Long[] longIds = new Long[strIds.length];
            for (int i = 0; i < strIds.length; i++) {
                longIds[i] = Long.valueOf(strIds[i]);
            }
            return longIds;
        } else {
            return null;
        }
    }

    public Integer getInt(String key, Integer def) {
        Object v = get(key);
        if (ObjectUtil.isNotEmpty(v)) {
            return Integer.parseInt(v.toString());
        } else {
            return def;
        }
    }

    public Integer getInt(String key) {
        Object v = get(key);
        if (ObjectUtil.isNotEmpty(v)) {
            return Integer.parseInt(v.toString());
        } else {
            return 0;
        }
    }

    public BigDecimal getBigDecimal(String key) {
        Object v = get(key);
        if (ObjectUtil.isNotEmpty(v)) {
            return new BigDecimal(v.toString());
        }
        return new BigDecimal("0");
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, T def) {
        Object obj = super.get(key);
        if (ObjectUtil.isEmpty(obj)) {
            return def;
        }
        return (T) obj;
    }

    public static BaseMap toBaseMap(Map<String, Object> obj) {
        BaseMap map = new BaseMap();
        map.putAll(obj);
        return map;
    }
}
