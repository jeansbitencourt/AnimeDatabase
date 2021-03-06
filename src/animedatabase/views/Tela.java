/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase.views;

import animedatabase.controls.AnimeControl;
import animedatabase.tools.Tools;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

/**
 *
 * @author Jean Bitencourt
 */
public class Tela extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     */
    private final AnimeControl animeControl;
    public static CardLayout cardLayout;
    
    private TelaAssistindo telaAssistindo;
    private TelaAssistidos telaAssistidos;
    private TelaInicial telaInicial;

    public Tela() throws IOException {
        initComponents();
        this.setTitle("Anime Database");
        this.setIconImage(Tools.getIcon().getImage());
        pnAba.setVisible(false);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        animeControl = new AnimeControl();
        telaInicial = new TelaInicial(animeControl);
        telaAssistindo = new TelaAssistindo(animeControl);
        telaAssistidos = new TelaAssistidos(animeControl);
        cardLayout = (CardLayout) pnConteudo.getLayout();
        pnConteudo.add(new JScrollPane(telaInicial), "Tela Inicial");
        pnConteudo.add(new JScrollPane(new TelaConfiguracoes(animeControl)), "Tela Configuracoes");
        pnConteudo.add(new JScrollPane(new TelaBuscar(animeControl)), "Tela Buscar");
        pnConteudo.add(new JScrollPane(telaAssistindo), "Tela Assistindo");
        pnConteudo.add(new JScrollPane(telaAssistidos), "Tela Assistidos");
        cardLayout.show(pnConteudo, "Tela Inicial");

//        for (Component component : pnConteudo.getComponents()) {
//            JScrollPane js = (JScrollPane) component;
//            js.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
//            js.setPreferredSize(Tools.telaSize());
//        }
        try {
            telaAssistidos.buscar();
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            telaAssistindo.buscar();
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
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

        pnConteudo = new javax.swing.JPanel();
        btAba = new javax.swing.JButton();
        pnAba = new javax.swing.JPanel();
        lbImg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btInicio = new javax.swing.JButton();
        btDados = new javax.swing.JButton();
        btConfiguracoes = new javax.swing.JButton();
        btRestart = new javax.swing.JButton();
        btAssistindo = new javax.swing.JButton();
        btAssistidos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnConteudo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnConteudo.setLayout(new java.awt.CardLayout());

        btAba.setBackground(new java.awt.Color(255, 255, 255));
        btAba.setText("▲");
        btAba.setToolTipText("Abrir aba de logs");
        btAba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbaActionPerformed(evt);
            }
        });

        pnAba.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbImg.setFocusable(false);

        taLog.setEditable(false);
        taLog.setColumns(20);
        taLog.setRows(5);
        taLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(taLog);

        javax.swing.GroupLayout pnAbaLayout = new javax.swing.GroupLayout(pnAba);
        pnAba.setLayout(pnAbaLayout);
        pnAbaLayout.setHorizontalGroup(
            pnAbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAbaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnAbaLayout.setVerticalGroup(
            pnAbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAbaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnAbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImg, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btInicio.setBackground(java.awt.Color.lightGray);
        btInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animedatabase/icons/23.png"))); // NOI18N
        btInicio.setText("Início");
        btInicio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btInicio.setFocusPainted(false);
        btInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInicioActionPerformed(evt);
            }
        });

        btDados.setBackground(java.awt.Color.lightGray);
        btDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animedatabase/icons/11.png"))); // NOI18N
        btDados.setText("Buscar");
        btDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btDados.setFocusPainted(false);
        btDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDadosActionPerformed(evt);
            }
        });

        btConfiguracoes.setBackground(java.awt.Color.lightGray);
        btConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animedatabase/icons/5.png"))); // NOI18N
        btConfiguracoes.setText("Configurações");
        btConfiguracoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btConfiguracoes.setFocusPainted(false);
        btConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfiguracoesActionPerformed(evt);
            }
        });

        btRestart.setBackground(java.awt.Color.lightGray);
        btRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animedatabase/icons/17.png"))); // NOI18N
        btRestart.setText("Reiniciar");
        btRestart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btRestart.setFocusPainted(false);
        btRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestartActionPerformed(evt);
            }
        });

        btAssistindo.setBackground(java.awt.Color.lightGray);
        btAssistindo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animedatabase/icons/38.png"))); // NOI18N
        btAssistindo.setText("Em andamento");
        btAssistindo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAssistindo.setFocusPainted(false);
        btAssistindo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAssistindoActionPerformed(evt);
            }
        });

        btAssistidos.setBackground(java.awt.Color.lightGray);
        btAssistidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animedatabase/icons/29.png"))); // NOI18N
        btAssistidos.setText("Terminados");
        btAssistidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAssistidos.setFocusPainted(false);
        btAssistidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAssistidosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDados, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAssistindo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAssistidos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConfiguracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(btRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btInicio)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btDados)
                        .addComponent(btConfiguracoes)
                        .addComponent(btRestart)
                        .addComponent(btAssistindo)
                        .addComponent(btAssistidos)))
                .addGap(0, 0, 0))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btDados, btInicio});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAba, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAba)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void ocultarMostrarPainel() {
        if (pnAba.isVisible()) {
            pnAba.setVisible(false);
            btAba.setText("▲");
        } else {
            pnAba.setVisible(true);
            btAba.setText("▼");
        }
    }

    private void btAbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbaActionPerformed
        ocultarMostrarPainel();
    }//GEN-LAST:event_btAbaActionPerformed

    private void btInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInicioActionPerformed
        cardLayout.show(pnConteudo, "Tela Inicial");
    }//GEN-LAST:event_btInicioActionPerformed

    private void btDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDadosActionPerformed
        cardLayout.show(pnConteudo, "Tela Buscar");
    }//GEN-LAST:event_btDadosActionPerformed

    private void btConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfiguracoesActionPerformed
        cardLayout.show(pnConteudo, "Tela Configuracoes");
    }//GEN-LAST:event_btConfiguracoesActionPerformed

    private void btRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestartActionPerformed
        try {
            Tools.restartApplication();
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRestartActionPerformed

    private void btAssistindoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAssistindoActionPerformed
        cardLayout.show(pnConteudo, "Tela Assistindo");
        try {
            telaAssistindo.buscar();
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAssistindoActionPerformed

    private void btAssistidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAssistidosActionPerformed
        cardLayout.show(pnConteudo, "Tela Assistidos");
        try {
            telaAssistidos.buscar();
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAssistidosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btAba;
    private javax.swing.JButton btAssistidos;
    private javax.swing.JButton btAssistindo;
    private javax.swing.JButton btConfiguracoes;
    private javax.swing.JButton btDados;
    private javax.swing.JButton btInicio;
    private javax.swing.JButton btRestart;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbImg;
    private static javax.swing.JPanel pnAba;
    public static javax.swing.JPanel pnConteudo;
    public static javax.swing.JTextArea taLog;
    // End of variables declaration//GEN-END:variables
}
