package festival.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import common.dto.PageDTO;
import festival.dto.FestivalDTO;
import festival.dto.FestivalDetailDTO;
import festival.dto.FestivalPageDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class FestivalDAOImpl implements FestivalDAO {
	private InputStream is;
	private String result = "";
	private URL urlCon;
	private HttpURLConnection httpCon;
	private String imgURL;
	private JSONArray info;
	private JSONArray imgInfos;
	private JSONObject obj;
	private int totalPage = 0;
	private int rownum = 0;
	private ArrayList<String> imgItems;
	private ArrayList<FestivalDTO> listItem;
	private FestivalDTO dto1;
	private FestivalDetailDTO dto2;
	private String title;
	private String festivalUrl;
	private String key = "8ZblmapB3MlUUucjwZUIZ4rtgZ7TTfi0hVsB4VPOX8Rlkdpb6ZHNR0N18gB1RsL5BauDNrkvcsJzKpyu1JzlDA%3D%3D";
	private DateInfo dateInfo;
	private String lastDay;
	private String seasonEndDate;
	private String seasonStartDate;
	private String year;
	private String start;
	private String endMonth;
	private String startYear;
	private String endYear;
	private String month;
	private String startMonth;
	private String startFirstDay;
	private String contentId;
	@Autowired
	private PageDTO page;

	public FestivalDAOImpl() {
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalPage() {
		return totalPage;
	}

	@Override
	public FestivalPageDTO getFestivalInfo(int offset, int pageNum, FestivalPageDTO page, int season) {
		dateInfo = new DateInfo();
		listItem = new ArrayList<FestivalDTO>();
		festivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?serviceKey=" + key
				+ "&numOfRows=" + offset + "&pageSize=" + offset + "&pageNo=" + pageNum
				+ "&startPage=1&MobileOS=AND&MobileApp=FestivalProject&arrange=B&listYN=Y";
		if (season != 0) {
			switch (season) {
			case 1:
				year = dateInfo.getDateYear();
				start = "0301";
				endMonth = "05";
				if (Integer.valueOf(endMonth) < Integer.valueOf(dateInfo.getDateMonth())) {
					year = String.valueOf(Integer.valueOf(year) + 1);
				}
				lastDay = dateInfo.getMonthLastDay(year, endMonth);
				seasonStartDate = year + start;
				seasonEndDate = year + endMonth + lastDay;

				festivalUrl += "&eventStartDate=" + seasonStartDate + "&eventEndDate=" + seasonEndDate + "&_type=json";
				break;
			case 2:
				year = dateInfo.getDateYear();
				start = "0601";
				endMonth = "08";

				if (Integer.valueOf(endMonth) < Integer.valueOf(dateInfo.getDateMonth())) {
					year = String.valueOf(Integer.valueOf(year) + 1);
				}

				lastDay = dateInfo.getMonthLastDay(year, endMonth);
				seasonStartDate = year + start;
				seasonEndDate = year + endMonth + lastDay;

				festivalUrl += "&eventStartDate=" + seasonStartDate + "&eventEndDate=" + seasonEndDate + "&_type=json";
				break;
			case 3:
				year = dateInfo.getDateYear();
				start = "0901";
				endMonth = "11";

				if (Integer.valueOf(endMonth) < Integer.valueOf(dateInfo.getDateMonth())) {
					year = String.valueOf(Integer.valueOf(year) + 1);
				}

				lastDay = dateInfo.getMonthLastDay(year, endMonth);
				seasonStartDate = year + start;
				seasonEndDate = year + endMonth + lastDay;

				festivalUrl += "&eventStartDate=" + seasonStartDate + "&eventEndDate=" + seasonEndDate + "&_type=json";
				break;
			case 4:
				startYear = dateInfo.getDateYear(); // 현재 2018
				endYear = String.valueOf(Integer.valueOf(dateInfo.getDateYear()) + 1);// 현재
																						// 2018+1=
																						// 2019
				month = dateInfo.getDateMonth(); // 현재 2
				startMonth = "12";
				startFirstDay = "01";
				endMonth = "02";

				if (Integer.valueOf(startMonth) - 11 <= Integer.valueOf(month)) {
					startYear = String.valueOf(Integer.valueOf(startYear) - 1);// 2017
					endYear = dateInfo.getDateYear();
				}

				lastDay = dateInfo.getMonthLastDay(String.valueOf(Integer.valueOf(endYear)), endMonth);
				seasonStartDate = startYear + startMonth + startFirstDay;
				seasonEndDate = endYear + endMonth + lastDay;

				festivalUrl += "&eventStartDate=" + seasonStartDate + "&eventEndDate=" + seasonEndDate + "&_type=json";
				break;
			}
		} else {
			festivalUrl += "&eventStartDate=" + dateInfo.getDateString() + "&_type=json";
		}

		obj = JSONObject.fromObject(httpConnection(festivalUrl)).getJSONObject("response").getJSONObject("body");
		info = obj.getJSONObject("items").getJSONArray("item");
		rownum = Integer.parseInt(obj.get("numOfRows").toString());
		totalPage = (int) Math.ceil(Double.parseDouble(obj.getString("totalCount").toString()) / offset);

		if (page != null) {
			page.setPageList(offset);
			page.setBlockPage(7);
			page.setCurPage(pageNum);
			page.setTotalList(Integer.valueOf(obj.getString("totalCount").toString()));
		}

		for (int i = 0; i < info.size(); i++) {
			/* 타이틀 검사 */
			if (title != "" && title != null) {
				if (info.getJSONObject(i).getString("title").toString().contains(title)) {
					dto1 = new FestivalDTO(info.getJSONObject(i).getString("contentid").toString(),
							info.getJSONObject(i).getString("eventstartdate").toString(),
							info.getJSONObject(i).getString("eventenddate").toString(),
							info.getJSONObject(i).getString("title").toString());
					dto1.setImage("./img/default.jpg");
					dto1.setReadcount(info.getJSONObject(i).getString("readcount").toString());
					dto1.setTerm(dateInfo.getEventTerm(info.getJSONObject(i).getString("eventstartdate").toString(),
							info.getJSONObject(i).getString("eventenddate").toString()));
					if (info.getJSONObject(i).has("firstimage2")) {
						dto1.setImage(info.getJSONObject(i).getString("firstimage2").toString());
					}
					if (info.getJSONObject(i).has("addr1")) {
						dto1.setAddr1(info.getJSONObject(i).getString("addr1").toString());
						dto1.setMapx(info.getJSONObject(i).getString("mapx").toString());
						dto1.setMapy(info.getJSONObject(i).getString("mapy").toString());
					}
				} else {
					System.out.println("검색하신 단어의 정보가 없습니다. 오류");
				}
			} else {
				dto1 = new FestivalDTO(info.getJSONObject(i).getString("contentid").toString(),
						info.getJSONObject(i).getString("eventstartdate").toString(),
						info.getJSONObject(i).getString("eventenddate").toString(),
						info.getJSONObject(i).getString("title").toString());
				dto1.setImage("./img/default.jpg");
				dto1.setReadcount(info.getJSONObject(i).getString("readcount").toString());
				dto1.setTerm(dateInfo.getEventTerm(info.getJSONObject(i).getString("eventstartdate").toString(),
						info.getJSONObject(i).getString("eventenddate").toString()));
				if (info.getJSONObject(i).has("firstimage2")) {
					dto1.setImage(info.getJSONObject(i).getString("firstimage2").toString());
				} else if (dto1.getImage() == null) {
					try {
						dto1.setImage(ImgInfo(dto1.getcontentId()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (info.getJSONObject(i).has("addr1")) {
					dto1.setAddr1(info.getJSONObject(i).getString("addr1").toString());
					dto1.setMapx(info.getJSONObject(i).getString("mapx").toString());
					dto1.setMapy(info.getJSONObject(i).getString("mapy").toString());
				}
			}

			listItem.add(dto1);
		}
		page.setListItem(listItem);
		return page;
	}

	public String httpConnection(String url) {

		try {
			urlCon = new URL(url);
			httpCon = (HttpURLConnection) urlCon.openConnection();
			// InputStream으로 서버로 부터 응답을 받겠다는 옵션.
			httpCon.setDoInput(true);
			try {
				is = httpCon.getInputStream();
				if (is != null)
					result = convertInputStreamToString(is);
				else
					result = "Did not work!";
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				httpCon.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("InputStream" + e.getLocalizedMessage());
		}
		return result;
	}

	public ArrayList<String> DetailImgInfo(String contentId) {
		imgItems = new ArrayList<String>();
		imgURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?serviceKey=" + key
				+ "&numOfRows=10&pageSize=10&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId=" + contentId
				+ "&contentTypeId=15&imageYN=Y&_type=json";
		try {
			imgInfos = JSONObject.fromObject(httpConnection(imgURL)).getJSONObject("response").getJSONObject("body")
					.getJSONObject("items").getJSONArray("item");

			for (int i = 0; i < imgInfos.size(); i++) {
				imgItems.add(imgInfos.getJSONObject(i).getString("smallimageurl").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgItems;
	}

	public String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;
	}

	public String ImgInfo(String contentId) {
		imgURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?serviceKey=" + key
				+ "&numOfRows=10&pageSize=10&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId=" + contentId
				+ "&contentTypeId=15&imageYN=Y&_type=json";
		try {
			obj = JSONObject.fromObject(httpConnection(imgURL)).getJSONObject("response").getJSONObject("body")
					.getJSONObject("items").getJSONObject("item");
			if (obj.has("originimgurl")) {
				imgURL = obj.getString("originimgurl").toString();
			} else {
				imgURL = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgURL;
	}

	@Override
	public HashMap<String, Object> getFestivalDetailInfo(String contentId, int offset, int pageNum) {
		String detailInfolUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?serviceKey="
				+ key + "&numOfRows="+offset+"&pageSize="+offset+"&pageNo="+pageNum+"&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+contentId+"&contentTypeId=15&introYN=Y&_type=json";
		
		JSONObject obj = JSONObject.fromObject(httpConnection(detailInfolUrl)).getJSONObject("response")
				.getJSONObject("body").getJSONObject("items").getJSONObject("item");
		dto2 = new FestivalDetailDTO();
		if (obj.has("agelimit")) {
			dto2.setAgelimit(obj.getString("agelimit").toString());
		}
		if (obj.has("discountinfofestival")) {
			dto2.setDiscountinfofestival(obj.getString("discountinfofestival").toString());
		}
		if (obj.has("eventhomepage")) {
			dto2.setEventhomepage(obj.getString("eventhomepage").toString());
		}
		if (obj.has("eventplace")) {
			dto2.setEventplace(obj.getString("eventplace").toString());
		}
		if (obj.has("placeinfo")) {
			dto2.setPlaceinfo(obj.getString("placeinfo").toString());
		}
		if (obj.has("playtime")) {
			dto2.setPlaytime(obj.getString("playtime").toString());
		}
		if (obj.has("program")) {
			dto2.setProgram(obj.getString("program").toString());
		}
		if (obj.has("sponsor2")) {
			dto2.setSponsor2(obj.getString("sponsor2").toString());
			dto2.setSponsor2tel(obj.getString("sponsor2tel").toString());
		} else if (obj.has("sponsor1")) {
			dto2.setSponsor1(obj.getString("sponsor1").toString());
			dto2.setSponsor1tel(obj.getString("sponsor1tel").toString());
		}
		if (obj.has("subevent")) {
			dto2.setSubevent(obj.getString("subevent").toString());
		}
		if (obj.has("usetimefestival")) {
			dto2.setUsetimefestival(obj.getString("usetimefestival").toString());
		}

		ArrayList<String> list = DetailImgInfo(contentId);
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("dto1",dto1);
		datas.put("img_list", list);
		datas.put("dto2", dto2);

		return datas;
	}

	public class DateInfo {
		String str_date, start, end, term;
		SimpleDateFormat sdf;
		StringBuffer sb;

		public DateInfo() {
		}

		public String getDateFormatString(String date) {
			sb = new StringBuffer();
			sb.append(date);
			str_date = sb.insert(4, ".").insert(7, ".").toString();
			sb.setLength(0);
			return str_date;
		}

		public String getDateYear() {
			sdf = new SimpleDateFormat("yyyy", Locale.KOREA);
			return sdf.format(new Date());
		}

		public String getDateMonth() {
			sdf = new SimpleDateFormat("MM", Locale.KOREA);
			return sdf.format(new Date());
		}

		public String getMonthLastDay(String year, String month) {
			Calendar calendar = Calendar.getInstance();
			calendar.set((Integer.valueOf(year)), (Integer.valueOf(month) - 1), 1); // 월은
																					// 0부터
																					// 시작
			String lastDay = String.valueOf(calendar.getActualMaximum(Calendar.DATE));

			return lastDay;
		}

		public String getDateString() {
			sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			str_date = sdf.format(new Date());
			return str_date;
		}

		public String getEventTerm(String start_date, String end_date) {
			sb = new StringBuffer();
			sb.append(start_date);
			start = sb.insert(4, ".").insert(7, ".").toString();
			sb.setLength(0);
			sb.append(end_date);
			end = sb.insert(4, ".").insert(7, ".").toString();
			sb.setLength(0);
			term = start + " ~ " + end;
			return term;
		}
	}
}
