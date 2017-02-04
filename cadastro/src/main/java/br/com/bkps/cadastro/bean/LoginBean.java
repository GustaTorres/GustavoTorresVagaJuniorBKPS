package br.com.bkps.cadastro.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.bkps.cadastro.dao.UsuarioDao;
import br.com.bkps.cadastro.model.Usuario;
import br.com.bkps.cadastro.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public RedirectView logar() {
		System.out.println("Usuario logado " + getUsuario().getEmail());

		FacesContext context = FacesContext.getCurrentInstance();

		boolean existe = new UsuarioDao().existe(this.usuario);

		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return new RedirectView("cliente");
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não localizado"));
		return new RedirectView("login");
	}

	public RedirectView deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return new RedirectView("login");

	}

}
