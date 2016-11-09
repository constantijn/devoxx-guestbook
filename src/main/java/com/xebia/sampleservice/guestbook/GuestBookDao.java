package com.xebia.sampleservice.guestbook;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.util.List;

public class GuestBookDao extends AbstractDAO<GuestBookEntry> {
    public GuestBookDao(SessionFactory factory) {
        super(factory);
    }

    public long create(GuestBookEntry entry) {
        return persist(entry).getId();
    }

    public List<GuestBookEntry> findAll() {
        return list(criteria().addOrder(Order.desc("id")).setMaxResults(6));
    }
}
