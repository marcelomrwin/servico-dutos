package br.com.comgas.beans;

/*
 * Objeto de mapeamento de dados (Base de Dutos)
 *
 */

public class Dutos {

	private Integer idTubo;
	private java.util.Date dataInstalacao;
	private Double pressao;
	private java.lang.String unidadePressao;
	private Double latitude;
	private Double longitude;
	private java.lang.String nomeLogradouro;
	private java.lang.Integer qtdeClientes;
	private java.lang.Integer diametro;
	private java.lang.String unidadeDiametro;
	private java.lang.String material;

	public Integer getIdTubo() {
		return idTubo;
	}

	public void setIdTubo(Integer idTubo) {
		this.idTubo = idTubo;
	}

	public java.util.Date getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(java.util.Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public Double getPressao() {
		return pressao;
	}

	public void setPressao(Double pressao) {
		this.pressao = pressao;
	}

	public java.lang.String getUnidadePressao() {
		return unidadePressao;
	}

	public void setUnidadePressao(java.lang.String unidadePressao) {
		this.unidadePressao = unidadePressao;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public java.lang.String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(java.lang.String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public java.lang.Integer getQtdeClientes() {
		return qtdeClientes;
	}

	public void setQtdeClientes(java.lang.Integer qtdeClientes) {
		this.qtdeClientes = qtdeClientes;
	}

	public java.lang.Integer getDiametro() {
		return diametro;
	}

	public void setDiametro(java.lang.Integer diametro) {
		this.diametro = diametro;
	}

	public java.lang.String getUnidadeDiametro() {
		return unidadeDiametro;
	}

	public void setUnidadeDiametro(java.lang.String unidadeDiametro) {
		this.unidadeDiametro = unidadeDiametro;
	}

	public java.lang.String getMaterial() {
		return material;
	}

	public void setMaterial(java.lang.String material) {
		this.material = material;
	}

}