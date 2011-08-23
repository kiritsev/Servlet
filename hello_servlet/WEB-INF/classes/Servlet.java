import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

	private enum Method {
		POST, GET
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int a;
		int b;
		int res;
		PrintWriter out;
		String body;
		String name_a = "a";
		String name_b = "b";

		if (request.getParameter(name_a) == null
				|| request.getParameter(name_b) == null
				|| request.getParameter(name_a).equals("")
				|| request.getParameter(name_b).equals("")) {
			body = getFormHtml(Method.GET, name_a, name_b);
			body += getFormHtml(Method.POST, name_a, name_b);
		} else {
			a = Integer.valueOf(request.getParameter(name_a));
			b = Integer.valueOf(request.getParameter(name_b));
			body = getSumStr(a, b);
			body += getFormHtml(Method.GET, name_a, name_b);
			body += getFormHtml(Method.POST, name_a, name_b);
		}

		response.setContentType("text/html");
		out = response.getWriter();
		out.println(getLayout(body));
	}

	private String getSumStr(int... elems) {
		String result_str = "";
		int result_int = 0;
		int i;

		if (elems.length == 0) {

		} else if (elems.length == 1) {
			result_int = elems[0];
			result_str = String.valueOf(elems[0]);
		} else {
			for (i = 0; i < elems.length - 1; i++) {
				result_str += String.valueOf(elems[i]) + " + ";
				result_int += elems[i];
			}
			result_int += elems[i];
			result_str += String.valueOf(elems[i]) + " = " + result_int;
		}

		return result_str;
	}

	private String getFormHtml(Method method, String name_a, String name_b) {
		String result = "";
		result += "<form method='" + method + "' action='./calc'>";
		result += "	<input type='text' name='" + name_a + "'>";
		result += "	<input type='text' name='" + name_b + "'>";
		result += "	<input type='submit' value='" + method + "'>";
		result += "</form>";
		return result;
	}

	private String getLayout(String body) {
		String result = "";
		result += "<html>";
		result += "<head>";
		result += "<head>";
		result += "<body>";
		result += body;
		result += "</body>";
		result += "</head>";
		result += "</html>";
		return result;
	}
}
