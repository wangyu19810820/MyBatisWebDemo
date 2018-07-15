package com.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * 类型处理器，将java8的LocalDateTime转换成Timestamp
 * 实际无此必要，新版本的MyBatis，默认就能很好的支持
 */
public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        Timestamp timestamp = new Timestamp(localDateTime.getYear() - 1900,
                localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(),
                localDateTime.getNano());
        preparedStatement.setTimestamp(i, timestamp);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(s);
        return LocalDateTime.of(timestamp.getYear() + 1900, timestamp.getMonth(),
                timestamp.getDate(), timestamp.getHours(),
                timestamp.getMinutes(), timestamp.getSeconds());
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(i);
        return LocalDateTime.of(timestamp.getYear() + 1900, timestamp.getMonth(),
                timestamp.getDate(), timestamp.getHours(),
                timestamp.getMinutes(), timestamp.getSeconds());
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(i);
        return LocalDateTime.of(timestamp.getYear() + 1900, timestamp.getMonth(),
                timestamp.getDate(), timestamp.getHours(),
                timestamp.getMinutes(), timestamp.getSeconds());
    }
}
