package com.genshin.genshindmg.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genshin.genshindmg.service.ex.ShSystemException;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库artifact表内容映射工具
 */
public class TableMappingUtil {
    private static final Map<Integer, String> SLOT_TO_STRING;
    private static final Map<String, Integer> SLOT_TO_INT;
    private static final Map<Integer, String> SET_TO_STRING;
    private static final Map<String, Integer> SET_TO_INT;
    private static final Map<Integer, String> STAT_TO_STRING;
    private static final Map<String, Integer> STAT_TO_INT;

    static {
        SLOT_TO_STRING = loadMap("artifact/mapping/slot.json");
        SLOT_TO_INT = createReverseMap(SLOT_TO_STRING);

        SET_TO_STRING = loadMap("artifact/mapping/set.json");
        SET_TO_INT = createReverseMap(SET_TO_STRING);

        STAT_TO_STRING = loadMap("artifact/mapping/stat.json");
        STAT_TO_INT = createReverseMap(STAT_TO_STRING);
    }


    public static String getSlotString(Integer n) {
        return SLOT_TO_STRING.get(n);
    }

    public static Integer getSlotInt(String s) {
        return SLOT_TO_INT.get(s);
    }

    public static Boolean hasSlotString(String s) {
        return SLOT_TO_STRING.containsValue(s);
    }

    public static Boolean hasSlotInt(Integer n) {
        return SLOT_TO_STRING.containsKey(n);
    }

    public static String getSetString(Integer n) {
        return SET_TO_STRING.get(n);
    }

    public static Integer getSetInt(String s) {
        return SET_TO_INT.get(s);
    }

    public static Boolean hasSetString(String s) {
        return SET_TO_STRING.containsValue(s);
    }

    public static Boolean hasSetInt(Integer n) {
        return SET_TO_STRING.containsKey(n);
    }

    public static String getStatString(Integer n) {
        return STAT_TO_STRING.get(n);
    }

    public static Integer getStatInt(String s) {
        return STAT_TO_INT.get(s);
    }

    public static Boolean hasStatString(String s) {
        return STAT_TO_STRING.containsValue(s);
    }

    public static Boolean hasStatInt(Integer n) {
        return STAT_TO_STRING.containsKey(n);
    }

    private static Map<Integer, String> loadMap(String jsonPath) {
        Map<Integer, String> res = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        // 读取数据文件
        try (InputStream input = TableMappingUtil.class.getClassLoader().getResourceAsStream(jsonPath)) {
            if (input == null) {
                throw new ShSystemException(ResultEnum.FILE_IO);
            }

            // 从 JSON 中获取数据
            Map<Integer, String> data = mapper.readValue(input, new TypeReference<Map<Integer, String>>() {});
            if (data != null) {
                res.putAll(data);
            }
        } catch (Exception e) {
            throw new ShSystemException(ResultEnum.FILE_IO);
        }

        return Collections.unmodifiableMap(res);
    }

    private static Map<String, Integer> createReverseMap(Map<Integer, String> origin) {
        Map<String, Integer> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : origin.entrySet()) {
            res.put(entry.getValue(), entry.getKey());
        }
        return Collections.unmodifiableMap(res);
    }

}
