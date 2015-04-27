/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.ArrayList;

/**
 *
 *store variable to transfer stream between class
 *in singleton pattern
 */
public class KnnAlgorithm {

    static KnnAlgorithm instance = null;
    public static KnnAlgorithm getInstance() {
        if(null == instance) {
            instance = new KnnAlgorithm();
        }
        return instance;
    }
    ArrayList<instance> traindata; //training data
    ArrayList<instance> testdata;  //testing data
    ArrayList<String> featureType; // the four type features

    public void setTraindata(ArrayList<instance> traindata) {
        this.traindata = traindata;
    }

    public ArrayList<instance> getTraindata() {
        return traindata;
    }
    
    public void setTestdata(ArrayList<instance> testdata) {
        this.testdata = testdata;
    }

    public ArrayList<instance> getTestdata() {
        return testdata;
    }
    public void setfeatureType(ArrayList<String> featureType) {
        this.featureType = featureType;
    }

    public ArrayList<String> getfeatureType() {
        return featureType;
    }
}
