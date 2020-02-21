package com.umitcelebi.dao;

import java.util.List;

import com.umitcelebi.model.Film;

public interface FilmDao {
	public void FilmEkle(Film film);
	public List<Film> filmler();
	public String filmDuzenle(int id,Film film);
	public void filmSil(int id);
	public List<Film>filmAraN(String filmAdi);
	public List<Film> filmAraT(String filmTuru);
}
