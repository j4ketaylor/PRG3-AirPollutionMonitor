package com.example.PRG3AirPollutionMonitor;

import java.util.ArrayList;
public class data_analysis {
    public double data=0;
    public double mean(ArrayList<Double> listmean){
        for(int i=0;i< listmean.size();i++) {
            data = data + listmean.get(i);
        }
        data = data/listmean.size();
        System.out.println("Mean="+data);
        return data;
    }

    public double score(ArrayList<Double> listaqi){
        data_analysis a = new data_analysis();
        listaqi.set(0,listaqi.get(0)*2);//Score weighting
        data = a.mean(listaqi);//Computes score
        System.out.println("Score="+data);
        return data;
    }





}