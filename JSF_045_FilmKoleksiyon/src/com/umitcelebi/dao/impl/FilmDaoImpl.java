package com.umitcelebi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.umitcelebi.dao.FilmDao;
import com.umitcelebi.model.Film;
import com.umitcelebi.model.Oyuncu;

@SuppressWarnings({ "unchecked", "deprecation" })
public class FilmDaoImpl implements FilmDao {

	@Override
	public void FilmEkle(Film film) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.save(film);
			transaction.commit();
		}catch (Exception e) {
			System.out.println("EKLENEMEDÝ..."+e);
		}finally {
			session.close();
			factory.close();
		}
		
	}

	@Override
	public List<Film> filmler() {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			session.beginTransaction();
			Query<Film>query=session.createQuery("from Film");
			List<Film> filmList=query.getResultList();
			
			return filmList;
		} catch (Exception e) {
			System.out.println("LISTELENEMEDI..."+e);
			return null;
		}finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public String filmDuzenle(int id, Film film) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			session.beginTransaction();
			Query<Film>query=session.createQuery("UPDATE Film SET filAdi=:filmAdi , yayinYili=:yil , filmTur=:tur , aciklama=:aciklama , medya=:medya where filmId=:id");
			query.setParameter("filmAdi", film.getFilAdi());
			query.setParameter("yil", film.getYayinYili());
			query.setParameter("tur", film.getFilmTur());
			query.setParameter("aciklama", film.getAciklama());
			query.setParameter("medya", film.getMedya());
			query.setParameter("id", id);
			
			int durum=query.executeUpdate();
			session.close();
			if(durum==1) {
				return "guncellemeBasarili";
			}else {
				return "guncellemeBasarisiz";
			}
		} catch (Exception e) {
			System.out.println("GÜNCELLENEMEDÝ..."+e);
			return "guncellemeBasarisiz";
		}finally {
			session.close();
			factory.close();
		}
		
	}

	@Override
	public void filmSil(int id) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			Transaction transaction=session.beginTransaction();
			Query<Film>query=session.createQuery("DELETE FROM Film WHERE (filmId =:id)");
			query.setParameter("id", id);
			int durum=query.executeUpdate();
			if(durum==1) {
				System.out.println("silme iþlemi baþarýlý...");
			}else {
				System.out.println("silme iþlemi baþarýsýz...");
			}
			transaction.commit();
			
		} catch (Exception e) {
			System.out.println("silinemedi: "+e);
		}finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public List<Film> filmAraN(String filmAdi) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		try {
			session.beginTransaction();
			Query<Film> query=session.createQuery("from Film where filAdi=:filmAdi");
			query.setParameter("filmAdi", filmAdi);
			List<Film> list=query.getResultList();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Hata oluþtu: "+e);
			return null;
		}finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public List<Film> filmAraT(String filmTuru) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		try {
			session.beginTransaction();
			Query<Film> query=session.createQuery("SELECT f FROM Film f WHERE f.filmTur LIKE :filmTuru");
			query.setParameter("filmTuru", "%"+filmTuru+"%");
			List<Film> list=query.getResultList();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Hata oluþtu: "+e);
			return null;
		}finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public List<Film> filmAraO(String oyuncuAdi) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		try {
			session.beginTransaction();
			Query<Film> query=session.createQuery("SELECT f FROM Film f join fetch f.oyuncu o where o.oyuncuAdi=:oyuncuAD");
			query.setParameter("oyuncuAD", oyuncuAdi);
			
			List<Film> list=query.getResultList();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Hata oluþtu: "+e);
			return null;
		}finally {
			session.close();
			factory.close();
		}
	}
	
	@Override
	public Film filmTumBilgiler(int filmId){
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			session.beginTransaction();
			Query<Film>query=session.createQuery("select f from Film f join fetch f.oyuncu o where f.filmId=:id");
			query.setParameter("id", filmId);
			Film film=query.getSingleResult();
			
			return film;
		} catch (Exception e) {
			System.out.println("LISTELENEMEDI..."+e);
			return null;
		}finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public void filmOyuncuEkle(int filmId,int oyuncuId) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			session.beginTransaction();
			Query<Film> query=session.createQuery("from Film where filmId=:filmId");
			query.setParameter("filmId", filmId);
			Film film=query.getSingleResult();
			Query<Oyuncu> query2=session.createQuery("from Oyuncu where oyuncuId=:id");
			query2.setParameter("id", oyuncuId);
			Oyuncu oyuncu=query2.getSingleResult();
			
			film.getOyuncu().add(oyuncu);
			oyuncu.getFilm().add(film);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("LISTELENEMEDI..."+e);
			
		}finally {
			session.close();
			factory.close();
		}
		
	}

	@Override
	public void filmOyuncuKaldir(int filmId,int oyuncuId) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			session.beginTransaction();
			Query<Film> query=session.createQuery("from Film where filmId=:filmId");
			query.setParameter("filmId", filmId);
			Film film=query.getSingleResult();
			Query<Oyuncu> query2=session.createQuery("from Oyuncu where oyuncuId=:id");
			query2.setParameter("id", oyuncuId);
			Oyuncu oyuncu=query2.getSingleResult();
			
			film.getOyuncu().remove(oyuncu);
			oyuncu.getFilm().remove(film);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("LISTELENEMEDI..."+e);
			
		}finally {
			session.close();
			factory.close();
		}
	}

	@Override
	public Film fimAra(int filmId) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			session.beginTransaction();
			Query<Film>query=session.createQuery("from Film where filmId=:id");
			query.setParameter("id", filmId);
			Film film=query.getSingleResult();
			
			return film;
		} catch (Exception e) {
			System.out.println("LISTELENEMEDI..."+e);
			return null;
		}finally {
			session.close();
			factory.close();
		}
	}

	
	  public List<Oyuncu> filmTumOyuncular(int id){
		  SessionFactory factory=new Configuration().configure().buildSessionFactory(); 
		  Session session=factory.openSession(); 
		  try { 
			  session.beginTransaction();
			  Query<Oyuncu>query=session.createQuery("SELECT o FROM Oyuncu o JOIN FETCH o.film f where f.filmId=:id" ,Oyuncu.class);
			  query.setParameter("id", id);
			  List<Oyuncu>list=query.getResultList();
	  
			  return list; 
	  } 
		  catch (Exception e) {
	  System.out.println("LISTELENEMEDI..."+e); 
	  return null; }
		  finally {
			  	session.close(); 
			  	factory.close();
		} 
	}
	 

}
