package com.example.web_customer_tracker.dao;


import com.example.web_customer_tracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return Optional.ofNullable(getSession().get(Customer.class, id));
    }

    @Override
    public List<Customer> getCustomers() {
        return getSession()
                .createQuery("from Customer order by firstName, lastName", Customer.class)
                .getResultList();
    }

    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        getSession().saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        getSession()
                .createQuery("delete from Customer where id=?1")
                .setParameter(1, id)
                .executeUpdate();
    }
}
