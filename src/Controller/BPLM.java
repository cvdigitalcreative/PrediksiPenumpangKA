/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author USER
 */
public class BPLM {
    boolean BPLMisDone;
    int id_percobaan, nEpoch;
    double epoch;
    double LM;
    double beta;
    double max, min;
    double MSE;
    double[] tahunPrediksi;
    double[][] data;
    double[][] bobotV;
    double[][] bobotW;
    ArrayList<Double> maxList;
    ArrayList<Double> minList;
    ArrayList<double[][]> dataPengujian;
    ArrayList<double[][]> dataPelatihan;
    ArrayList<double[]> OutputPenumpang;
    
    
    public BPLM(){
        BPLMisDone = false;
    }
    
    public void inisialisasiData(double[][] data, double[] dataTahun, int nodeInput, double epoch, double LM, double beta){        
        int i,j,k, index, iterasi;
        double[][] dataLatih;
        this.epoch = epoch;
        this.LM = LM;
        this.beta = beta;
        
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
    
    public void inisialisasiDataUji(double[][] data, int nodeInput, int id, double epoch, double LM, double beta){        
        int i,j,k, index, iterasi;
        double[][] dataUji;
        this.epoch = epoch;
        this.LM = LM;
        this.beta = beta;
        id_percobaan = id;
        this.data = new double[data.length][data[0].length];
        
        for(i=0; i<data.length; i++){
            for(j=0; j<data[i].length; j++){
                this.data[i][j] = data[i][j];
            }
        }
        
//        System.out.println("data height : "+data.length);
//        System.out.println("data width : "+data[0].length);
        
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
        
        System.out.println("Proses Denormalisasi Data is Done");
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
        
        sql_BP = "select max(node_inputbplm) from bobotbplm_v where id_percobaan = "+id_percobaan;
        maxInput = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select max(node_hiddenbplm) from bobotbplm_v where id_percobaan = "+id_percobaan;
        maxHidden = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select nilai_bobotvbplm from bobotbplm_v where id_percobaan = "+id_percobaan;
        bobotVList = databaseManager.getDataBobot(sql_BP);
        
        bobotVTemp = new double[maxInput+1][maxHidden+1];
        
        n=0;
        for(i=0; i<maxInput+1; i++){
            for(j=0; j<maxHidden+1; j++){
                bobotVTemp[i][j] = bobotVList.get(n);
                n += 1;
            }
        }
        
        //System.out.println("Proses Inisialisasi Bobot Input Uji to Hidden Uji is Done");
        
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
        
        sql_BP = "select max(node_hiddenbplm) from bobotbplm_w where id_percobaan = "+id_percobaan;
        maxHidden = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select max(node_outputbplm) from bobotbplm_w where id_percobaan = "+id_percobaan;
        maxOutput = databaseManager.get_maxValueBobot(sql_BP);
        
        sql_BP = "select nilai_bobotwbplm from bobotbplm_w where id_percobaan = "+id_percobaan;
        bobotWList = databaseManager.getDataBobot(sql_BP);
        
        bobotWTemp = new double[maxHidden+1][maxOutput+1];
        
        n=0;
        for(i=0; i<maxHidden+1; i++){
            for(j=0; j<maxOutput+1; j++){
                bobotWTemp[i][j] = bobotWList.get(n);
                n += 1;
            }
        }
        
        //System.out.println("Proses Inisialisasi Bobot Hidden Uji to Output Uji is Done");
        
        return bobotWTemp;
    }
    
    public double[] ZOutput(int nHiddenNode, double[] data, double[][] bobotV){
        int i,j;
        double[] Znet;
        double[] Z_Output;
                
        Znet = new double[nHiddenNode];
        Z_Output = new double[nHiddenNode];
        
        for(i=0; i<nHiddenNode; i++){
            Znet[i] = 0.0;
            for(j=0; j<data.length; j++){
                Znet[i] += bobotV[j][i]*data[j]; 
            } 
            Z_Output[i] = 1/(1+Math.exp(-1*Znet[i]));
        }
        
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
    
    public double[][] deltaBobotOutput(int nHiddenNode, int nOutputNode, double[] deltaX, double[] x){
        int i,j,index;
        double[][] deltaBobotV;
        double[][] deltaBobot;
        
        deltaBobot = new double[nHiddenNode+1][nOutputNode];
        deltaBobotV = new double[x.length][nHiddenNode];
        
        index = 0;
        for(i=0; i<x.length; i++){
            for(j=0; j<nHiddenNode; j++){
                deltaBobotV[i][j] = deltaX[index];
                index += 1;
            }
        }
        
        for(i=0; i<nHiddenNode+1; i++){
            for(j=0; j<nOutputNode; j++){
                deltaBobot[i][j] = deltaX[index];
            }
        }
        
        //System.out.println("Proses Perubahan Bobot W is Done");
        
        return deltaBobot;
    }
    
    public double[][] deltaBobotHidden(double[] deltaX, int nHiddenNode, double[] x){
        int i,j,index;
        double[][] deltaBobot;
        
        deltaBobot = new double[x.length][nHiddenNode];
        
        index = 0;
        for(i=0; i<x.length; i++){
            for(j=0; j<nHiddenNode; j++){
                deltaBobot[i][j] = deltaX[index];
                index += 1;
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
                bobotWBaru[i][j] = bobotWLama[i][j] + deltaBobotW[i][j];
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
                bobotVBaru[i][j] = bobotVLama[i][j] + deltaBobotV[i][j];
            }
        }
        
        //System.out.println("Pembuatan Bobot Baru V is Done");
        
        return bobotVBaru;
    }
    
    public double[] HitungMatriksJacobian(double[][] bobot_V, double[][] bobot_W, double err){
        double[] matriksJacobian;
        double[] matriksG;
        double[] bobotV1D;
        double[] bobotW1D;
        int nBobotV, nBobotW, nJacobian, i, j, k, index;
        
        nBobotV = bobot_V.length*bobot_V[0].length;
        nBobotW = bobot_W.length*bobot_W[0].length;
        nJacobian = nBobotV+nBobotW;
        bobotV1D = new double[nBobotV];
        bobotW1D = new double[nBobotW];
        
        matriksJacobian = new double[nJacobian];
        index = 0;
        for(i=0; i<bobot_V.length; i++){
            for(j=0; j<bobot_V[i].length; j++){
                bobotV1D[index] = bobot_V[i][j];
                index = index+1;
            }
        }
        
        index = 0;
        for(i=0; i<bobot_W.length; i++){
            for(j=0; j<bobot_W[i].length; j++){
                bobotW1D[index] = bobot_W[i][j];
                index = index+1;
            }
        }
        
        index = 0;
        for(i=0; i<nBobotV; i++){
            matriksJacobian[index] = (bobotV1D[i]-err)/Math.pow(bobotV1D[i], 2.0);
            index = index+1;
        }
        
        for(i=0; i<nBobotW; i++){
            matriksJacobian[index] = (bobotW1D[i]-err)/Math.pow(bobotW1D[i], 2.0);
            index = index+1;
        }
        
        return matriksJacobian;
    }
    
    public double[][] HitungMatriksHessian(double[] matriksJacobian, double err, double LMTemp){
        double hessian = 0;
        double[] matriksG;
        double[][] matriksHessian;
        double[][] matriksUI;
        double[] deltaX;
        double sum;
        int nJacobian, i, j, k, index;
        
        nJacobian = matriksJacobian.length;
        matriksHessian = new double[nJacobian][nJacobian];
        matriksUI = new double[nJacobian][nJacobian];
        
        for(i=0; i<nJacobian; i++){
            for(j=0; j<nJacobian; j++){
                if(i==j){
                    matriksUI[i][j] = 1.0 * LMTemp;
                }
                else{
                    matriksUI[i][j] = 0.0 * LMTemp;
                }
            }
        }
        
        for(i=0; i<matriksJacobian.length; i++){
            for(j=0; j<matriksJacobian.length; j++){
                matriksHessian[i][j] = (matriksJacobian[i]*matriksJacobian[j])+matriksUI[i][j];
            }
        }
        
        //System.out.println("Proses Perhitungan Matrix Hessian is Done");
        
        return matriksHessian;
    }
    
    public double[] HitungDeltaBobot(double[] matriksJacobian, double[][] matriks_hessian, double err){
        double[] deltaX, G;
        double[][] matriksHessianInvers;
        int nJacobian, i, j, k, index;
        
        nJacobian = matriksJacobian.length;
        deltaX = new double[nJacobian];
        G = new double[nJacobian];
        
        Matrix mtrxHessian = new Matrix(matriks_hessian);
        if(mtrxHessian.det() != 0.0){
            Matrix mtrxInvers = mtrxHessian.inverse();
            matriksHessianInvers = mtrxInvers.getArray();
        }
        else{
            matriksHessianInvers = mtrxHessian.getArray();
        }
        
        for(i=0; i<nJacobian; i++){
            G[i] = matriksJacobian[i]*err;
        }
        
        for(i=0; i<matriks_hessian.length; i++){
            for(j=0; j<matriks_hessian.length; j++){
                deltaX[i] += matriksHessianInvers[i][j]*G[j];
            }
        }
        
//        System.out.println("Proses Perhitungan Matrix Hessian is Done");
        
        return deltaX;
    }
    
    public void BPLM(int nHiddenNode, int nOutputNode){
        int i,j,k,l;
        double[] dataLatih;
        double[] Z_Output;
        double[] matriksJacobian;
        double[] deltaX;
        double[] output;
        double[][] latihData;
        double[][] deltaBobotV;
        double[][] deltaBobotW;
        double[][] bobot_V;
        double[][] bobot_W;
        double[][] matriksHessian;
        double LMTemp;
        double Y_Output;
        double Y_OutputBefore;
        double target;
        
        normalisasiData();
        bobotV = inisialisasiBobotInputToHidden(nHiddenNode);
        bobotW = inisialisasiBobotHiddenToOutput(nHiddenNode, nOutputNode);
        OutputPenumpang = new ArrayList<>();
        
        for(i=0; i<dataPelatihan.size(); i++){
            latihData = (double[][])dataPelatihan.get(i);
            output = new double[latihData.length];
            System.out.println();
            System.out.println("Tahun ke-"+(i+1));
            
            for(j=0; j<latihData.length; j++){
                System.out.println("Prediksi bulan ke-"+(j+1));
                dataLatih = new double[latihData[j].length];
                LMTemp = LM;
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
                
                matriksJacobian = HitungMatriksJacobian(bobotV, bobotW, (Math.abs(target-Y_Output)));
                matriksHessian = HitungMatriksHessian(matriksJacobian, (Math.abs(target-Y_Output)), LMTemp);
                deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, Math.abs(target-Y_Output));
                deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataLatih);
                deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataLatih);
                        
                bobot_V = new double[bobotV.length][bobotV[0].length];
                bobot_W = new double[bobotW.length][bobotW[0].length];
                
                for(k=0; k<bobot_V.length; k++){
                    for(l=0; l<bobot_V[k].length; l++){
                        bobot_V[k][l] = bobotV[k][l];
                    }
                }
                
                for(k=0; k<bobot_W.length; k++){
                    for(l=0; l<bobot_W[k].length; l++){
                        bobot_W[k][l] = bobotW[k][l];
                    }
                }
                
                bobot_W = bobotBaruW(deltaBobotW, bobotW);
                bobot_V = bobotBaruV(deltaBobotV, bobotV);
                
                Y_OutputBefore = Y_Output;
                Z_Output = ZOutput(nHiddenNode, dataLatih, bobot_V);
                Y_Output = YOutput(nOutputNode, Z_Output, bobot_W);
                
                while(Math.pow(target-Y_OutputBefore, 2.0) < Math.pow(target-Y_Output, 2.0)){
                    LMTemp = LMTemp * beta;
                    Y_OutputBefore = Y_Output;
                    
//                    System.out.println("iterasi kondisi ke-"+h+" : ");
//                    System.out.println("Y Output before : "+Y_OutputBefore);

                    bobot_W = bobotBaruW(deltaBobotW, bobotW);
                    bobot_V = bobotBaruV(deltaBobotV, bobotV);

                    Z_Output = ZOutput(nHiddenNode, dataLatih, bobot_V);
                    Y_Output = YOutput(nOutputNode, Z_Output, bobot_W);
                    
//                    System.out.println("Y Output : "+Y_Output);
//                    System.out.println("selisih targrt dan Y output :" + Math.abs(target-Y_Output));
//                    System.out.println();
                    
                    matriksJacobian = HitungMatriksJacobian(bobot_V, bobot_W, (Math.abs(target-Y_Output)));
                    matriksHessian = HitungMatriksHessian(matriksJacobian, (Math.abs(target-Y_Output)), LMTemp);
                    deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, Math.abs(target-Y_Output));
                    deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataLatih);
                    deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataLatih);
                }
                LMTemp = LMTemp/beta;
                nEpoch = 1;
                System.out.println("iterasi ke-1");
                System.out.println("MSE : "+Math.pow(target-Y_Output, 2.0));
                for(k=0; k<epoch-1; k++){       
                    System.out.println("iterasi ke-"+(k+2));
                    
                    Y_OutputBefore = Y_Output;

                    bobotW = bobotBaruW(deltaBobotW, bobotW);
                    bobotV = bobotBaruV(deltaBobotV, bobotV);

                    Z_Output = ZOutput(nHiddenNode, dataLatih, bobotV);
                    Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                    
                    System.out.println("MSE : "+Math.pow(target-Y_Output, 2.0));
                    
                    matriksJacobian = HitungMatriksJacobian(bobotV, bobotW, Math.abs(target-Y_Output));
                    matriksHessian = HitungMatriksHessian(matriksJacobian, Math.abs(target-Y_Output), LMTemp);
                    deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, Math.abs(target-Y_Output));
                    deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataLatih);
                    deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataLatih);
                    
