/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eti.wiki.ui;

import com.eti.wiki.Configuration;
import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.io.WikiXMLReader;
import com.eti.wiki.parser.WikiXMLPagesParser;
import com.eti.wiki.parser.phase1.PageIdProcessor;
import com.eti.wiki.parser.phase2.OccurencesCounterProcessor;
import com.eti.wiki.parser.phase3.OccurencesFinder;
import com.eti.wiki.parser.phase4.OccurencesFinderAll;
import com.eti.wiki.parser.phase5.GraphFileReader;
import com.eti.wiki.pagerank.Gauss;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultCaret;

import org.hibernate.Session;
import org.jboss.logging.Logger;

public class MainWindow extends javax.swing.JFrame implements IParsingProgressListener {

    public MainWindow() {
        initComponents();
    }

    @Override
    public void progressChanged(int howMuch, int outOf) {
        jProgressBar1.setValue((int) (100.0 * ((double) ((double) howMuch / (double) outOf))));
        statusLabel.setText("Aktualnie przetwarzana strona: " + howMuch + " z " + outOf);
    }

    @Override
    public void currentlyProcessedPageChanged(String toPage) {
        statusLabel2.setText(toPage);
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
        faza1Btn = new javax.swing.JButton();
        btnFaza1 = new javax.swing.JButton();
        faza2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        faza2Btn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        btnFaza2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        threshhold = new javax.swing.JTextField();
        faza3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        faza3Btn = new javax.swing.JButton();
        btnFaza3 = new javax.swing.JButton();
        faza4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        faza4Btn = new javax.swing.JButton();
        btnFaza4 = new javax.swing.JButton();
        faza5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        faza5Btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnFaza5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        postep_label = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();
        statusLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        String[] columnNames = {"Lp.",
                "Tytu³",
                "PageRank"};
        jTable1 = new javax.swing.JTable(new DefaultTableModel(columnNames, 0));
   //     TableCellRenderer renderer = new DefaultTableCellRenderer();
 //       ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
  //      jTable1.getColumnModel().getColumn(1).setHeaderRenderer(renderer);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jButton2 = new javax.swing.JButton();
        
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 700));
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

        faza1Btn.setText("Uruchom");
        faza1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faza1BtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.01;
        faza1.add(faza1Btn, gridBagConstraints);

        btnFaza1.setText("Wyœwietl");
        btnFaza1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaza1ActionPerformed(evt);
            }
        });
        faza1.add(btnFaza1, new java.awt.GridBagConstraints());

        jPanel2.add(faza1);

        faza2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Faza 2 (Zliczanie wyst¹pieñ wikioccurences):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        faza2.add(jLabel3, gridBagConstraints);

        faza2Btn.setText("Uruchom");
        faza2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faza2BtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.01;
        faza2.add(faza2Btn, gridBagConstraints);

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

        btnFaza2.setText("Wyœwietl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        faza2.add(btnFaza2, gridBagConstraints);

        jLabel13.setText("Threshhold:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        faza2.add(jLabel13, gridBagConstraints);

        threshhold.setText("5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        faza2.add(threshhold, gridBagConstraints);

        jPanel2.add(faza2);

        faza3.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Faza 3 (Szukanie referencji miêdzy wydzielonymi stronami):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        faza3.add(jLabel4, gridBagConstraints);

        faza3Btn.setText("Uruchom");
        faza3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faza3BtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.01;
        faza3.add(faza3Btn, gridBagConstraints);

        btnFaza3.setText("Wyœwietl");
        faza3.add(btnFaza3, new java.awt.GridBagConstraints());

        jPanel2.add(faza3);

        faza4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Faza 4 (Szukanie referencji miêdzy wikioccurences a wszystkimi rekordami):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        faza4.add(jLabel5, gridBagConstraints);

        faza4Btn.setText("Uruchom");
        faza4Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faza4BtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.01;
        faza4.add(faza4Btn, gridBagConstraints);

        btnFaza4.setText("Wyœwietl");
        faza4.add(btnFaza4, new java.awt.GridBagConstraints());

        jPanel2.add(faza4);

        faza5.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("Faza 5 (Szukanie stron poprzez referencje wg³¹b):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        faza5.add(jLabel7, gridBagConstraints);

        faza5Btn.setText("Uruchom");
        faza5Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faza5BtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        faza5.add(faza5Btn, gridBagConstraints);

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

        btnFaza5.setText("Wyœwietl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        faza5.add(btnFaza5, gridBagConstraints);

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

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        statusLabel.setMinimumSize(new java.awt.Dimension(300, 15));
        statusLabel.setPreferredSize(new java.awt.Dimension(300, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(statusLabel, gridBagConstraints);

        statusLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        statusLabel2.setMinimumSize(new java.awt.Dimension(300, 15));
        statusLabel2.setPreferredSize(new java.awt.Dimension(300, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel4.add(statusLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel5.add(jButton7, gridBagConstraints);

        jLabel11.setText("Tytu³y wydzielonych stron:");
        jLabel11.setMaximumSize(new java.awt.Dimension(140, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(140, 14));
        jLabel11.setPreferredSize(new java.awt.Dimension(140, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
    //    jPanel5.add(jLabel11, gridBagConstraints);

        jLabel12.setText("Iloœæ stron:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel5.add(jLabel12, gridBagConstraints);

        jTextField6.setText("6000");
        jTextField6.setCaretPosition(1);
        jTextField6.setPreferredSize(new java.awt.Dimension(20, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel5.add(jTextField6, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(425, 120));

      /*  jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));*/
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel5.add(jScrollPane1, gridBagConstraints);

        jButton2.setText("Wyœwietl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 10);
        jPanel5.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
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

    private void faza1BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faza1BtnActionPerformed
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
    }//GEN-LAST:event_faza1BtnActionPerformed

    private void faza2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faza2BtnActionPerformed
        try {
            Configuration.OCCURENCES_THRESHOLD = Integer.parseInt(threshhold.getText());
        } catch (Exception e) {
            statusLabel.setText("Niepoprawna wartoœæ pola Threshhold!");
            return;
        }

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
    }//GEN-LAST:event_faza2BtnActionPerformed

    private void faza3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faza3BtnActionPerformed
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
    }//GEN-LAST:event_faza3BtnActionPerformed

    private void faza4BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faza4BtnActionPerformed
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
    }//GEN-LAST:event_faza4BtnActionPerformed

    private void faza5BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faza5BtnActionPerformed
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
    }//GEN-LAST:event_faza5BtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                setButtons(false);
                try {
                	jProgressBar1.setValue(0);
                    pagerank();
                    jProgressBar1.setValue(100);
                } catch (FileNotFoundException ex) {
                    java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                setButtons(true);
            }
        }).start();
    }//GEN-LAST:event_jButton7ActionPerformed
    
    private void jButton2ActionPerformed(ActionEvent evt) {
    	if(jTable1.getRowCount() > 0)
    	{
	    	int rowIndex = this.jTable1.getSelectedRow();
	    	if(rowIndex != -1)
	    	{
	    		String title = this.jTable1.getValueAt(rowIndex, 1).toString();
	    		statusLabel.setText("Wczytywanie zawartoœci strony");
	    		String content = parseContent(returnContent(title));
	    		createFrame(title, content);
	    		statusLabel.setText("");
	    	}
    	}
		
	}
    
    public String parseContent(String content) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < content.length(); i++)
		{
			char c = content.charAt(i);
			if(i%240 == 0)
			{
				sb.append("\n");
				
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public static void createFrame(String title, String content)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame(title);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try 
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                   e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JTextArea textArea = new JTextArea(50,150);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));
                JScrollPane scroller = new JScrollPane(textArea);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                textArea.setText(content);
            //    JTextField input = new JTextField(20);
           //     JButton button = new JButton("Enter");
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
          //      inputpanel.add(input);
         //       inputpanel.add(button);
                panel.add(inputpanel);
                scroller.setSize(10, 10);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
         //       input.requestFocus();
            }
        });
    }
    private void btnFaza1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaza1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFaza1ActionPerformed

    private void setButtons(boolean to) {
        jButton1.setEnabled(to);
        faza1Btn.setEnabled(to);
        faza2Btn.setEnabled(to);
        faza3Btn.setEnabled(to);
        faza4Btn.setEnabled(to);
        faza5Btn.setEnabled(to);

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
        PageIdProcessor processor = new PageIdProcessor(this);

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
        OccurencesCounterProcessor processor = new OccurencesCounterProcessor(this);

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
        OccurencesFinder finder = new OccurencesFinder(this);

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
        OccurencesFinderAll finder = new OccurencesFinderAll(jTextField1.getText(), this);

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
        GraphFileReader reader = new GraphFileReader(jTextField1.getText(), this, depth);
        reader.readLayer(word, depth);
    }

    private void pagerank() throws FileNotFoundException {
        int pageCount = Integer.parseInt(jTextField6.getText());
        Gauss gauss = new Gauss(pageCount, this.jProgressBar1, this.statusLabel);
        Map<Integer, Double> pagerankResults = gauss.solveGaussSeidel(this.jProgressBar1, this.statusLabel);
        int i = 1;
        for (Map.Entry<Integer, Double> entry : pagerankResults.entrySet()) {
            String title = gauss.findTitle(entry.getKey());
            Double value = BigDecimal.valueOf(entry.getValue())
                    .setScale(6, RoundingMode.HALF_UP)
                    .doubleValue();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{Integer.toString(i++), title, value});
       //     gauss.saveToDatabase(i++, title);
        }
    }
    public String returnContent(String title)
    {
    	Session session = DatabaseSession.getSessionFactory().getCurrentSession();
    	String content = "";
    	try
    	{
    		session.getTransaction().begin();
            String sqlQuery = "SELECT content FROM wikioccurences WHERE title='"+ title+"'";
            List<Object> references = (List<Object>)session.createSQLQuery(sqlQuery).list();
            Iterator<Object> itr = references.iterator();
            while(itr.hasNext())
            {
                content = (String) itr.next();
            }
            session.getTransaction().commit();	
    	}
    	catch (RuntimeException e)
    	{
    	    session.getTransaction().rollback();
    	    throw e;
    	}
    	return content;
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFaza1;
    private javax.swing.JButton btnFaza2;
    private javax.swing.JButton btnFaza3;
    private javax.swing.JButton btnFaza4;
    private javax.swing.JButton btnFaza5;
    private javax.swing.JPanel faza1;
    private javax.swing.JButton faza1Btn;
    private javax.swing.JPanel faza2;
    private javax.swing.JButton faza2Btn;
    private javax.swing.JPanel faza3;
    private javax.swing.JButton faza3Btn;
    private javax.swing.JPanel faza4;
    private javax.swing.JButton faza4Btn;
    private javax.swing.JPanel faza5;
    private javax.swing.JButton faza5Btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel postep_label;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusLabel2;
    private javax.swing.JTextField threshhold;
    // End of variables declaration//GEN-END:variables
}
