package edu.cpp.cs580.data.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.airbrake.javabrake.Notifier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.cpp.cs580.data.GpsProduct;

public class EBayGpsProductManager implements GpsProductManager {

	public static int[] markers = new int[100];
	int projectId = 224063;
	String projectKey = "bfb372b2f7403b6fe4e8821340d25bc5";
	Notifier notifier = new Notifier(projectId, projectKey);
	@Override
	public List<GpsProduct> listGps() {
		List<GpsProduct> gpsProducts = new ArrayList<>();
		markers[0] = 1;
		Document doc;
		markers[1] = 1;
		try {
			doc = Jsoup.connect("http://www.ebay.com/sch/i.html?_from=R40&_trksid=p2050601.m570.l1313.TR0.TRC0.H0.Xgps.TRS0&_nkw=gps&_sacat=0").get();
			markers[2] = 1;

			// title
			Elements elements = doc.select("h3.lvtitle");
			markers[3] = 1;
			for(Element e : elements) {
				markers[4] = 1;
				System.out.println(e.text());
				markers[5] = 1;
			}

			// price
			Elements elements2 = doc.select("li.lvprice");
			markers[6] = 1;
			for(Element e : elements2) {
				System.out.println(e.text());
				markers[7] = 1;
			}

			// imgUrl
			Elements elements3 = doc.select("div.lvpicinner img");
			for(Element e : elements3) {
				System.out.println(e.attr("src"));
			}

			for(int i = 0; i < 10; i++) {
				GpsProduct g = new GpsProduct(
						elements.get().text(),
						elements2.get(i).text(),
						elements3.get(i).attr("src"));
				gpsProducts.add(g);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			notifier.report(e1);
			markers[10] = 1;
		}

		return gpsProducts;
	}

}