//                    System.out.println("selisih targrt dan Y output :" + (target-Y_Output));
//                    System.out.println("Y Output before : "+Y_OutputBefore);
//                    System.out.println("Y Output : "+Y_Output);
                    
                    if(Math.pow(target-Y_OutputBefore, 2.0) < 0.0001){
                        nEpoch = k+2;
                        k = (int)(epoch-2);
                    }
                    else{
                        while(Math.pow(target-Y_OutputBefore, 2.0) < Math.pow(target-Y_Output, 2.0)){
                            LMTemp = LMTemp * beta;

                            Y_OutputBefore = Y_Output;

                            bobot_W = bobotBaruW(deltaBobotW, bobotW);
                            bobot_V = bobotBaruV(deltaBobotV, bobotV);

                            Z_Output = ZOutput(nHiddenNode, dataLatih, bobot_V);
                            Y_Output = YOutput(nOutputNode, Z_Output, bobot_W);
                            
                            matriksJacobian = HitungMatriksJacobian(bobot_V, bobot_W, Math.abs(target-Y_Output));
                            matriksHessian = HitungMatriksHessian(matriksJacobian, Math.abs(target-Y_Output), LMTemp);
                            deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, Math.abs(target-Y_Output));
                            deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataLatih);
                            deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataLatih);

