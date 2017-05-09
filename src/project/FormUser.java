/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hasyim_Asyari
 */
public class FormUser extends javax.swing.JFrame {
    DefaultComboBoxModel modelRambut = new DefaultComboBoxModel();
     private Connection con = koneksi.getConnection();
//     java.util.Date tglsekarang = new java.util.Date();
//     private SimpleDateFormat smpdtfmt = new SimpleDateFormat(" dd MMMMMMMMM yyyy", Locale.getDefault());
//    //diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
//     private String tanggal = smpdtfmt.format(tglsekarang);
     
     private Statement stt;
     private ResultSet rss;
     private DefaultTableModel model;
     private int baris;
     private boolean data;
    
     /**
     * Creates new form FormUser
     */
    public FormUser() {
        initComponents();
        no(false);
//        setLocationRelativeTo(this);
//        tgl.setText(tanggal);
        Date now = new Date();
        DateFormat tanggal = DateFormat.getDateInstance(DateFormat.FULL,new Locale("in","ID"));
        tgl.setText(tanggal.format(now));
        
        
    }
   private void no(boolean a){
        txtno.setEnabled(a);
        txtharga.setEnabled(a);
    };
    

    private void InitTable(){
        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("No. Telpon");
        model.addColumn("Alamat");
        model.addColumn("Model Rambut");
        
        jTable1.setModel(model);
    }
    
