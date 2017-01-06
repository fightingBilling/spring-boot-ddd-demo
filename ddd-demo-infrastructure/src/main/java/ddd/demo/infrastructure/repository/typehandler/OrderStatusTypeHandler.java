package ddd.demo.infrastructure.repository.typehandler;

import ddd.demo.domain.order.model.OrderStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * OrderStatus类型扩展
 */
public class OrderStatusTypeHandler extends BaseTypeHandler<OrderStatus> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, OrderStatus orderStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, orderStatus.value());

    }

    @Override
    public OrderStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int orderStatus = resultSet.getInt(s);
        return OrderStatus.parse(orderStatus);
    }

    @Override
    public OrderStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int orderStatus = resultSet.getInt(i);
        return OrderStatus.parse(orderStatus);
    }

    @Override
    public OrderStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int orderStatus = callableStatement.getInt(i);
        return OrderStatus.parse(orderStatus);
    }
}
