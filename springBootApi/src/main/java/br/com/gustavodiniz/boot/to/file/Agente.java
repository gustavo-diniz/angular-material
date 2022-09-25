package br.com.gustavodiniz.boot.to.file;

import java.util.List;

public class Agente {

	private String codigo;
	private String data;
	
	private List<Regiao> regiao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Regiao> getRegiao() {
		return regiao;
	}

	public void setRegiao(List<Regiao> regiao) {
		this.regiao = regiao;
	}
	
	

	
	
	
	
}
