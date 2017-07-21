/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eti.wiki.ui;

import com.eti.wiki.Configuration;
import com.eti.wiki.io.WikiXMLReader;
import com.eti.wiki.parser.WikiXMLPagesParser;
import com.eti.wiki.parser.phase1.PageIdProcessor;
import com.eti.wiki.parser.phase2.OccurencesCounterProcessor;
import com.eti.wiki.parser.phase3.OccurencesFinder;
import com.eti.wiki.parser.phase4.OccurencesFinderAll;
import com.eti.wiki.parser.phase5.GraphFileReader;
import com.eti.wiki.pagerank.Gauss;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import org.jboss.logging.Logger;

/**
 *
 * @author pawel
 */
public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        faza1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        faza2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        faza3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        faza4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        faza5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        postep_label = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane(jTextArea1,
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Plik XML:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.01;
        jPanel1.add(jLabel1, gridBagConstraints);

        jTextField1.setText("E:\\enwiki-latest-pages-meta-current.xml");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField1, gridBagConstraints);

        jButton1.setText("Wybierz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.01;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        faza1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Faza 1 (Wydzielanie ID oraz tytu³u strony do tabeli wikipage):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        faza1.add(jLabel2, gridBagConstraints);

        jButton2.setText("Uruchom");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.01;
        faza1.add(jButton2, gridBagConstraints);

        jPanel2.add(faza1);

        faza2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Faza 2 (Zliczanie wyst¹pieñ wikioccurences):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        faza2.add(jLabel3, gridBagConstraints);

        jButton3.setText("Uruchom");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.01;
        faza2.add(jButton3, gridBagConstraints);

        jLabel10.setText("D³ugoœæ kolejki:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        faza2.add(jLabel10, gridBagConstraints);

        jTextField4.setText("6000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        faza2.add(jTextField4, gridBagConstraints);

        jPanel2.add(faza2);

        faza3.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Faza 3 (Szukanie referencji miêdzy wydzielonymi stronami):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        faza3.add(jLabel4, gridBagConstraints);

        jButton4.setText("Uruchom");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.01;
        faza3.add(jButton4, gridBagConstraints);

        jPanel2.add(faza3);

        faza4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Faza 4 (Szukanie referencji miêdzy wikioccurences a wszystkimi rekordami):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        faza4.add(jLabel5, gridBagConstraints);

        jButton5.setText("Uruchom");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.01;
        faza4.add(jButton5, gridBagConstraints);

        jPanel2.add(faza4);

        faza5.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("Faza 5 (Szukanie stron poprzez referencje wg³¹b):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        faza5.add(jLabel7, gridBagConstraints);

        jButton6.setText("Uruchom");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        faza5.add(jButton6, gridBagConstraints);

        jLabel8.setText("G³êbokoœæ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        faza5.add(jLabel8, gridBagConstraints);

        jTextField3.setText("2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        faza5.add(jTextField3, gridBagConstraints);

        jPanel2.add(faza5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.9;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("S³owa kluczowe (oddzielone przecinkami):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.01;
        jPanel3.add(jLabel6, gridBagConstraints);

        jTextField2.setText("Informatics");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel3.add(jTextField2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.01;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("Postêp:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(postep_label, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jProgressBar1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setMinimumSize(new java.awt.Dimension(47, 14));
        jPanel5.setPreferredSize(new java.awt.Dimension(183, 44));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton7.setText("Uruchom");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel5.add(jButton7, gridBagConstraints);

        jLabel11.setText("Tytu³y wydzielonych stron:");
        jLabel11.setMaximumSize(new java.awt.Dimension(140, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(140, 14));
        jLabel11.setPreferredSize(new java.awt.Dimension(140, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel5.add(jLabel11, gridBagConstraints);

        jLabel12.setText("Iloœæ stron:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jLabel12, gridBagConstraints);

        jTextField6.setText("6000");
        jTextField6.setCaretPosition(1);
        jTextField6.setPreferredSize(new java.awt.Dimension(20, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        jPanel5.add(jTextField6, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(425, 120));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setPreferredSize(new java.awt.Dimension(42500, 6400));
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        getContentPane().add(jPanel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final JFileChooser fc = new JFileChooser("E:/");
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            Logger.getLogger(getClass().getName()).debug("Opening: " + file.getName() + ".");
            jTextField1.setText(file.getAbsolutePath());
        } else {
            Logger.getLogger(getClass().getName()).debug("Open command cancelled by user.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        postep_label.setText("Faza 1");
        Configuration.MAX_QUEUE_SIZE = Integer.parseInt(jTextField4.getText());
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                phase1();
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String[] splits = jTextField2.getText().split(",");

        postep_label.setText("Faza 2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                phase2(splits);
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String[] splits = jTextField2.getText().split(",");

        postep_label.setText("Faza 3");
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                phase3(splits);
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String[] splits = jTextField2.getText().split(",");

        postep_label.setText("Faza 4");
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                phase4(splits);
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String[] splits = jTextField2.getText().split(",");
        Integer depth = Integer.parseInt(jTextField3.getText());

        postep_label.setText("Faza 5");
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                phase5(splits[0], depth);
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                try {              
                    pagerank();
                } catch (FileNotFoundException ex) {
                    java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void setButtons(boolean to) {
        jButton1.setEnabled(to);
        jButton2.setEnabled(to);
        jButton3.setEnabled(to);
        jButton4.setEnabled(to);
        jButton5.setEnabled(to);
        jButton6.setEnabled(to);
        
        jTextField1.setEnabled(to);
        jTextField2.setEnabled(to);
        jTextField3.setEnabled(to);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    private void phase1() {
        // czyta plik
        WikiXMLReader reader = new WikiXMLReader(jTextField1.getText());

        // parsuje go do obiektu WikiPage
        WikiXMLPagesParser parser = new WikiXMLPagesParser();

        // czyta WikiPage, zamienia go na Page i wstawia do bazy
        PageIdProcessor processor = new PageIdProcessor();

        // sprawia, ¿e po przeczytaniu page'a jest on wysy³any do parsera
        reader.addObserver(parser);

        // sprawia ¿e po przeczytaniu i sparsowaniu obiekt wysy³any jest do
        // processora
        // ten wstawia go do bazy danych
        parser.addObserver(processor);

        // rozpocznij pracê
        reader.read();
    }

    private void phase2(String[] words) {
        // przygotowanie keywords
        for (String word : words) {
            Configuration.KEYWORDS.add(word);
        }

        // czyta plik
        WikiXMLReader reader = new WikiXMLReader(jTextField1.getText());

        // parsuje go do obiektu WikiPage
        WikiXMLPagesParser parser = new WikiXMLPagesParser();

        // czyta WikiPage, dodaje go do listy.
        OccurencesCounterProcessor processor = new OccurencesCounterProcessor();

        // sprawia, ¿e po przeczytaniu page'a jest on wysy³any do parsera
        reader.addObserver(parser);

        // sprawia ¿e po przeczytaniu i sparsowaniu obiekt wysy³any jest do
        // processora
        // processor dodaje elementy do listy (kolejka priorytetowa)
        parser.addObserver(processor);

        // rozpocznij pracê
        reader.read();

        // wstaw do bazy rekordy z kolekcji
        processor.finishJob();

        // wyczyœæ s³ówka
        Configuration.KEYWORDS.clear();
    }

    private void phase3(String[] words) {
        // przygotowanie keywords
        for (String word : words) {
            Configuration.KEYWORDS.add(word);
        }

        // stworzenie findera
        OccurencesFinder finder = new OccurencesFinder();

        // rozpoczêcie pracy
        finder.findReferences();

        // wyczyœæ s³ówka
        Configuration.KEYWORDS.clear();
    }

    private void phase4(String[] words) {
        // przygotowanie keywords
        for (String word : words) {
            Configuration.KEYWORDS.add(word);
        }

        // czyta plik
        WikiXMLReader reader = new WikiXMLReader(jTextField1.getText());

        // parsuje go do obiektu WikiPage
        WikiXMLPagesParser parser = new WikiXMLPagesParser();

        // stworzenie findera
        OccurencesFinderAll finder = new OccurencesFinderAll(jTextField1.getText());

        // sprawia, ¿e po przeczytaniu page'a jest on wysy³any do parsera
        reader.addObserver(parser);

        // sprawia ¿e po przeczytaniu i sparsowaniu obiekt wysy³any jest do
        // processora
        // processor dodaje elementy do listy (kolejka priorytetowa)
        parser.addObserver(finder);

        // rozpoczêcie pracy
        finder.findReferences();

        // rozpocznij pracê
        reader.read();

        // wyczyœæ s³ówka
        Configuration.KEYWORDS.clear();
    }

    private void phase5(String word, int depth) {
        GraphFileReader reader = new GraphFileReader(jTextField1.getText());
        reader.readLayer(word, depth);
    }
    
    private void pagerank() throws FileNotFoundException{
        int pageCount = Integer.parseInt(jTextField6.getText());
        Gauss gauss =  new Gauss(pageCount);
        Map<Integer, Double> pagerankResults = gauss.solveGaussSeidel();
        jTextArea1.setRows(pagerankResults.size());
        int i = 1;
        for(Map.Entry<Integer, Double> entry : pagerankResults.entrySet()){
            String title = gauss.findTitle(entry.getKey());
            jTextArea1.append(Integer.toString(i++)+".");
            jTextArea1.append(title+"   ");
            Double value = BigDecimal.valueOf(entry.getValue())
                .setScale(6, RoundingMode.HALF_UP)
                .doubleValue();
            jTextArea1.append(value+"\n");
        }
        gauss.closeSession();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel faza1;
    private javax.swing.JPanel faza2;
    private javax.swing.JPanel faza3;
    private javax.swing.JPanel faza4;
    private javax.swing.JPanel faza5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel postep_label;
    // End of variables declaration//GEN-END:variables
}
