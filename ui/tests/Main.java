package ui.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import films3000.Jozin;

public class Main {
	

	public static void main(String args[]) throws IOException, SQLException{
		Jozin jozin = new Jozin("20d2c5786283461e15f82c94a98cca17", "", new Date(),"en","images");
		Bazin frame = new Bazin(jozin);

		frame.setVisible(true);
	}
}
