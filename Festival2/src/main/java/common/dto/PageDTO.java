package common.dto;

public class PageDTO {	
	private String search, keyword;
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	private int totalList; //총목록수: setter, getter
	public int getTotalList() {
		return totalList;
	}
//	448: 한페이지에 10건씩 표현
//        각 페이지에 보여지는 페이지번호 10개씩 표현	
//	전체 페이지수: 45
	private int pageList = 10; //한 페이지당 보여질 목록수
	private int blockPage = 10; //한 블럭당 보여질 페이지수
	
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getBlockPage() {
		return blockPage;
	}
	private int totalPage; //총페이지수
	
	
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		totalBlock = totalPage / blockPage + (totalPage % blockPage==0 ?  0 : 1);
		setCurBlock();
	}
	public int getTotalPage() {
		return totalPage;
	}
	private int totalBlock; //총블럭수
	//총 목록수에 따라 총페이지수가 결정됨
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		
		//총페이지수: 448 / 10 = 44 ... 8,  440 / 10 = 44 ..0 
		totalPage = totalList / pageList + (totalList % pageList==0 ? 0 : 1);
		
		//총블럭: 1...10 , 11..20, 21..30, 31..40, 41..45
		//: 45 / 10 = 4 ...5 --> 5블럭,  40 / 10 = 4 ...0 --> 4블럭
		totalBlock = totalPage / blockPage + (totalPage % blockPage==0 ?  0 : 1);
		setCurBlock();
	}
	
	private int curPage; //현재페이지번호
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	private int curBlock; //현재블럭번호: 페이지에 따라 결정됨
	public int getCurBlock() {
		return curBlock;
	}
	// 각 블럭마다 보여지는 페이지번호가 다르다.
	private int beginPage, endPage; // 시작페이지번호, 끝페이지번호
	
	public int getBeginPage() {
		return beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setCurBlock() {
		//1블럭: 1...10 , 2블럭: 11..20, 3블럭: 21..30, 31..40, 41..45
		//현재블럭번호: 23 -> 3블럭
		curBlock = curPage / blockPage;
		if( curPage % blockPage >0 ) ++curBlock;
		
//		1: 1...10   2: 11..20  3: 21..30  4: 31..40  5: 41..50
//		1: 1...5   2: 6..10  3: 11..15
		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage-1);
		
		// 계산된 끝페이지번호가 총페이지수보다 크다면
		// 총페이지수가 끝페이지번호가 되게 한다.
		if( endPage>totalPage) endPage = totalPage;
		
		// 각 페이지에 따라 보여지는 시작글과 끝글번호
		// 1페이지: 448~439,  2페이지: 438~429, 3페이지: 428~419, ..
		// 끝글: 448, 438, 428, .. -> 총글수 - (페이지번호-1) * 페이지당보여질목록수
		// 시작글: 439, 429, 419, ...
		endList = totalList - (curPage-1)*pageList;
		beginList = endList - (pageList-1);
	}
	private int beginList, endList;// 시작글과 끝글번호
	public int getBeginList() {
		return beginList;
	}
	public int getEndList() {
		return endList;
	}
	
	
}
