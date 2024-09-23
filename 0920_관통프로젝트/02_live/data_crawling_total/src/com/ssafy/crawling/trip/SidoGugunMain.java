package com.ssafy.crawling.trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SidoGugunMain {
	
//	관광지 타입
//	insert into contenttypes (content_type_id, content_type_name)
//	values (12, '관광지'), (14, '문화시설'), (15, '축제공연행사'), (25, '여행코스'), (28, '레포츠'), (32, '숙박'), (38, '쇼핑'), (39, '음식점');
//
//	commit;

	private static final String dataApiUrl = "https://apis.data.go.kr/B551011/KorService1/areaCode1";
	private static final String dataApiKey = "XqzbfhNXUSSyTQ0%2FK0PlrPpOhi%2BnZqAp3LP7guL1Djh9cYxsubBJJpWMmZ8EttAYZneUdg9GfBAdj3wzglcR7g%3D%3D";

//	공통 파라미터
	private static final String numOfRows = "50";
	private static final String pageNo = "1";
	private static final String MobileOS = "ETC";
	private static final String MobileApp = "AppTest";
	private static final String _type = "xml";
//	private static String areaCode = "";
	
	public static void main(String[] args)  throws IOException {
		
		List<SidoGugunDto> sidoList = new ArrayList<>();
		List<SidoGugunDto> gugunList = new ArrayList<>();
		
//		시도 정보.
		StringBuilder dataUrlBuilder = new StringBuilder(dataApiUrl).append("?serviceKey=").append(dataApiKey)
				.append("&pageNo=").append(URLEncoder.encode(pageNo, "UTF-8")).append("&numOfRows=")
				.append(URLEncoder.encode(numOfRows, "UTF-8")).append("&MobileOS=")
				.append(URLEncoder.encode(MobileOS, "UTF-8")).append("&MobileApp=")
				.append(URLEncoder.encode(MobileApp, "UTF-8")).append("&_type=")
				.append(URLEncoder.encode(_type, "UTF-8"));

		URL sidoUrl = new URL(dataUrlBuilder.toString());
		HttpURLConnection sidoConn = (HttpURLConnection) sidoUrl.openConnection();
		sidoConn.setRequestMethod("GET");
		sidoConn.setRequestProperty("Content-type", "application/json");

		BufferedReader sidoIn;
		if (sidoConn.getResponseCode() >= 200 && sidoConn.getResponseCode() <= 300) {
			sidoIn = new BufferedReader(new InputStreamReader(sidoConn.getInputStream()));
		} else {
			sidoIn = new BufferedReader(new InputStreamReader(sidoConn.getErrorStream()));
		}
		StringBuilder sidoSb = new StringBuilder();
		String sidoLine;
		while ((sidoLine = sidoIn.readLine()) != null) {
			sidoSb.append(sidoLine);
		}
		sidoIn.close();
		sidoConn.disconnect();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document sidoDocument = builder.parse(new InputSource(new StringReader(sidoSb.toString())));
			NodeList sidoNodeList = sidoDocument.getElementsByTagName("item");
			for (int i = 0; i < sidoNodeList.getLength(); i++) {

				SidoGugunDto sidoGugunDto = new SidoGugunDto();

				Node sidoNode = sidoNodeList.item(i);// 첫번째 element 얻기  
				if (sidoNode.getNodeType() == Node.ELEMENT_NODE) {

					Element sidoElement = (Element) sidoNode;
					sidoGugunDto.setSidoCode(Integer.parseInt(sidoElement.getElementsByTagName("code").item(0).getTextContent()));
					sidoGugunDto.setSidoName(sidoElement.getElementsByTagName("name").item(0).getTextContent());
					
					sidoList.add(sidoGugunDto);
				}
			}
			System.out.println(">>>> sido count : " + sidoList.size());
			AttractionDaoImpl.getAttractionDao().registSido(sidoList);
			
			for(SidoGugunDto sidoGugunDto: sidoList) {
				gugunList.clear();
				URL gugunUrl = new URL(dataUrlBuilder.toString() + "&areaCode=" + sidoGugunDto.getSidoCode());
				HttpURLConnection gugunConn = (HttpURLConnection) gugunUrl.openConnection();
				gugunConn.setRequestMethod("GET");
				gugunConn.setRequestProperty("Content-type", "application/json");

				BufferedReader gugunIn;
				if (gugunConn.getResponseCode() >= 200 && gugunConn.getResponseCode() <= 300) {
					gugunIn = new BufferedReader(new InputStreamReader(gugunConn.getInputStream()));
				} else {
					gugunIn = new BufferedReader(new InputStreamReader(gugunConn.getErrorStream()));
				}
				StringBuilder gugunSb = new StringBuilder();
				String gugunLine;
				while ((gugunLine = gugunIn.readLine()) != null) {
					gugunSb.append(gugunLine);
				}
				gugunIn.close();
				gugunConn.disconnect();
				
				Document gugunDocument = builder.parse(new InputSource(new StringReader(gugunSb.toString())));
				NodeList gugunNodeList = gugunDocument.getElementsByTagName("item");
				for (int i = 0; i < gugunNodeList.getLength(); i++) {

					SidoGugunDto gugunDto = new SidoGugunDto();

					Node gugunNode = gugunNodeList.item(i);// 첫번째 element 얻기  
					if (gugunNode.getNodeType() == Node.ELEMENT_NODE) {

						Element sidoElement = (Element) gugunNode;
						gugunDto.setGugunCode(Integer.parseInt(sidoElement.getElementsByTagName("code").item(0).getTextContent()));
						gugunDto.setGugunName(sidoElement.getElementsByTagName("name").item(0).getTextContent());
						gugunDto.setSidoCode(sidoGugunDto.getSidoCode());
						
						gugunList.add(gugunDto);
					}
				}
				System.out.println(">>>> sidocode : " + sidoGugunDto.getSidoCode() + "\tgugun count : " + gugunList.size());
				AttractionDaoImpl.getAttractionDao().registGugun(gugunList);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
}
