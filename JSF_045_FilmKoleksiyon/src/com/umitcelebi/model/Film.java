package com.umitcelebi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Film implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int filmId;
	
	private String filAdi;
	private int yayinYili;
	private String filmTur;
	private String aciklama;
	private String medya;
	//---------------------------------------
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Oyuncu>oyuncu=new ArrayList<Oyuncu>();
	
	public List<Oyuncu> getOyuncu() {
		return oyuncu;
	}
	public void setOyuncu(List<Oyuncu> oyuncu) {
		this.oyuncu = oyuncu;
	}
	//---------------------------------------
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getFilAdi() {
		return filAdi;
	}
	public void setFilAdi(String filAdi) {
		this.filAdi = filAdi;
	}
	public int getYayinYili() {
		return yayinYili;
	}
	public void setYayinYili(int yayinYili) {
		this.yayinYili = yayinYili;
	}
	public String getFilmTur() {
		return filmTur;
	}
	public void setFilmTur(String filmTur) {
		this.filmTur = filmTur;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public String getMedya() {
		return medya;
	}
	public void setMedya(String medya) {
		this.medya = medya;
	}
	
}
