package com.umitcelebi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.umitcelebi.dao.FilmDao;
import com.umitcelebi.model.Film;

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
		}
	}

}
