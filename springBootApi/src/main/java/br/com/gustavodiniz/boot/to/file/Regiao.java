package br.com.gustavodiniz.boot.to.file;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Regiao {

	@XStreamAlias("sigla")
	private String sigla;

	private Geracao geracao;
	private Compra compra;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Geracao getGeracao() {
		return geracao;
	}

	public void setGeracao(Geracao geracao) {
		this.geracao = geracao;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	

}
