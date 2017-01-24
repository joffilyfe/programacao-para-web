package br.edu.ifpb.memoriam.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Usuario;
import br.edu.ifpb.memoriam.facade.ContatoController;
import br.edu.ifpb.memoriam.facade.LoginController;
import br.edu.ifpb.memoriam.facade.OperadoraController;
import br.edu.ifpb.memoriam.facade.Resultado;

@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContatoController contatoCtrl = new ContatoController();
	private OperadoraController operadoraCtrl = new OperadoraController();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String proxima = null;

		this.getServletContext().removeAttribute("msgs");
		String operacao = request.getParameter("op");

		if (operacao == null) {
			this.getServletContext().setAttribute("msgs", "Operação não especificada na requisição!");
			return;
		}

		switch(operacao) {
		case "conctt":
			List<Contato> contatos = contatoCtrl.consultar((Usuario) session.getAttribute("usuario"));
			request.setAttribute("contatos", contatos);
			request.setAttribute("title", "Consulta");
			proxima = "contato/consulta.jsp";
			break;

		case "contatoEditar":
			Resultado resultado = contatoCtrl.editar(request.getParameterMap());
			if (!resultado.isErro()) {
				request.setAttribute("contato", resultado.getEntidade());
				request.setAttribute("msgs", resultado.getMensagens());
				proxima = "contato/cadastro.jsp";
			} else {
				proxima = "controller.do?op=conctt";
			}
			break;

		case "operadoras":
			List<Operadora> operadoras = operadoraCtrl.consultar();
			request.setAttribute("operadoras", operadoras);
			request.setAttribute("title", "Operadoras");
			proxima = "operadora/consulta.jsp";
			break;

		case "operadoraEditar":
			resultado = operadoraCtrl.editar(request.getParameterMap());

			if (!resultado.isErro()) {
				request.setAttribute("operadora", resultado.getEntidade());
				request.setAttribute("msgs", resultado.getMensagens());
				proxima = "operadora/cadastro.jsp";
			} else {
				proxima = "controller.do?op=operadoras";
			}
			break;
		}

		//  Verifica se o usuário está logado
		if (session.getAttribute("usuario") == null) {
			proxima = "/login/login.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(proxima);
		dispatcher.forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContatoController contatoCtrl = new ContatoController();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		this.getServletContext().removeAttribute("msgs");
		String operacao = request.getParameter("op");

		if (operacao == null) {
			this.getServletContext().setAttribute("msgs", "Operação não especificada na requisição!");
			return;
		}

		Resultado resultado = null;
		String paginaSucesso = "controller.do?op=conctt";
		String paginaErro = "contato/cadastro.jsp";
		String proximaPagina = null;

		switch(operacao) {
		case "login":
			LoginController loginCtrl = new LoginController();
			resultado = loginCtrl.isValido(request.getParameterMap());

			if (resultado.isErro()) {
				proximaPagina = "/login/login.jsp";
				request.setAttribute("msgs", resultado.getMensagens());
			} else {
				proximaPagina = "controller.do?op=conctt";
				session.setAttribute("usuario", (resultado.getEntidade()));
			}
			break;
		case "contatoCadastro":
			resultado = contatoCtrl.cadastrar(request.getParameterMap());
			if (!resultado.isErro()) {
				proximaPagina = paginaSucesso;
				request.setAttribute("msgs", resultado.getMensagens());
			} else {
				request.setAttribute("contato", resultado.getEntidade());
				proximaPagina = paginaErro;
			}
			break;

		case "operadoraDeletar":
			resultado = contatoCtrl.deletar(request.getParameterValues("id"));

			if (!resultado.isErro()) {
				request.setAttribute("msgs", resultado.getMensagens());
			}

			proximaPagina = "controller.do?op=conctt";
			break;

		case "operadoraCadastro":
			resultado = operadoraCtrl.cadastrar(request.getParameterMap());
			if (!resultado.isErro()) {
				proximaPagina = "controller.do?op=operadoras";
				request.setAttribute("msgs", resultado.getMensagens());
			} else {
				request.setAttribute("operadora", resultado.getEntidade());
				proximaPagina = "operadora/cadastro.jsp";
			}
			break;

		default:
			request.setAttribute("erro", "Operação não especificada no servlet!");
			proximaPagina= "../erro/erro.jsp";
		}

		if (resultado.isErro()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(proximaPagina);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(proximaPagina);
		}


	}

}
