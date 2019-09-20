/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anbientget.views;

import anbientget.controls.AnimeControl;
import anbientget.models.Anime;
import anbientget.tools.Tools;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jeans
 */
public class JanelaAnime extends javax.swing.JFrame {

    private final Anime anime;
    private AnimeControl animeControl;

    /**
     * Creates new form JanelaAnime
     */
    public JanelaAnime(Anime anime) {
        initComponents();
        this.anime = anime;
        this.animeControl = new AnimeControl();
        this.setTitle("Todos os detalhes de " + anime.getTitulo());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setIconImage(Tools.getIcon().getImage());

        try {
            carregarInformacoes();
        } catch (IOException ex) {
            Logger.getLogger(JanelaAnime.class.getName()).log(Level.SEVERE, null, ex);
        }

        taSinopse.setLineWrap(true);
        taSinopse.setWrapStyleWord(true);

        lbCapa.requestFocus();
    }

    private String testarEmpty(String string) {
        if (string != null && (string.isEmpty() || string.equals("null"))) {
            return "";
        } else {
            return string;
        }
    }

    private void carregarInformacoes() throws IOException {
        tfAno.setText(testarEmpty(anime.getAno().toString()));
        tfEpisodios.setText(testarEmpty(anime.getEpisodiosTotais().toString()));
        tfGeneros.setText(testarEmpty(anime.getGeneros()));
        tfInfo.setText(testarEmpty(anime.getInfo()));
        tfNota.setText(testarEmpty(anime.getNota().toString()));
        tfTitulo.setText(testarEmpty(anime.getTitulo()));
        tfTituloAlternativo.setText(testarEmpty(anime.getTituloAlternativo()));
        tfTrailerUrl.setText(testarEmpty(anime.getTrailerUrl()));
        taSinopse.setText(testarEmpty(anime.getSinopse()));
        lbCapa.setIcon(anime.getCapaImg());
        carregarScreenshots();
    }

    private void carregarScreenshots() throws IOException {
        pnScreenshots.removeAll();
        pnScreenshots.setBorder(javax.swing.BorderFactory.createTitledBorder(anime.getScreenshots().size() + " screenshots"));
        pnScreenshots.setLayout(new GridLayout(0, 2));
        for (String screenshot : anime.getScreenshots()) {
            JLabel label = new JLabel();
            label.setIcon(Tools.imageBase64ToImageIcon(screenshot));
            pnScreenshots.add(label);
        }
        pnScreenshots.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfNota = new javax.swing.JTextField();
        tfEpisodios = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfAno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfGeneros = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfTituloAlternativo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pnScreenshots = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taSinopse = new javax.swing.JTextArea();
        lbCapa = new javax.swing.JLabel();
        tfTitulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfTrailerUrl = new javax.swing.JTextField();
        btCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfInfo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Episódios:");

        jLabel4.setText("Nota:");

        jLabel5.setText("Título alternativo: ");

        jLabel6.setText("Informações: ");

        jLabel7.setText("Trailer URL:");

        pnScreenshots.setBorder(javax.swing.BorderFactory.createTitledBorder("Screenshots"));

        javax.swing.GroupLayout pnScreenshotsLayout = new javax.swing.GroupLayout(pnScreenshots);
        pnScreenshots.setLayout(pnScreenshotsLayout);
        pnScreenshotsLayout.setHorizontalGroup(
            pnScreenshotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnScreenshotsLayout.setVerticalGroup(
            pnScreenshotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setText("Sinopse:");

        btSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btSalvar.setText("Salvar alterações");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        taSinopse.setColumns(20);
        taSinopse.setRows(5);
        jScrollPane1.setViewportView(taSinopse);

        lbCapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tfTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfTitulo.setText("Titulo");

        jLabel1.setText("Gêneros:");

        btCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btCancelar.setText("Cancelar e fechar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Ano:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btCancelar))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTituloAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEpisodios, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNota, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTrailerUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnScreenshots, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfTituloAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(tfGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfEpisodios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tfInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfTrailerUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnScreenshots, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        boolean erro = false;
        anime.setTitulo(tfTitulo.getText().trim());
        anime.setTituloAlternativo(tfTituloAlternativo.getText().trim());
        anime.setGeneros(tfGeneros.getText().trim());
        try {
            anime.setAno(Integer.parseInt(tfAno.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar o ano! Verifique se a informação esta correta!",
                    "Ops",
                    JOptionPane.WARNING_MESSAGE);
            erro = true;
        }
        try {
            anime.setEpisodiosTotais(Integer.parseInt(tfEpisodios.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar o número de episódios! Verifique se a informação esta correta!",
                    "Ops",
                    JOptionPane.WARNING_MESSAGE);
            erro = true;
        }
        try {
            anime.setNota(Double.parseDouble(tfNota.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar a nota! Verifique se a informação esta correta!",
                    "Ops",
                    JOptionPane.WARNING_MESSAGE);
            erro = true;
        }
        anime.setInfo(tfInfo.getText());
        anime.setTrailerUrl(tfTrailerUrl.getText());
        anime.setSinopse(taSinopse.getText());

        if (!erro) {
            animeControl.alterar(anime);
            JOptionPane.showMessageDialog(this,
                    "Alterações no cadastro do anime " + anime.getTitulo() + " salvas com sucesso!",
                    "Sucesso!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCapa;
    private javax.swing.JPanel pnScreenshots;
    private javax.swing.JTextArea taSinopse;
    private javax.swing.JTextField tfAno;
    private javax.swing.JTextField tfEpisodios;
    private javax.swing.JTextField tfGeneros;
    private javax.swing.JTextField tfInfo;
    private javax.swing.JTextField tfNota;
    private javax.swing.JTextField tfTitulo;
    private javax.swing.JTextField tfTituloAlternativo;
    private javax.swing.JTextField tfTrailerUrl;
    // End of variables declaration//GEN-END:variables
}