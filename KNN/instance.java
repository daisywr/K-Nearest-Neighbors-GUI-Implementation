/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
 *
 *define the type of instance in training and testing data
 */
public class instance {
	private String cls;
	private double[] fea;
	public instance() {}
	public instance(String cls, double[] features)
	{
		this.cls=cls;
		this.fea=features.clone();
	}
	public void setcls(String cls) {this.cls=cls;}
	public void setfea(double[] features) {this.fea=features;}//
	public String getcls () {return cls;}
	public double[] getfea() {return fea;}	
}