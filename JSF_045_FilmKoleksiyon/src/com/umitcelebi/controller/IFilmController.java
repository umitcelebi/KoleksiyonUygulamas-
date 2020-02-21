package com.umitcelebi.controller;

import java.util.ArrayList;

import com.umitcelebi.model.Film;

public interface IFilmController {
	public void FilmEkle();
	public ArrayList<Film> filmler();
	public String filmDuzenle();
	public String filmDuzenleSayfasi(Film film);
	public void filmDetay(Film film);
	public void filmSil(int id);
	public ArrayList<Film>filmAraN();
	public ArrayList<Film>filmAraT();
	public void filmListele();
	public void ismeGoreAra();
	public void tureGoreAra();
}