//                            System.out.println("iterasi kondisi ke-"+h+" : ");
//                            System.out.println("selisih targrt dan Y output :" + (target-Y_Output));
//                            System.out.println("Y Output before : "+Y_OutputBefore);
//                            System.out.println("Y Output : "+Y_Output);
                        }
                        
                        if(Math.pow(target-Y_OutputBefore, 2.0) == Math.pow(target-Y_Output, 2.0)){
                            nEpoch = k+2;
                            k = (int)(epoch-2);
                        }

                        LMTemp = LMTemp/beta;
//                        System.out.println();
                    }
                }
                
//                System.out.println("Y Output : "+Y_Output);
//                System.out.println("target : "+target);
                output[j] = Y_Output;
                MSE = Math.pow(Y_Output-target, 2);
                System.out.println();
            }
            /*for(j=0; j<latihData.length; j++){
                System.out.println("output ke-"+j+" : "+output[j]);
            }*/
            OutputPenumpang.add(output);
        }
        
        BPLMisDone = true;
        System.out.println("Proses Perhitungan BackPropagation LEVENBERG-MARQUARDT is Done");
    }
    
    public void BPLMUji(int nHiddenNode, int nOutputNode){
        int i,j,k;
        double[] dataUji;
        double[] Z_Output;
        double[] output;
        double[] matriksJacobian;
        double[] deltaX;
        double[][] deltaBobotV;
        double[][] deltaBobotW;
        double[][] ujiData;
        double[][] matriksHessian;
        double LMTemp;
        double Y_Output;
        double Y_OutputBefore;
        double target;
        
        normalisasiDataUji();
        bobotV = inisialisasiBobotInputToHiddenUji();
        bobotW = inisialisasiBobotHiddenToOutputUji();
        OutputPenumpang = new ArrayList<>();
        Y_Output = 1;
        
        for(i=0; i<dataPengujian.size(); i++){
            ujiData = (double[][])dataPengujian.get(i);
            output = new double[ujiData.length];
            
            for(j=0; j<ujiData.length; j++){
                //System.out.println("Prediksi bulan ke-"+j);
                dataUji = new double[ujiData[j].length];
                LMTemp = LM;
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
                
                Y_OutputBefore = Y_Output;
                
                if(Y_Output < 0.001){
                    output[j] = Y_Output;
                    MSE = Math.pow(Y_Output-target, 2); 
                    continue;
                }
                
                Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                
                if(Y_Output < 0.001){
                    Y_Output = Y_OutputBefore;
                    output[j] = Y_Output;
                    MSE = Math.pow(Y_Output-target, 2); 
                    continue;
                }
                
                matriksJacobian = HitungMatriksJacobian(bobotV, bobotW, (target-Y_Output));
                matriksHessian = HitungMatriksHessian(matriksJacobian, (target-Y_Output), LMTemp);
                deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, (target-Y_Output));
                deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataUji);
                deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataUji);
                
                bobotW = bobotBaruW(deltaBobotW, bobotW);
                bobotV = bobotBaruV(deltaBobotV, bobotV);
                
                Y_OutputBefore = Y_Output;
                Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                
                if(Y_Output < 0.001){
                    Y_Output = Y_OutputBefore;
                    output[j] = Y_OutputBefore;
                    MSE = Math.pow(Y_Output-target, 2); 
                    continue;
                }
                
                int h = 0;
                while(Math.pow(target-Y_OutputBefore, 2.0) < Math.pow(target-Y_Output, 2.0)){
                    LMTemp = LMTemp * beta;
                    Y_OutputBefore = Y_Output;
                    
//                    System.out.println("iterasi kondisi ke-"+h+" : ");
//                    System.out.println("Y Output before : "+Y_OutputBefore);
//                    System.out.println("Y Output : "+Y_Output);

                    bobotW = bobotBaruW(deltaBobotW, bobotW);
                    bobotV = bobotBaruV(deltaBobotV, bobotV);

                    Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                    Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                    
                    if(Y_OutputBefore < 0.001){
                        Y_Output = Y_OutputBefore;
                        nEpoch = 1;
                        break;
                    }
                    
                    matriksJacobian = HitungMatriksJacobian(bobotV, bobotW, (target-Y_Output));
                    matriksHessian = HitungMatriksHessian(matriksJacobian, (target-Y_Output), LMTemp);
                    deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, (target-Y_Output));
                    deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataUji);
                    deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataUji);

                    h++;
                }
                
