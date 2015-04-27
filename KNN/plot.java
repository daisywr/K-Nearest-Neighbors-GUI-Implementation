/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

/**
 *
 * @author wangru
 */
public class plot extends javax.swing.JFrame {

    /**
     * Creates new form plot
     */
    public plot() {
        initComponents();
    }
    public static int xindex=0;
    public static int yindex=0;
    public static int clickNext=0;
    //public static int clickPrevious=0;
    public final static int range=50;  //set to scale the point in the panel
    public final static int x_bias=-30;  
    public final static int y_bias=-30;
    public final static Color[] defa={Color.BLUE,Color.RED,Color.YELLOW,Color.PINK};
    public final static String[] shape={"+","o","*"};
    public static ArrayList<String> clss=new ArrayList<String>();
    public ArrayList<instance> euclideanDist(double[] testTmp, ArrayList train) {
		// calculate the distance of each instances in test to all train data and return the distance and type
		ArrayList<instance> res = new ArrayList<instance>();
		for (int i = 0; i < train.size(); i++) {
			instance trainTmp = (instance) train.get(i);
			double[] sum = new double[1];
			for (int j = 0; j < testTmp.length; j++)
				sum[0] += Math.sqrt(Math.abs(Math.pow(testTmp[j], 2)-Math.pow(trainTmp.getfea()[j], 2)));
			instance tmp=new instance(trainTmp.getcls(),sum);
			res.add(tmp);
		}
		return res;
	}

