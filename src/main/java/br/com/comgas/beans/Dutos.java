package br.com.comgas.beans;

import java.util.Date;

/*
 * Objeto de mapeamento de dados (Base de Dutos)
 *
 */

public class Dutos {

    private int idTubo;
    private Date dataInstalacao;
    private int Pressao;
    private String unidadePressao;
    private String Latitude;
    private String Longitude;
    private String nomeLogradouro;
    private int qtdeClientes;
    private int Diametro;
    private String unidadeDiametro;
    private String Material;

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
        return Pressao;
    }

    public void setPressao(int pressao) {
        Pressao = pressao;
    }

    public String getUnidadePressao() {
        return unidadePressao;
    }

    public void setUnidadePressao(String unidadePressao) {
        this.unidadePressao = unidadePressao;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
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
        return Diametro;
    }

    public void setDiametro(int diametro) {
        Diametro = diametro;
    }

    public String getUnidadeDiametro() {
        return unidadeDiametro;
    }

    public void setUnidadeDiametro(String unidadeDiametro) {
        this.unidadeDiametro = unidadeDiametro;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

}