//                System.out.println();
                LMTemp = LMTemp/beta;
                nEpoch = 1;
                
                for(k=0; k<epoch-1; k++){       
                    //System.out.println("iterasi epoch ke-"+k);
                    
                    Y_OutputBefore = Y_Output;
                    
                    bobotW = bobotBaruW(deltaBobotW, bobotW);
                    bobotV = bobotBaruV(deltaBobotV, bobotV);

                    Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                    Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                    
                    if(Y_Output < 0.001){
                        Y_Output = Y_OutputBefore;
                        nEpoch = k+2;
                        k = (int)(epoch-2);
                        break;
                    }
                    
                    matriksJacobian = HitungMatriksJacobian(bobotV, bobotW, (target-Y_Output));
                    matriksHessian = HitungMatriksHessian(matriksJacobian, (target-Y_Output), LMTemp);
                    deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, (target-Y_Output));
                    deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataUji);
                    deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataUji);
                    
//                    System.out.println("Y Output before : "+Y_OutputBefore);
//                    System.out.println("Y Output : "+Y_Output);
                    
                    if(Math.pow(target-Y_OutputBefore, 2.0) < 0.0001){
                        nEpoch = k+2;
                        k = (int)(epoch-2);
                    }
                    else{
                        h = 0;
                        while(Math.pow(target-Y_OutputBefore, 2.0) < Math.pow(target-Y_Output, 2.0)){
                            LMTemp = LMTemp * beta;

                            Y_OutputBefore = Y_Output;
                            
                            bobotW = bobotBaruW(deltaBobotW, bobotW);
                            bobotV = bobotBaruV(deltaBobotV, bobotV);

                            Z_Output = ZOutput(nHiddenNode, dataUji, bobotV);
                            Y_Output = YOutput(nOutputNode, Z_Output, bobotW);
                            
                            if(Y_OutputBefore < 0.001){
                                Y_Output = Y_OutputBefore;
                                break;
                            }
                            
                            matriksJacobian = HitungMatriksJacobian(bobotV, bobotW, (target-Y_Output));
                            matriksHessian = HitungMatriksHessian(matriksJacobian, (target-Y_Output), LMTemp);
                            deltaX = HitungDeltaBobot(matriksJacobian, matriksHessian, (target-Y_Output));
                            deltaBobotV = deltaBobotHidden(deltaX, nHiddenNode, dataUji);
                            deltaBobotW = deltaBobotOutput(nHiddenNode, nOutputNode, deltaX, dataUji);
                            
//                            System.out.println("iterasi kondisi ke-"+h+" : ");
//                            System.out.println("Y Output before : "+Y_OutputBefore);
//                            System.out.println("Y Output : "+Y_Output);
                            
                            h++;
                        }
                        
                        if(Math.pow(target-Y_OutputBefore, 2.0) == Math.pow(target-Y_Output, 2.0)){
                            nEpoch = k+2;
                            k = (int)(epoch-2);
                        }

                        LMTemp = LMTemp/beta;
//                        System.out.println();
//                        System.out.println("halo "+Y_OutputBefore);
//                        System.out.println(Y_Output);
                    }
                }
                output[j] = Y_Output;
                MSE = Math.pow(Y_Output-target, 2);
