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
	private int totalList; //�Ѹ�ϼ�: setter, getter
	public int getTotalList() {
		return totalList;
	}
//	448: ���������� 10�Ǿ� ǥ��
//        �� �������� �������� ��������ȣ 10���� ǥ��	
//	��ü ��������: 45
	private int pageList = 10; //�� �������� ������ ��ϼ�
	private int blockPage = 10; //�� ���� ������ ��������
	
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getBlockPage() {
		return blockPage;
	}
	private int totalPage; //����������
	
	
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
	private int totalBlock; //�Ѻ���
	//�� ��ϼ��� ���� ������������ ������
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		
		//����������: 448 / 10 = 44 ... 8,  440 / 10 = 44 ..0 
		totalPage = totalList / pageList + (totalList % pageList==0 ? 0 : 1);
		
		//�Ѻ�: 1...10 , 11..20, 21..30, 31..40, 41..45
		//: 45 / 10 = 4 ...5 --> 5��,  40 / 10 = 4 ...0 --> 4��
		totalBlock = totalPage / blockPage + (totalPage % blockPage==0 ?  0 : 1);
		setCurBlock();
	}
	
	private int curPage; //������������ȣ
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	private int curBlock; //�������ȣ: �������� ���� ������
	public int getCurBlock() {
		return curBlock;
	}
	// �� ������ �������� ��������ȣ�� �ٸ���.
	private int beginPage, endPage; // ������������ȣ, ����������ȣ
	
	public int getBeginPage() {
		return beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setCurBlock() {
		//1��: 1...10 , 2��: 11..20, 3��: 21..30, 31..40, 41..45
		//�������ȣ: 23 -> 3��
		curBlock = curPage / blockPage;
		if( curPage % blockPage >0 ) ++curBlock;
		
//		1: 1...10   2: 11..20  3: 21..30  4: 31..40  5: 41..50
//		1: 1...5   2: 6..10  3: 11..15
		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage-1);
		
		// ���� ����������ȣ�� �������������� ũ�ٸ�
		// ������������ ����������ȣ�� �ǰ� �Ѵ�.
		if( endPage>totalPage) endPage = totalPage;
		
		// �� �������� ���� �������� ���۱۰� ���۹�ȣ
		// 1������: 448~439,  2������: 438~429, 3������: 428~419, ..
		// ����: 448, 438, 428, .. -> �ѱۼ� - (��������ȣ-1) * �������纸������ϼ�
		// ���۱�: 439, 429, 419, ...
		endList = totalList - (curPage-1)*pageList;
		beginList = endList - (pageList-1);
	}
	private int beginList, endList;// ���۱۰� ���۹�ȣ
	public int getBeginList() {
		return beginList;
	}
	public int getEndList() {
		return endList;
	}
	
	
}
