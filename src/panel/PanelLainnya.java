/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Customer.HalamanUtama;
import Login.DbConnection;
import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rolis
 */
public class PanelLainnya extends javax.swing.JPanel {
    private HalamanUtama halamanUtama;
    private JTable jTable; // Variabel untuk tabel
    private DefaultTableModel tableModel; // Model data untuk tabel

    private int lastMouseY;
       
    /**
     * Creates new form TesPanel
     */
    public PanelLainnya(HalamanUtama halamanUtama) {
        this.halamanUtama = halamanUtama; // Simpan referensi objek HalamanUtama
        initComponents();  // Inisialisasi komponen GUI

        // Tambahkan listener untuk Checkbox dan Spinner setelah initComponents()
        IceCapCbox.addActionListener(this::IceCapCboxActionPerformed);
        IceChocoCbox.addActionListener(this::IceChocoCboxActionPerformed);
        KelpCbox.addActionListener(this::KelpCboxActionPerformed);
        CocaColaCbox.addActionListener(this::CocaColaCboxActionPerformed);
        LeMineCbox.addActionListener(this::LeMineCboxActionPerformed);

    // Menambahkan listener untuk spinnerBeefBurg
    spinnerIceCap.addChangeListener(e -> {
        int value = (Integer) spinnerIceCap.getValue();
        if (value < 0) {
            spinnerIceCap.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
        }
        updateTableFromSpinner("M007", spinnerIceCap);
        updateCheckBoxFromSpinner(IceCapCbox, spinnerIceCap);  // Memperbarui checkbox sesuai dengan nilai spinner
    });

    // Menambahkan listener untuk spinnerDblBeef
    spinnerIceChoco.addChangeListener(e -> {
        int value = (Integer) spinnerIceChoco.getValue();
        if (value < 0) {
            spinnerIceChoco.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
        }
        updateTableFromSpinner("M008", spinnerIceChoco);
        updateCheckBoxFromSpinner(IceChocoCbox, spinnerIceChoco);  // Memperbarui checkbox sesuai dengan nilai spinner
    });

    // Menambahkan listener untuk spinnerDelxBurg
    spinnerKelp.addChangeListener(e -> {
        int value = (Integer) spinnerKelp.getValue();
        if (value < 0) {
            spinnerKelp.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
        }
        updateTableFromSpinner("M009", spinnerKelp);
        updateCheckBoxFromSpinner(KelpCbox, spinnerKelp);  // Memperbarui checkbox sesuai dengan nilai spinner
    });

    // Menambahkan listener untuk spinnerChezzBurg
    spinnerCocaCola.addChangeListener(e -> {
        int value = (Integer) spinnerCocaCola.getValue();
        if (value < 0) {
            spinnerCocaCola.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
        }
        updateTableFromSpinner("M010", spinnerCocaCola);
        updateCheckBoxFromSpinner(CocaColaCbox, spinnerCocaCola);  // Memperbarui checkbox sesuai dengan nilai spinner
    });

    // Menambahkan listener untuk spinnerChikBurg
    spinnerLeMine.addChangeListener(e -> {
        int value = (Integer) spinnerLeMine.getValue();
        if (value < 0) {
            spinnerLeMine.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
        }
        updateTableFromSpinner("M011", spinnerLeMine);
        updateCheckBoxFromSpinner(LeMineCbox, spinnerLeMine);  // Memperbarui checkbox sesuai dengan nilai spinner
    });

        
        // Menetapkan model spinner dengan validasi agar nilai tidak kurang dari 0
        spinnerIceCap.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        spinnerIceChoco.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        spinnerKelp.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        spinnerCocaCola.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        spinnerLeMine.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        
        // Pastikan komponen-komponen sudah terinisialisasi sebelum memodifikasi tableModel
        jPanel1.setPreferredSize(new Dimension(menu.getWidth(), 800)); // Contoh tinggi 2000
        jPanel1.revalidate();     

        // Inisialisasi tableModel setelah komponen terinisialisasi
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Menu ID", "Nama Menu", "Kategori", "Harga"});

        // Jika jTable belum diinisialisasi secara otomatis, lakukan inisialisasi manual:
        if (jTable == null) {
            jTable = new javax.swing.JTable();  // Membuat instance jTable jika belum ada
        }
        jTable.setModel(tableModel);  // Menghubungkan tableModel dengan jTable

        // Tambahkan jTable ke dalam scrollPane dan layout panel (jika perlu)
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(scrollPane, BorderLayout.CENTER);  // Menambahkan jTable ke panel
    }
    
