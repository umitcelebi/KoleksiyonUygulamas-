package com.umitcelebi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kullanici {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String kulAdi;
	private String parola;
	
	//-----------------------------------------
	public String getKulAdi() {
		return kulAdi;
	}
	public void setKulAdi(String kulAdi) {
		this.kulAdi = kulAdi;
	}
	//-----------------------------------------
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	//-----------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
