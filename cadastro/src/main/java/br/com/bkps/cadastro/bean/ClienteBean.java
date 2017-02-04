package br.com.bkps.cadastro.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bkps.cadastro.dao.DAO;
import br.com.bkps.cadastro.model.Cliente;
import br.com.bkps.cadastro.util.RedirectView;

@ManagedBean
@ViewScoped
public class ClienteBean {
	
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;

	public Cliente getCliente() {
		return cliente;
	}
	
	public void gravar() {
		System.out.println("Gravando cliente " + this.cliente.getRazao());
		
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);

		if (this.cliente.getId() == null) {
			dao.adiciona(this.cliente);
			this.clientes = dao.listaTodos();
		} else {
			new DAO<Cliente>(Cliente.class).atualiza(this.cliente);
		}
		this.cliente= new Cliente();
	}
	
	public List<Cliente> getClientes() {

		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);

		if (this.clientes == null) {
			this.clientes = dao.listaTodos();
		}
		return clientes;
	}

}
