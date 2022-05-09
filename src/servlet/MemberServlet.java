package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import service.MemberService;

/**
 * Servlet implementation class MemberServlet
 */

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService service;

	public MemberServlet() {
		service = new MemberService();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getRequestURI());

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();

		if (uri.equals(contextPath + "/join")) {

			Member member = new Member();
			member.setId(request.getParameter("userid"));
			member.setPw(request.getParameter("userpw"));
			member.setEmail(request.getParameter("email"));
			member.setName(request.getParameter("name"));

			boolean rs = service.join(member);

			if (rs)
				response.sendRedirect("loginForm");
			else
				response.sendRedirect("joinForm");
		} else if (uri.equals(contextPath + "/loginForm")) {
			//
			request.getRequestDispatcher("loginForm.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/joinForm")) {
			request.getRequestDispatcher("joinForm.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/login")) {
			String id = request.getParameter("userid");
			String pw = request.getParameter("userpw");

			boolean rs = service.login(id, pw);

			String url = null;
			String msg = null;
			if (rs) {
				// 로그인 성공
				// 메인화면 보여주기
				msg = "로그인 성공!";
				url = "main";
				request.getSession().setAttribute("userid", id);
			} else {
				msg = "로그인 실패!";
				url = "loginForm";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);

			request.getRequestDispatcher("result.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/main")) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/memberList")) {
			java.util.List<Member> memberList = service.getAllMembers();
			request.setAttribute("memberList", memberList);
			request.getRequestDispatcher("memberList.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/modifyForm")) {
			String userid = (String) request.getSession().getAttribute("userid");
			Member member = service.getMember(userid);
			// 가져온 정보를 request에 담고
			request.setAttribute("member", member);
			// 그 request를 유지한 상태로 페이지 이동
			request.getRequestDispatcher("modifyForm.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("userid");
			response.sendRedirect("loginForm.jsp");
		} else if (uri.equals(contextPath + "/modify")) {

			Member member = new Member();
			member.setId(request.getParameter("userid"));
			member.setPw(request.getParameter("userpw"));
			member.setEmail(request.getParameter("email"));
			member.setName(request.getParameter("name"));

			boolean result = service.modify(member);

			String url = "main";
			String msg = null;
			if (result) {
				// 로그인 성공
				// 메인화면 보여주기
				msg = "변경 성공!";
			} else {
				msg = "변경 실패!";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);

			request.getRequestDispatcher("result.jsp").forward(request, response);
		}

	}

}
