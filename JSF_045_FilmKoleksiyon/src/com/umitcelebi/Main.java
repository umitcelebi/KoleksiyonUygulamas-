package com.umitcelebi;

import com.umitcelebi.dao.impl.FilmDaoImpl;

public class Main {

	public static void main(String[] args) {
		//FilmController controller=new FilmController();
		//controller.setFilmId(1);
		//controller.setFilAdi("avanak kuzenler");
		//controller.filmDuzenle();
		
		FilmDaoImpl dao=new FilmDaoImpl();
		dao.filmler();
		
	}

}
