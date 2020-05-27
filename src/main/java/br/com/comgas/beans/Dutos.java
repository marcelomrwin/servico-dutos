package br.com.comgas.beans;

import java.util.Date;

/*
 * Objeto de mapeamento de dados (Base de Dutos)
 *
 */

public class Dutos {

	private int idTubo;
	private Date dataInstalacao;
	private int pressao;
	private String unidadePressao;
	private String latitude;
	private String longitude;
	private String nomeLogradouro;
	private int qtdeClientes;
	private int diametro;
	private String unidadeDiametro;
	private String material;

	public int getIdTubo() {
		return idTubo;
	}

	public void setIdTubo(int idTubo) {
		this.idTubo = idTubo;
	}

	public Date getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public int getPressao() {
		return pressao;
	}

	public void setPressao(int pressao) {
		this.pressao = pressao;
	}

	public String getUnidadePressao() {
		return unidadePressao;
	}

	public void setUnidadePressao(String unidadePressao) {
		this.unidadePressao = unidadePressao;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public int getQtdeClientes() {
		return qtdeClientes;
	}

	public void setQtdeClientes(int qtdeClientes) {
		this.qtdeClientes = qtdeClientes;
	}

	public int getDiametro() {
		return diametro;
	}

	public void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	public String getUnidadeDiametro() {
		return unidadeDiametro;
	}

	public void setUnidadeDiametro(String unidadeDiametro) {
		this.unidadeDiametro = unidadeDiametro;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

}