    private void customizeScrollPanel() {
        menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        menu.getHorizontalScrollBar().setUI(null); // Hapus UI scrollbar horizontal
        menu.getVerticalScrollBar().setUI(null);   // Hapus UI scrollbar vertikal

        // Tambahkan MouseListener ke jPanel1
        jPanel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jPanel1.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        IceCapCbox = new javax.swing.JCheckBox();
        IceChocoCbox = new javax.swing.JCheckBox();
        KelpCbox = new javax.swing.JCheckBox();
        CocaColaCbox = new javax.swing.JCheckBox();
        LeMineCbox = new javax.swing.JCheckBox();
        spinnerLeMine = new javax.swing.JSpinner();
        spinnerCocaCola = new javax.swing.JSpinner();
        spinnerKelp = new javax.swing.JSpinner();
        spinnerIceChoco = new javax.swing.JSpinner();
        spinnerIceCap = new javax.swing.JSpinner();
        spinnerLeMine1 = new javax.swing.JSpinner();
        LeMineCbox1 = new javax.swing.JCheckBox();
        spinnerLeMine2 = new javax.swing.JSpinner();
        LeMineCbox2 = new javax.swing.JCheckBox();

        menu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        menu.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/kentang.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Rp 12.000");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("French Fries");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(4, 4, 4))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/icecreamcorn.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Rp 12.000");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Ice Cream Corn");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(28, 28, 28))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(4, 4, 4))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/nugget.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Rp 20.000");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Gedagedi Nuggets");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(4, 4, 4))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/onionring.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Rp 15.000");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Onion Rings");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addGap(4, 4, 4))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/icecreamstick.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Rp 7.000");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("ChocoNut Ais");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(3, 3, 3)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(4, 4, 4))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/icecreamcup.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Rp 10.000");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Ice Cream Cup");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(3, 3, 3)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(4, 4, 4))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/cookies.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Rp 10.000");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Cookies");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(3, 3, 3)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(4, 4, 4))
        );

        IceCapCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IceCapCboxActionPerformed(evt);
            }
        });

        IceChocoCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IceChocoCboxActionPerformed(evt);
            }
        });

        KelpCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KelpCboxActionPerformed(evt);
            }
        });

        CocaColaCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CocaColaCboxActionPerformed(evt);
            }
        });

        LeMineCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeMineCboxActionPerformed(evt);
            }
        });

        LeMineCbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeMineCbox1ActionPerformed(evt);
            }
        });

        LeMineCbox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeMineCbox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(IceCapCbox)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerIceCap)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(KelpCbox)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerKelp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CocaColaCbox)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerCocaCola))
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(IceChocoCbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spinnerIceChoco, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LeMineCbox2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spinnerLeMine2))
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LeMineCbox)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spinnerLeMine))
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LeMineCbox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinnerLeMine1))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IceChocoCbox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(IceCapCbox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerIceChoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerIceCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KelpCbox)
                    .addComponent(CocaColaCbox)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerCocaCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerKelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LeMineCbox)
                        .addComponent(spinnerLeMine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LeMineCbox1)
                        .addComponent(spinnerLeMine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LeMineCbox2)
                    .addComponent(spinnerLeMine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menu.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        lastMouseY = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
         int deltaY = lastMouseY - evt.getY();
        lastMouseY = evt.getY(); // Perbarui posisi terakhir mouse

        // Dapatkan viewport dari JScrollPane
        JViewport viewport = menu.getViewport();
        Point viewPosition = viewport.getViewPosition();

        // Geser viewport berdasarkan perubahan vertikal (deltaY)
        viewPosition.translate(0, deltaY); // Geser vertikal, tidak horizontal
        menu.getViewport().setViewPosition(viewPosition);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void IceCapCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IceCapCboxActionPerformed
    if (IceCapCbox.isSelected()) {
        loadData("M007", spinnerIceCap);
    } else {
        removeData("M007");
    }
    }//GEN-LAST:event_IceCapCboxActionPerformed

    private void IceChocoCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IceChocoCboxActionPerformed
    if (IceChocoCbox.isSelected()) {
        loadData("M008", spinnerIceChoco);
    } else {
        removeData("M008");
    }
    }//GEN-LAST:event_IceChocoCboxActionPerformed

    private void KelpCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KelpCboxActionPerformed
    if (KelpCbox.isSelected()) {
        loadData("M009", spinnerKelp);
    } else {
        removeData("M009");
    }
    }//GEN-LAST:event_KelpCboxActionPerformed

    private void CocaColaCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CocaColaCboxActionPerformed
    if (CocaColaCbox.isSelected()) {
        loadData("M010", spinnerCocaCola);
    } else {
        removeData("M010");
    }
    }//GEN-LAST:event_CocaColaCboxActionPerformed

    private void LeMineCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeMineCboxActionPerformed
    if (LeMineCbox.isSelected()) {
        loadData("M011", spinnerLeMine);
    } else {
        removeData("M011");
    }
    }//GEN-LAST:event_LeMineCboxActionPerformed

    private void LeMineCbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeMineCbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LeMineCbox1ActionPerformed

    private void LeMineCbox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeMineCbox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LeMineCbox2ActionPerformed
    
