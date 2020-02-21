package com.umitcelebi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.umitcelebi.dao.KullaniciDao;
import com.umitcelebi.model.Kullanici;

@SuppressWarnings({ "deprecation", "unchecked" })
public class KullaniciDaoImpl implements KullaniciDao {

	@Override
	public String girisYap(String username, String parola) {
		
		try {
			SessionFactory factory=new Configuration().configure().buildSessionFactory();
			Session session=factory.openSession();
			Transaction transaction=session.beginTransaction();
			Query<Kullanici>query=session.createQuery("from Kullanici where kulAdi=:kuladi and parola=:parola");
			query.setParameter("kuladi", username);
			query.setParameter("parola", parola);
			List<Kullanici>list=query.getResultList();
			
			transaction.commit();
			if(list.isEmpty()) {
				return "girisBasarisiz";
			}else {
				return "girisBasarili";
			}
		} catch (Exception e) {
			System.out.println("Giriþ yaparken hata oluþtu..."+e);
			return "girisBasarisiz";
		}
	}

	@Override
	public String kayitEkle(Kullanici kullanici) {
		
		try {
			SessionFactory factory=new Configuration().configure().buildSessionFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			session.save(kullanici);
			session.getTransaction().commit();
			session.close();
			return "kayitBasarili";
		} catch (Exception e) {
			System.out.println("kayýt ederken hata oluþtu: "+e);
			return "kayitBasarisiz";
		}
	}

}
