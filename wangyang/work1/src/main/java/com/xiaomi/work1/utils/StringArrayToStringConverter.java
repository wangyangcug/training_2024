package com.xiaomi.work1.utils;

/**
 * ClassName: StringArrayToStringConverter
 * Package: com.xiaomi.work1.utils
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/6 16:37
 * @Version 1.0
 */
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class StringArrayToStringConverter implements AttributeConverter<String[], String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        System.out.println("--------------------------------------------");
        if (attribute == null || attribute.length == 0) {
            return null;
        }
        return String.join(DELIMITER, attribute);
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new String[0];
        }
        return dbData.split(DELIMITER);
    }
}