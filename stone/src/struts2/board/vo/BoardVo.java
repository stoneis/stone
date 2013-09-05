package struts2.board.vo;

/**
 * 게시판 모델 클래스 (스트럿츠2 용)
 * @since 2013.08.30
 * @author stoneis.pe.kr
 */
public class BoardVo {
	
	/** 번호 */
	private int num;
	/** 제목 */
	private String subject;
	/** 작성자 */
	private String writer;
	/** 내용 */
	private String contents;
	/** 아이피 */
	private String ip;
	/** 조회수 */
	private int hit = 0;
	/** 등록 일시 */
	private String regDate;
	/** 수정 일시 */
	private String modDate;
	/** 페이지 번호 */
	private String pageNum = "1";
	/** 검색 항목 */
	private String searchType = "";
	/** 검색어 */
	private String searchText = "";
	/** 목록 페이지 게시물 노출 수 */
	private int listCount = 10;
	/** 목록 페이지 네비게이터 블록 수 */
	private int pagePerBlock = 10;
	/** 목록 페이지 시작 인덱스 위치 */
	private int startIndex = 0;
	
	/**
	 * 생성자
	 */
	public BoardVo() { }

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getStartIndex() {
		startIndex = listCount * (Integer.parseInt(pageNum)-1);
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
}
