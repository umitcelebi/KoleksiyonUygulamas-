package com.umitcelebi.dao;

import com.umitcelebi.model.Kullanici;

public interface KullaniciDao {
	public String girisYap(String username,String parola);
	public String kayitEkle(Kullanici kullanici);
}