// Method untuk memperbarui checkbox berdasarkan nilai spinner
private void updateCheckBoxFromSpinner(javax.swing.JCheckBox checkBox, javax.swing.JSpinner spinner) {
        int value = (Integer) spinner.getValue();
        checkBox.setSelected(value > 0);
    }
      
// Memuat data ke dalam tabel berdasarkan menuId dan nilai spinner
private void loadData(String menuId, javax.swing.JSpinner spinner) {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "SELECT * FROM menu WHERE menu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, menuId); // menu_id dari database
        ResultSet rs = pstmt.executeQuery();

        // Cek apakah ada data yang ditemukan
        if (rs.next()) {
            String namaMenu = rs.getString("nama_menu");
            double harga = rs.getDouble("harga");
            int jumlah = (Integer) spinner.getValue(); // Mengambil jumlah dari spinner
            double total = harga * jumlah;

            DefaultTableModel tableModel = halamanUtama.getTableModel();
            boolean menuExists = false;

            // Periksa apakah menu sudah ada di tabel
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(menuId)) {
                    // Jika menu sudah ada, update jumlah dan total
                    tableModel.setValueAt(jumlah, i, 3);  // Update jumlah
                    tableModel.setValueAt(total, i, 4);   // Update total
                    menuExists = true;
                    break;
                }
            }

            // Jika menu belum ada, tambahkan data baru
            if (!menuExists && jumlah > 0) {
                tableModel.addRow(new Object[]{menuId, namaMenu, harga, jumlah, total});
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Method untuk menghapus data berdasarkan menuId
private void removeData(String menuId) {
    DefaultTableModel tableModel = halamanUtama.getTableModel();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        if (tableModel.getValueAt(i, 0).equals(menuId)) {
            tableModel.removeRow(i);  // Menghapus baris berdasarkan menuId
            break;
        }
    }
}

// Method untuk memperbarui checkbox berdasarkan nilai spinner
private void updateCheckBoxFromSpinner(javax.swing.JCheckBox checkBox, javax.swing.JSpinner spinner, String menuId) {
    int value = (Integer) spinner.getValue();
    if (value > 0) {
        checkBox.setSelected(true);
        loadData(menuId, spinner);  // Memuat data ke dalam tabel
    } else {
        checkBox.setSelected(false);
        removeData(menuId);  // Menghapus data dari tabel
    }
}

// Memperbarui tabel dari perubahan nilai spinner
private void updateTableFromSpinner(String menuId, javax.swing.JSpinner spinner) {
    int jumlah = (Integer) spinner.getValue();
    
    // Jika jumlah > 0, tambahkan atau update data ke tabel
    if (jumlah > 0) {
        loadData(menuId, spinner);  // Memuat data ke dalam tabel
    } else {
        removeData(menuId);  // Menghapus data jika jumlahnya 0
    }
}

// Mendapatkan spinner terkait berdasarkan menuId
// Mendapatkan spinner terkait berdasarkan menuId
private javax.swing.JSpinner getSpinnerForMenu(String menuId) {
    switch (menuId) {
        case "M007":
            return spinnerIceCap;
        case "M008":
            return spinnerIceChoco;
        case "M009":
            return spinnerKelp;
        case "M010":
            return spinnerCocaCola;
        case "M011":
            return spinnerLeMine;
        default:
            return null;
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CocaColaCbox;
    private javax.swing.JCheckBox IceCapCbox;
    private javax.swing.JCheckBox IceChocoCbox;
    private javax.swing.JCheckBox KelpCbox;
    private javax.swing.JCheckBox LeMineCbox;
    private javax.swing.JCheckBox LeMineCbox1;
    private javax.swing.JCheckBox LeMineCbox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane menu;
    private javax.swing.JSpinner spinnerCocaCola;
    private javax.swing.JSpinner spinnerIceCap;
    private javax.swing.JSpinner spinnerIceChoco;
    private javax.swing.JSpinner spinnerKelp;
    private javax.swing.JSpinner spinnerLeMine;
    private javax.swing.JSpinner spinnerLeMine1;
    private javax.swing.JSpinner spinnerLeMine2;
    // End of variables declaration//GEN-END:variables
}
