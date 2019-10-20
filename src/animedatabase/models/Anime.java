/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase.models;

import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Jean Bitencourt
 */
public class Anime {

    Integer id;
    String titulo;
    String generos;
    Integer ano;
    Integer episodios;
    Integer episodiosTotais;
    String info;
    Double nota;
    String capa;
    ImageIcon capaImg;
    String sinopse;
    String tituloAlternativo;
    String trailerUrl;
    Boolean assistindo;
    Boolean assistido;
    List<String> screenshots;
    List<ImageIcon> screenshotsImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public ImageIcon getCapaImg() {
        return capaImg;
    }

    public void setCapaImg(ImageIcon capaImg) {
        this.capaImg = capaImg;
    }

    public String getTituloAlternativo() {
        return tituloAlternativo;
    }

    public void setTituloAlternativo(String tituloAlternativo) {
        this.tituloAlternativo = tituloAlternativo;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    public Integer getEpisodiosTotais() {
        return episodiosTotais;
    }

    public void setEpisodiosTotais(Integer episodiosTotais) {
        this.episodiosTotais = episodiosTotais;
    }

    public List<ImageIcon> getScreenshotsImg() {
        return screenshotsImg;
    }

    public void setScreenshotsImg(List<ImageIcon> screenshotsImg) {
        this.screenshotsImg = screenshotsImg;
    }

    public Boolean getAssistindo() {
        return assistindo;
    }

    public void setAssistindo(Boolean assistindo) {
        this.assistindo = assistindo;
    }

    public Boolean getAssistido() {
        return assistido;
    }

    public void setAssistido(Boolean assistido) {
        this.assistido = assistido;
    }

    
}
