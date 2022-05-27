<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.sql.*,java.util.*,java.text.*"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!-- DB 연결 설정 -->
<%@ include file="dbconn_mysql.jsp"%>


<%
String fileurl = application.getRealPath("upload");
/* 경로 확인
out.println("이클립스의 물리적인 경로 : " + fileurl + "<p><p>");
String upload = application.getRealPath("upload"); 
out.println("이클립스 톰캣의 물리적인 경로 : " + upload);

if (true) return;
*/

String saveFolder = "upload";
String encType = "UTF-8";
int Maxsize = 5 * 1024 * 1024 * 1024;		// 최대 업로드 용량 : 5 GB

ServletContext context = getServletContext();
MultipartRequest multi = null;
// 파일 업로드 정책 객체
	// 업로드 폴더에 동일한 이름의 파일이 존재할 경우, 파일이름 뒤에 번호를 할당해서 업로드
DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
multi = new MultipartRequest(request, fileurl, Maxsize, encType, policy);

String wn = multi.getParameter("wname");
String cat = multi.getParameter("category");
String pn = multi.getParameter("pname");
String sn = multi.getParameter("sname");
int price = Integer.parseInt(multi.getParameter("price"));
int dprice = Integer.parseInt(multi.getParameter("dprice"));
int stock = Integer.parseInt(multi.getParameter("stock"));
String des = multi.getParameter("description");

/* 데이터 확인
out.println(wn);
out.println(cat);
out.println(pn);
out.println(sn);
out.println(price);
out.println(dprice);
out.println(stock);
out.println(des);

if (true) return;
*/

long id = 0;			// 상품 고유 번호 할당
int pos = 0;

// 상품 설명이 들어간 컬럼에 '(작은따옴표)가 들어가면 DB 저장 시 오류가 발생된다.
	// 오류 처리
while ((pos = des.indexOf("\'", pos)) != -1) {
	String left = des.substring(0, pos);
	String right = des.substring(pos, des.length());
	des = left + "\'" + right;
	pos += 2;
}

java.util.Date yymmdd = new java.util.Date();
SimpleDateFormat myformat = new SimpleDateFormat("yy-MM-d h:mm a");

String ymd = myformat.format(yymmdd);

String sql = null;
//Connection con=null;
Statement st = null;
ResultSet rs = null;
int cnt = 0;

try {

	st = conn.createStatement();
	
	// DB의 category 컬럼을 조건으로 레코드의 총 갯수 select => 페이징 처이를 위해
	sql = "select max(id) from product where category= '" + cat + "'";

	rs = st.executeQuery(sql);
	rs.next();
	id = rs.getLong(1);

	// 가져온 레코드가 0. 즉 category 컬럼에 상품이 존재하지 않는 경우
	if (id == 0)
		id = Integer.parseInt(cat + "00001");
	else
		id = id + 1;
	
	
	Enumeration files = multi.getFileNames();	// 첨부 파일 2개의 Name을 한꺼번에 가져온다.
	String fname1 = (String) files.nextElement();
	String filename1 = multi.getFilesystemName(fname1);
	String fname2 = (String) files.nextElement();
	String filename2 = multi.getFilesystemName(fname2);

	if (filename2 == null)
		filename2 = filename1;
	sql = "insert into product(id,category,wname,pname,sname,price,downprice";
	sql = sql + ",inputdate,stock,small,large,description) values(" + id + ",'";
	sql = sql + cat + "','" + wn + "','" + pn + "','" + sn + "'," + price + "," + dprice + ",'" + ymd;
	sql = sql + "'," + stock + ",'" + filename2 + "','" + filename1 + "','" + des + "')";

	cnt = st.executeUpdate(sql);

	if (cnt > 0)
		out.println("상품을 등록했습니다.");
	else
		out.println("상품이 등록되지 않았습니다. ");

	st.close();
	conn.close();

} catch (SQLException e) {
	out.println(e);
}
%>
<P>
	<A href="product_list.jsp">[상품 목록으로]</A> &nbsp; <A
		href="product_write.htm">[상품 올리는 곳으로]</A>