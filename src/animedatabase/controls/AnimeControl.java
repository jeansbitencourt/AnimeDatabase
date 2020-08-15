/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase.controls;

import animedatabase.models.Anime;
import animedatabase.tools.Tools;
import animedatabase.views.JanelaMigrar;
import animedatabase.views.Tela;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Jean Bitencourt
 */
public class AnimeControl {

    private StringBuilder html;
    private int totalAnimes;
    public int atualAnime = 0;

    private Anime anime;
    private StringBuilder codigoFonte;
    private List<String> screenshots;
    private InputStreamReader inputStreamReader;

    public AnimeControl() {
        this.html = new StringBuilder();
    }

    public void lerHtmlAnbient(boolean salvarImgs, boolean resetarBanco) throws MalformedURLException, IOException {
        if (resetarBanco) {
            Tools.dropTables(Tools.url);
            Tools.createTables(Tools.url);
        }
        URL url = new URL("https://www.anbient.com/anime/lista");
        totalAnimes = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                html.append(line).append(System.getProperty("line.separator"));
                if (line.trim().equals("<td class=\"titulo\">")) {
                    totalAnimes++;
                }
            }
            lerHtmlBaixado(salvarImgs, resetarBanco);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerHtmlAnbient(salvarImgs, false);
        }
    }

    public void lerHtmlBaixado(boolean salvarImgs, boolean resetarBanco) throws IOException {
        boolean flag = false;
        for (String linha : html.toString().split("[\\r\\n]+")) {
            if (flag) {
                flag = false;
                atualAnime++;
                if (resetarBanco || !verificaRegistro(linha.split("\">")[1].replace("</a>", "").replace("</td>", "").trim())) {
                    salvar(lerAnimeAnbient(linha.split("\">")[0].replace("<a href=\"", "").trim(), salvarImgs, totalAnimes, atualAnime));
                } else {
                    Tela.taLog.append(Tools.getTime() + " - Anime " + linha.split("\">")[1].replace("</a>", "").replace("</td>", "").trim() + " não foi salvo pois já existe no banco de dados! " + System.getProperty("line.separator"));
                    Tela.taLog.setCaretPosition(Tela.taLog.getDocument().getLength());
                }
            }
            if (linha.trim().equals("<td class=\"titulo\">")) {
                flag = true;
            }
            html = new StringBuilder(html.substring(html.indexOf(System.getProperty("line.separator")) + 1));
        }
    }

    public Anime lerAnimeAnbient(String endereco, boolean salvarImgs, int total, int atual) throws MalformedURLException, IOException {
        anime = new Anime();
        URL url = new URL("https://www.anbient.com/" + endereco);
        String imgUrl;
        int flagSinopse = 0;
        boolean flagGeneros = false;
        boolean flagTituloAlt = false;
        int flagAno = 0;
        int flagNota = 0;
        boolean flagScreenshots = false;
        int countScreenshots = 0;
        String generos = "";
        String epitexto = "";
        int epi = 0;
        codigoFonte = new StringBuilder();
        screenshots = new ArrayList();
        try (BufferedReader reader = new BufferedReader(inputStreamReader = new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                codigoFonte.append(line).append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o anime " + anime.getTitulo() + " -> " + e.toString());
            //lerAnimeAnbient(endereco, salvarImgs, total, atual);
        }

        for (String line : codigoFonte.toString().split("\n")) {
            //System.out.println(line);
            if (flagSinopse > 0) {
                flagSinopse++;
                if (flagSinopse == 5) {
                    flagSinopse = 0;
                }
            }

            if (line.contains("Sinopse  </h3>")) {
                flagSinopse = 1;
            }

            if (flagSinopse == 4) {
                anime.setSinopse(StringEscapeUtils.unescapeHtml(line.replace("</div>", "").trim().replaceAll("\\<.*?>", "")));
            }

            if (line.contains("class=\"title\"")) {
                anime.setTitulo(StringEscapeUtils.unescapeHtml(line.split("\">")[1].replace("</h1>", "").trim()));
                Tela.taLog.append(Tools.getTime() + " - Lendo anime -> " + anime.getTitulo() + " (" + atual + " de " + total + ")" + System.getProperty("line.separator"));
                Tela.taLog.setCaretPosition(Tela.taLog.getDocument().getLength());
            }

            if (line.contains("<span itemprop=\"image\">")) {
                try {
                    imgUrl = line.split("img src=\"")[1].split("\"")[0].trim();
                    System.out.println(imgUrl);
                    if (salvarImgs) {
                        Tools.saveImg(imgUrl, Tools.formataNomeArquivo(anime.getTitulo()), Tools.formataNomeArquivo(anime.getTitulo()));
                    }
                    anime.setCapa(Tools.imageUrlToBase64(imgUrl));
                    anime.setCapaImg(Tools.imageBase64ToImageIcon(anime.getCapa()));
                    Tela.lbImg.setIcon(anime.getCapaImg());

                } catch (Exception e) {
                    System.out.println("Erro ao ler capa do anime " + anime.getTitulo());
                }
            }

            if (line.contains("<span class=\"generos\">") || flagGeneros) {
                try {
                    flagGeneros = true;
                    if (line.contains("</span>")) {
                        flagGeneros = false;
                        anime.setGeneros(generos.trim());
                    }

                    if (line.contains("<a href=\"")) {
                        generos = generos + (generos.isEmpty() ? "" : ", ") + line.split("<a href=\"")[1].split("\">")[1].replace("</a>", "").replace("</b>", "").trim();
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao ler generos do anime " + anime.getTitulo());
                }
            }

            if (line.contains("<i class=\"field-titulos-alt\">") || flagTituloAlt) {
                if (flagTituloAlt) {
                    anime.setTituloAlternativo(line.replace("</i>", "").trim());
                }
                flagTituloAlt = !flagTituloAlt;
            }

            if (line.contains("<a href=\"https://www.youtube.com/watch?")) {
                try {
                    anime.setTrailerUrl(line.split("<a href=\"")[1].split("class=\"trailer-button\"")[0].replaceAll("\"", "\\\"").trim());
                } catch (Exception e) {
                    System.out.println("Erro ao ler trailer do anime " + anime.getTitulo());
                }
            }

            if (line.contains("<div class=\"field field-estreia inline\">") || flagAno > 0) {
                flagAno++;

                if (flagAno == 7) {
                    flagAno = 0;
                    try {
                        anime.setAno(Integer.parseInt(line.replace("</div>", "").trim()));
                    } catch (Exception e) {
                        System.out.println("Erro ao ler ano do anime " + anime.getTitulo());
                    }
                }
            }

            if (line.contains("<a href=\"http://anilist.co/anime/")) {
                try {
                    anime.setInfo(line.split("<a href=\"")[1].split("\" rel=\"nofollow\"")[0].trim());
                } catch (Exception e) {
                    System.out.println("Erro ao ler info do anime " + anime.getTitulo());
                }
            }

            if (line.contains("<div class=\"field field-nota\">") || flagNota > 0) {
                flagNota++;

                if (flagNota == 4) {
                    flagNota = 0;
                    try {
                        anime.setNota(Double.parseDouble(line.replace("</div>", "").trim()));
                    } catch (Exception e) {
                        System.out.println("Erro ao ler nota do anime " + anime.getTitulo());
                    }
                }
            }

            if (line.contains("<div class=\"field field-screenshots\">") || flagScreenshots) {
                flagScreenshots = true;
                if (line.contains("<div>") && flagScreenshots && !line.contains("<a href=\"")) {
                    flagScreenshots = false;
                    anime.setScreenshots(screenshots);
                    countScreenshots = 0;
                }

                if (line.contains("<a href=\"")) {
                    countScreenshots++;
                    imgUrl = line.split("<a href=\"")[1].split("\"")[0].trim();
                    if (imgUrl.contains("/files/")) {
                        if (salvarImgs) {
                            try {
                                Tools.saveImg(imgUrl, Tools.formataNomeArquivo(anime.getTitulo() + "_screenshot_" + countScreenshots), Tools.formataNomeArquivo(anime.getTitulo()));
                            } catch (Exception e) {
                                System.out.println("Erro ao ler screenshot do anime " + anime.getTitulo());
                            }
                        }
                        try {
                            screenshots.add(Tools.imageUrlToBase64(imgUrl));
                        } catch (Exception e) {
                            System.out.println("Erro ao ler screenshot do anime " + anime.getTitulo() + " " + e);
                        }
                    }
                }
            }

            if (line.contains("<span itemprop=\"numberOfEpisodes\">")) {
                epitexto = line.split("<span itemprop=\"numberOfEpisodes\">")[1].split("</span>")[0];

                if (epitexto.contains("Completo")) {
                    try {
                        epi = Integer.parseInt(epitexto.replace("(Completo)", "").trim());
                    } catch (Exception e) {
                        epi = 0;
                    }
                    anime.setEpisodiosTotais(epi);
                } else {
                    try {
                        epi = Integer.parseInt(epitexto.split("/")[0].trim());
                        anime.setEpisodiosTotais(Integer.parseInt(epitexto.split("/")[1].trim()));
                    } catch (Exception e) {
                        epi = 0;
                        anime.setEpisodiosTotais(epi);
                    }
                }
                anime.setEpisodios(0);
            }
        }

        return anime;
    }
    
    public Anime preencherModelBasico(ResultSet rs) throws SQLException, IOException{
        Anime anime = new Anime();
        anime.setTitulo(rs.getString("titulo"));
        anime.setId(rs.getInt("id"));
        anime.setCapa(rs.getString("capa"));
        anime.setCapaImg(Tools.imageBase64ToImageIcon(anime.getCapa()));
        return anime;
    }

    public Anime preencherModelCompleto(ResultSet rs) throws SQLException, IOException {
        Anime anime = new Anime();
        anime.setTitulo(rs.getString("titulo"));
        anime.setAno(rs.getInt("ano"));
        anime.setCapa(rs.getString("capa"));
        anime.setEpisodios(rs.getInt("episodios"));
        anime.setEpisodiosTotais(rs.getInt("episodiostotais"));
        anime.setGeneros(rs.getString("generos"));
        anime.setId(rs.getInt("id"));
        anime.setInfo(rs.getString("info"));
        anime.setNota(rs.getDouble("nota"));
        anime.setSinopse(rs.getString("sinopse"));
        anime.setTituloAlternativo(rs.getString("tituloalternativo"));
        anime.setTrailerUrl(rs.getString("trailerurl"));
        anime.setScreenshots(listarScreenshots(anime.getId()));
        anime.setCapaImg(Tools.imageBase64ToImageIcon(anime.getCapa()));
        anime.setAssistido(rs.getBoolean("assistido"));
        anime.setAssistindo(rs.getBoolean("assistindo"));
        
        List<ImageIcon> screens = new ArrayList<>();
        for (String screenshot : anime.getScreenshots()) {
            screens.add(Tools.imageBase64ToImageIcon(screenshot));
        }
        anime.setScreenshotsImg(screens);

        return anime;
    }

    public List<String> listarScreenshots(Integer id) {
        List<String> screenshots = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM screenshots WHERE anime = '" + id + "'");
            while (rs.next()) {
                screenshots.add(rs.getString("screenshot"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return screenshots;
    }

    private int ultimoId() {
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT MAX(id) as id FROM animes");
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private boolean verificaRegistro(String titulo) {
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                PreparedStatement pstm = conn.prepareStatement("SELECT id FROM animes WHERE titulo = ?")) {
            pstm.setString(1, StringEscapeUtils.unescapeHtml(titulo));
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se existe o registro! " + e.getMessage());
        }
        return false;
    }
    
    public Anime buscar(Integer id) throws IOException {
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM animes WHERE id = " + id);
            if (rs.next()) {
                return preencherModelCompleto(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Anime> listar(String titulo) throws IOException {
        List<Anime> lista = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT id, titulo, capa FROM animes WHERE titulo LIKE '%" + titulo + "%'");
            while (rs.next()) {
                lista.add(preencherModelBasico(rs));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public List<Anime> listarAssistindo() throws IOException{
        List<Anime> lista = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT id, titulo, capa FROM animes WHERE assistindo = true");
            while (rs.next()) {
                lista.add(preencherModelBasico(rs));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public List<Anime> listarAssistidos() throws IOException{
        List<Anime> lista = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT id, titulo, capa FROM animes WHERE assistido = 1");
            while (rs.next()) {
                lista.add(preencherModelBasico(rs));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Anime> listarUltimos() throws IOException {
        List<Anime> lista = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM animes ORDER BY id DESC LIMIT 5");
            while (rs.next()) {
                lista.add(preencherModelCompleto(rs));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Anime> listarDestaques() throws IOException {
        List<Anime> lista = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM animes ORDER BY RANDOM() LIMIT 5");
            while (rs.next()) {
                lista.add(preencherModelCompleto(rs));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int alterar(Anime anime) {
        String sqlAlteraAnime;
        int resultado = 0;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {

            sqlAlteraAnime = "UPDATE animes SET titulo = '" + anime.getTitulo() + "', generos = '" + anime.getGeneros() + "', ano = " + anime.getAno() + ", episodios = " + anime.getEpisodios() + ", episodiostotais = " + anime.getEpisodiosTotais() + ", info = '" + anime.getInfo() + "', nota = '" + anime.getNota() + "', capa = '" + anime.getCapa() + "', sinopse = '" + anime.getSinopse() + "', tituloalternativo = '" + anime.getTituloAlternativo() + "', trailerurl = '" + anime.getTrailerUrl() + "', assistido = " + anime.getAssistido() + ", assistindo = " + anime.getAssistindo() + " WHERE id = " + anime.getId();

            resultado = stmt.executeUpdate(sqlAlteraAnime);
            Tela.taLog.append(Tools.getTime() + " - Atualizando anime " + anime.getTitulo() + " no banco de dados!" + System.getProperty("line.separator"));
            Tela.taLog.setCaretPosition(Tela.taLog.getDocument().getLength());
            Tela.lbImg.setIcon(anime.getCapaImg());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public void salvar(Anime anime) throws IOException {
        int id = ultimoId() + 1;
        String sqlInsereAnime = null;
        String sqlInsereScreenshot = null;

        // System.out.println("Salvando animes no banco de dados");
        anime.setId(id);
        sqlInsereAnime = "INSERT INTO animes (id, titulo, generos, ano, episodios, episodiostotais, info, nota, capa, sinopse, tituloalternativo, trailerurl) VALUES \n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(Tools.url);
                PreparedStatement pstm = conn.prepareStatement(sqlInsereAnime)) {
            pstm.setInt(1, anime.getId());
            pstm.setString(2, anime.getTitulo());
            pstm.setString(3, anime.getGeneros());
            pstm.setInt(4, anime.getAno());
            try {
                pstm.setInt(5, anime.getEpisodios());
            } catch (Exception e) {
                pstm.setNull(5, java.sql.Types.INTEGER);
            }
            try {
                pstm.setInt(6, anime.getEpisodiosTotais());
            } catch (Exception e) {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }
            pstm.setString(7, anime.getInfo());
            try {
                pstm.setDouble(8, anime.getNota());
            } catch (Exception e) {
                pstm.setNull(8, java.sql.Types.DOUBLE);
            }
            pstm.setString(9, anime.getCapa());
            pstm.setString(10, anime.getSinopse());
            pstm.setString(11, anime.getTituloAlternativo());
            pstm.setString(12, anime.getTrailerUrl());
            pstm.executeUpdate();
            Tela.taLog.append(Tools.getTime() + " - Inserindo anime " + anime.getTitulo() + " no banco de dados!" + System.getProperty("line.separator"));
            Tela.taLog.setCaretPosition(Tela.taLog.getDocument().getLength());
            anime.setCapaImg(Tools.imageBase64ToImageIcon(anime.getCapa()));
            Tela.lbImg.setIcon(anime.getCapaImg());

            if (anime.getScreenshots() != null && anime.getScreenshots().size() > 0) {
                for (String screenshot : anime.getScreenshots()) {

                    if (screenshot != null && anime.getId() != null) {
                        sqlInsereScreenshot = "INSERT INTO screenshots (anime, screenshot) VALUES \n"
                                + "(" + anime.getId() + ", '" + screenshot + "')";

                        conn.createStatement().execute(sqlInsereScreenshot);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void migrarAnimes(String url, String usuario, String senha, JanelaMigrar janela) {
        ResultSet rs;
        PreparedStatement pstm;
        String sqlInsereAnime;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM animes");
            while (rs.next()) {
                try (Connection conn2 = DriverManager.getConnection(url, usuario, senha)) {
                    sqlInsereAnime = "INSERT INTO animes (id, titulo, generos, ano, episodios, episodiostotais, info, nota, capa, sinopse, tituloalternativo, trailerurl) VALUES \n"
                            + "(?,?,?,?,?,?,?,?,?,?,?,?)";
                    pstm = conn2.prepareStatement(sqlInsereAnime);
                    pstm.setInt(1, rs.getInt("id"));
                    pstm.setString(2, rs.getString("titulo"));
                    pstm.setString(3, rs.getString("generos"));
                    pstm.setInt(4, rs.getInt("ano"));
                    try {
                        pstm.setInt(5, rs.getInt("episodios"));
                    } catch (Exception e) {
                        pstm.setNull(5, java.sql.Types.INTEGER);
                    }
                    try {
                        pstm.setInt(6, rs.getInt("episodiostotais"));
                    } catch (Exception e) {
                        pstm.setNull(6, java.sql.Types.INTEGER);
                    }
                    pstm.setString(7, rs.getString("info"));
                    try {
                        pstm.setDouble(8, rs.getDouble("nota"));
                    } catch (Exception e) {
                        pstm.setNull(8, java.sql.Types.DOUBLE);
                    }
                    pstm.setString(9, rs.getString("capa"));
                    pstm.setString(10, rs.getString("sinopse"));
                    pstm.setString(11, rs.getString("tituloalternativo"));
                    pstm.setString(12, rs.getString("trailerurl"));
                    janela.lbMsg.setText("Migrando dados do anime " + rs.getString("titulo"));
                    pstm.executeUpdate();
                } catch (SQLException e) {
                    janela.lbMsg.setText("Erro ao migrar dados do anime " + rs.getString("titulo") + " - " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void migrarScreenshots(String url, String usuario, String senha, JanelaMigrar janela) {
        ResultSet rs;
        PreparedStatement pstm;
        String sqlInsereAnime;
        try (Connection conn = DriverManager.getConnection(Tools.url);
                Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM screenshots");
            while (rs.next()) {
                try (Connection conn2 = DriverManager.getConnection(url, usuario, senha)) {
                    sqlInsereAnime = "INSERT screenshots (anime, screenshot) VALUES (?, ?)";
                    pstm = conn2.prepareStatement(sqlInsereAnime);
                    pstm.setInt(1, rs.getInt("anime"));
                    pstm.setString(2, rs.getString("screenshot"));
                    janela.lbMsg.setText("Migrando screenshots do anime id " + rs.getInt("anime"));
                    pstm.executeUpdate();
                } catch (SQLException e) {
                    janela.lbMsg.setText("Erro ao migrar screenshots do anime id " + rs.getInt("anime") + " - " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
