/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanzahmsautoverify;

import AESsecurity.EnkripsiAES;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author khanzasoft
 */
public class frmUtama extends javax.swing.JFrame {
    private final DefaultTableModel tabMode;
    private Connection koneksi;
    private PreparedStatement ps,ps2;
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private ResultSet rs; 
    int i=0,j=0;
    Properties prop = new Properties();
    /**
     * Creates new form frmUtama
     */
    public frmUtama() {
        initComponents();
        Object[] row={"Id","NIK","Nama","Shift","Jam Datang","Saat Ini","Status","Keterlambatan","Durasi","Photo"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class,  java.lang.Object.class,  java.lang.Object.class,  
                java.lang.Object.class,  java.lang.Object.class,  java.lang.Object.class,  java.lang.Object.class,  
                java.lang.Object.class,  java.lang.Object.class, java.lang.Object.class,
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbTemporary.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbTemporary.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTemporary.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTemporary.getColumnModel().getColumn(i);
            if(i==0){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(90);
            }else if(i==4){
                column.setPreferredWidth(125);
            }else if(i==5){
                column.setPreferredWidth(125);
            }else if(i==9){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else{
                column.setPreferredWidth(115);
            }
        }
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        this.setSize(screen.width-100,screen.height-100);
        this.setLocationRelativeTo(null);
        jam();
        javax.swing.Timer timer = new javax.swing.Timer(100,null);
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTemporary = new widget.Table();
        panelGlass5 = new widget.panelisi();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        textArea1 = new widget.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("::[ SIMRS KhanzaHMS Submenu Auto Verifikasi Presensi ]::");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Auto Verifikasi Presensi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 40))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setOpaque(true);

        tbTemporary.setAutoCreateRowSorter(true);
        tbTemporary.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Scroll.setViewportView(tbTemporary);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setPreferredSize(new java.awt.Dimension(100, 120));

        textArea1.setColumns(20);
        textArea1.setRows(5);
        scrollPane1.setViewportView(textArea1);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
       
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnKeluar;
    private widget.ScrollPane Scroll;
    private widget.InternalFrame internalFrame1;
    private widget.panelisi panelGlass5;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbTemporary;
    private widget.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
    public void tampil() {
        j=tabMode.getRowCount();
        for(i=0;i<j;i++){
            tabMode.removeRow(0);
        }
        try{   
            cekserver();
            ps=koneksi.prepareStatement(
                    "SELECT pegawai.id, pegawai.nik, pegawai.nama, temporary_presensi.shift, " +
                    "temporary_presensi.jam_datang, now() as jam_pulang, temporary_presensi.status,  " +
                    "temporary_presensi.keterlambatan, ((unix_timestamp(now()) - unix_timestamp(jam_datang))/3600) as durasi,photo  from pegawai  " +
                    "inner join temporary_presensi on pegawai.id=temporary_presensi.id ");
            try {
                rs=ps.executeQuery(); 
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10)
                    });
                }
            } catch (Exception e) {
                textArea1.append(""+e+"  ");
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(koneksi!=null){
                    koneksi.close();
                }
            }
        }catch(Exception e){
            textArea1.append("Notifikasi : "+e+"  ");
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_detik = "";                
                Date now = Calendar.getInstance().getTime();
                nilai_detik = now.getSeconds();
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }
                
                String detik = nol_detik + Integer.toString(nilai_detik);
                textArea1.append("detik : "+detik+"  ");
                if(detik.equals("15")){   
                    textArea1.setText("");
                    try {
                        cekserver();
                        textArea1.append("Melakukan Pengecekan data"+"  ");
                        koneksi.setAutoCommit(false);
                        for(i=0;i<tbTemporary.getRowCount();i++){ 
                            if(Double.parseDouble(tbTemporary.getValueAt(i,8).toString())>18){
                                ps2=koneksi.prepareStatement("insert into rekap_presensi values(?,?,?,?,?,?,?,?,?)");
                                try {
                                    ps2.setString(1,tbTemporary.getValueAt(i,0).toString());
                                    ps2.setString(2,tbTemporary.getValueAt(i,3).toString());
                                    ps2.setString(3,tbTemporary.getValueAt(i,4).toString());
                                    ps2.setString(4,tbTemporary.getValueAt(i,5).toString());
                                    ps2.setString(5,tbTemporary.getValueAt(i,6).toString());
                                    ps2.setString(6,tbTemporary.getValueAt(i,7).toString());
                                    ps2.setString(7,tbTemporary.getValueAt(i,8).toString());
                                    ps2.setString(8,"-");
                                    ps2.setString(9,tbTemporary.getValueAt(i,9).toString());
                                    ps2.executeUpdate();
                                    textArea1.append("Presensi "+tbTemporary.getValueAt(i,2).toString()+" dipindahkan ke Rekap Presensi"+"  ");
                                } catch (Exception er) {
                                    textArea1.append("Notifikasi : "+er+"  ");
                                } finally{
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
                                    
                                ps2=koneksi.prepareStatement("delete from temporary_presensi where id=? and jam_datang=?");
                                try {
                                    ps2.setString(1,tbTemporary.getValueAt(i,0).toString());
                                    ps2.setString(2,tbTemporary.getValueAt(i,4).toString());
                                    ps2.executeUpdate();
                                } catch (Exception er) {
                                    textArea1.append("Notifikasi : "+er+"  ");
                                } finally{
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
                            }
                        }
                        koneksi.setAutoCommit(true);
                        tampil();
                    } catch (Exception ez) {
                        textArea1.append("Notifikasi : "+ez+"  ");
                    }                                              
                }
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void cekserver() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            MysqlDataSource server=new MysqlDataSource();
            server.setServerName(EnkripsiAES.decrypt(prop.getProperty("HOST")));
            server.setPort(Integer.parseInt(EnkripsiAES.decrypt(prop.getProperty("PORT"))));
            server.setUser(EnkripsiAES.decrypt(prop.getProperty("USER")));
            server.setPassword(EnkripsiAES.decrypt(prop.getProperty("PAS")));
            server.setDatabaseName(EnkripsiAES.decrypt(prop.getProperty("DATABASE")));
            koneksi=server.getConnection();
            textArea1.append("Tersambung ke server"+"  ");
        } catch (Exception e) {
            textArea1.append("Notifikasi : "+e+"  ");
        }
    }
}
