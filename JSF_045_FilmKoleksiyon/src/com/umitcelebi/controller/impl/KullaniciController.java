package com.umitcelebi.controller.impl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.umitcelebi.controller.IKullaniciController;
import com.umitcelebi.dao.impl.KullaniciDaoImpl;
import com.umitcelebi.model.Kullanici;

@ManagedBean(name = "kullaniciBean")
@SessionScoped
public class KullaniciController implements IKullaniciController {

	private String kulAdi;
	private String parola;
	//--------------------------------
	KullaniciDaoImpl dao=new KullaniciDaoImpl();
	//-----------------------------
	public String getKulAdi() {
		return kulAdi;
	}
	public void setKulAdi(String kulAdi) {
		this.kulAdi = kulAdi;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	
	//------------------------------------------------------------------------------
	@Override
	public String girisYap() {
		String mesaj=dao.girisYap(kulAdi, parola);
		this.kulAdi="";
		this.parola="";
		return mesaj;
	}

	@Override
	public String kayitEkle() {
		Kullanici kullanici=new Kullanici();
		kullanici.setKulAdi(kulAdi);
		kullanici.setParola(parola);
		
		kulAdi="";
		parola="";
		return dao.kayitEkle(kullanici);
	}

}
