package br.mil.eb.sermil.assinatura.model;

import java.util.Date;

public class Certificado {

	private String hash;
	private Date emissiaoData;
	private Date validateData;
	private Long ra;
	private String nome;
	private String mae;
	private String pai;
	private String nascimentoLocal;
	private Date nascimentoData;
	private String jsm;
	private String informacoesComplementares;
	private Date expedicaoData;

	public Certificado(String hash, Date emissiaoData, Date validateData, Long ra, String nome, String mae, String pai,
			String nascimentoLocal, Date nascimentoData, String jsm, String informacoesComplementares,
			Date expedicaoData) {
		super();
		this.hash = hash;
		this.emissiaoData = emissiaoData;
		this.validateData = validateData;
		this.ra = ra;
		this.nome = nome;
		this.mae = mae;
		this.pai = pai;
		this.nascimentoLocal = nascimentoLocal;
		this.nascimentoData = nascimentoData;
		this.jsm = jsm;
		this.informacoesComplementares = informacoesComplementares;
		this.expedicaoData = expedicaoData;
	}

	public Certificado() {
		// TODO Auto-generated constructor stub
	}

	public Date getEmissiaoData() {
		return emissiaoData;
	}

	public void setEmissiaoData(Date emissiaoData) {
		this.emissiaoData = emissiaoData;
	}

	public Date getValidateData() {
		return validateData;
	}

	public void setValidateData(Date validateData) {
		this.validateData = validateData;
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getNascimentoLocal() {
		return nascimentoLocal;
	}

	public void setNascimentoLocal(String nascimentoLocal) {
		this.nascimentoLocal = nascimentoLocal;
	}

	public Date getNascimentoData() {
		return nascimentoData;
	}

	public void setNascimentoData(Date nascimentoData) {
		this.nascimentoData = nascimentoData;
	}

	public String getJsm() {
		return jsm;
	}

	public void setJsm(String jsm) {
		this.jsm = jsm;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}

	public Date getExpedicaoData() {
		return expedicaoData;
	}

	public void setExpedicaoData(Date expedicaoData) {
		this.expedicaoData = expedicaoData;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
