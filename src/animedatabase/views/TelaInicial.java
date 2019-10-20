/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase.views;

import animedatabase.controls.AnimeControl;
import animedatabase.models.Anime;
import animedatabase.tools.Tools;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import animedatabase.tools.JLabelShadow;

/**
 *
 * @author Jean Bitencourt
 */
public class TelaInicial extends javax.swing.JPanel {

    private URL url = null;
    private URL url2 = null;
    private BufferedImage image = null;
    private BufferedImage image2 = null;
    private AnimeControl animeController;
    private List<Anime> listaDestaques;
    private Anime animex;
    private int i = 0;
    private int height;
    private JLabel destaques;
    private JLabel fundo;
    private boolean flagThread;
    private JanelaAnime janelaAnime;
    private MouseAdapter mouseEvent;

    public TelaInicial(AnimeControl animeControl) throws MalformedURLException, IOException {
        initComponents();
        sinopseAnime.setLineWrap(true);
        sinopseAnime.setWrapStyleWord(true);
        setSize(200, 200);
        capaAnime.setText("");
        fundo = new JLabel();
        fundo.setOpaque(true);
        fundo.setBackground(new Color(0, 0, 0, 180));
        fundo.setBounds(0, 0, Tools.resWidth(), Tools.resHeight());
        animeController = animeControl;
        destaques = new JLabel();
        destaques.setBounds(0, 0, 0, 0);
        sinopseAnime.setBackground(new Color(6, 6, 6, 255));
        pauseorplay.setToolTipText("Parar transição de animes em destaque");
        this.add(fundo);
        this.add(destaques);
        pauseorplay.setText("");
        Image img = ImageIO.read(getClass().getResource("/animedatabase/icons/playpause.png"));
        Image resizedImage = img.getScaledInstance(80, 80, 100);
        pauseorplay.setIcon(new ImageIcon(resizedImage));
        
        getDestaques();

        flagThread = true;
        AnimeChangeThread animeChangeThread = new AnimeChangeThread();
        animeChangeThread.start();
    }
    
    public void getDestaques() throws IOException{
        listaDestaques = animeController.listarDestaques();
    }

    public void destaques() {
        capaAnime.removeMouseListener(mouseEvent);
        tituloAnime.removeMouseListener(mouseEvent);
        height = Tools.resHeight();
        if (animex.getScreenshotsImg().size() > 0) {
            destaques.setIcon(new ImageIcon(animex.getScreenshotsImg().get(0).getImage().getScaledInstance((int) this.getBounds().getSize().getWidth(), height, Image.SCALE_DEFAULT)));
        }else{
            destaques.setBackground(Color.black);
        }
        capaAnime.setIcon(new ImageIcon(animex.getCapaImg().getImage().getScaledInstance((int) 180, 250, Image.SCALE_DEFAULT)));
        destaques.setSize((int) this.getBounds().getSize().getWidth(), height);
        tituloAnime.setText(animex.getTitulo());
        generosAnime.setText(animex.getGeneros());
        sinopseAnime.setText(animex.getSinopse());
        anoAnime.setText("Ano: " + animex.getAno());

        mouseEvent = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                janelaAnime = new JanelaAnime(animex);
            }
        };

        capaAnime.addMouseListener(mouseEvent);
        tituloAnime.addMouseListener(mouseEvent);
        capaAnime.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tituloAnime.setCursor(new Cursor(Cursor.HAND_CURSOR));
        capaAnime.setToolTipText("Ver todos os detalhes de " + animex.getTitulo());
        tituloAnime.setToolTipText("Ver todos os detalhes de " + animex.getTitulo());
    }

    public class AnimeChangeThread extends Thread {

        public void run() {
            jProgressBar.setMinimum(0);
            jProgressBar.setMaximum(5000);
            jProgressBar.setStringPainted(true);
            int progCont = 0;
            int t = 4999;
            while (true) {
                if (flagThread) {
                    if (i > 4) {
                        i = 0;
                        progCont = 0;
                    }
                    if (t == 4999) {
                        progCont++;
                        t = 0;
                        jProgressBar.setString("Anime em destaque nº " + progCont);
                        animex = (Anime) listaDestaques.get(i);
                        destaques();
                        i++;
                    }
                    jProgressBar.setValue(t);
                    t++;
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generosAnime = new JLabelShadow();
        tituloAnime = new JLabelShadow();
        jProgressBar = new javax.swing.JProgressBar();
        anoAnime = new JLabelShadow();
        capaAnime = new javax.swing.JLabel();
        pauseorplay = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sinopseAnime = new javax.swing.JTextArea();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        generosAnime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        generosAnime.setForeground(new java.awt.Color(204, 204, 204));
        generosAnime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        generosAnime.setText("GÊNEROS");

        tituloAnime.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        tituloAnime.setForeground(new java.awt.Color(0, 204, 0));
        tituloAnime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloAnime.setText("TITULO");

        anoAnime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        anoAnime.setForeground(new java.awt.Color(204, 204, 204));
        anoAnime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anoAnime.setText("Ano");

        capaAnime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        capaAnime.setText("CAPA");

        pauseorplay.setText("Pause");
        pauseorplay.setBorderPainted(false);
        pauseorplay.setContentAreaFilled(false);
        pauseorplay.setFocusPainted(false);
        pauseorplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseorplayActionPerformed(evt);
            }
        });

        sinopseAnime.setColumns(20);
        sinopseAnime.setForeground(new java.awt.Color(255, 255, 255));
        sinopseAnime.setRows(5);
        jScrollPane2.setViewportView(sinopseAnime);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(generosAnime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloAnime, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                    .addComponent(anoAnime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(capaAnime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(23, 23, 23)
                .addComponent(pauseorplay)
                .addGap(129, 129, 129))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloAnime, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generosAnime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(anoAnime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(capaAnime, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pauseorplay)
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (i > 0) {
            destaques();
        }
    }//GEN-LAST:event_formComponentResized

    private void pauseorplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseorplayActionPerformed
        if (flagThread) {
            flagThread = false;
            pauseorplay.setToolTipText("Continuar transição de animes em destaque");
        } else {
            flagThread = true;
            pauseorplay.setToolTipText("Parar transição de animes em destaque");
        }
    }//GEN-LAST:event_pauseorplayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anoAnime;
    private javax.swing.JLabel capaAnime;
    private javax.swing.JLabel generosAnime;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton pauseorplay;
    private javax.swing.JTextArea sinopseAnime;
    private javax.swing.JLabel tituloAnime;
    // End of variables declaration//GEN-END:variables
}
