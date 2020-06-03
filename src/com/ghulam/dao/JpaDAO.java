package com.ghulam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public abstract class JpaDAO<T> {
	private Session session;
	private SessionFactory sessionFactory;
	private Class<T> class1;
	private Class<?> classTypes[];

	@SafeVarargs
	public JpaDAO(Class<T> class1, Class<?>... classTypes) {
		this.class1 = class1;
		this.classTypes = classTypes;
	}

	private void initSession() {
		Configuration configure = new Configuration().configure();
		configure.addAnnotatedClass(class1);
		for (Class<?> class1 : classTypes) {
			configure.addAnnotatedClass(class1);
		}
		sessionFactory = configure.buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	protected List<T> findWithQuery(String query, HashMap<String, String> parameters) {
		initSession();
		Query<T> query2 = session.createQuery(query, class1);
		for (Entry<String, String> entity : parameters.entrySet()) {
			query2.setParameter(entity.getKey(), entity.getValue());
		}
		List<T> list = query2.getResultList();
		endSession();
		return list;
	}

	public T get(int id) {
		initSession();
		T t = session.get(class1, id);
		endSession();
		return t;
	}

	public List<T> getAll() {
		initSession();
		List<T> list = session.createQuery("FROM " + class1.getSimpleName(), class1).getResultList();
		endSession();
		return list;
	}

	public void save(T obj) {
		initSession();
		session.save(obj);
		updateCount(1);
		endSession();
	}
	public void saveAll(List<T> list){
		initSession();
		for (T t:list){
			session.save(t);
			updateCount(1);
		}
		endSession();
	}

	public void update(T obj) {
		initSession();
		session.update(obj);
		endSession();
	}

	public void delete(T obj) {
		initSession();
		session.delete(obj);
		updateCount(-1);
		endSession();
	}

	public void delete(int id) {
		initSession();
		session.createQuery("DELETE FROM " + class1.getSimpleName() + " t WHERE t.id=" + id).executeUpdate();
		updateCount(-1);
		endSession();
	}

	protected abstract void updateCount(int increamentValue);

	private void endSession() {
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
