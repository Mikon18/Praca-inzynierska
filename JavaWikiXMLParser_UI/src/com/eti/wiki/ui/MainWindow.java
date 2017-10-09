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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultCaret;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.jboss.logging.Logger;
import org.sweble.wikitext.engine.PageId;
import org.sweble.wikitext.engine.PageTitle;
import org.sweble.wikitext.engine.WtEngineImpl;
import org.sweble.wikitext.engine.config.WikiConfig;
import org.sweble.wikitext.engine.nodes.EngProcessedPage;
import org.sweble.wikitext.engine.output.HtmlRenderer;
import org.sweble.wikitext.engine.utils.DefaultConfigEnWp;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

public class MainWindow extends javax.swing.JFrame implements IParsingProgressListener {

	private IStoppable runningTask = null;
	
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
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new javax.swing.JPanel();
		jPanel1.setBorder(new EmptyBorder(10, 10, 0, 10));
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jPanel2.setBorder(new EmptyBorder(0, 10, 0, 10));
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
		jPanel3.setBorder(new EmptyBorder(0, 10, 0, 10));
		jLabel6 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jPanel4 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		postep_label = new javax.swing.JLabel();
		jProgressBar1 = new javax.swing.JProgressBar();
		statusLabel = new javax.swing.JLabel();
		statusLabel2 = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		String[] columnNames = { "Lp.", "Tytu³", "PageRank", "Id" };
		btnFaza2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFazaActionPerformed("wikioccurences");
			}
		});
		btnFaza3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFazaActionPerformed("wikireferencekeyword");
			}
		});
		btnFaza4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFazaActionPerformed("wikireferencekeywordall");
			}
		});
		btnFaza5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFazaActionPerformed("wikireference");
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1024, 768));
		setPreferredSize(new Dimension(1024, 768));
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

		jLabel2.setText("Faza 0 (Wydzielanie ID oraz tytu³u strony do tabeli wikipage):");
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_1.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints_1.weightx = 1.0;
		faza1.add(jLabel2, gridBagConstraints_1);

		faza1Btn.setText("Uruchom");
		faza1Btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				faza1BtnActionPerformed(evt);
			}
		});
		gridBagConstraints_2 = new java.awt.GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_2.gridx = 1;
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_2.anchor = java.awt.GridBagConstraints.LINE_END;
		gridBagConstraints_2.weightx = 0.01;
		faza1.add(faza1Btn, gridBagConstraints_2);

		btnFaza1.setText("Wyœwietl");
		btnFaza1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFaza1ActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_btnFaza1 = new GridBagConstraints();
		gbc_btnFaza1.insets = new Insets(0, 0, 0, 5);
		gbc_btnFaza1.gridx = 2;
		gbc_btnFaza1.gridy = 0;
		faza1.add(btnFaza1, gbc_btnFaza1);

		jPanel2.add(faza1);

		clearWikiPages = new JButton("Wyczy\u015B\u0107 tabel\u0119");
		clearWikiPages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int howMany = truncateTable("wikipage");
					JOptionPane.showMessageDialog(MainWindow.this, "Usuniêto: " + howMany + " rekordów.",
							"Wynik usuwania", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLGrammarException exc) {
					JOptionPane.showMessageDialog(MainWindow.this,
							"Nie uda³o siê wyczyœciæ tabeli. Nale¿y najpierw wyczyœciæ tabelê wikireference (faza 4).",
							"B³¹d bazy danych", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_clearWikiPages = new GridBagConstraints();
		gbc_clearWikiPages.gridx = 3;
		gbc_clearWikiPages.gridy = 0;
		faza1.add(clearWikiPages, gbc_clearWikiPages);

		GridBagLayout gbl_faza2 = new GridBagLayout();
		gbl_faza2.rowHeights = new int[] { 0 };
		gbl_faza2.columnWidths = new int[] { 0 };
		faza2.setLayout(gbl_faza2);

		jLabel3.setText("Faza 1 (Zliczanie wyst¹pieñ wikioccurences):");
		gridBagConstraints_3 = new java.awt.GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_3.gridx = 0;
		gridBagConstraints_3.gridy = 0;
		gridBagConstraints_3.gridheight = 2;
		gridBagConstraints_3.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_3.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints_3.weightx = 1.0;
		faza2.add(jLabel3, gridBagConstraints_3);

		faza2Btn.setText("Uruchom");
		faza2Btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				faza2BtnActionPerformed(evt);
			}
		});
		gridBagConstraints_5 = new java.awt.GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_5.gridx = 3;
		gridBagConstraints_5.gridy = 0;
		gridBagConstraints_5.gridheight = 2;
		gridBagConstraints_5.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_5.anchor = java.awt.GridBagConstraints.LINE_END;
		gridBagConstraints_5.weightx = 0.01;
		faza2.add(faza2Btn, gridBagConstraints_5);

		jLabel10.setText("D³ugoœæ kolejki:");
		gridBagConstraints_6 = new java.awt.GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_6.gridx = 1;
		gridBagConstraints_6.gridy = 0;
		faza2.add(jLabel10, gridBagConstraints_6);

		jTextField4.setText("6000");
		gridBagConstraints_4 = new java.awt.GridBagConstraints();
		gridBagConstraints_4.gridx = 2;
		gridBagConstraints_4.gridy = 0;
		gridBagConstraints_4.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_4.weightx = 0.5;
		gridBagConstraints_4.insets = new Insets(0, 10, 5, 10);
		faza2.add(jTextField4, gridBagConstraints_4);

		btnFaza2.setText("Wyœwietl");
		gridBagConstraints_7 = new java.awt.GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_7.gridx = 4;
		gridBagConstraints_7.gridy = 0;
		gridBagConstraints_7.gridheight = 2;
		faza2.add(btnFaza2, gridBagConstraints_7);

		clearWikiOccurences = new JButton("Wyczy\u015B\u0107 tabel\u0119");
		clearWikiOccurences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int howMany = truncateTable("wikioccurences");
					JOptionPane.showMessageDialog(MainWindow.this, "Usuniêto: " + howMany + " rekordów.",
							"Wynik usuwania", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLGrammarException exc) {
					JOptionPane.showMessageDialog(MainWindow.this,
							"Nie uda³o siê wyczyœciæ tabeli. Nale¿y najpierw wyczyœciæ tabelê wikireference (faza 4).",
							"B³¹d bazy danych", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_clearWikiOccurences = new GridBagConstraints();
		gbc_clearWikiOccurences.gridheight = 2;
		gbc_clearWikiOccurences.gridx = 5;
		gbc_clearWikiOccurences.gridy = 0;
		faza2.add(clearWikiOccurences, gbc_clearWikiOccurences);
		jLabel13 = new javax.swing.JLabel();

		jLabel13.setText("Threshhold:");
		gridBagConstraints_8 = new java.awt.GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_8.gridx = 1;
		gridBagConstraints_8.gridy = 1;
		faza2.add(jLabel13, gridBagConstraints_8);
		threshhold = new javax.swing.JTextField();
		threshhold.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String thr = threshhold.getText();
				try {
					Integer thrInt = Integer.parseInt(thr);
					if (thrInt < 0) {
						threshhold.setText("0");
					}
				} catch (Exception excd) {
					threshhold.setText("0");
				}
			}
		});

		threshhold.setText("5");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.insets = new Insets(0, 10, 0, 10);
		faza2.add(threshhold, gridBagConstraints);

		jPanel2.add(faza2);

		faza3.setLayout(new java.awt.GridBagLayout());

		jLabel4.setText("Faza 2 (Szukanie referencji miêdzy wydzielonymi stronami):");
		gridBagConstraints_9 = new java.awt.GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_9.gridx = 0;
		gridBagConstraints_9.gridy = 0;
		gridBagConstraints_9.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_9.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints_9.weightx = 1.0;
		faza3.add(jLabel4, gridBagConstraints_9);

		faza3Btn.setText("Uruchom");
		faza3Btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				faza3BtnActionPerformed(evt);
			}
		});
		gridBagConstraints_10 = new java.awt.GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_10.gridx = 1;
		gridBagConstraints_10.gridy = 0;
		gridBagConstraints_10.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_10.weightx = 0.01;
		faza3.add(faza3Btn, gridBagConstraints_10);

		btnFaza3.setText("Wyœwietl");
		GridBagConstraints gbc_btnFaza3 = new GridBagConstraints();
		gbc_btnFaza3.insets = new Insets(0, 0, 0, 5);
		gbc_btnFaza3.gridx = 2;
		gbc_btnFaza3.gridy = 0;
		faza3.add(btnFaza3, gbc_btnFaza3);

		jPanel2.add(faza3);

		clearWikiReferencesKeyword = new JButton("Wyczy\u015B\u0107 tabel\u0119");
		clearWikiReferencesKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int howMany = truncateTable("wikireferencekeyword");
					JOptionPane.showMessageDialog(MainWindow.this, "Usuniêto: " + howMany + " rekordów.",
							"Wynik usuwania", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLGrammarException exc) {
					JOptionPane.showMessageDialog(MainWindow.this,
							"Nie uda³o siê wyczyœciæ tabeli. Nale¿y najpierw wyczyœciæ tabelê wikireference (faza 4).",
							"B³¹d bazy danych", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_clearWikiReferencesKeyword = new GridBagConstraints();
		gbc_clearWikiReferencesKeyword.gridx = 3;
		gbc_clearWikiReferencesKeyword.gridy = 0;
		faza3.add(clearWikiReferencesKeyword, gbc_clearWikiReferencesKeyword);

		faza4.setLayout(new java.awt.GridBagLayout());

		jLabel5.setText("Faza 3 (Szukanie referencji miêdzy wikioccurences a wszystkimi rekordami):");
		gridBagConstraints_11 = new java.awt.GridBagConstraints();
		gridBagConstraints_11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_11.gridx = 0;
		gridBagConstraints_11.gridy = 0;
		gridBagConstraints_11.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_11.weightx = 1.0;
		faza4.add(jLabel5, gridBagConstraints_11);

		faza4Btn.setText("Uruchom");
		faza4Btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				faza4BtnActionPerformed(evt);
			}
		});
		gridBagConstraints_12 = new java.awt.GridBagConstraints();
		gridBagConstraints_12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_12.gridx = 1;
		gridBagConstraints_12.gridy = 0;
		gridBagConstraints_12.weightx = 0.01;
		faza4.add(faza4Btn, gridBagConstraints_12);

		btnFaza4.setText("Wyœwietl");
		GridBagConstraints gbc_btnFaza4 = new GridBagConstraints();
		gbc_btnFaza4.insets = new Insets(0, 0, 0, 5);
		gbc_btnFaza4.gridx = 2;
		gbc_btnFaza4.gridy = 0;
		faza4.add(btnFaza4, gbc_btnFaza4);

		jPanel2.add(faza4);

		clearWikiReferenceKeywordAll = new JButton("Wyczy\u015B\u0107 tabel\u0119");
		clearWikiReferenceKeywordAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int howMany = truncateTable("wikireferencekeywordall");
					JOptionPane.showMessageDialog(MainWindow.this, "Tabela `wikireferencekeywordall` wyczyszczona.",
							"Wynik usuwania", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLGrammarException exc) {
					JOptionPane.showMessageDialog(MainWindow.this,
							"Nie uda³o siê wyczyœciæ tabeli. Nale¿y najpierw wyczyœciæ tabelê wikireference (faza 4).",
							"B³¹d bazy danych", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_clearWikiReferenceKeywordAll = new GridBagConstraints();
		gbc_clearWikiReferenceKeywordAll.gridx = 3;
		gbc_clearWikiReferenceKeywordAll.gridy = 0;
		faza4.add(clearWikiReferenceKeywordAll, gbc_clearWikiReferenceKeywordAll);

		faza5.setLayout(new java.awt.GridBagLayout());

		jLabel7.setText("Faza 4 (Szukanie stron poprzez referencje wg³¹b):");
		gridBagConstraints_13 = new java.awt.GridBagConstraints();
		gridBagConstraints_13.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_13.gridx = 0;
		gridBagConstraints_13.gridy = 0;
		gridBagConstraints_13.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_13.weightx = 1.0;
		faza5.add(jLabel7, gridBagConstraints_13);

		faza5Btn.setText("Uruchom");
		faza5Btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				faza5BtnActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(0, 10, 5, 5);
		faza5.add(faza5Btn, gridBagConstraints);

		jLabel8.setText("G³êbokoœæ:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(0, 10, 5, 5);
		faza5.add(jLabel8, gridBagConstraints);

		jTextField3.setText("2");
		gridBagConstraints_14 = new java.awt.GridBagConstraints();
		gridBagConstraints_14.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_14.gridx = 2;
		gridBagConstraints_14.gridy = 0;
		gridBagConstraints_14.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_14.weightx = 0.2;
		faza5.add(jTextField3, gridBagConstraints_14);

		btnFaza5.setText("Wyœwietl");
		gridBagConstraints_15 = new java.awt.GridBagConstraints();
		gridBagConstraints_15.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_15.gridx = 4;
		gridBagConstraints_15.gridy = 0;
		faza5.add(btnFaza5, gridBagConstraints_15);

		jPanel2.add(faza5);

		clearWikiReferences = new JButton("Wyczy\u015B\u0107 tabel\u0119");
		clearWikiReferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int howMany = truncateTable("wikireference");
					JOptionPane.showMessageDialog(MainWindow.this, "Tabela `wikireference` wyczyszczona.",
							"Wynik usuwania", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLGrammarException exc) {
					JOptionPane.showMessageDialog(MainWindow.this,
							"Nie uda³o siê wyczyœciæ tabeli. Nale¿y najpierw wyczyœciæ tabelê wikireference (faza 4).",
							"B³¹d bazy danych", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_clearWikiReferences = new GridBagConstraints();
		gbc_clearWikiReferences.insets = new Insets(0, 0, 5, 0);
		gbc_clearWikiReferences.gridx = 5;
		gbc_clearWikiReferences.gridy = 0;
		faza5.add(clearWikiReferences, gbc_clearWikiReferences);

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

		jPanel4.setBorder(new EmptyBorder(0, 10, 0, 10));
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

		jPanel5.setBorder(new EmptyBorder(0, 10, 0, 10));
		GridBagLayout gbl_jPanel5 = new GridBagLayout();
		gbl_jPanel5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		jPanel5.setLayout(gbl_jPanel5);
		jLabel12 = new javax.swing.JLabel();
		// jPanel5.add(jLabel11, gridBagConstraints);

		jLabel12.setText("Maksymalna ilo\u015B\u0107 stron:");
		gridBagConstraints_17 = new java.awt.GridBagConstraints();
		gridBagConstraints_17.insets = new Insets(10, 0, 5, 0);
		gridBagConstraints_17.gridy = 0;
		gridBagConstraints_17.gridx = 0;
		gridBagConstraints_17.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_17.weightx = 1.0;
		jPanel5.add(jLabel12, gridBagConstraints_17);
		jTextField6 = new javax.swing.JTextField();
		
				jTextField6.setText("6000");
				jTextField6.setCaretPosition(1);
				jTextField6.setPreferredSize(new Dimension(30, 30));
				gridBagConstraints_16 = new java.awt.GridBagConstraints();
				gridBagConstraints_16.insets = new Insets(0, 0, 5, 145);
				gridBagConstraints_16.gridy = 0;
				gridBagConstraints_16.gridx = 1;
				gridBagConstraints_16.fill = java.awt.GridBagConstraints.BOTH;
				gridBagConstraints_16.weightx = 1.0;
				jPanel5.add(jTextField6, gridBagConstraints_16);

		jLabel11.setText("Tytu³y wydzielonych stron:");
		jLabel11.setMaximumSize(new java.awt.Dimension(140, 14));
		jLabel11.setMinimumSize(new java.awt.Dimension(140, 14));
		jLabel11.setPreferredSize(new java.awt.Dimension(140, 14));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
				
				btnZapisz = new JButton("Zapisz");
				btnZapisz.setEnabled(false);
				btnZapisz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveToFile();
					}

				});
				jButton7 = new javax.swing.JButton();
				
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
						gridBagConstraints.insets = new Insets(5, 15, 5, 15);
						jPanel5.add(jButton7, gridBagConstraints);
				
						breaker = new JButton("Przerwij");
						breaker.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (runningTask != null) {
									runningTask.stop();
								}
							}
						});
						GridBagConstraints gbc_breaker = new GridBagConstraints();
						gbc_breaker.fill = GridBagConstraints.BOTH;
						gbc_breaker.insets = new Insets(5, 15, 5, 15);
						gbc_breaker.gridx = 3;
						gbc_breaker.gridy = 0;
						jPanel5.add(breaker, gbc_breaker);
				jButton2 = new javax.swing.JButton();
				jButton2.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButton2ActionPerformed(evt);
					}
				});
				
						jButton2.setText("Wyœwietl");
						gridBagConstraints = new java.awt.GridBagConstraints();
						gridBagConstraints.gridx = 4;
						gridBagConstraints.gridy = 0;
						gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
						gridBagConstraints.insets = new Insets(5, 15, 5, 15);
						jPanel5.add(jButton2, gridBagConstraints);
				GridBagConstraints gbc_btnZapisz = new GridBagConstraints();
				gbc_btnZapisz.insets = new Insets(5, 15, 5, 15);
				gbc_btnZapisz.gridx = 5;
				gbc_btnZapisz.gridy = 0;
				jPanel5.add(btnZapisz, gbc_btnZapisz);

		/*
		 * jTable1.setModel(new javax.swing.table.DefaultTableModel( new Object
		 * [][] { {null, null, null, null}, {null, null, null, null}, {null,
		 * null, null, null}, {null, null, null, null} }, new String [] {
		 * "Title 1", "Title 2", "Title 3", "Title 4" } ));
		 */

		gridBagConstraints_18 = new java.awt.GridBagConstraints();
		gridBagConstraints_18.weighty = 1.0;
		gridBagConstraints_18.gridy = 4;
		gridBagConstraints_18.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints_18.weightx = 1.0;
		gridBagConstraints_18.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jPanel5, gridBagConstraints_18);
						jScrollPane1 = new javax.swing.JScrollPane();
						jTable1 = new javax.swing.JTable(new DefaultTableModel(columnNames, 0)) {
							private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
						};
						// TableCellRenderer renderer = new DefaultTableCellRenderer();
						// ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
						// jTable1.getColumnModel().getColumn(1).setHeaderRenderer(renderer);
						jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
						jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
						
								jScrollPane1.setPreferredSize(new Dimension(800, 120));
								jScrollPane1.setViewportView(jTable1);
								
										gridBagConstraints = new java.awt.GridBagConstraints();
										gridBagConstraints.gridx = 0;
										gridBagConstraints.gridy = 1;
										gridBagConstraints.gridwidth = 9;
										gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
										gridBagConstraints.weightx = 1.0;
										gridBagConstraints.weighty = 1.0;
										gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
										jPanel5.add(jScrollPane1, gridBagConstraints);

		String[] columnNames2 = { "Tytu³" };
		jTable2 = new JTable(new DefaultTableModel(columnNames2, 0)) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
        };};
		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnFazaActionPerformed(String tableName) {
		createPhaseFrame(tableName);
	}
	private void saveToFile() {
		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		jfc.setAcceptAllFileFilterUsed(false);
		int option = jfc.showSaveDialog(MainWindow.this);
		if(option == JFileChooser.APPROVE_OPTION) {
			String ext = jfc.getFileFilter().getDescription();
			if(ext.equals("*.txt")) {
				try {
					PrintWriter pr = new PrintWriter(jfc.getSelectedFile() + ".txt");
					DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
					for(int i = 0; i < model.getRowCount(); i++) {
						for(int j = 0; j < model.getColumnCount(); j++) {
							pr.print(model.getValueAt(i,j));
							pr.print("\t");
						}
						pr.println();						
					}
					pr.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private int truncateTable(String tableName) {
		String hql = String.format("truncate table %s", tableName);
		Query query = DatabaseSession.getSessionFactory().openSession().createSQLQuery(hql);
		return query.executeUpdate();
	}
	private void createDisplayFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame();
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
                JScrollPane scroller = new JScrollPane();
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                addPageRankTitles();
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField input = new JTextField(20);
                input.setEditable(false);
                JButton button = new JButton("Wyœwietl zawartoœæ");
                button.setEnabled(false);
                panel.add(scroller);
                inputpanel.add(input);
                inputpanel.add(button);
                panel.add(inputpanel);
                jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    public void valueChanged(ListSelectionEvent event) {
                    	button.setEnabled(true);
                    	int rowIndex = jTable2.getSelectedRow();
                        input.setText(jTable2.getValueAt(rowIndex, 0).toString());
                    }
                });            
                button.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                    	if(jTable2.getRowCount() > 0)
                    	{
                	    	int rowIndex = jTable2.getSelectedRow();
                	    	if(rowIndex != -1)
                	    	{
                	    		String title = input.getText();
                	    		String content = parseContent(returnContent(title));
                	    		createFrame(title, content);
                	    	}
                    	}
                    }
                });
                scroller.setViewportView(jTable2);
                panel.add(scroller);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(true);
            }
        });
    }

	private static void createPhaseFrame(String tableName) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setOpaque(true);
				JScrollPane scroller = new JScrollPane();
				scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				addTitles(tableName);
				JPanel inputpanel = new JPanel();
				inputpanel.setLayout(new FlowLayout());
				JTextField input = new JTextField(20);
				JButton button = new JButton("Wyœwietl zawartoœæ");
				panel.add(scroller);
				inputpanel.add(input);
				inputpanel.add(button);
				panel.add(inputpanel);
				jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						int rowIndex = jTable2.getSelectedRow();
						if (rowIndex != -1) {
							input.setText(jTable2.getValueAt(rowIndex, 0).toString());
						}
					}
				});
				button.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						if (jTable2.getRowCount() > 0) {
							int rowIndex = jTable2.getSelectedRow();
							if (rowIndex != -1) {
								String title = input.getText();
								String content = parseContent(returnContent(title));
								createFrame(title, content);
							}
						}
					}
				});
				scroller.setViewportView(jTable2);
				panel.add(scroller);
				frame.getContentPane().add(BorderLayout.CENTER, panel);
				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				frame.setResizable(true);
			}
		});
	}

	private static void addTitles(String table) {
		Object title;
		Session session = DatabaseSession.getSessionFactory().getCurrentSession();
		Transaction t = session.getTransaction();
		try {
			DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i > -1; i--) {
					model.removeRow(i);
				}
			}

			t.begin();
			String sqlQuery = null;
			if (table.contains("reference")) {
				sqlQuery = "SELECT reference_id FROM " + table;
			} else {
				sqlQuery = "SELECT title FROM " + table;
			}

			@SuppressWarnings("unchecked")
			List<Object> references = (List<Object>) session.createSQLQuery(sqlQuery).list();
			Iterator<Object> itr = references.iterator();
			while (itr.hasNext()) {
				title = itr.next();

				model.addRow(new Object[] { title });
			}
			t.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void addPageRankTitles() {
		DefaultTableModel sourceModel = (DefaultTableModel) jTable1.getModel();
		DefaultTableModel destModel = (DefaultTableModel) jTable2.getModel();
		for(int i = 0; i < sourceModel.getRowCount(); i++) {
			destModel.addRow(new Object[]{sourceModel.getValueAt(i,1)});	
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		final JFileChooser fc = new JFileChooser("E:/");
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			Logger.getLogger(getClass().getName()).debug("Opening: " + file.getName() + ".");
			jTextField1.setText(file.getAbsolutePath());
		} else {
			Logger.getLogger(getClass().getName()).debug("Open command cancelled by user.");
		}
	}// GEN-LAST:event_jButton1ActionPerformed

	private void faza1BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_faza1BtnActionPerformed
		postep_label.setText("Faza 1");
		// Integer intQueueSize = Integer.parseInt(jTextField4.getText());
		// if(intQueueSize < 0){
		// JOptionPane.showMessageDialog(MainWindow.this,
		// "Zwiêksz rozmiar kolejki.", "Niepoprawna wartoœæ",
		// JOptionPane.ERROR_MESSAGE);
		// return;
		// }
		// Configuration.MAX_QUEUE_SIZE = intQueueSize;
		new Thread(new Runnable() {
			@Override
			public void run() {
				setButtons(false);
				phase1();
				setButtons(true);
			}
		}).start();
	}// GEN-LAST:event_faza1BtnActionPerformed

	private void faza2BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_faza2BtnActionPerformed
		Integer intQueueSize = Integer.parseInt(jTextField4.getText());
		if (intQueueSize < 0) {
			JOptionPane.showMessageDialog(MainWindow.this, "Zwiêksz rozmiar kolejki.", "Niepoprawna wartoœæ",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Configuration.MAX_QUEUE_SIZE = intQueueSize;
		try {
			Integer intThreshhold = Integer.parseInt(threshhold.getText());
			if (intThreshhold < 0) {
				throw new NumberFormatException();
			}
			Configuration.OCCURENCES_THRESHOLD = intThreshhold;
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
	}// GEN-LAST:event_faza2BtnActionPerformed

	private void faza3BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_faza3BtnActionPerformed
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
	}// GEN-LAST:event_faza3BtnActionPerformed

	private void faza4BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_faza4BtnActionPerformed
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
	}// GEN-LAST:event_faza4BtnActionPerformed

	private void faza5BtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_faza5BtnActionPerformed
		String[] splits = jTextField2.getText().split(",");
		Integer depth = Integer.parseInt(jTextField3.getText());

		if (depth < 0) {
			JOptionPane.showMessageDialog(MainWindow.this, "Z³a wartoœæ pola G³êbokoœæ.", "Niepoprawna wartoœæ",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		postep_label.setText("Faza 5");
		new Thread(new Runnable() {
			@Override
			public void run() {
				setButtons(false);
				phase5(splits[0], depth);
				setButtons(true);
			}
		}).start();
	}// GEN-LAST:event_faza5BtnActionPerformed

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7ActionPerformed
		new Thread(new Runnable() {
			@Override
			public void run() {
				setButtons(false);
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				model.setRowCount(0);
				jTable1.revalidate();
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
	}// GEN-LAST:event_jButton7ActionPerformed

	private void jButton2ActionPerformed(ActionEvent evt) {
		createDisplayFrame();
		/*if (jTable1.getRowCount() > 0) {
			int rowIndex = this.jTable1.getSelectedRow();
			if (rowIndex != -1) {
				String title = this.jTable1.getValueAt(rowIndex, 1).toString();
				statusLabel.setText("Wczytywanie zawartoœci strony");
				String content = parseContent(returnContent(title));
				createFrame(title, content);
				statusLabel.setText("");
			}
		}*/
	}
	public static String parseContent(String content) {
		Pattern p = Pattern.compile("<ref[^>]*>(.*?)</ref>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		content = p.matcher(content).replaceAll("");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (i % 240 == 0) {
				sb.append("\n");

			}
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void createFrame(String title, String content) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setOpaque(true);
				// JTextArea textArea = new JTextArea(50, 150);
				// textArea.setWrapStyleWord(true);
				// textArea.setEditable(false);
				// textArea.setFont(Font.getFont(Font.SANS_SERIF));
				JLabel label = new JLabel();

				// JScrollPane scroller = new JScrollPane(textArea);
				JScrollPane scroller = new JScrollPane(label);
				scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				JPanel inputpanel = new JPanel();
				inputpanel.setLayout(new FlowLayout());

				String ourHtml = null;
				try {
					WikiConfig config = DefaultConfigEnWp.generate();
					WtEngineImpl engine = new WtEngineImpl(config);

					PageTitle pageTitle = PageTitle.make(config, "file");

					EngProcessedPage cp = engine.postprocess(new PageId(pageTitle, 1L), content, null);
					ourHtml = HtmlRenderer.print(new MyRendererCallback(), config, pageTitle, cp.getPage());
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					ourHtml = "Error while parsing.";
				}

				// textArea.setText(ourHtml);
				label.setText("<html>" + ourHtml + "</html>");
				// DefaultCaret caret = (DefaultCaret) textArea.getCaret();
				// caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				panel.add(scroller);
				panel.add(inputpanel);
				scroller.setSize(10, 10);
				frame.getContentPane().add(BorderLayout.CENTER, panel);
				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				frame.setResizable(true);
			}
		});
	}

	private void btnFaza1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFaza1ActionPerformed
		btnFazaActionPerformed("wikipage");
	}// GEN-LAST:event_btnFaza1ActionPerformed

	private void setButtons(boolean to) {
		jButton1.setEnabled(to);
		jButton2.setEnabled(to);
		jButton7.setEnabled(to);
		faza1Btn.setEnabled(to);
		faza2Btn.setEnabled(to);
		faza3Btn.setEnabled(to);
		faza4Btn.setEnabled(to);
		faza5Btn.setEnabled(to);
		btnZapisz.setEnabled(to);

		jTextField1.setEnabled(to);
		jTextField2.setEnabled(to);
		jTextField3.setEnabled(to);
		breaker.setEnabled(!to);
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

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
		try {
			runningTask = reader;
			reader.read();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindow.this,
					"Nie uda³o wczytaæ plik. Sprawdz czy plik jest w poprawnym formacie.", "B³¹d bazy danych",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
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
		try {
			runningTask = reader;
			reader.read();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindow.this,
					"Nie uda³o wczytaæ plik. Sprawdz czy plik jest w poprawnym formacie.", "B³¹d bazy danych",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

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
		try {
			runningTask = finder;
			finder.findReferences();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindow.this,
					"Nie uda³o wczytaæ plik. Sprawdz czy plik jest w poprawnym formacie.", "B³¹d bazy danych",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

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
		try {
			runningTask = reader;
			reader.read();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindow.this,
					"Nie uda³o wczytaæ plik. Sprawdz czy plik jest w poprawnym formacie.", "B³¹d bazy danych",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// wyczyœæ s³ówka
		Configuration.KEYWORDS.clear();
	}

	private void phase5(String word, int depth) {
		GraphFileReader reader = new GraphFileReader(jTextField1.getText(), this, depth);
		try {
			runningTask = reader;
			reader.readLayer(word, depth);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindow.this,
					"Nie uda³o wczytaæ plik. Sprawdz czy plik jest w poprawnym formacie.", "B³¹d bazy danych",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void pagerank() throws FileNotFoundException {
		int pageCount = Integer.parseInt(jTextField6.getText());
		Gauss gauss = new Gauss(pageCount, this.jProgressBar1, this.statusLabel, this.jTextField2.getText());
		Map<Integer, Double> pagerankResults = gauss.solveGaussSeidel(this.jProgressBar1, this.statusLabel);
		int i = 1;
		for (Map.Entry<Integer, Double> entry : pagerankResults.entrySet()) {
			String title = gauss.findTitle(entry.getKey());
			Double value = BigDecimal.valueOf(entry.getValue()).setScale(16, RoundingMode.HALF_UP).doubleValue();
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			DecimalFormat df = new DecimalFormat();
			DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
			symbols.setDecimalSeparator('.');
			df.setMaximumFractionDigits(8);
			df.setDecimalFormatSymbols(symbols);
			model.addRow(new Object[] { Integer.toString(i++), title, df.format(value), entry.getKey() });
		}
		btnZapisz.setEnabled(true);
	}

	public static String returnContent(String title) {
		Session session = DatabaseSession.getSessionFactory().getCurrentSession();
		String content = "";
		try {
			session.getTransaction().begin();
			String sqlQuery = "SELECT content FROM wikioccurences WHERE title='" + title + "'";
			List<Object> references = (List<Object>) session.createSQLQuery(sqlQuery).list();
			Iterator<Object> itr = references.iterator();
			while (itr.hasNext()) {
				content = (String) itr.next();
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (session.isOpen())
				session.close();
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
	private static JTable jTable2;
	private GridBagConstraints gridBagConstraints_1;
	private GridBagConstraints gridBagConstraints_2;
	private JButton clearWikiPages;
	private GridBagConstraints gridBagConstraints_3;
	private GridBagConstraints gridBagConstraints_4;
	private JButton clearWikiOccurences;
	private GridBagConstraints gridBagConstraints_5;
	private GridBagConstraints gridBagConstraints_6;
	private GridBagConstraints gridBagConstraints_7;
	private GridBagConstraints gridBagConstraints_8;
	private GridBagConstraints gridBagConstraints_9;
	private GridBagConstraints gridBagConstraints_10;
	private JButton clearWikiReferencesKeyword;
	private GridBagConstraints gridBagConstraints_11;
	private JButton clearWikiReferenceKeywordAll;
	private GridBagConstraints gridBagConstraints_12;
	private GridBagConstraints gridBagConstraints_13;
	private GridBagConstraints gridBagConstraints_14;
	private JButton clearWikiReferences;
	private GridBagConstraints gridBagConstraints_15;
	private GridBagConstraints gridBagConstraints_16;
	private JButton breaker;
	private JButton btnZapisz;
	private GridBagConstraints gridBagConstraints_17;
	private GridBagConstraints gridBagConstraints_18;
	// End of variables declaration//GEN-END:variables
}
