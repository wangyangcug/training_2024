package com.xiaomi.work1.handler;

/**
 * ClassName: StringArrayTypeHandler
 * Package: com.xiaomi.work1.handler
 * Description:
 *
 * @Author WangYang
 * @Create 2024/6/6 17:21
 * @Version 1.0
 */
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        // 将数组转换为字符串，以逗号分隔，并设置到 PreparedStatement 中
        String arrayAsString = String.join(",", parameter);
        ps.setString(i, arrayAsString);
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从 ResultSet 中获取数据库字段的值，并将其转换为数组
        String arrayAsString = rs.getString(columnName);
        return arrayAsString.split(",");
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从 ResultSet 中获取数据库字段的值，并将其转换为数组
        String arrayAsString = rs.getString(columnIndex);
        return arrayAsString.split(",");
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从 CallableStatement 中获取数据库字段的值，并将其转换为数组
        String arrayAsString = cs.getString(columnIndex);
        return arrayAsString.split(",");
    }
}