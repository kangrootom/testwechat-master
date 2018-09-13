package com.wechat.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wechat.dao.UserDao;
import com.wechat.entity.po.User;
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		String hsql="from User";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        userList = query.list();
        return userList;
	}

	@Override
	public boolean isExists(String username) {
		Query query = sessionFactory.openSession()
				.createQuery("from User u where u.username = :username").setParameter("username", username);
		System.out.println(query.list().size());
		return query.list().size()>0?true:false;
	}

}
