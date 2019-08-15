package com.hiddenhopes.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hiddenhopes.spring.model.Book;

@Repository
public class BookDaoImp implements BookDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public long save(Book book) {
      sessionFactory.getCurrentSession().save(book);
      return book.getId();
   }

   @Override
   public Book get(long id) {
      return sessionFactory.getCurrentSession().get(Book.class, id);
   }

   @Override
   public List<Book> list() {
      List<Book> list = sessionFactory.getCurrentSession().createQuery("from Book").list();
      return list;
   }

   @Override
   public void update(long id, Book book) {
      Session session = sessionFactory.getCurrentSession();
      Book oldBook = session.byId(Book.class).load(id);
      oldBook.setTitle(book.getTitle());
      oldBook.setAuthor(book.getAuthor());
      session.flush();
   }

   @Override
   public void delete(long id) {
      Book book = sessionFactory.getCurrentSession().byId(Book.class).load(id);
      sessionFactory.getCurrentSession().delete(book);
   }

}
