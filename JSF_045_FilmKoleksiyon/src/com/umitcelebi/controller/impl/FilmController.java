package com.umitcelebi.controller.impl;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.umitcelebi.controller.IFilmController;
import com.umitcelebi.dao.impl.FilmDaoImpl;
import com.umitcelebi.model.Film;

@ManagedBean(name = "filmBean")
@SessionScoped
public class FilmController implements IFilmController {
	private int filmId;
	private String filAdi;
	private int yayinYili;
	private String filmTur;
	private String aciklama;
	private String medya;
	private Film film;
	
	//------------------------------------
	public boolean detay=false;
	public boolean isDetay() {
		return detay;
	}
	public void setDetay(boolean detay) {
		this.detay = detay;
	}
	//-----------------------------------
	FilmDaoImpl dao=new FilmDaoImpl();
	//------------------------------------
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
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}

	//-----------------------------------------------------
	
	@Override
	public void FilmEkle() {
		Film film=new Film();
		film.setFilAdi(filAdi);
		film.setFilmTur(filmTur);
		film.setAciklama(aciklama);
		film.setMedya(medya);
		film.setYayinYili(yayinYili);
		
		filAdi="";
		filmTur="";
		yayinYili=0;
		aciklama="";
		medya="";
		
		dao.FilmEkle(film);
		
	}
	@Override
	public ArrayList<Film> filmler() {
		
		if(filmListele) {
			return (ArrayList<Film>) dao.filmler();
			
		}else if(IsmeGoreAra){
			return filmAraN();
			
		}else if(tureGoreAra){
			return filmAraT();
			
		}else {
			return null;
		}
	}
	@Override
	public String filmDuzenle() {
		Film film=new Film();
		film.setFilAdi(this.filAdi);
		film.setFilmTur(this.filmTur);
		film.setAciklama(this.aciklama);
		film.setMedya(this.medya);
		film.setYayinYili(this.yayinYili);
		String durum=dao.filmDuzenle(this.filmId, film);
		return durum;
	}
	@Override
	public void filmSil(int id) {
		dao.filmSil(id);
		
	}
	@Override
	public String filmDuzenleSayfasi(Film film) {
		filmId=film.getFilmId();
		filAdi=film.getFilAdi();
		yayinYili=film.getYayinYili();
		filmTur=film.getFilmTur();
		aciklama=film.getAciklama();
		medya=film.getMedya();
		
		return "duzenle";
	}
	@Override
	public void filmDetay(Film film) {
		filmId=film.getFilmId();
		filAdi=film.getFilAdi();
		yayinYili=film.getYayinYili();
		filmTur=film.getFilmTur();
		aciklama=film.getAciklama();
		medya=film.getMedya();
		
		detay=!detay;
	}
	
	@Override
	public ArrayList<Film> filmAraN() {
		return (ArrayList<Film>) dao.filmAraN(filAdi);
	}
	@Override
	public ArrayList<Film> filmAraT() {
		return (ArrayList<Film>) dao.filmAraT(filmTur);
	}
	//-----------------------------------------------------
	public boolean IsmeGoreAra,filmListele,tureGoreAra;
	
	public boolean isIsmeGoreAra() {
		return IsmeGoreAra;
	}
	public void setIsmeGoreAra(boolean ismeGoreAra) {
		IsmeGoreAra = ismeGoreAra;
	}
	public boolean isFilmListele() {
		return filmListele;
	}
	public void setFilmListele(boolean filmListele) {
		this.filmListele = filmListele;
	}
	public boolean isTureGoreAra() {
		return tureGoreAra;
	}
	public void setTureGoreAra(boolean tureGoreAra) {
		this.tureGoreAra = tureGoreAra;
	}
	@Override
	public void filmListele() {
		filmListele=true;
		IsmeGoreAra=false;
		tureGoreAra=false;
	}
	@Override
	public void ismeGoreAra() {
		filmListele=false;
		IsmeGoreAra=true;
		tureGoreAra=false;
	}
	@Override
	public void tureGoreAra() {
		filmListele=false;
		IsmeGoreAra=false;
		tureGoreAra=true;
	}
	
	
	
}
