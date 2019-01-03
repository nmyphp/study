package com.free.spring.condition.dao;

import com.free.spring.jdbc.demo.dao.po.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ThirdPartyOrderDao {

    public static final String SQL_FIND_BY_TID = "select tid,reseive_address,buyer_name,buyer_phone,creation_date from "
        + "order_3party where tid = ?";

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Order> findByTid(Long tid) {
        return jdbcTemplate.query(SQL_FIND_BY_TID, (resultSet, index) -> {
            Order order = new Order();
            order.setTid(resultSet.getLong(1));
            order.setReseiveAddress(resultSet.getString(2));
            order.setBuyerName(resultSet.getString(3));
            order.setBuyerPhone(resultSet.getString(4));
            order.setCreationDate(resultSet.getDate(5));
            return order;
        }, tid);
    }
}
