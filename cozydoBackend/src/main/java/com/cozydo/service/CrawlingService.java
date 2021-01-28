package com.cozydo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozydo.dao.CrawlingDao;
import com.cozydo.model.place.Coronalevel;

@Service
public class CrawlingService {
	@Autowired
	private CrawlingDao crawlingDao;
	
	public void saveAll() throws IOException {
		List<Coronalevel> list = new ArrayList<Coronalevel>();

		String url = "http://ncov.mohw.go.kr/";
		Document document = Jsoup.connect(url).get();// 해당 URL에 연결
		Elements p_name = document.getElementsByClass("rssm_graph").select("button"); // class이름으로 긁기
		Elements p_price = document.getElementsByClass("catalog-price");

		// 끝날날짜
		String img_alt = document.getElementsByClass("t_dp_n m_dp_n").select("img").attr("alt"); // class이름으로 긁기
		String str[] = new String[4];
		str = img_alt.split(",");
		String strenddate = str[3].replaceAll("[^0-9]", "");
////	Date date = new Date();
////	SimpleDateFormat sdf = new SimpleDateFormat("Md", Locale.KOREA);
////	String today = sdf.format(date);
////	Date startDate = sdf.parse(today);
////	Date endDate = sdf.parse(strenddate);
////	long diffday = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
////	System.out.println(diffday + "일");

		// 지역별 단계
		String[] arr = new String[15];
		arr = p_name.text().split(" ");

		for (String a : arr) {
			String si = a.substring(0, 2);
			String level = a.substring(2);
			list.add(new Coronalevel(si, level, strenddate));
		}
		System.out.println(list.get(0));
	}
}
