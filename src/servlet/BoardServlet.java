package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Board;
import service.BoardService;

public class BoardServlet extends HttpServlet {

	private BoardService service;

	public BoardServlet() {
		service = new BoardService(); // �����ڿ��� ���� ��ü ����
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req, resp);
	}

	protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���⼭ ��� ó��
		// ���ڵ� ó��
		req.setCharacterEncoding("utf-8");

		// ����¡ ó��
		/*
		 * ó���ؾ��� ��û /write : �޼��� �ۼ� /messageList : �޽��� ��� ȭ�� ��û /pwCheck :
		 * ��й�ȣ�� Ȯ�����ִ� ��û
		 * 
		 */

		String contextPath = req.getContextPath(); // �� ���ø����̼��� �⺻�ּ�
		String requestURI = req.getRequestURI(); // ��û�� ���� �ּ�

		// �⺻ �ּ� + ��û = ��û�� ���� �ּ�
		if (requestURI.equals(contextPath + "/write")) {
			// �޼��� �ۼ� ��û
			// form ���� �Է��� �� �Ķ���ͷ� ����
			String name = req.getParameter("guestName");
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			Board mes = new Board();
			mes.setName(name);
			mes.setTitle(title);
			mes.setContent(content);

			// ������ �޽��� ���� ��� ����
			boolean result = service.writeMessage(mes);

			if (result == true) {
				req.setAttribute("msg", "작성 완료했습니다.");
			} else {
				req.setAttribute("msg", "실패다 인마.");
			}

			req.setAttribute("url", "messageList");
			req.getRequestDispatcher("result.jsp").forward(req, resp);
		} else if (requestURI.equals(contextPath + "/messageList")) {
			// �޼��� ��� ȭ�� ��û
			// List<Message> messageList = service.getAllMessages();

			// request�� �Ӽ��� messageList ���
			// �� request �� �㳪��? ��û �ѹ��� ó���ϰ� �����͸� ���ǿ�
			// ������ �ʿ䰡 ���� ������
			// req.setAttribute("messageList", messageList );

			// ��û ������ ��� �̾� ���� ���̹Ƿ� forward ���
			// req.getRequestDispatcher("messageList.jsp").forward(req, resp);

			// classNotFound exception : servlet ? jdbc oracle ?
			// servlet => servlet Ŭ���� ������ ��ã�°�. => web.xml ��Ÿ
			// jdbc oracle => ConnectionProvider, WebContent/WEB-INF/lib ���� ��
			// ojdbc ���̺귯�� �־��ᳪ Ȯ��

			// ������ ó�� ����
			int pageNumber = 1;
			String strPageNumber = req.getParameter("page");
			// �츮�� ���� ������ ������ ������ �ִ��� Ȯ��

			if (strPageNumber != null) {
				// �츮�� ���� ������ ������ ������ �ִ�.
				pageNumber = Integer.parseInt(strPageNumber);
			} // ������ ������ ���ٸ� �⺻���� 1�������� ���� �������� �ɰ��Դϴ�.

			// ������ ����� ���� �츮�� ���� ����־��� Map�� �����´�.
			Map<String, Object> viewData = service.getMessageList(pageNumber);

			req.setAttribute("viewData", viewData);
			req.getRequestDispatcher("messageList.jsp").forward(req, resp);

		} else if (requestURI.equals(contextPath + "/searchList")) {
			req.getRequestDispatcher("search.jsp").forward(req, resp);
		}

		else if (requestURI.equals(contextPath + "/search")) {

			String name = req.getParameter("name");

			int pageNumber = 1;
			String strPageNumber = req.getParameter("page");

			if (strPageNumber != null)
				pageNumber = Integer.parseInt(strPageNumber);

			Map<String, Object> viewData = service.getSearchMessageList(name, pageNumber);

			req.setAttribute("viewData", viewData);
			req.getRequestDispatcher("searchList.jsp").forward(req, resp);

		} else if (requestURI.equals(contextPath + "/confirmDelete")) {

			HttpSession session = req.getSession();

			String id = req.getParameter("id");
			String name = (String) req.getSession().getAttribute("userid");

			// ���ڿ��� int Ÿ������ �ٲٱ�
			int idnum = Integer.parseInt(id);
			System.out.println(idnum);
			// service�� ���� �޼��� ���� ��� ����
			boolean result = service.deleteMessage(idnum, name);

			System.out.println(name);
			// result.jsp �� ������ �޼���
			String msg = "니가 쓴거 아니잖아 인마.";
			if (result) {
				// ���� ������ result.jsp�� ������ �޽����� �ٲ��ָ� �ȴ�.
				msg = "삭제됐다 인마.";
			}
			// ��� ���������� �˶����� ����� ����
			req.setAttribute("msg", msg);
			// ��� ���������� �˶��� ���� �� �ڿ� �̵��� ������ �ּ�
			req.setAttribute("url", "messageList");

			// �� ����� result.jsp�� ���� �˷��ָ� �˴ϴ�.
			req.getRequestDispatcher("result.jsp").forward(req, resp);

		}

	}

}
