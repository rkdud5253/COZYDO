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
	public List<String[]> Classification(String Classification) {
		String eve = "";
		String serviceKey1 = "KNAn2%2FZ4AHleCgBLRpkWG%2BSaaTMYFaEgKcFVpxEOgG7oLurUhSyFNykiFfPti%2Bj3DWIJ%2BShGa7gELJTMcu5yvg%3D%3D";
		String serviceKey2 = "aXJ8BHN7sNEi101DUimfRvfH%2FRD73I9Fy9YUIyXtKDJO24g9N1IcSoz6mhiXiwVxxH7o0Lh7pxg5f3%2FPmkeYbw%3D%3D";
		String serviceKey3 = "O8U8oKgWVITgeL0tyopjnlko2qLn6oOq2PNPE%2BzKvy2u49xTmiFUku%2FbfZDebH8ev9Bsq9TaLQuYaEo9t3DyvA%3D%3D";
		if (Classification.equals("today")) { // 누적
			eve = "-2";
			try {
				return Today(Confirmed(eve, serviceKey1));
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (Classification.equals("sido")) {
			eve = "-2";
			try {
				return Sido(Confirmed(eve, serviceKey2));
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { // 일주일
			eve = "-8";
			try {
				return Week(Confirmed(eve, serviceKey3));
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private List<String[]> Today(List<String[]> infomation) {
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < 38; i++) {
			if (infomation.get(i)[0].equals("합계")) {
				list.add(infomation.get(i));
			}
		}
		return list;
	}

	private List<String[]> Sido(List<String[]> infomation) {
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < 38; i++) {
			if (infomation.get(i)[0].equals("합계")) {
				continue;
			}
			list.add(infomation.get(i));
		}
		return list;
	}

	private List<String[]> Week(List<String[]> infomation) {
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < infomation.size(); i++) {
			if (infomation.get(i)[0].equals("합계")) {
				list.add(infomation.get(i));
			}
		}
		return list;
	}

	private List<String[]> Confirmed(String eve, String serviceKey)
			throws ParserConfigurationException, SAXException, IOException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(cal.getTime());

		cal.add(Calendar.DATE, Integer.parseInt(eve));
		String otherDay = df.format(cal.getTime());

		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /* URL */
//		String serviceKey = "aXJ8BHN7sNEi101DUimfRvfH%2FRD73I9Fy9YUIyXtKDJO24g9N1IcSoz6mhiXiwVxxH7o0Lh7pxg5f3%2FPmkeYbw%3D%3D";
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
