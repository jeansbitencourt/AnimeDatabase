/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase.tools;

import animedatabase.AnimeDatabase;
import animedatabase.views.Tela;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Jean Bitencourt
 */
public class Tools {

    public static String url = "jdbc:sqlite:animeDatabase.db";

    private static ImageIcon icone = new ImageIcon(Tools.class.getClassLoader().getResource("animedatabase/icons/icon.png"));

    public static ImageIcon getIcon() {
        return icone;
    }

    public static Dimension telaSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static String formataNomeArquivo(String nome) {
        return nome.replaceAll("\\W+", "");
    }

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void saveImg(String imageUrl, String fileName, String folderName) throws MalformedURLException, IOException {
        URL url = new URL(imageUrl);
        BufferedImage img = ImageIO.read(url);
        File imagesFolder = new File("images");
        imagesFolder.mkdir();
        File file = new File("images/" + folderName + "/" + fileName + ".jpg");
        file.getParentFile().mkdir();
        ImageIO.write(img, "jpg", file);
    }

    public static String imageUrlToBase64(String imageUrl) throws MalformedURLException, IOException {
        URL url = new URL(imageUrl);
        BufferedImage img = ImageIO.read(url);
        File file = new File("downloaded.jpg");
        file.mkdir();
        ImageIO.write(img, "jpg", file);
        InputStream finput = new FileInputStream(file);
        byte[] imageBytes = new byte[(int) file.length()];
        finput.read(imageBytes, 0, imageBytes.length);
        finput.close();
        return "data:image/png;base64," + Base64.encodeBase64String(imageBytes);
    }

    public static ImageIcon imageBase64ToImageIcon(String base64) throws IOException {
        String base64Image = base64.split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        return new ImageIcon(img);
    }

    public static void dropTables(String url) {
        dropTables(url, null, null);
    }

    public static void dropTables(String url, String user, String password) {

        String sqlDropTableAnimes = "DROP TABLE IF EXISTS animes";

        String sqlDropScreenshots = "DROP TABLE IF EXISTS screenshots";

        if (user == null) {
            try (Connection conn = DriverManager.getConnection(url);
                    Statement stmt = conn.createStatement()) {

                stmt.execute(sqlDropTableAnimes);
                stmt.execute(sqlDropScreenshots);

                System.out.println("Excluindo tabelas antigas do banco de dados");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement()) {

                stmt.execute(sqlDropTableAnimes);
                stmt.execute(sqlDropScreenshots);

                System.out.println("Excluindo tabelas antigas do banco de dados");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void createTables(String url) {
        createTables(url, null, null);
    }

    public static void createTables(String url, String user, String password) {

        String sqlCreateTableAnimes = "CREATE TABLE IF NOT EXISTS animes (\n"
                + "	id integer PRIMARY KEY UNIQUE,\n"
                + "	titulo varchar(255) NOT NULL UNIQUE,\n"
                + "	generos varchar(255) NOT NULL,\n"
                + "	ano decimal(4) NULL,\n"
                + "	episodios decimal NULL,\n"
                + "	episodiostotais decimal NULL,\n"
                + "	info varchar(255) NULL,\n"
                + "	nota double NULL,\n"
                + "	capa BLOB NULL,\n"
                + "	sinopse text NOT NULL,\n"
                + "	tituloalternativo varchar(255) NULL,\n"
                + "	trailerurl varchar(255) NULL, \n"
                + "	assistindo boolean NOT NULL DEFAULT false, \n"
                + "	assistido boolean NOT NULL DEFAULT false \n"
                + ");";

        String sqlCreateScreenshots = "CREATE TABLE IF NOT EXISTS screenshots (\n"
                + "	anime integer NOT NULL,\n"
                + "	screenshot MEDIUMBLOB NOT NULL \n"
                + ");";

        if (user == null) {
            try (Connection conn = DriverManager.getConnection(url);
                    Statement stmt = conn.createStatement()) {

                stmt.execute(sqlCreateTableAnimes);
                stmt.execute(sqlCreateScreenshots);

                System.out.println("Criando novas tabelas no banco de dados");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement()) {

                stmt.execute(sqlCreateTableAnimes);
                stmt.execute(sqlCreateScreenshots);

                System.out.println("Criando novas tabelas no banco de dados");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int resWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    }

    public static int resHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    }

    public static void restartApplication() throws IOException, URISyntaxException {
        AnimeDatabase.tela.dispose();
        AnimeDatabase.tela = new Tela();
        AnimeDatabase.tela.setVisible(true);
        AnimeDatabase.tela.revalidate();
    }
}
