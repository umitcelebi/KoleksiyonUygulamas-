package com.umitcelebi;

import com.umitcelebi.controller.impl.FilmController;

public class Main {

	public static void main(String[] args) {
		FilmController controller=new FilmController();
		controller.setFilmId(1);
		controller.setFilAdi("avanak kuzenler");
		controller.filmDuzenle();

	}

}
