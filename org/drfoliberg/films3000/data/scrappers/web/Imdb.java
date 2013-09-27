package org.drfoliberg.films3000.data.scrappers.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.drfoliberg.films3000.models.movie.Duration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Imdb {

	public static ArrayList<Duration> scrapeDurees(String idImdb, int idTmdb) {
		String[] runtimes = new String[0];
		try {
			Document doc = Jsoup.connect("http://www.imdb.com/title/" + idImdb + "/technical").userAgent("Mozilla")
					.get();
			Elements tr = doc.getElementsByTag("tr");
			for (Element e : tr) {
				Elements enfants = e.children();
				if (enfants.size() > 1 && enfants.get(0).text().equalsIgnoreCase("Runtime")) {
					String runtimesString = enfants.get(1).html();
					runtimes = runtimesString.split("<br />");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int duree = 0;
		int idVersion = 0;
		String nom = "";
		Pattern p = Pattern.compile("\\([0-9]{2,3} min\\)");
		Pattern convertirInt = Pattern.compile("[0-9]{2,3}");
		ArrayList<Duration> durees = new ArrayList<>();

		for (String s : runtimes) {
			Matcher m = p.matcher(s);
			if (m.find()) {
				// duree en minutes
				String dureeStr = s.substring(m.start(), m.end());
				Matcher convertirIntMatcher = convertirInt.matcher(dureeStr);
				convertirIntMatcher.find();
				duree = Integer.parseInt(dureeStr.substring(convertirIntMatcher.start(), convertirIntMatcher.end()));
				// nom
				nom = s.substring(m.end()).trim().replace("(", "").replace(")", "");
				durees.add(new Duration(duree, nom, idTmdb, idVersion++));
			}
		}

		return durees;
	}
}
