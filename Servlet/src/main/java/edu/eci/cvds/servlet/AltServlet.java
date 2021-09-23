package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;
import java.awt.List;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	    urlPatterns = "/my_page"
	)

public class AltServlet extends HttpServlet{
	
	@Override
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       Writer responseWriter = resp.getWriter();
	       Optional<String> optId = Optional.ofNullable(req.getParameter("id"));
	       int id = optId.isPresent() && !optId.get().isEmpty() ? Integer.parseInt(optId.get()) : 0;

	       List<Todo> todo = new List<Todo>();
	       todo.add(Service.getTodo(id));
	       resp.setStatus(HttpServletResponse.SC_OK);
	       responseWriter.write(Service.todosToHTMLTable(todo));
	       responseWriter.flush();
	       
	   }
}