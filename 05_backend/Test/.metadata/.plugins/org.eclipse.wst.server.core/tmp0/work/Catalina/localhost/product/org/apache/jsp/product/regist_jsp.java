/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.18
 * Generated at: 2024-03-21 08:11:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import com.ssafy.member.model.dto.MemberDto;

public final class regist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/lib/jakarta.servlet.jsp.jstl-3.0.1.jar", Long.valueOf(1707789474000L));
    _jspx_dependants.put("jar:file:/C:/00.%20Test/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WEB_TEST/WEB-INF/lib/jakarta.servlet.jsp.jstl-3.0.1.jar!/META-INF/c.tld", Long.valueOf(1664421078000L));
    _jspx_dependants.put("/nav.jsp", Long.valueOf(1711008014862L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.ssafy.member.model.dto.MemberDto");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
 
	String root= request.getContextPath();
	application.setAttribute("root", root);

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>상품 등록</title>\r\n");
      out.write("<style>\r\n");
      out.write("nav {\r\n");
      out.write("	display: flex;\r\n");
      out.write("	justify-content: space-between;\r\n");
      out.write("	align-items: center\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("nav a {\r\n");
      out.write("	margin-right: 10px\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<nav>\r\n");
      out.write("		<h2>상품 정보 등록</h2>\r\n");
      out.write("		<!-- 로그인 전/후 메뉴 조각 페이지 삽입 -->\r\n");
      out.write("		");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- jstl 사용 위한 선언 -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 로그인 전 메뉴 구성 -->\r\n");

	MemberDto memberInfo=(MemberDto) session.getAttribute("memberInfo");
	if(memberInfo==null){
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<a href=\"");
      out.print(application.getAttribute("root"));
      out.write("/member?action=mvlogin\">로그인</a>\r\n");
      out.write("\r\n");

	} else{

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 로그인 후 메뉴 구성 -->\r\n");
      out.write("<div>\r\n");
      out.write("	<span> ");
      out.print(memberInfo.getUserName() );
      out.write("님 로그인 중</span> \r\n");
      out.write("	<a href=\"");
      out.print(application.getAttribute("root"));
      out.write("/member?action=logout\">로그아웃</a>\r\n");
      out.write("	<a href=\"");
      out.print(application.getAttribute("root"));
      out.write("/product?action=list\">상품목록</a>\r\n");
      out.write("	<a href=\"");
      out.print(application.getAttribute("root"));
      out.write("/product?action=mvregist\">상품등록</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");

	}

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("		\r\n");
      out.write("	</nav>\r\n");
      out.write("\r\n");
      out.write("	<form id=\"registForm\" method=\"POST\" action=\"");
      out.print(application.getAttribute("root"));
      out.write("/product?action=regist\" >\r\n");
      out.write("		<input type=\"hidden\" name=\"action\" value=\"regist\">\r\n");
      out.write("		<div>\r\n");
      out.write("			<label for=\"productCode\">고유번호</label> <input type=\"text\"\r\n");
      out.write("				id=\"productCode\" name=\"code\" placeholder=\"고유번호 입력\">\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<label for=\"model\">모델명</label> <input type=\"text\" id=\"model\"\r\n");
      out.write("				name=\"model\" placeholder=\"모델명 입력\">\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<label for=\"price\">가격</label> <input type=\"number\" id=\"price\"\r\n");
      out.write("				name=\"price\" placeholder=\"가격 입력\">\r\n");
      out.write("		</div>\r\n");
      out.write("\r\n");
      out.write("		<button type=\"submit\" id=\"regist\">등록</button>\r\n");
      out.write("		<a href=\"#\">취소</a>\r\n");
      out.write("	</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
