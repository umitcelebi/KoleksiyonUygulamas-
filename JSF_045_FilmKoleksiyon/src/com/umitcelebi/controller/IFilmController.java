package com.umitcelebi.controller;

import java.util.ArrayList;
import com.umitcelebi.model.Film;

public interface IFilmController {
	public ArrayList<Film> filmler();
	public ArrayList<Film>filmAraN();
	public ArrayList<Film>filmAraT();
	public Film filmTumBilgiler(int ID);
	public String filmDuzenle();
	public String filmDuzenleSayfasi(Film film);
	public String cikisYap();
	public String anaSayfa();
	public void FilmEkle();
	public void filmDetay(Film film);
	public void filmSil(int id);
	
	public void filmOyuncuEkle();
	public void filmOyuncuKaldir();
	
	public void filmEkleF();
	public void oyuncuEkleF();
	public void ismeGoreAra();
	public void tureGoreAra();
}
