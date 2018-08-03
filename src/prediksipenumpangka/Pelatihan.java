/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prediksipenumpangka;

import Controller.BP;
import Controller.BPLM;
import Controller.DokumenManager;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Pelatihan extends javax.swing.JFrame {

    /**
     * Creates new form Pelatihan
     */
    
    private BP bp;
    private BPLM bplm;
    private double[][] dataLatih;
    private double[] dataTahun;
    private double[] bobotV;
    private double[] bobotW;
    private boolean isBp, isBPLM;
    public Pelatihan() {
        isBp = false;
        isBPLM = false;
        initComponents();
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
        error = new javax.swing.JTextField();
        epoch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        alpha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        Browse = new javax.swing.JButton();
        BBP = new javax.swing.JButton();
        BSimpanParameter = new javax.swing.JButton();
        BBPLM = new javax.swing.JButton();
        BBMenuUtama = new javax.swing.JButton();
        HasilTraining = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        beta = new javax.swing.JTextField();
        epochBPLM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LM = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblBPPrediksi = new javax.swing.JTable();
        Mse_bp = new javax.swing.JLabel();
        Iterasi_bp = new javax.swing.JLabel();
        Waktu_Eksekusi_bp = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblBPLMPrediksi = new javax.swing.JTable();
        Mse_bplm = new javax.swing.JLabel();
        Iterasi_bplm = new javax.swing.JLabel();
        Waktu_Eksekusi_bplm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parameter BP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Banyak Epoch :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nilai Minimum error : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Alpha : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(epoch, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(alpha)
                    .addComponent(error))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(epoch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(alpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(error, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Browse.setText("Load Excel");
        Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseActionPerformed(evt);
            }
        });

        BBP.setText("Proses Bp");
        BBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBPActionPerformed(evt);
            }
        });

        BSimpanParameter.setText("Simpan Bobot");
        BSimpanParameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanParameterActionPerformed(evt);
            }
        });

        BBPLM.setText("Proses BPLM");
        BBPLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBPLMActionPerformed(evt);
            }
        });

        BBMenuUtama.setText("Balik ke Menu Utama");
        BBMenuUtama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBMenuUtamaActionPerformed(evt);
            }
        });

        HasilTraining.setText("Tampilkan Hasil Training");
        HasilTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HasilTrainingActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parameter BPLM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Banyak Epoch :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Parameter Beta :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Parameter LM :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(epochBPLM, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(LM)
                    .addComponent(beta))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(epochBPLM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BBMenuUtama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Browse, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BSimpanParameter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(BBPLM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BBP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HasilTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Browse, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BBP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BBPLM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BSimpanParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HasilTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BBMenuUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prediksi Penumpang KA (BP)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18))); // NOI18N

        TblBPPrediksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tahun Prediksi", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TblBPPrediksi);

        Mse_bp.setText("MSE : ");

        Iterasi_bp.setText("Iterasi : ");

        Waktu_Eksekusi_bp.setText("Waktu Eksekusi : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Mse_bp, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(Iterasi_bp)
                        .addGap(67, 67, 67)
                        .addComponent(Waktu_Eksekusi_bp)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mse_bp)
                    .addComponent(Iterasi_bp, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Waktu_Eksekusi_bp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prediksi Penumpang KA (BPLM)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18))); // NOI18N

        TblBPLMPrediksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tahun Prediksi", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TblBPLMPrediksi);

        Mse_bplm.setText("MSE : ");

        Iterasi_bplm.setText("Iterasi : ");

        Waktu_Eksekusi_bplm.setText("Waktu Eksekusi : ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Mse_bplm, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(Iterasi_bplm)
                        .addGap(154, 154, 154)
                        .addComponent(Waktu_Eksekusi_bplm)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mse_bplm)
                    .addComponent(Iterasi_bplm, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Waktu_Eksekusi_bplm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseActionPerformed
        // TODO add your handling code here:
        int i,j, nBulan, nTahun;
        ArrayList<ArrayList> data;
        
        DokumenManager dokManager = new DokumenManager();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file : " + selectedFile.getAbsolutePath());
            String FILE_NAME = selectedFile.getAbsolutePath();
            data = dokManager.get_data(FILE_NAME);
            
            nBulan = data.get(0).size()-1;
            nTahun = data.size()-1;
            
            dataLatih = new double[nTahun][nBulan];
            dataTahun = new double[nTahun];            
            
            for(i=0; i<nTahun; i++){
                //System.out.println("Data ke-" + i + ": ");
                for(j=0; j<nBulan; j++){
                    dataLatih[i][j] = Double.parseDouble(data.get(i+1).get(j+1).toString());
                }
            }
            
            for(i=1; i<nTahun; i++){
                dataTahun[i] = Double.parseDouble(data.get(i+1).get(0).toString());
            }
            
            JOptionPane.showMessageDialog(null, "Pengambilan data pada file excel berhasil dilakukan.");
        }
        
        epoch.setText(String.valueOf(1000));
        alpha.setText(String.valueOf(0.3));
        error.setText(String.valueOf(0.0005));
        
        epochBPLM.setText(String.valueOf(1000));
        LM.setText(String.valueOf(0.1));
        beta.setText(String.valueOf(10));
    }//GEN-LAST:event_BrowseActionPerformed

    private void BBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBPActionPerformed
        // TODO add your handling code here:
        int i, nEpoch;
        ArrayList<double[]> dataPenumpangList;
        double MSE;
        double[] dataPenumpang;
        double[] tahunPrediksi;
        
        bp = new BP();
        
        String epochValue = epoch.getText();
        String alphaValue = alpha.getText();
        String errorValue = error.getText();
        
        long startTime = System.nanoTime(); 
        
        bp.inisialisasiData(dataLatih, dataTahun, Double.parseDouble(epochValue), Double.parseDouble(alphaValue), Double.parseDouble(errorValue));
        bp.BackPropagation(7, 1);
        
        long elapsedTime = System.nanoTime() - startTime;
        double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) / 1000.0;
        
        dataPenumpangList = bp.getDataOutput();
        nEpoch = bp.getNEpoch();
        MSE = bp.getMSE();
        tahunPrediksi = bp.getDataTahun();
        isBp = bp.isBPDone();
        
        String[] row = new String[13];
        DefaultTableModel BPPrediksi_Model = (DefaultTableModel) TblBPPrediksi.getModel();
        for(i=0; i<dataPenumpangList.size(); i++){
            dataPenumpang = dataPenumpangList.get(i);
            
            row[0] = String.valueOf((int)tahunPrediksi[i+1]);
            row[1] = String.valueOf((int)dataPenumpang[0]);
            row[2] = String.valueOf((int)dataPenumpang[1]);
            row[3] = String.valueOf((int)dataPenumpang[2]);
            row[4] = String.valueOf((int)dataPenumpang[3]);
            row[5] = String.valueOf((int)dataPenumpang[4]);
            row[6] = String.valueOf((int)dataPenumpang[5]);
            row[7] = String.valueOf((int)dataPenumpang[6]);
            row[8] = String.valueOf((int)dataPenumpang[7]);
            row[9] = String.valueOf((int)dataPenumpang[8]);
            row[10] = String.valueOf((int)dataPenumpang[9]);
            row[11] = String.valueOf((int)dataPenumpang[10]);
            row[12] = String.valueOf((int)dataPenumpang[11]);   

            BPPrediksi_Model.addRow(row);
        }
        
        Mse_bp.setText("MSE : "+MSE);
        Iterasi_bp.setText("Iterasi : "+nEpoch);
        Waktu_Eksekusi_bp.setText("Waktu Eksekusi : "+elapsedTimeInSeconds+" s");
        
        JOptionPane.showMessageDialog(null, "Pelatihan menggunakan metode Back-propagation berhasil dilakukan.");
    }//GEN-LAST:event_BBPActionPerformed

    private void BBPLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBPLMActionPerformed
        // TODO add your handling code here:
        int i, nEpoch;
        ArrayList<double[]> dataPenumpangList;
        double MSE;
        double[] dataPenumpang;
        double[] tahunPrediksi;
        
        bplm = new BPLM();
        
        String epochValue = epochBPLM.getText();
        String LMValue = LM.getText();
        String betaValue = beta.getText();
        
        long startTime = System.nanoTime();  
        bplm.inisialisasiData(dataLatih, dataTahun, 5, Double.parseDouble(epochValue), Double.parseDouble(LMValue), Double.parseDouble(betaValue));
        bplm.BPLM(7, 1);
        
        long elapsedTime = System.nanoTime() - startTime;
        double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) / 1000.0;
        
        dataPenumpangList = bplm.getDataOutput();
        tahunPrediksi = bplm.getDataTahun();
        nEpoch = bplm.getNEpoch();
        MSE = bplm.getMSE();
        isBPLM = bplm.isBPLMDone();
        
        String[] row = new String[13];
        DefaultTableModel BPLMPrediksi_Model = (DefaultTableModel) TblBPLMPrediksi.getModel();
        for(i=0; i<dataPenumpangList.size(); i++){
            dataPenumpang = dataPenumpangList.get(i);
            
            row[0] = String.valueOf((int)tahunPrediksi[i+1]);
            row[1] = String.valueOf((int)dataPenumpang[0]);
            row[2] = String.valueOf((int)dataPenumpang[1]);
            row[3] = String.valueOf((int)dataPenumpang[2]);
            row[4] = String.valueOf((int)dataPenumpang[3]);
            row[5] = String.valueOf((int)dataPenumpang[4]);
            row[6] = String.valueOf((int)dataPenumpang[5]);
            row[7] = String.valueOf((int)dataPenumpang[6]);
            row[8] = String.valueOf((int)dataPenumpang[7]);
            row[9] = String.valueOf((int)dataPenumpang[8]);
            row[10] = String.valueOf((int)dataPenumpang[9]);
            row[11] = String.valueOf((int)dataPenumpang[10]);
            row[12] = String.valueOf((int)dataPenumpang[11]);   

            BPLMPrediksi_Model.addRow(row);
        }
        
        Mse_bplm.setText("MSE : "+MSE);
        Iterasi_bplm.setText("Iterasi : "+nEpoch);
        Waktu_Eksekusi_bplm.setText("Waktu Eksekusi : "+elapsedTimeInSeconds+" s");
        
        JOptionPane.showMessageDialog(null, "Pelatihan menggunakan metode Back-propagation LEVENBERG-MARQUARDT berhasil dilakukan.");
    }//GEN-LAST:event_BBPLMActionPerformed

    private void BSimpanParameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanParameterActionPerformed
        // TODO add your handling code here:        
        if(isBp){
            bp.simpan_parameterHasilBP();
        }
        
        if(isBPLM){
            bplm.simpan_parameterHasilBPLM();
        }
        
        if(isBp == true || isBPLM == true){
            JOptionPane.showMessageDialog(null, "Parameter BP atau BPLM berhasil disimpan");
        }
        
        if(isBp == false && isBPLM == false){
            JOptionPane.showMessageDialog(null, "Anda belum melakukan proses BP atau BPLM");
        }
    }//GEN-LAST:event_BSimpanParameterActionPerformed

    private void BBMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBMenuUtamaActionPerformed
        // TODO add your handling code here:
        MenuUtama menuUtama = new MenuUtama();
        menuUtama.setVisible(true);
        dispose();
    }//GEN-LAST:event_BBMenuUtamaActionPerformed

    private void HasilTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HasilTrainingActionPerformed
        // TODO add your handling code here:
        ParameterHasilTraining hasilTraining = new ParameterHasilTraining();
        hasilTraining.setVisible(true);
    }//GEN-LAST:event_HasilTrainingActionPerformed

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
            java.util.logging.Logger.getLogger(Pelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pelatihan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBMenuUtama;
    private javax.swing.JButton BBP;
    private javax.swing.JButton BBPLM;
    private javax.swing.JButton BSimpanParameter;
    private javax.swing.JButton Browse;
    private javax.swing.JButton HasilTraining;
    private javax.swing.JLabel Iterasi_bp;
    private javax.swing.JLabel Iterasi_bplm;
    private javax.swing.JTextField LM;
    private javax.swing.JLabel Mse_bp;
    private javax.swing.JLabel Mse_bplm;
    private javax.swing.JTable TblBPLMPrediksi;
    private javax.swing.JTable TblBPPrediksi;
    private javax.swing.JLabel Waktu_Eksekusi_bp;
    private javax.swing.JLabel Waktu_Eksekusi_bplm;
    private javax.swing.JTextField alpha;
    private javax.swing.JTextField beta;
    private javax.swing.JTextField epoch;
    private javax.swing.JTextField epochBPLM;
    private javax.swing.JTextField error;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
