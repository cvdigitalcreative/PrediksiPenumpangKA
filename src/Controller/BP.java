/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class BP {
    boolean BpisDone;
    int id_percobaan, nEpoch;
    double epoch;
    double alpha;
    double error;
    double MSE;
    double[] tahunPrediksi;
    double[][] data;
    double[][] bobotV;
    double[][] bobotW;
    double max, min, maxUji, minUji;
    ArrayList<Double> maxList;
    ArrayList<Double> minList;
    ArrayList<double[][]> dataPengujian;
    ArrayList<double[][]> dataPelatihan;
    ArrayList<double[]> OutputPenumpang;
    
    public BP(){
        BpisDone = false;
    }
    
    public void inisialisasiData(double[][] data, double[] dataTahun, double epoch, double alpha, double error){
        int i,j,k, index, iterasi;
        double[][] dataLatih;
        this.epoch = epoch;
        this.alpha = alpha;
        this.error = error;
        
        tahunPrediksi = new double[dataTahun.length];
        
        for(i=0; i<dataTahun.length; i++){
            tahunPrediksi[i] = dataTahun[i];
        }
        
        dataPelatihan = new ArrayList<>();
        for(i=0; i<data.length-1; i++){
            dataLatih = new double[12][13];
                
            index = 0;
            for(j=0; j<12; j++){
                iterasi = 0;
                for(k=0; k<13; k++){
                    if(k+index < 12){
                        dataLatih[j][k] = data[i][k+index];
                    }
                    else{
                        dataLatih[j][k] = data[i+1][iterasi];
                        iterasi += 1;
                    }
                }
                index += 1;
            }
            
            dataPelatihan.add(dataLatih);
        }
        
        /*System.out.println("Data Uji : ");
        for(i=0; i<data.length; i++){
            for(j=0; j<data[0].length; j++){
                System.out.print(this.data[i][j]+" ");
            }
            System.out.println();
        }*/
        
        System.out.println("Proses Inisialisasi Data is Done");
    }
    
    public void inisialisasiDataUji(double[][] data, int nodeInput, int id, double epoch, double alpha, double error){       
        int i,j,k, index, iterasi;
        double[][] dataUji;
        this.epoch = epoch;
        this.alpha = alpha;
        this.error = error;
        id_percobaan = id;
        this.data = new double[data.length][data[0].length];
        
        for(i=0; i<data.length; i++){
            for(j=0; j<data[i].length; j++){
                this.data[i][j] = data[i][j];
            }
        }
        
        dataPengujian = new ArrayList<>();
        for(i=0; i<data.length-1; i++){
            dataUji = new double[12][13];
                
            index = 0;
            for(j=0; j<12; j++){
                iterasi = 0;
                for(k=0; k<13; k++){
                    if(k+index < 12){
                        dataUji[j][k] = data[i][k+index];
                    }
                    else{
                        dataUji[j][k] = data[i+1][iterasi];
                        iterasi += 1;
                    }
                }
                index += 1;
            }
            
            dataPengujian.add(dataUji);
        }
        
        /*System.out.println("Data Uji : ");
        for(i=0; i<data.length; i++){
            for(j=0; j<data[0].length; j++){
                System.out.print(this.data[i][j]+" ");
            }
            System.out.println();
        }*/
        
        System.out.println("Proses Inisialisasi Data Uji is Done");
    }
    
    public void normalisasiData(){
        int i,j,k;
        double[][] dataLatih;
        double[][] dataLatihTemp;
        
        maxList = new ArrayList<>();
        minList = new ArrayList<>();
        
        for(i=0; i<dataPelatihan.size(); i++){
            dataLatih = (double[][])dataPelatihan.get(i);
            
            double dataMin = 10000.0;
            double dataMax = 0.0;

            for(j=0; j<dataLatih.length; j++){
                for(k=0; k<dataLatih[j].length; k++){
                    if(dataMin > dataLatih[j][k]){
                        dataMin = dataLatih[j][k];
                    }

                    if(dataMax < dataLatih[j][k]){
                        dataMax = dataLatih[j][k];
                    }
                }
            }

            maxList.add(dataMax);
            minList.add(dataMin);
            
            dataLatihTemp = new double[dataLatih.length][dataLatih[0].length];
            
            for(j=0; j<dataLatih.length; j++){
                for(k=0; k<dataLatih[j].length; k++){
                    dataLatihTemp[j][k] = (dataLatih[j][k]-dataMin)/(dataMax-dataMin);
                }
            }
            
            dataPelatihan.set(i, dataLatihTemp);
        }
        
        System.out.println("Proses normalisasi Data is Done");
    }
    
    public void normalisasiDataUji(){
        int i,j,k;
        double[][] dataUji;
        double[][] dataUjiTemp;
        
        maxList = new ArrayList<>();
        minList = new ArrayList<>();
        
        for(i=0; i<dataPengujian.size(); i++){
            dataUji = (double[][])dataPengujian.get(i);
            
            double dataMin = 10000.0;
            double dataMax = 0.0;

            for(j=0; j<dataUji.length; j++){
                for(k=0; k<dataUji[j].length; k++){
                    if(dataMin > dataUji[j][k]){
                        dataMin = dataUji[j][k];
                    }

                    if(dataMax < dataUji[j][k]){
                        dataMax = dataUji[j][k];
                    }
                }
            }

            maxList.add(dataMax);
            minList.add(dataMin);
            
            dataUjiTemp = new double[dataUji.length][dataUji[0].length];
            
            for(j=0; j<dataUji.length; j++){
                for(k=0; k<dataUji[j].length; k++){
                    dataUjiTemp[j][k] = (dataUji[j][k]-dataMin)/(dataMax-dataMin);
                }
            }
            
            dataPengujian.set(i, dataUjiTemp);
        }
        
        /*for(i=0; i<dataPengujian.size(); i++){
            dataUji = (double[][])dataPengujian.get(i);
            System.out.println("Data Uji Prediksi tahun ke-"+i);
            for(j=0; j<dataUji.length; j++){
                System.out.print("Bulan ke-"+j+": ");
                for(k=0; k<dataUji[j].length; k++){
                    System.out.print(dataUji[j][k]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }*/
        
        System.out.println("Proses normalisasi Data Uji is Done");
    }
    
    public void denormalisasiData(){
        int i,j;
        double[] output;
        for(i=0; i<OutputPenumpang.size(); i++){
            output = new double[OutputPenumpang.get(i).length];
            for(j=0; j<OutputPenumpang.get(i).length; j++){
                output[j] = (OutputPenumpang.get(i)[j]*(maxList.get(i)-minList.get(i)))+minList.get(i);
            }
            OutputPenumpang.set(i, output);
        }
        
        System.out.println("Proses normalisasi Data is Done");
    }
    
    public void denormalisasiDataUji(){
        int i,j;
        double[] output;
        for(i=0; i<OutputPenumpang.size(); i++){
            output = new double[OutputPenumpang.get(i).length];
            for(j=0; j<OutputPenumpang.get(i).length; j++){
                output[j] = (OutputPenumpang.get(i)[j]*(maxList.get(i)-minList.get(i)))+minList.get(i);
            }
            OutputPenumpang.set(i, output);
        }
        
        System.out.println("Proses Denormalisasi Data Uji is Done");
    }
    
    public double[][] inisialisasiBobotInputToHidden(int nHiddenNode){
        int i,j;
        double[][] bobotVTemp;
        
        bobotVTemp = new double[dataPelatihan.get(0)[0].length][nHiddenNode];
        for(i=0; i<dataPelatihan.get(0)[0].length; i++){
            for(j=0; j<nHiddenNode; j++){
                bobotVTemp[i][j] = Math.random();
                
                while(bobotVTemp[i][j] > 0.5 && bobotVTemp[i][j] < -0.5){
                    bobotVTemp[i][j] = Math.random();
                }
            }
        }
        
        System.out.println("Proses Inisialisasi Bobot Input to Hidden is Done");
        
        return bobotVTemp;
    }
    
    public double[][] inisialisasiBobotInputToHiddenUji(){
        int i,j,n, maxInput, maxHidden;
        double[][] bobotVTemp;
        ArrayList<Double> bobotVList;
        ArrayList<Double> bobotWList;
        
        DatabaseManager databaseManager;
        String sql_BP;
        
        databaseManager = new DatabaseManager();
        databaseManager.set_KoneksiDatabase();
        
        sql_BP = "select max(node_input) from bobotbp_v where id_percobaan = "+id_percobaan;
        maxInput = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select max(node_hidden) from bobotbp_v where id_percobaan = "+id_percobaan;
        maxHidden = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select nilai_bobotv from bobotbp_v where id_percobaan = "+id_percobaan;
        bobotVList = databaseManager.getDataBobot(sql_BP);
        
        bobotVTemp = new double[maxInput+1][maxHidden+1];
        
        n=0;
        for(i=0; i<maxInput+1; i++){
            for(j=0; j<maxHidden+1; j++){
                bobotVTemp[i][j] = bobotVList.get(n);
                n += 1;
            }
        }
        
        System.out.println("Proses Inisialisasi Bobot Input Uji to Hidden Uji is Done");
        
        return bobotVTemp;
    }
    
    public double[][] inisialisasiBobotHiddenToOutput(int nHiddenNode, int nOutputNode){
        int i,j;
        double[][] bobotWTemp;
        
        bobotWTemp = new double[nHiddenNode+1][nOutputNode];
        for(i=0; i<nHiddenNode+1; i++){
            for(j=0; j<nOutputNode; j++){
                bobotWTemp[i][j] = Math.random();
                
                while(bobotWTemp[i][j] > 0.5 && bobotWTemp[i][j] < -0.5){
                    bobotWTemp[i][j] = Math.random();
                }
            }
        }
        
        System.out.println("Proses Inisialisasi Bobot Hidden to Output is Done");
        
        return bobotWTemp;
    }
    
    public double[][] inisialisasiBobotHiddenToOutputUji(){
        int i,j,n, maxHidden, maxOutput;
        double[][] bobotWTemp;
        ArrayList<Double> bobotWList;
        
        DatabaseManager databaseManager;
        String sql_BP;
        
        databaseManager = new DatabaseManager();
        databaseManager.set_KoneksiDatabase();
        
        sql_BP = "select max(node_hidden) from bobotbp_w where id_percobaan = "+id_percobaan;
        maxHidden = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select max(node_output) from bobotbp_w where id_percobaan = "+id_percobaan;
        maxOutput = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select nilai_bobotw from bobotbp_w where id_percobaan = "+id_percobaan;
        bobotWList = databaseManager.getDataBobot(sql_BP);
        
        bobotWTemp = new double[maxHidden+1][maxOutput+1];
        
        n=0;
        for(i=0; i<maxHidden+1; i++){
            for(j=0; j<maxOutput+1; j++){
                bobotWTemp[i][j] = bobotWList.get(n);
                n += 1;
            }
        }
        
        System.out.println("Proses Inisialisasi Bobot Hidden Uji to Output Uji is Done");
        
        return bobotWTemp;
    }
    
    public double[] ZOutput(int nHiddenNode, double[] dataLatih, double[][] bobotV){
        int i,j;
        double[] Znet;
        double[] Z_Output;
                
        Znet = new double[nHiddenNode];
        Z_Output = new double[nHiddenNode];
        
        for(i=0; i<nHiddenNode; i++){
            Znet[i] = 0.0;
            for(j=0; j<dataLatih.length; j++){
                Znet[i] += bobotV[j][i]*dataLatih[j]; 
            } 
            Z_Output[i] = 1.0/(1.0+Math.exp(-1*Znet[i]));
        }
        
        /*System.out.println("Data Z Output: ");
        
        for(i=0; i<nHiddenNode; i++){ 
            System.out.print(Z_Output[i]+" ");
        }*/
        
        //System.out.println("Proses Perhitungan Znet dan Z is Done");
        
        return Z_Output;
    }
    
    public double YOutput(int nOutputNode, double[] Z_Output, double[][] bobotW){
        int i,j;
        double Ynet = 0.0;
        double Y_Output = 0.0;
        
        for(i=0; i<nOutputNode; i++){
            for(j=0; j<Z_Output.length+1; j++){
                if(j==0){
                    Ynet += bobotW[j][i]; 
                }
                else{
                    Ynet += bobotW[j-1][i]*Z_Output[j-1]; 
                }
            } 
            Y_Output = 1/(1+Math.exp(-1*Ynet));
        }
        
        //System.out.println("Proses Perhitungan Ynet dan Y is Done");
        
        return Y_Output;
    }
    
    public double errorOutput(double target, double YOutput){
        double errOutput;
        
        errOutput = (target-YOutput)*YOutput*(1.0-YOutput);
        
        //System.out.println("Proses Perhitungan error Y is Done");
        
        return errOutput;
    }
    
    public double[][] deltaBobotOutput(int nHiddenNode, int nOutputNode, double alpha, double errOutput, double[] ZOutput){
        int i,j;
        double[][] deltaBobot;
        
        deltaBobot = new double[nHiddenNode+1][nOutputNode];
        
        for(i=0; i<nHiddenNode+1; i++){
            for(j=0; j<nOutputNode; j++){
                if(i==0){
                    deltaBobot[i][j] = alpha*errOutput*1.0;
                }
                else{
                    deltaBobot[i][j] = alpha*errOutput*ZOutput[i-1];
                }
            }
        }
        
        //System.out.println("Proses Perubahan Bobot W is Done");
        
        return deltaBobot;
    }
    
    public double[] errorHidden(int nHiddenNode, int nOutputNode, double YOutput, double[] ZOutput, double[][] deltaBobotY){
        int i,j;
        double[] errHidden;
        double[] errHiddenNet;
        
        errHidden = new double[nHiddenNode];
        errHiddenNet = new double[nHiddenNode];
        
        for(i=0; i<nHiddenNode; i++){
            for(j=0; j<nOutputNode; j++){
                errHiddenNet[i] = YOutput*deltaBobotY[i+1][0];
                errHidden[i] = errHiddenNet[i]*ZOutput[i]*(1.0-ZOutput[i]);
            }
        }
        
        //System.out.println("Proses Perhitungan error Z is Done");
        
        return errHidden;
    }
    
    public double[][] deltaBobotHidden(double alpha, double[] errHidden, double[] x){
        int i,j;
        double[][] deltaBobot;
        
        deltaBobot = new double[x.length][errHidden.length];
        
        for(i=0; i<x.length; i++){
            for(j=0; j<errHidden.length; j++){
                deltaBobot[i][j] = alpha*errHidden[j]*x[i];
            }
        }
        
        //System.out.println("Proses Perubahan Bobot V is Done");
        
        return deltaBobot;
    }
    
    public double[][] bobotBaruW(double[][] deltaBobotW, double[][] bobotWLama){
        int i,j;
        double[][] bobotWBaru;
        
        bobotWBaru = new double[deltaBobotW.length][deltaBobotW[0].length];
        
        for(i=0; i<deltaBobotW.length; i++){
            for(j=0; j<deltaBobotW[i].length; j++){
                bobotWBaru[i][j] = deltaBobotW[i][j] + bobotWLama[i][j];
            }
        }
        
        return bobotWBaru;
    }
    
    public double[][] bobotBaruV(double[][] deltaBobotV, double[][] bobotVLama){
        int i,j;
        
        double[][] bobotVBaru;
        
        bobotVBaru = new double[deltaBobotV.length][deltaBobotV[0].length];
        
        for(i=0; i<deltaBobotV.length; i++){
            for(j=0; j<deltaBobotV[i].length; j++){
                bobotVBaru[i][j] = deltaBobotV[i][j] + bobotVLama[i][j];
            }
        }
        
        return bobotVBaru;
    }
    
    public void BackPropagation(int nHiddenNode, int nOutputNode){
        int i,j,k;
        double[] dataLatih;
        double[] Z_Output;
        double[][] latihData;
        double[][] deltaBobotY;
        double[][] deltaBobotZ;
        double[] errHidden;
        double[] output;
        double Y_Output;
        double target;
        double errOutput;
        
        normalisasiData();
        bobotV = inisialisasiBobotInputToHidden(nHiddenNode);
        bobotW = inisialisasiBobotHiddenToOutput(nHiddenNode, nOutputNode);
                
        OutputPenumpang = new ArrayList<>();
        
        for(i=0; i<dataPelatihan.size(); i++){
            latihData = (double[][])dataPelatihan.get(i);
            output = new double[latihData.length];
            System.out.println("Tahun ke-"+(i+1));
            
            for(j=0; j<latihData.length; j++){
                System.out.println("Prediksi bulan ke-"+(j+1));
                dataLatih = new double[latihData[j].length];

                target = 0;

                for(k=0; k<latihData[j].length; k++){            
                    if(k == 0){
                        dataLatih[k] = 1.0;
                    }
                    else{
                        dataLatih[k] = latihData[j][k-1];
                    }

                    if(k == latihData[j].length-1){
                        target = latihData[j][k];
                    }
                }

                Z_Output = ZOutput(nHiddenNode, dataLatih, bobotV);
                Y_Output = YOutput(nOutputNode, Z_Output, bobotW);

                errOutput = errorOutput(target, Y_Output);
                deltaBobotY = deltaBobotOutput(nHiddenNode, nOutputNode, alpha, errOutput, Z_Output);

                errHidden = errorHidden(nHiddenNode, nOutputNode, Y_Output, Z_Output, deltaBobotY);
                deltaBobotZ = deltaBobotHidden(alpha, errHidden, dataLatih);
                
                System.out.println("iterasi ke-1");
                System.out.println("MSE : "+Math.abs(Math.pow(target-Y_Output, 2.0)));
                System.out.println();
                
                for(k=0; k<epoch-1; k++){
                    bobotW = bobotBaruW(deltaBobotY, bobotW);
                    bobotV = bobotBaruV(deltaBobotZ, bobotV);

                    Z_Output = ZOutput(nHiddenNode, dataLatih, bobotV);
                    Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                    
                    errOutput = errorOutput(target, Y_Output);
                    deltaBobotY = deltaBobotOutput(nHiddenNode, nOutputNode, alpha, errOutput, Z_Output);

                    errHidden = errorHidden(nHiddenNode, nOutputNode, Y_Output, Z_Output, deltaBobotY);
                    deltaBobotZ = deltaBobotHidden(alpha, errHidden, dataLatih);

                    if(k==epoch-2 || Math.abs(Math.pow(target-Y_Output, 2.0)) < error){
                        nEpoch = k+1;
                        
                        System.out.println("iterasi ke-"+(k+2));
                        System.out.println("MSE : "+Math.abs(Math.pow(target-Y_Output, 2.0)));
                        System.out.println();
                        
                        k = (int)epoch;
                    }else{
                        System.out.println("iterasi ke-"+(k+2));
                        System.out.println("MSE : "+Math.abs(Math.pow(target-Y_Output, 2.0)));
                        System.out.println();
                    }
                }
                
                output[j] = Y_Output;
                MSE = Math.pow(Y_Output-target, 2);
            }
            
            /*for(j=0; j<ujiData.length; j++){
                System.out.println("output ke-"+j+" : "+output[j]);
            }*/
            OutputPenumpang.add(output);    
            //System.out.println();
        }
        
        BpisDone = true;
        System.out.println("Proses Perhitungan BackPropagation is Done");
    }
    
    public void BackPropagationUji(int nHiddenNode, int nOutputNode){
        int i,j,k;
        double[] dataUji;
        double[] Z_Output;
        double[][] deltaBobotY;
        double[][] deltaBobotZ;
        double[][] ujiData;
        double[] errHidden;
        double[] output;
        double Y_Output;
        double target;
        double errOutput;
        
        normalisasiDataUji();
        bobotV = inisialisasiBobotInputToHiddenUji();
        bobotW = inisialisasiBobotHiddenToOutputUji();
        OutputPenumpang = new ArrayList<>();
        
        for(i=0; i<dataPengujian.size(); i++){
            ujiData = (double[][])dataPengujian.get(i);
            output = new double[ujiData.length];
            //System.out.println("Output tahun ke-"+i);
            
            for(j=0; j<ujiData.length; j++){
                //System.out.println("Prediksi bulan ke-"+j);
                dataUji = new double[ujiData[j].length];

                target = 0;

                for(k=0; k<ujiData[j].length; k++){            
                    if(k == 0){
                        dataUji[k] = 1.0;
                    }
                    else{
                        dataUji[k] = ujiData[j][k-1];
                    }

                    if(k == ujiData[j].length-1){
                        target = ujiData[j][k];
                    }
                }

                Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                Y_Output = YOutput(nOutputNode, Z_Output, bobotW);

                errOutput = errorOutput(target, Y_Output);
                deltaBobotY = deltaBobotOutput(nHiddenNode, nOutputNode, alpha, errOutput, Z_Output);

                errHidden = errorHidden(nHiddenNode, nOutputNode, Y_Output, Z_Output, deltaBobotY);
                deltaBobotZ = deltaBobotHidden(alpha, errHidden, dataUji);

                //System.out.println();
                //System.out.println("iterasi ke-1");
                for(k=0; k<epoch-1; k++){       
                    //System.out.println("iterasi ke-"+k);
                    bobotW = bobotBaruW(deltaBobotY, bobotW);
                    bobotV = bobotBaruV(deltaBobotZ, bobotV);

                    Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                    Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                    
                    errOutput = errorOutput(target, Y_Output);
                    deltaBobotY = deltaBobotOutput(nHiddenNode, nOutputNode, alpha, errOutput, Z_Output);

                    errHidden = errorHidden(nHiddenNode, nOutputNode, Y_Output, Z_Output, deltaBobotY);
                    deltaBobotZ = deltaBobotHidden(alpha, errHidden, dataUji);

                    if(k==epoch-2 || Math.abs(Math.pow(target-Y_Output, 2.0)) < error){
                        nEpoch = k+1;
                        k = (int)epoch;
                    }
                }
                output[j] = Y_Output;
                MSE = Math.pow(Y_Output-target, 2);
            }
            
            OutputPenumpang.add(output);    
            System.out.println();
        }
        
        System.out.println("Proses Perhitungan BackPropagation is Done");
    }
    
    public boolean isBPDone(){
        return BpisDone;
    }
    
    public int getNEpoch(){
        return nEpoch;
    }
    
    public double getMSE(){
        return MSE;
    }
    
    public ArrayList<double[]> getDataOutput(){
        int i,j;
        denormalisasiData();
        
        for(i=0; i<OutputPenumpang.size(); i++){
            System.out.println("Prediksi Tahun ke-"+i);
            for(j=0; j<OutputPenumpang.get(i).length; j++){
                System.out.println("Bulan ke-"+j+" : "+(int)OutputPenumpang.get(i)[j]);
            }
            System.out.println();
        }
        return OutputPenumpang;
    }
    
    public double[] getDataTahun(){
        return tahunPrediksi;
    }
    
    public ArrayList<double[]> getDataOutputUji(){
        int i,j;
        denormalisasiDataUji();
        
        for(i=0; i<OutputPenumpang.size(); i++){
            System.out.println("Prediksi Tahun ke-"+i);
            for(j=0; j<OutputPenumpang.get(i).length; j++){
                System.out.println("Bulan ke-"+j+" : "+(int)OutputPenumpang.get(i)[j]);
            }
            System.out.println();
        }
        return OutputPenumpang;
    }
    
    public void simpan_parameterHasilBP(){
        int nBobotV, nBobotW, nDataVAll, nDataWAll, nParameterBp, i, j;
        boolean flagBp;
        DatabaseManager databaseManager;
        String sql_BP;
        
        databaseManager = new DatabaseManager();
        databaseManager.set_KoneksiDatabase();
        
        sql_BP = "select count(*) from parameter_bp where epoch="+(int)epoch+" and learning_rate="+alpha+" and minimum_error="+error;
//        System.out.println(sql_BP);
        flagBp = databaseManager.cekKesamaanParameter(sql_BP);
        
        //mencari jumlah seluruh bobot dari database bobot V 
        sql_BP = "select count(*) from bobotbp_v";
        nDataVAll = databaseManager.SelectSingleOrder(sql_BP);

        //mencari jumlah seluruh bobot dari database bobot W 
        sql_BP = "select count(*) from bobotbp_w";
        nDataWAll = databaseManager.SelectSingleOrder(sql_BP);
        
        if(flagBp == false){
            //mencari jumlah data seluruh dari tabel parameter_bp 
            sql_BP = "select count(*) from parameter_bp";
            nParameterBp = databaseManager.getSumData(sql_BP);
            
            nParameterBp = nParameterBp+1;
            //insert data parameter untuk tabel parameter_bp
            sql_BP = "INSERT INTO parameter_bp (id_percobaan, epoch, learning_rate, minimum_error) VALUES (?, ?, ?, ?)";
            databaseManager.insertParameterBp(sql_BP, nParameterBp, epoch, alpha, error);
            
            int n;

            //insert data bobot v untuk tabel bobotbp_v
            n = nDataVAll+1;
            sql_BP = "INSERT INTO bobotbp_v (id_bobotv, id_percobaan, node_input, node_hidden, nilai_bobotv) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotV.length; i++){
                for(j=0; j<bobotV[i].length; j++){
                    databaseManager.insertDataBobotV(sql_BP, bobotV[i][j], n, nParameterBp, i, j);
                    n = n+1;
                }
            }
            
            //insert data bobot w untuk tabel bobotbp_w
            n=nDataWAll+1;
            sql_BP = "INSERT INTO bobotbp_w (id_bobotw, id_percobaan, node_hidden, node_output, nilai_bobotw) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotW.length; i++){
                for(j=0; j<bobotW[i].length; j++){
                    databaseManager.insertDataBobotW(sql_BP, bobotW[i][j], n, nParameterBp, i, j);
                    n = n+1;
                }
            }
            
        }
        else{
            int id, idAwalV, idAwalW, nDataV, nDataW;
            //mencari percobaan ke - ? berdasarkan data dari epoch, learning rate, dan minimum error
            sql_BP = "select id_percobaaan from parameter_bp where epoch="+epoch+" and learning_rate="+alpha+" and minimum_error="+error;
            id = databaseManager.SelectSingleOrder(sql_BP);
            
            //mencari jumlah data dari tabel bobotbp_v berdasarkan percobaan ke - ?
            sql_BP = "select count(*) from bobotbp_v where id_percobaan="+id;
            nBobotV = databaseManager.SelectSingleOrder(sql_BP);
            
            //mencari id awal bobot V dari tabel bobotbp_w berdasarkan percobaan ke - ?
            sql_BP = "select id_bobotv from  bobotbp_v where id_percobaan="+id;
            idAwalV = databaseManager.SelectSingleOrder(sql_BP);
            
            //mencari jumlah data dari tabel bobotbp_v berdasarkan percobaan ke - ?
            sql_BP = "select count(*) from bobotbp_w where id_percobaan="+id;
            nBobotW = databaseManager.SelectSingleOrder(sql_BP);
            
            //mencari id awal bobot W dari tabel bobotbp_w berdasarkan percobaan ke - ?
            sql_BP = "select id_bobotw from  bobotbp_w where id_percobaan="+id;
            idAwalW = databaseManager.SelectSingleOrder(sql_BP);
            
            int n;

            //menghapus data bobot V berdasarkan id bobot awal sampai bobot akhir pada percobaaan ke - ?
            for(i=idAwalV; i<=nBobotV; i++){
                sql_BP = "delete from bobotbp_v where id_bobotv = ?";
                databaseManager.deleteDataBobotV(sql_BP, i);
            }

            //menghapus data bobot W berdasarkan id bobot awal sampai bobot akhir pada percobaaan ke - ?
            for(i=idAwalW; i<=nBobotW; i++){
                sql_BP = "delete from bobotbp_w where id_bobotw = ?";
                databaseManager.deleteDataBobotW(sql_BP, i);
            }
            
            //mengubah id bobot V berdasarkan id bobot akhir percobaaan ke - ? sampai bobot akhir percobaan terakhir  
            for(i=nBobotV+1; i<=nDataVAll; i++){
                sql_BP = "UPDATE bobotbp_v set id_bobotv=? where id_bobotv = ?";
                databaseManager.updateIdBobotV(sql_BP, idAwalV, i);
                idAwalV = idAwalV + 1;
            }

            //menghapus id bobot W berdasarkan id bobot akhir percobaaan ke - ? sampai bobot akhir percobaan terakhir  
            for(i=nBobotW+1; i<=nDataWAll; i++){
                sql_BP = "UPDATE bobotbp_w set id_bobotw=? where id_bobotw = ?";
                databaseManager.updateIdBobotW(sql_BP, idAwalW, i);
                idAwalW = idAwalW + 1;
            }
            
            //mencari jumlah data seluruh bobot dari database bobot V 
            sql_BP = "select count(*) from bobotbp_v";
            nDataV = databaseManager.SelectSingleOrder(sql_BP);

            //mencari jumlah data seluruh bobot dari database bobot W 
            sql_BP = "select count(*) from bobotbp_w";
            nDataW = databaseManager.SelectSingleOrder(sql_BP);
            
            n = nDataV+1;
            sql_BP = "INSERT INTO bobotbp_v (id_bobotv, id_percobaan, node_input, node_hidden, nilai_bobotv) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotV.length; i++){
                for(j=0; j<bobotV[i].length; j++){
                    databaseManager.insertDataBobotV(sql_BP, bobotV[i][j], n, id, i, j);
                    n = n+1;
                }
            }

            n = nDataW+1;
            sql_BP = "INSERT INTO bobotbp_w (id_bobotw, id_percobaan, node_hidden, node_output, nilai_bobotw) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotW.length; i++){
                for(j=0; j<bobotW[i].length; j++){
                    databaseManager.insertDataBobotW(sql_BP, bobotW[i][j], n, id, i, j);
                    n = n+1;
                }
            }
        }
    }
}
