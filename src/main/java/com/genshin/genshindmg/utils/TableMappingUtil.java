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
    private static final Map<Integer, String> PART_TO_STRING;
    private static final Map<String, Integer> PART_TO_INT;
    private static final Map<Integer, String> SUIT_TO_STRING;
    private static final Map<String, Integer> SUIT_TO_INT;

    static {
        PART_TO_STRING = loadMap("artifact/mapping/part.json");
        PART_TO_INT = createReverseMap(PART_TO_STRING);

        SUIT_TO_STRING = loadMap("artifact/mapping/suit.json");
        SUIT_TO_INT = createReverseMap(SUIT_TO_STRING);
    }


    public static String getPartString(Integer n) {
        return PART_TO_STRING.get(n);
    }

    public static Integer getPartInt(String s) {
        return PART_TO_INT.get(s);
    }

    public static Boolean hasPartString(String s) {
        return PART_TO_STRING.containsValue(s);
    }

    public static Boolean hasPartInt(Integer n) {
        return PART_TO_STRING.containsKey(n);
    }

    public static String getSuitString(Integer n) {
        return SUIT_TO_STRING.get(n);
    }

    public static Integer getSuitInt(String s) {
        return SUIT_TO_INT.get(s);
    }

    public static Boolean hasSuitString(String s) {
        return SUIT_TO_STRING.containsValue(s);
    }

    public static Boolean hasSuitInt(Integer n) {
        return SUIT_TO_STRING.containsKey(n);
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