	public ArrayList<instance> dist(ArrayList<instance> train, instance test) {
		// calculate distances of a item in test to train data
		ArrayList<instance> dtt = new ArrayList<instance>();
		dtt = euclideanDist(test.getfea(), train);
		return dtt;
	}
	public instance Max(ArrayList<instance> tmp){
            //get the instance with max feature
		instance max=new instance();
		double m=-1;
		for(int i=0; i<tmp.size();i++){
			if(tmp.get(i).getfea()[0]>m){
				max=tmp.get(i);
				m=max.getfea()[0];
			}
		}
		return max;
	}
	public String knn(instance testInstance, float[] xtrain, float[] ytrain) {
                //KNN implemetation
		ArrayList<instance> allTrainInstance = KnnAlgorithm.getInstance().getTraindata(); //return train instance
		ArrayList<instance> testDisToAlltrain = dist(allTrainInstance, testInstance);//return distance of all tests element to train
		ArrayList<instance> KQue=new ArrayList<instance>();//store 10 nearest instance{distance and type}
                ArrayList<String> traincls=new ArrayList<String>();
	        for(int i=0;i<allTrainInstance.size();i++){// extract all possible class in training data
                    instance tmp=allTrainInstance.get(i);
                    String cls=tmp.getcls();
                    if(!traincls.contains(cls)){
                    traincls.add(cls);
                    }
                }					
                for (int j = 0; j < testDisToAlltrain.size(); j++) {// for every instance in train
                    if(KQue.size()>=10){
                        if(Max(KQue).getfea()[0]>testDisToAlltrain.get(j).getfea()[0]){
                            KQue.remove(Max(KQue));
                            KQue.add(testDisToAlltrain.get(j));
                        }
                    }
                    else{
                        KQue.add(testDisToAlltrain.get(j));
                    }
                }
                int cc=0;
                for(int i=0;i<testDisToAlltrain.size();i++){
                    if(KQue.contains(testDisToAlltrain.get(i))){
                        xtrain[cc]=(float)allTrainInstance.get(i).getfea()[xindex];
                        ytrain[cc]=(float)allTrainInstance.get(i).getfea()[yindex];
                        cc++;
                    }//get the 10 nearest point coordinates and keep them in float
                }
		int[] countcls = new int[traincls.size()];
		instance[] kItems=new instance[10];
		instance[] tt=KQue.toArray(kItems);
		for(int e=0; e<tt.length;e++) {
                    for(int ss=0;ss<traincls.size();ss++){
			String a=tt[e].getcls();
			String b=traincls.get(ss);
			if(a.equals(b))//note!
                            countcls[ss]++;
                    }
                }//calculate the times of each class occured
		int maxIndex = 0;
		for (int y = 0; y < countcls.length; y++) {
                    if (countcls[y] > countcls[maxIndex]) {
			maxIndex =y;
                    }
		}//get the index with max times of occuring class
		String testType=traincls.get(maxIndex);
                return testType;
	}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        Xaxis = new javax.swing.JComboBox();
        Yaxis = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        note = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Xaxis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sepal length", "Sepal width", "Petal length", "Petal width" }));
        Xaxis.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                XaxisComponentAdded(evt);
            }
        });
        Xaxis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XaxisActionPerformed(evt);
            }
        });

        Yaxis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sepal length", "Sepal width", "Petal length", "Petal width" }));
        Yaxis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YaxisActionPerformed(evt);
            }
        });

        jLabel1.setText("X:");

        jLabel2.setText("Y:");

        jButton1.setText("Plot training data");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton3.setText("Next");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Plot every test data");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );

        note.setText("Testing!");

        jMenu2.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu2.add(jMenuItem1);

        jMenu4.setText("jMenu4");
        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Yaxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Xaxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Xaxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Yaxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(74, 74, 74))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(10, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void XaxisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XaxisActionPerformed
        
                
    }//GEN-LAST:event_XaxisActionPerformed

    private void YaxisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YaxisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YaxisActionPerformed

    private void XaxisComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_XaxisComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_XaxisComponentAdded

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        String xName=(String)Xaxis.getSelectedItem();
        String yName=(String)Yaxis.getSelectedItem();
        ArrayList<instance> trainData=KnnAlgorithm.getInstance().getTraindata();
        ArrayList<String> feaType=KnnAlgorithm.getInstance().getfeatureType();
        float[] x=new float [trainData.size()];
        float[] y=new float [trainData.size()];
            for(int j=0; j<4; j++){
                if(feaType.get(j).equals(xName))
                    xindex=j;
                if(feaType.get(j).equals(yName))
                    yindex=j;
        }//after user changes the type of 2 dimensions, get the corresponding coordinates of the test data
        for(int i=0;i<trainData.size();i++){// extract the two features into float x and y
            instance tmp=trainData.get(i);
            String cls=tmp.getcls();
            x[i]=(float) tmp.getfea()[xindex];
            y[i]=(float) tmp.getfea()[yindex];
            if(!clss.contains(cls)){
                clss.add(cls);
            }
        }     
        //JPanel panel = new JPanel();
        Graphics2D plot= (Graphics2D) jPanel1.getGraphics();
        //super.paint(plot);
        for(int i=0;i<trainData.size();i++){
            instance tmp=trainData.get(i);
            for(int j=0; j<clss.size();j++){
                if(tmp.getcls().equals(clss.get(j))){
                    plot.setColor(defa[j]);
                    float x1=x[i]*range-x_bias;
                    float y1=(y[i])*range-y_bias;
                    plot.drawString(shape[j],(int)x1,(int)y1);  
                }
            }
        }

    }//GEN-LAST:event_jButton1MouseClicked
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
//        jPanel1.repaint();
        //first initiate the point, clear the panel
        
        int i=clickNext;
        ArrayList<instance> TestData=KnnAlgorithm.getInstance().getTestdata();
        if(i==TestData.size()){
            note.setText("Test done!");
        }
        else{
        instance curTestData=TestData.get(i);
        String testType=new String();
        float xtest=(float) curTestData.getfea()[xindex];
        float ytest=(float) curTestData.getfea()[yindex];
        float[] xtrain=new float[10];
        float[] ytrain=new float[10];
        ArrayList<String> feaType=KnnAlgorithm.getInstance().getfeatureType();
        Graphics2D plot= (Graphics2D) jPanel1.getGraphics(); 
        Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.8f);
        plot.setComposite(c);
        testType=knn(curTestData,xtrain,ytrain);
//achieve link the test to trains with trains color and paint the test in its recogized type color
        int Colorindex=0;
        for(int k=0;k<clss.size();k++){
            if(clss.get(k).equals(testType)){
                Colorindex=k; break;}
        }
        plot.setColor(defa[Colorindex]);//the color is the same as the determined type
        float x1=(xtest)*range-x_bias;
        float y1=(ytest)*range-y_bias;      
        plot.fillRect((int)x1, (int)y1, 6, 6);
        for(int j=0;j<10;j++){   
            float x2=(xtrain[j])*range-x_bias;
            float y2=(ytrain[j])*range-y_bias;
            //set color of edge
            float x1_adp=x1;
            float y1_adp=y1;
            float x2_adp=x2;
            float y2_adp=y2;
            plot.drawLine((int)x1_adp,(int)y1_adp,(int)x2_adp,(int)y2_adp);
        }       
        clickNext++;
        jPanel1.removeAll();             
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(plot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(plot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(plot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(plot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new plot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Xaxis;
    private javax.swing.JComboBox Yaxis;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField note;
    // End of variables declaration//GEN-END:variables
}