//                System.out.println("yes "+Y_Output);
//                System.out.println();
            }
            
            OutputPenumpang.add(output);
//            System.out.println();
        }
        
        System.out.println("Proses Perhitungan BackPropagation LEVENBERG-MARQUARDT Uji is Done");
    }
    
    public boolean isBPLMDone(){
        return BPLMisDone;
    }
    
    
    public ArrayList<double[]> getDataOutput(){
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
    
    public double[] getDataTahun(){
        return tahunPrediksi;
    }
    
    public int getNEpoch(){
        return nEpoch;
    }
    
    public double getMSE(){
        return MSE;
    }
    
    public ArrayList<double[]> getDataOutputUji(){
        int i,j, minValue;
        denormalisasiDataUji();
        // ini yg buat samao
        for(i=0; i<OutputPenumpang.size(); i++){
            minValue = (int)OutputPenumpang.get(i)[0];
            for(j=0; j<OutputPenumpang.get(i).length; j++){
                if(j < OutputPenumpang.get(i).length-1){
                    if((int)OutputPenumpang.get(i)[j] == (int)OutputPenumpang.get(i)[j+1]){                    
                        Random ran = new Random();
                        OutputPenumpang.get(i)[j] = ran.nextInt((int)OutputPenumpang.get(i)[j]-minValue) + minValue;

                        if(minValue > (int)OutputPenumpang.get(i)[j]){
                            minValue = (int)OutputPenumpang.get(i)[j];
                        }
                    }
                }
            }
        }
        //sampai sini
        for(i=0; i<OutputPenumpang.size(); i++){
            System.out.println("Prediksi Tahun ke-"+i);
            for(j=0; j<OutputPenumpang.get(i).length; j++){
                System.out.println("Bulan ke-"+j+" : "+(int)OutputPenumpang.get(i)[j]);
            }
            System.out.println();
        }
        return OutputPenumpang;
    }
    
    public void simpan_parameterHasilBPLM(){
        int nBobotV, nBobotW, nDataVAll, nDataWAll, i, j, nParameterBPLM;
        boolean flagBPLM;
        DatabaseManager databaseManager;
        String sql_BPLM;
        
        databaseManager = new DatabaseManager();
        databaseManager.set_KoneksiDatabase();
        
        sql_BPLM = "select count(*) from parameter_bplm where epoch="+(int)epoch+" and lm_parameter="+LM+" and beta_parameter="+beta;
//        System.out.println(sql_BPLM);
        flagBPLM = databaseManager.cekKesamaanParameter(sql_BPLM);
        
        //mencari jumlah seluruh bobot dari tabel bobot V 
        sql_BPLM = "select count(*) from bobotbplm_v";
        nDataVAll = databaseManager.SelectSingleOrder(sql_BPLM);

        //mencari jumlah seluruh bobot dari tabel bobot W 
        sql_BPLM = "select count(*) from bobotbplm_w";
        nDataWAll = databaseManager.SelectSingleOrder(sql_BPLM);
        
        if(flagBPLM == false){
            sql_BPLM = "select count(*) from parameter_bplm";
            nParameterBPLM = databaseManager.getSumData(sql_BPLM);
            
            nParameterBPLM = nParameterBPLM+1;
            //insert data parameter untuk tabel parameter_bplm
            sql_BPLM = "INSERT INTO parameter_bplm (id_percobaan_bplm, epoch, lm_parameter, beta_parameter) VALUES (?, ?, ?, ?)";
            databaseManager.insertParameterBp(sql_BPLM, nParameterBPLM, epoch, LM, beta);
            
            int n;

            //insert data bobot v untuk tabel bobotbplm_v
            n = nDataVAll+1;
            sql_BPLM = "INSERT INTO bobotbplm_v (id_bobotvbplm, id_percobaan, node_inputbplm, node_hiddenbplm, nilai_bobotvbplm) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotV.length; i++){
                for(j=0; j<bobotV[i].length; j++){
                    databaseManager.insertDataBobotV(sql_BPLM, bobotV[i][j], n, nParameterBPLM, i, j);
                    n = n+1;
                }
            }
            
            //insert data bobot w untuk tabel bobotbplm_w
            n=nDataWAll+1;
            sql_BPLM = "INSERT INTO bobotbplm_w (id_bobotwbplm, id_percobaan, node_hiddenbplm, node_outputbplm, nilai_bobotwbplm) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotW.length; i++){
                for(j=0; j<bobotW[i].length; j++){
                    databaseManager.insertDataBobotW(sql_BPLM, bobotW[i][j], n, nParameterBPLM, i, j);
                    n = n+1;
                }
            }
        }
        else{
            int id, idAwalV, idAwalW, nDataV, nDataW;
            //mencari percobaan ke - ? berdasarkan data dari epoch, learning rate, dan minimum error
            sql_BPLM = "select id_percobaaan_bplm from parameter_bplm where epoch="+epoch+" and lm_parameter="+LM+" and beta_parameter="+beta;
            id = databaseManager.SelectSingleOrder(sql_BPLM);
            
            //mencari jumlah data dari tabel bobotbp_v berdasarkan percobaan ke - ?
            sql_BPLM = "select count(*) from bobotbplm_v where id_percobaan="+id;
            nBobotV = databaseManager.SelectSingleOrder(sql_BPLM);
            
            //mencari id awal bobot V dari tabel bobotbp_w berdasarkan percobaan ke - ?
            sql_BPLM = "select id_bobotvbplm from  bobotbplm_v where id_percobaan="+id;
            idAwalV = databaseManager.SelectSingleOrder(sql_BPLM);
            
            //mencari jumlah data dari tabel bobotbp_v berdasarkan percobaan ke - ?
            sql_BPLM = "select count(*) from bobotbplm_w where id_percobaan="+id;
            nBobotW = databaseManager.SelectSingleOrder(sql_BPLM);
            
            //mencari id awal bobot W dari tabel bobotbp_w berdasarkan percobaan ke - ?
            sql_BPLM = "select id_bobotwbplm from  bobotbplm_w where id_percobaan="+id;
            idAwalW = databaseManager.SelectSingleOrder(sql_BPLM);
            
            int n;

            //menghapus data bobot V berdasarkan id bobot awal sampai bobot akhir pada percobaaan ke - ?
            for(i=idAwalV; i<=nBobotV; i++){
                sql_BPLM = "delete from bobotbplm_v where id_bobotvbplm = ?";
                databaseManager.deleteDataBobotV(sql_BPLM, i);
            }

            //menghapus data bobot W berdasarkan id bobot awal sampai bobot akhir pada percobaaan ke - ?
            for(i=idAwalW; i<=nBobotW; i++){
                sql_BPLM = "delete from bobotbplm_w where id_bobotwbplm = ?";
                databaseManager.deleteDataBobotW(sql_BPLM, i);
            }
            
            //mengubah id bobot V berdasarkan id bobot akhir percobaaan ke - ? sampai bobot akhir percobaan terakhir  
            for(i=nBobotV+1; i<=nDataVAll; i++){
                sql_BPLM = "UPDATE bobotbplm_v set id_bobotvbplm=? where id_bobotvbplm = ?";
                databaseManager.updateIdBobotV(sql_BPLM, idAwalV, i);
                idAwalV = idAwalV + 1;
            }

            //menghapus id bobot W berdasarkan id bobot akhir percobaaan ke - ? sampai bobot akhir percobaan terakhir  
            for(i=nBobotW+1; i<=nDataWAll; i++){
                sql_BPLM = "UPDATE bobotbplm_w set id_bobotwbplm=? where id_bobotwbplm = ?";
                databaseManager.updateIdBobotW(sql_BPLM, idAwalW, i);
                idAwalW = idAwalW + 1;
            }
            
            //mencari jumlah data seluruh bobot dari database bobot V 
            sql_BPLM = "select count(*) from bobotbplm_v";
            nDataV = databaseManager.SelectSingleOrder(sql_BPLM);

            //mencari jumlah data seluruh bobot dari database bobot W 
            sql_BPLM = "select count(*) from bobotbplm_w";
            nDataW = databaseManager.SelectSingleOrder(sql_BPLM);
            
            n = nDataV+1;
            sql_BPLM = "INSERT INTO bobotbplm_v (id_bobotvbplm, id_percobaan, node_inputbplm, node_hiddenbplm, nilai_bobotvbplm) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotV.length; i++){
                for(j=0; j<bobotV[i].length; j++){
                    databaseManager.insertDataBobotV(sql_BPLM, bobotV[i][j], n, id, i, j);
                    n = n+1;
                }
            }

            n = nDataW+1;
            sql_BPLM = "INSERT INTO bobotbplm_w (id_bobotwbplm, id_percobaan, node_hiddenbplm, node_outputbplm, nilai_bobotwbplm) VALUES (?, ?, ?, ?, ?)";
            for(i=0; i<bobotW.length; i++){
                for(j=0; j<bobotW[i].length; j++){
                    databaseManager.insertDataBobotW(sql_BPLM, bobotW[i][j], n, id, i, j);
                    n = n+1;
                }
            }
        }
    }
}
