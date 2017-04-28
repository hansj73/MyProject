package OpenApi_org;

public class DataApiDto {

	/** 지역정보 리스트***/
	String numOfRows="";	/*한 페이지 결과 수	한 페이지 결과 수*/
	String pageNo="";	/*페이지 번호	현재 페이지 번호*/
	String totalCount="";	/*전체 결과 수	전체 결과 수*/
	String addr1="";	/*주소	주소(예, 서울 중구 다동)를 응답*/
	String addr2="";	/*상세주소	상세주소*/
	String areacode="";	/*지역코드	지역코드*/
	String booktour="";	/*교과서 여행지 여부	교과서 속 여행지 여부 (1=여행지, 0=해당없음)*/
	String cat1="";	/*대분류	대분류 코드*/
	String cat2="";	/*중분류	중분류 코드*/
	String cat3="";	/*소분류	소분류 코드*/
	String contentid="";	/*콘텐츠ID	콘텐츠ID*/
	String contenttypeid="";	/*콘텐츠타입ID	관광타입(관광지, 숙박 등) ID*/
	String createdtime="";	/*등록일	콘텐츠 최초 등록일*/
	String firstimage="";	/*대표이미지(원본)	원본 대표이미지(약 500*333 size) URL 응답*/
	String firstimage2="";	/*대표이미지(썸네일)	썸네일 대표이미지(약 150*100 size) URL 응답*/
	String mapx="";	/*GPS X좌표	GPS X좌표(WGS84 경도 좌표) 응답*/
	String mapy="";	/*GPS Y좌표	GPS Y좌표(WGS84 위도 좌표) 응답*/
	String mlevel="";	/*Map Level	Map Level 응답*/
	String modifiedtime="";	/*수정일	콘텐츠 수정일*/
	int readcount=0;	/*조회수	콘텐츠 조회수(korean.visitkorea.or.kr 웹사이트 기준)*/
	String sigungucode="";	/*시군구코드	시군구코드*/
	String tel="";	/*전화번호	전화번호*/
	String title="";	/*제목	콘텐츠 제목*/
	String zipcode=""; /*우편번호*/
	
	public String getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(String numOfRows) {
		this.numOfRows = numOfRows;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getBooktour() {
		return booktour;
	}
	public void setBooktour(String booktour) {
		this.booktour = booktour;
	}
	public String getCat1() {
		return cat1;
	}
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}
	public String getCat2() {
		return cat2;
	}
	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}
	public String getCat3() {
		return cat3;
	}
	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getContenttypeid() {
		return contenttypeid;
	}
	public void setContenttypeid(String contenttypeid) {
		this.contenttypeid = contenttypeid;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getFirstimage() {
		return firstimage;
	}
	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
	public String getFirstimage2() {
		return firstimage2;
	}
	public void setFirstimage2(String firstimage2) {
		this.firstimage2 = firstimage2;
	}
	public String getMapx() {
		return mapx;
	}
	public void setMapx(String mapx) {
		this.mapx = mapx;
	}
	public String getMapy() {
		return mapy;
	}
	public void setMapy(String mapy) {
		this.mapy = mapy;
	}
	public String getMlevel() {
		return mlevel;
	}
	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}
	public String getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(String sigungucode) {
		this.sigungucode = sigungucode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
