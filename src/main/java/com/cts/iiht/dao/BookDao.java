package com.cts.iiht.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;

import com.cts.iiht.entity.Book;
import com.cts.iiht.entity.Subject;
import com.cts.iiht.util.JPAUtil;

@Component
public class BookDao {


	public void addBook(Book book ) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction txn = em.getTransaction();
        txn.begin();
        em.persist(book);
        txn.commit();
		System.out.println("Book added succesfully");
	}

	public void deleteBook(long id) {
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			EntityTransaction txn = em.getTransaction();
	        txn.begin();
	        Book book = searchBook(id);
	        if(book != null) {
	        	book = em.merge(book);
	        	book.setSubject(null);
	        	em.persist(book);
	        	txn.commit();
	        	txn.begin();
	        	book = em.merge(book);
	        	em.remove(book);
		        txn.commit();
				System.out.println("Book with id: "+ id + " has been deleted successfully");
	        }
			else {
				System.out.println("No Book found with id: "+ id );
			}		
	}

	public Book searchBook(long id) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.find(Book.class, id);
	}
	
	public List<Book> getAllBooks() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.createQuery("FROM Book b").getResultList();
	}
	
	public List<Book> getAllBooksSortByTitle(){
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.createQuery("FROM Book b order by b.title").getResultList();
	}
	
	public List<Book> getAllBooksSortByPublishDate(){
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		return em.createQuery("FROM Book b order by b.publishDate").getResultList();
	}
}
