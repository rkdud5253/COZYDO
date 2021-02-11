package com.cozydo.corona.crawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cozydo.dao.CoronaApiDao;
import com.cozydo.model.crawling.CoronaInfomation;

@Component
public class CoronaApi implements CoronaApiDao {

	@Override
	public List<ArrayList<String[]>> Classification() {
		String eve = "";
		eve = "-8";
		try {
			return Week(Confirmed(eve));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<ArrayList<String[]>> Week(List<String[]> infomation) {
		List<ArrayList<String[]>> list = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> today = new ArrayList<String[]>();
		ArrayList<String[]> sido = new ArrayList<String[]>();
		ArrayList<String[]> week = new ArrayList<String[]>();

		for (int i = 0; i < 38; i++) {
			if (infomation.get(i)[0].equals("합계")) {
				today.add(infomation.get(i));
			} else if (!infomation.get(i)[0].equals("합계"))
				sido.add(infomation.get(i));
		}

		for (int i = 0; i < infomation.size(); i++) {
			if (infomation.get(i)[0].equals("합계")) {
				week.add(infomation.get(i));
			}
		}
		list.add(today);
		list.add(sido);
		list.add(week);
		return list;
	}

	private List<String[]> Confirmed(String eve) throws ParserConfigurationException, SAXException, IOException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(cal.getTime());

		cal.add(Calendar.DATE, Integer.parseInt(eve));
		String otherDay = df.format(cal.getTime());

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /* URL */
		String serviceKey = "aXJ8BHN7sNEi101DUimfRvfH%2FRD73I9Fy9YUIyXtKDJO24g9N1IcSoz6mhiXiwVxxH7o0Lh7pxg5f3%2FPmkeYbw%3D%3D";
		String serviceKeyDecoded = URLDecoder.decode(serviceKey, "UTF-8");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
				+ URLEncoder.encode(serviceKeyDecoded, "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("startCreateDt", "UTF-8") + "="
				+ URLEncoder.encode(otherDay, "UTF-8")); /* 검색할 생성일 범위의 시작 */
		urlBuilder.append("&" + URLEncoder.encode("endCreateDt", "UTF-8") + "="
				+ URLEncoder.encode(today, "UTF-8")); /* 검색할 생성일 범위의 종료 */
		String allurl = urlBuilder.toString();
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(allurl);

		// root tag
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("item");
		List<String[]> list = new ArrayList<String[]>();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String[] day = getTagValue("createDt", eElement).split(" ");

				list.add(new String[] { getTagValue("gubun", eElement), day[0], getTagValue("deathCnt", eElement),
						getTagValue("defCnt", eElement), getTagValue("incDec", eElement),
						getTagValue("isolClearCnt", eElement), getTagValue("localOccCnt", eElement),
						getTagValue("overFlowCnt", eElement) });
			}
		}
		if (list.size() == 0)
			return Confirmed(eve);
		return list;
	}

	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}
}