    private void TambahData(String nm, String np, String at, String mr){
        try {
            String sql = 
                    "INSERT INTO pelanggan VALUES (NULL,'"+nm+"','"+np+"','"+at+"','"+mr+"')";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            model.addRow(new Object[]{nm,np,at,mr});
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void UbahData(String nm, String np, String at, String mr, String id){
        try {
            
            String sql = "UPDATE pelanggan SET "
                         + "nm_pelanggan='"+nm+"',"
                         + "no_pelanggan='"+np+"',"
                         + "al_pelanggan='"+at+"',"
                         + "mr_pelanggan='"+mr+"'"
                         + "WHERE id_pelanggan='"+id+"'";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void TampilData(){
        try {
            String sql = "SELECT * FROM pelanggan";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                Object[] o = new Object[5];
                o[0] = rss.getInt("id_pelanggan");
                o[1] = rss.getString("nm_pelanggan");
                o[2] = rss.getString("no_pelanggan");
                o[3] = rss.getString("al_pelanggan");
                o[4] = rss.getString("mr_pelanggan");
                model.addRow(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     private void ValidasiData(String nm, String np, String at ,String mr){              //Method untuk validasi data
        try{
            String sql = "SELECT*from pelanggan ";      //query untuk melihat isi tabel buku pada database
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
              Object[] o = new Object[2];                               //membuat Objek
              o[0] = rss.getString("id_pelanggan").toLowerCase();              //objek 0 menampung data judul
              o[1] = rss.getString("nm_pelanggan").toLowerCase();            //objek 1 menampung data penulils
              
              if(o[0].equals(nm.toLowerCase())&& o[1].equals(np.toLowerCase())){     //jika data judul sudah ada dan penulis sudah ada
                  JOptionPane.showMessageDialog(null, "Data SUDAH ADA!!!!");                //akan tampil bahwa data sudah ada
                  data = false ;                                                            
                  break;                                                                    //proses akan berhenti
              }
            }
            if(data==true){                                                                 //jika data belum ada
                TambahData(nm,np,at,mr);                                            //akan memanggil method TambahData untuk mengisi judul, penuli, harga
                    JOptionPane.showMessageDialog(null, "Berhasil Simpan Data");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
     private void p(){
        txtnama.setEnabled(false);
        txtnohp.setEnabled(false);
        jtxtalamat.setEnabled(false);
        jcbmodelrambut.setEnabled(false);
        kalender.setEnabled(false);
        btnBatal.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSimpan.setEnabled(false);
     }
     private void r(){
        txtnama.setEnabled(true);
        txtnohp.setEnabled(true);
        jtxtalamat.setEnabled(true);
        jcbmodelrambut.setEnabled(true);
        kalender.setEnabled(true);
        btnBatal.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSimpan.setEnabled(true);
        txtnama.requestFocus();
    }
    
     private void o(){
        txtnama.setText("");
        txtnohp.setText("");
        jtxtalamat.setText("");        
        jcbmodelrambut.setSelectedIndex(0);
     }
     
      private void ComboRambut(){
        jcbmodelrambut.setModel(modelRambut);
        try{
            stt = con.createStatement();
            rss = stt.executeQuery("SELECT * FROM model");
            
            while(rss.next()){
                modelRambut.addElement(rss.getString("jenis"));
            }
        }catch(Exception e){
            
        }
    }
      
      private void Transaksi(){
    
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblForm = new javax.swing.JLabel();
        lblPelanggan = new javax.swing.JLabel();
        lblTransaksi = new javax.swing.JLabel();
        lblTentang = new javax.swing.JLabel();
        lblKeluar = new javax.swing.JLabel();
        lblhome = new javax.swing.JLabel();
        lblform = new javax.swing.JLabel();
        lblpelanggan = new javax.swing.JLabel();
        lbltransaksi = new javax.swing.JLabel();
        lbltentang = new javax.swing.JLabel();
        lblkeluar = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtnohp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtalamat = new javax.swing.JTextArea();
        tgl = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        jcbmodelrambut = new javax.swing.JComboBox();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnEdit = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        kalender = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Kasir");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logo.png"))); // NOI18N

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.png"))); // NOI18N
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        lblForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/form.png"))); // NOI18N
        lblForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFormMouseClicked(evt);
            }
        });

        lblPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/member1.png"))); // NOI18N
        lblPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPelangganMouseClicked(evt);
            }
        });

        lblTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/kasir.png"))); // NOI18N
        lblTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTransaksiMouseClicked(evt);
            }
        });

        lblTentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tentang.png"))); // NOI18N
        lblTentang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTentangMouseClicked(evt);
            }
        });

        lblKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logout.png"))); // NOI18N
        lblKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKeluarMouseClicked(evt);
            }
        });

        lblhome.setText("Home");
        lblhome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhomeMouseClicked(evt);
            }
        });

        lblform.setText("Form");
        lblform.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblformMouseClicked(evt);
            }
        });

        lblpelanggan.setText("Pelanggan");
        lblpelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpelangganMouseClicked(evt);
            }
        });

        lbltransaksi.setText("Transaksi");
        lbltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltransaksiMouseClicked(evt);
            }
        });

        lbltentang.setText("Tentang");
        lbltentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltentangMouseClicked(evt);
            }
        });

        lblkeluar.setText("Keluar");
        lblkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblkeluarMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("BARBER KILAP SEMPURNA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(lblhome)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblHome)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblForm)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblform)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPelanggan)
                    .addComponent(lblpelanggan))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTransaksi)
                        .addGap(67, 67, 67)
                        .addComponent(lblTentang)
                        .addGap(64, 64, 64)
                        .addComponent(lblKeluar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbltransaksi)
                        .addGap(40, 40, 40)
                        .addComponent(lbltentang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblkeluar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(306, 306, 306))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblForm, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblPelanggan, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblTransaksi)
                                            .addComponent(lblKeluar)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTentang, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblHome, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblform)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblpelanggan)
                                .addComponent(lbltransaksi)
                                .addComponent(lbltentang)
                                .addComponent(lblkeluar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblhome)
                                .addContainerGap())))))
        );

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("FORM PELANGGAN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(340, 340, 340))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Nama");

        jLabel16.setText("No. Telp");

        jLabel17.setText("Alamat");

        jLabel18.setText("Model Rambut");

        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });

        jtxtalamat.setColumns(20);
        jtxtalamat.setRows(5);
        jScrollPane2.setViewportView(jtxtalamat);

        tgl.setText("jLabel19");

        jLabel20.setText("No ");

        jcbmodelrambut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "== Pilih ==" }));

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "No. Telp", "Model Rambut"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jLabel2.setText("Tanggal");

        jLabel3.setText("Harga");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18)))
                                .addComponent(btnKembali))
                            .addGap(38, 38, 38)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtnohp, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addGap(28, 28, 28)
                                                    .addComponent(kalender, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(btnTambah)
                                                    .addGap(27, 27, 27)
                                                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnSimpan)
                                            .addGap(67, 67, 67))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(300, 300, 300)
                                                    .addComponent(tgl))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(269, 269, 269)
                                                    .addComponent(jLabel20)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(56, 56, 56))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jcbmodelrambut, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tgl)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(187, 187, 187))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtnohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbmodelrambut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel18))
                            .addComponent(kalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnBatal)
                    .addComponent(btnEdit)
                    .addComponent(btnSimpan)
                    .addComponent(btnKembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        // TODO add your handling code here:
        new MenuKasir().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFormMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_lblFormMouseClicked

    private void lblPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPelangganMouseClicked
        // TODO add your handling code here:
        new Pelanggan().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblPelangganMouseClicked

    private void lblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiMouseClicked
        // TODO add your handling code here:
        new Transaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblTransaksiMouseClicked

    private void lblTentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTentangMouseClicked
        // TODO add your handling code here:
        new Tentang().setVisible(true);
    }//GEN-LAST:event_lblTentangMouseClicked

    private void lblKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKeluarMouseClicked
        // TODO add your handling code here:
       int a;
            a=JOptionPane.showConfirmDialog(null, "Yakin Keluar Dari Menu Kasir .?", "Informasi", JOptionPane. YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION)
            {new awal().setVisible(true);
                dispose();
            }
        else
            {return;}
    }//GEN-LAST:event_lblKeluarMouseClicked

    private void lblhomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhomeMouseClicked
        // TODO add your handling code here:
        new MenuKasir().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblhomeMouseClicked

    private void lblformMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblformMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblformMouseClicked

    private void lblpelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpelangganMouseClicked
        // TODO add your handling code here:
        new Pelanggan().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblpelangganMouseClicked

    private void lbltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltransaksiMouseClicked
        // TODO add your handling code here:
        new Transaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_lbltransaksiMouseClicked

    private void lbltentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltentangMouseClicked
        // TODO add your handling code here:
        new Tentang().setVisible(true);
       
    }//GEN-LAST:event_lbltentangMouseClicked

    private void lblkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblkeluarMouseClicked
        // TODO add your handling code here:
       int a;
            a=JOptionPane.showConfirmDialog(null, "Yakin Keluar Dari Menu Kasir .?", "Informasi", JOptionPane. YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION)
            {new awal().setVisible(true);
                dispose();
            }
        else
            {return;}
    }//GEN-LAST:event_lblkeluarMouseClicked

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        new MenuKasir().setVisible(true);
       dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        r();
        
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        txtnama.setText("");
        txtnohp.setText("");
        jtxtalamat.setText("");        
        jcbmodelrambut.setSelectedIndex(0);
       
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
         int baris = jTable1.getSelectedRow();
        
        
        jTable1.setValueAt(txtnama.getText(),baris,1);
        jTable1.setValueAt(txtnohp.getText(),baris,2);
        jTable1.setValueAt(jtxtalamat.getText(),baris,3);
        jTable1.setValueAt(jcbmodelrambut.getSelectedItem(),baris,4);
        
        
        String nm=jTable1.getValueAt(baris,1).toString();
        String np=jTable1.getValueAt(baris,2).toString();
        String at=jTable1.getValueAt(baris,3).toString();
        String mr=jTable1.getValueAt(baris,4).toString();
        String id=jTable1.getValueAt(baris,0).toString();
        
        
        txtnama.setText(nm);
        txtnohp.setText(np);
        jtxtalamat.setText(at);        
        jcbmodelrambut.setSelectedItem(mr);
        
        UbahData(nm,np,at,mr,id);
        p();
        o();
       
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
                if(txtnama.getText().equals("") && txtnohp.getText().equals(""))
     {
           JOptionPane.showMessageDialog(null, "Data Belum Lengkap","Warning !!!!",JOptionPane.INFORMATION_MESSAGE);
           txtnama.requestFocus();
     } else{
        String nm = txtnama.getText();
        String np = txtnohp.getText();
        String at = jtxtalamat.getText();
        String mr = jcbmodelrambut.getSelectedItem().toString();
        
       
        TambahData(nm,np,at,mr);
        
        
        
        InitTable();
        TampilData();
       
    }                                        
    
    
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int baris = jTable1.getSelectedRow();               //memilih baris yang akan dipilih dengan mouse
        
        txtnama.setText(jTable1.getValueAt(baris, 1).toString());
        txtnohp.setText(jTable1.getValueAt(baris, 2).toString());
        jtxtalamat.setText(jTable1.getValueAt(baris, 3).toString());
        jcbmodelrambut.setSelectedItem(jTable1.getValueAt(baris, 4).toString());
       
        r();
        
        
         
         /*String nm=jTable1.getValueAt(baris,1).toString();
        String np=jTable1.getValueAt(baris,2).toString();
        String at=jTable1.getValueAt(baris,3).toString();
        String mr=jTable1.getValueAt(baris,4).toString();
        String id=jTable1.getValueAt(baris,0).toString();
        
        
        txtnama.setText(nm);
        txtnohp.setText(np);
        jtxtalamat.setText(at);        
        jcbmodelrambut.setSelectedItem(mr);*/
       
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilData();
        ComboRambut();
    }//GEN-LAST:event_formComponentShown

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        this.p();
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox jcbmodelrambut;
    private javax.swing.JTextArea jtxtalamat;
    private com.toedter.calendar.JDateChooser kalender;
    private javax.swing.JLabel lblForm;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblKeluar;
    private javax.swing.JLabel lblPelanggan;
    private javax.swing.JLabel lblTentang;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JLabel lblform;
    private javax.swing.JLabel lblhome;
    private javax.swing.JLabel lblkeluar;
    private javax.swing.JLabel lblpelanggan;
    private javax.swing.JLabel lbltentang;
    private javax.swing.JLabel lbltransaksi;
    private javax.swing.JLabel tgl;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtnohp;
    // End of variables declaration//GEN-END:variables
}
