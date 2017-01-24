package br.edu.ifpb.memoriam.facade;

import java.util.Map;

import br.edu.ifpb.memoriam.dao.UsuarioDAO;
import br.edu.ifpb.memoriam.entity.Usuario;
import br.edu.ifpb.memoriam.util.PasswordUtil;

public class LoginController {
	Resultado resultado;

	public LoginController() {
	}

	public Resultado isValido(Map<String, String[]> parametros) {
		this.resultado = new Resultado();
		resultado.setErro(false);

		String login = parametros.get("login")[0];
		String senha = parametros.get("senha")[0];

		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.findByLogin(login);

		if (login == null || senha == null || user == null
				|| !user.getSenha().equals(PasswordUtil.encryptMD5(senha))) {
			resultado.setErro(true);
			resultado.addMensagem(new Mensagem("Usuário ou senha inválidos", Categoria.ERRO));
			return resultado;
		}

		resultado.setEntidade(user);

		return resultado;
	}
}
