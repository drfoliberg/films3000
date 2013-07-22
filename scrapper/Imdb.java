package scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Imdb {

	public static String[] scrapeDurees(String idImdb) {
		String [] runtimes = new String [0];
		try {
			Document doc = Jsoup.connect("http://www.imdb.com/title/" + idImdb + "/technical").userAgent("Mozilla").get();
			Elements tr = doc.getElementsByTag("tr");
			for (Element e : tr) {
				Elements enfants = e.children();
				if(enfants.size() > 1 && enfants.get(0).text().equalsIgnoreCase("Runtime")){
					String runtimesString = enfants.get(1).html();
					runtimes = runtimesString.split("<br />");
					break;
				}			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return runtimes;

	}
}
