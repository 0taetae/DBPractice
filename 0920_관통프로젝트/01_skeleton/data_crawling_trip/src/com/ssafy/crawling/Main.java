package com.ssafy.crawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"https://apis.data.go.kr/B551011/KorService1/areaBasedList1");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey=", "UTF-8")
				+ "------------------------- 공공 데이터 서비스 키 입력 -------------------------");
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("A", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); // 관광지
		urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("6", "UTF-8")); // 부산
		urlBuilder.append("&" + URLEncoder.encode("sigunguCode", "UTF-8") + "=" + URLEncoder.encode("16", "UTF-8")); // 해운대구
		System.out.println(urlBuilder.toString());
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
			NodeList nodeList = document.getElementsByTagName("item");
			System.out.println("관광지 count : " + nodeList.getLength());
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);// 첫번째 element 얻기  
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) node;
					String title = eElement.getElementsByTagName("title").item(0).getTextContent();
					System.out.println("-------------------------- " + title + " --------------------------");
					System.out.println("관광지 코드: " + eElement.getElementsByTagName("contentid").item(0).getTextContent());
					System.out.println("지역코드: " + eElement.getElementsByTagName("sigungucode").item(0).getTextContent());
					System.out.println("관광지 타입: " + eElement.getElementsByTagName("contenttypeid").item(0).getTextContent());
					System.out.println("대표 이미지: " + eElement.getElementsByTagName("firstimage").item(0).getTextContent());
					System.out.println("위도: " + eElement.getElementsByTagName("mapy").item(0).getTextContent());
					System.out.println("경도: " + eElement.getElementsByTagName("mapx").item(0).getTextContent());
					System.out.println("주소: " + eElement.getElementsByTagName("addr1").item(0).getTextContent() + " " + eElement.getElementsByTagName("addr2").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}
