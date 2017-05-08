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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hasyim_Asyari
 */
public class Kasir extends javax.swing.JFrame {
     private DefaultTableModel model;                        //Tabel model yg dibuat
    private Connection con = koneksi.getConnection();       //mengkoneksikan ke database
    private Statement stt;                                  //mengeksekusi queri
    private ResultSet rss;                                  //menampung queri
    private int baris; 
    private boolean data=true;   
    /**
     * Creates new form Kasir
     */
    public Kasir() {
        initComponents();
    }
     private void InitTable(){
        model = new DefaultTableModel();        //membuat tabel baru di model
        model.addColumn("Id");                  //kolom id dalam model
        model.addColumn("Nama");               //kolom judul dalam model
        model.addColumn("Username");             //kolom penulis dalam model
        model.addColumn("Password");               //kolom harga dalam model
        
        jTable1.setModel(model);                //tabel berisi data dari model
    }
    private void TampilData(){      //Method untuk menampilkan data
        try{
            String sql = "SELECT * FROM user";          // queriuntuk menampilkan isi tabel buku dari database
            stt = con.createStatement();                
            rss = stt.executeQuery(sql);                
            while(rss.next()){                          
                Object[] o = new Object[4];             //membuat Objek
                o[0] = rss.getInt("id_user");                //objek 0 menampung data id
                o[1] = rss.getString("fullname");          //objek 1 menampung data judul
                o[2] = rss.getString("username");        //objek 2 menampung data penulis
                o[3] = rss.getString("password");             //objek 3 menampung data harga
                model.addRow(o);                        //baris pada model
            }
        }catch(SQLException e){
            System.out.printf(e.getMessage());
        }
    }
    private void TambahData( String username, String password,String fullname, String level){                   //Method untuk menambah data 
        try{
            String sql = "INSERT INTO user VALUES (NULL,'"+username+"','"+password+"','"+fullname+"','"+level+"')";  //query untuk menambah data dari database
            stt = con.createStatement();
            stt.executeUpdate(sql);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean UbahData( String id, String nama, String username, String password){        //Method untuk mengubah data
        try{
            String sql = "UPDATE user SET nama='"+nama+"', username='"+username+"', password="+password+" where id="+id+";";  //query untuk mengubah data dari database
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean HapusData( String id){           //Method untuk menghapus data
        try{
            String sql = "DELETE from user where Id_user='"+id+"'";      //query untuk menghapus data buku dari database
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
     private void ValidasiData(String username, String password, String fullname, String level){              //Method untuk validasi data
        try{
            String sql = "SELECT*from user ";      //query untuk melihat isi tabel buku pada database
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
              Object[] o = new Object[2];                               //membuat Objek
              o[0] = rss.getString("username").toLowerCase();              //objek 0 menampung data judul
              o[1] = rss.getString("password").toLowerCase();            //objek 1 menampung data penulils
              
              if(o[0].equals(username.toLowerCase())&& o[1].equals(password.toLowerCase())){     //jika data judul sudah ada dan penulis sudah ada
                  JOptionPane.showMessageDialog(null, "Data SUDAH ADA!!!!");                //akan tampil bahwa data sudah ada
                  data = false ;                                                            
                  break;                                                                    //proses akan berhenti
              }
            }
            if(data==true){                                                                 //jika data belum ada
                TambahData(username,password,fullname,level);                                            //akan memanggil method TambahData untuk mengisi judul, penuli, harga
                    JOptionPane.showMessageDialog(null, "Berhasil Simpan Data");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblhome = new javax.swing.JLabel();
        lblkasir = new javax.swing.JLabel();
        lbltentang = new javax.swing.JLabel();
        lblmodel = new javax.swing.JLabel();
        lblkeluar = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblKasir = new javax.swing.JLabel();
        lblTentang = new javax.swing.JLabel();
        lblModel = new javax.swing.JLabel();
        lblKeluar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtnama = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Admin");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logo.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BARBER KILAP SEMPURNA");

        lblhome.setText("Home");
        lblhome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhomeMouseClicked(evt);
            }
        });

        lblkasir.setText("Kasir");
        lblkasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblkasirMouseClicked(evt);
            }
        });

        lbltentang.setText("Tentang");
        lbltentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltentangMouseClicked(evt);
            }
        });

        lblmodel.setText("Model Rambut");
        lblmodel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmodelMouseClicked(evt);
            }
        });

        lblkeluar.setText("Keluar");
        lblkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblkeluarMouseClicked(evt);
            }
        });

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.png"))); // NOI18N
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        lblKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/username.png"))); // NOI18N
        lblKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKasirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKasirMouseEntered(evt);
            }
        });

        lblTentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tentang.png"))); // NOI18N
        lblTentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTentangMouseClicked(evt);
            }
        });

        lblModel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/rambut.png"))); // NOI18N
        lblModel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModelMouseClicked(evt);
            }
        });

        lblKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logout.png"))); // NOI18N
        lblKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKeluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(lblhome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(lblkasir)
                                .addGap(13, 13, 13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblHome)
                                .addGap(26, 26, 26)
                                .addComponent(lblKasir)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblmodel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(lblModel)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lbltentang))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(lblTentang)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKeluar)
                            .addComponent(lblkeluar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel7)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTentang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblHome)
                                        .addComponent(lblKasir))
                                    .addGap(2, 2, 2))
                                .addComponent(lblKeluar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(lblModel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblkasir)
                            .addComponent(lblmodel)
                            .addComponent(lbltentang)
                            .addComponent(lblkeluar)
                            .addComponent(lblhome))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Kasir");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel1)
                .addContainerGap(311, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Username", "Password"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Nama");

        jLabel3.setText("Username");

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel4.setText("Password");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTambah)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnHapus)
                .addGap(147, 147, 147))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(46, 46, 46)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtpassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(txtusername, javax.swing.GroupLayout.Alignment.LEADING))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        // TODO add your handling code here:
        new MenuKasir().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblKasirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasirMouseEntered
        // TODO add your handling code here:
        new Kasir().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblKasirMouseEntered

    private void lblKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasirMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_lblKasirMouseClicked

    private void lblTentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTentangMouseClicked
        // TODO add your handling code here:
        new Tentang().setVisible(true);
       
    }//GEN-LAST:event_lblTentangMouseClicked

    private void lblModelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModelMouseClicked
        // TODO add your handling code here:
        new ModelRambut().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblModelMouseClicked

    private void lblKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKeluarMouseClicked
        // TODO add your handling code here:
        int a;
            a=JOptionPane.showConfirmDialog(null, "Yakin Keluar Dari Menu Admin .?", "Informasi", JOptionPane. YES_NO_OPTION);
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

    private void lblkasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblkasirMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblkasirMouseClicked

    private void lbltentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltentangMouseClicked
        // TODO add your handling code here:
         new Tentang().setVisible(true);
        
    }//GEN-LAST:event_lbltentangMouseClicked

    private void lblmodelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmodelMouseClicked
        // TODO add your handling code here:
         new ModelRambut().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblmodelMouseClicked

    private void lblkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblkeluarMouseClicked
        // TODO add your handling code here:
        int a;
            a=JOptionPane.showConfirmDialog(null, "Yakin Keluar Dari Menu Admin .?", "Informasi", JOptionPane. YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION)
            {new awal().setVisible(true);
                dispose();
            }
        else
            {return;}
    }//GEN-LAST:event_lblkeluarMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        String fullnama = txtnama.getText();                                //untuk mengambil nilai di komponen pada txtJudul
        String username = txtusername.getText();                        //untuk mengambil nilai di komponen pada comboPenulis
        String password = txtpassword.getText();                        //untuk mengambil nilai di komponen pada txtHarga
         
        InitTable();
        TampilData();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();                                //baris untuk memilih baris pada tabel

       String id = jTable1.getValueAt(baris, 0).toString();                 //untuk mengambil nilai id
       String nama = txtnama.getText();                                   //untuk mengambil nilai judul
       String username = txtusername.getText();          //untuk mengambil nilai penulis
       String password = txtpassword.getText();                                   //untuk mengambil nilai harga
       if(UbahData(id, nama, username, password))
           JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");       //pemberitahuan bahwa data telah di ubah
       else
           JOptionPane.showConfirmDialog(null, "Gagal Ubah Data");          //pemberitahuan bahwa data gagal di ubah
       InitTable();
       TampilData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();                               //baris untuk memilih baris pada tabel
                String id = jTable1.getValueAt(baris, 0).toString();                //untuk mengambil nilai id
                if(HapusData(id))
                 JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");      //pemberitahuan bahwa data telah di hapus
                else
                 JOptionPane.showConfirmDialog(null, "Gagal Hapus Data");         //pemberitahuan bahwa data gagal di hapus
         
       InitTable();
       TampilData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();               //memilih baris yang akan dipilih dengan mouse
        
        txtnama.setText(jTable1.getValueAt(baris, 1).toString());
        txtusername.setText(jTable1.getValueAt(baris, 2).toString());
        txtpassword.setText(jTable1.getValueAt(baris, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblKasir;
    private javax.swing.JLabel lblKeluar;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblTentang;
    private javax.swing.JLabel lblhome;
    private javax.swing.JLabel lblkasir;
    private javax.swing.JLabel lblkeluar;
    private javax.swing.JLabel lblmodel;
    private javax.swing.JLabel lbltentang;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables

    
}
