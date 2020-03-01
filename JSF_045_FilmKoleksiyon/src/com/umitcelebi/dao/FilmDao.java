package com.umitcelebi.dao;

import java.util.List;

import com.umitcelebi.model.Film;

public interface FilmDao {
	public List<Film>filmAraN(String filmAdi);
	public List<Film> filmAraT(String filmTuru);
	public List<Film> filmAraO(String oyuncuAdi);
	public List<Film> filmler();
	public Film filmTumBilgiler(int filmId);
	public Film fimAra(int filmId);
	
	public String filmDuzenle(int id,Film film);
	public void FilmEkle(Film film);
	public void filmSil(int id);
	public void filmOyuncuEkle(int filmId,int oyuncuId);
	public void filmOyuncuKaldir(int filmId,int oyuncuId);
}
