/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animedatabase.views;

import animedatabase.controls.AnimeControl;
import animedatabase.models.Anime;
import animedatabase.tools.Tools;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author jeans
 */
public class TelaAssistidos extends javax.swing.JPanel {

    private final AnimeControl animeControl;

    /**
     * Creates new form TelaExibir
     * @param animeControl
     */
    public TelaAssistidos(AnimeControl animeControl) {
        initComponents();
        this.animeControl = animeControl;
        pnResultados.setLayout(new GridLayout(0, 1));
    }

    public void buscar() throws IOException {
        Tela.taLog.append(Tools.getTime() + " Buscando animes assistidos" + System.getProperty("line.separator"));
        Tela.taLog.setCaretPosition(Tela.taLog.getDocument().getLength());
        new Thread() {                  
            @Override
            public void run() {
                try {
                    listarResultados(animeControl.listarAssistidos());
                } catch (IOException ex) {
                    Logger.getLogger(TelaBuscar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    private void listarResultados(List<Anime> lista) {
        pnResultados.removeAll();
        pnResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Exibindo " + lista.size() + " resultados para animes terminados"));
        pnResultados.setLayout(new GridLayout(0, 5));
        for (Anime anime : lista) {
            System.out.println("Encontrado na lista de animes terminados -> " + anime.getTitulo());
            JLabel labelCapa = new JLabel();
            labelCapa.setToolTipText("Ver todos os detalhes de " + anime.getTitulo());
            labelCapa.setIcon(anime.getCapaImg());
            labelCapa.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //labelCapa.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black, 3, true));
            pnResultados.add(labelCapa);

            labelCapa.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new JanelaAnime(anime.getId());
                }

            });
        }
        pnResultados.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnResultados = new javax.swing.JPanel();

        pnResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de animes terminados"));

        javax.swing.GroupLayout pnResultadosLayout = new javax.swing.GroupLayout(pnResultados);
        pnResultados.setLayout(pnResultadosLayout);
        pnResultadosLayout.setHorizontalGroup(
            pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        pnResultadosLayout.setVerticalGroup(
            pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnResultados;
    // End of variables declaration//GEN-END:variables
}
