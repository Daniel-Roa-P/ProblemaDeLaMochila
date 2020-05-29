
package mochila;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Mochila extends JFrame implements ActionListener{

    JLabel texto1 = new JLabel("Ingreso valores:");
    JLabel texto2 = new JLabel("Matriz:");
    JLabel texto3 = new JLabel("Resumen:");
    JLabel texto4 = new JLabel("Ingresar peso limite: ");
    JLabel texto5 = new JLabel("Ingresar Ingresar numero de articulos: ");
    
    JLabel lPesos;
    JLabel lValores;
    
    JTextField fieldPeso = new JTextField("10");
    JTextField fieldArt = new JTextField("3");

    JTextField matrizA[][];
    JTextField matrizB[][];
    JTextField matrizF[][];

    JButton ingresar = new JButton("Ingresar");
    JButton calcular = new JButton("Calcular");

    JPanel jpan1 = new JPanel(); 
    JPanel jpan2 = new JPanel();
    JPanel jpan3 = new JPanel();
    
    int valorOptimo;
    
    int pesoLim,articulos,columA,columB,filasF,columF;

    int contador;
    
    public static void main(String[] args) {
        
        Mochila mochila = new Mochila();
        mochila.setSize(1000, 710);
        mochila.setTitle("Problema de la mochila");
        mochila.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mochila.setVisible(true); 
        
    }

    Mochila(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(jpan1);
        c.add(jpan2);
        c.add(jpan3);
        
        c.add(texto1);
        c.add(texto2);
        c.add(texto3);
        c.add(texto4);
        c.add(texto5);
        
        c.add(fieldPeso);
        c.add(fieldArt);
        
        c.add(ingresar);
        c.add(calcular);
        
        texto1.setBounds(10, 100, 200, 20);
        texto2.setBounds(10, 350, 200, 20);
        texto3.setBounds(350, 100, 200, 20);
        texto4.setBounds(10, 20, 250, 20);
        texto5.setBounds(10, 50, 250, 20);
        
        fieldPeso.setBounds(250, 20, 20, 20);
        fieldArt.setBounds(250, 50, 20, 20);
        
        ingresar.addActionListener(this);
        ingresar.setBounds(330, 20, 100, 20);
        calcular.addActionListener(this);
        calcular.setBounds(330, 50, 100, 20);
     
        jpan1.setBounds(10, 120, 250, 220);
        jpan1.setBackground(Color.WHITE);
        jpan2.setBounds(10, 370, 900, 270);
        jpan2.setBackground(Color.DARK_GRAY);
        jpan3.setBounds(350, 120, 220, 220);
        jpan3.setBackground(Color.DARK_GRAY);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                
       
        if(e.getSource()==ingresar){
        
            pesoLim = Integer.parseInt(fieldPeso.getText());    
            
            articulos = Integer.parseInt(fieldArt.getText());   
            
            matrizA = new JTextField[articulos][2];
            
            lPesos = new JLabel("Digite pesos:");
            lPesos.setBounds(0,0,100,20);
            
            lValores = new JLabel("Digite valores");
            lValores.setBounds(0,100,100,20);
            
            jpan1.add(lPesos);
            jpan1.add(lValores);
            
            jpan1.repaint();
            
            for(int i = 0; i<articulos;i++){
                
                for(int j = 0; j<2;j++){
                    
                    if(j==0){
                    
                        matrizA[i][j] = new JTextField(Integer.toString(i*j));
                        matrizA[i][j].setBounds(i*30,30,20,20);
                        jpan1.add(matrizA[i][j]);
                    
                    } else {
                        
                        matrizA[i][j] = new JTextField(Integer.toString(i*j));
                        matrizA[i][j].setBounds(i*30,120,20,20);
                        jpan1.add(matrizA[i][j]); 
                        
                    }
                }
                
            }
            
        } else if (e.getSource()==calcular){
        
            for(int i=0;i < articulos - 1; i++) {
            
                for(int j=i+1; j < articulos; j++) {

                    if(Integer.parseInt(matrizA[i][0].getText()) > Integer.parseInt(matrizA[j][0].getText())) {

                            String temp = matrizA[i][0].getText();
                            matrizA[i][0].setText(matrizA[j][0].getText());
                            matrizA[j][0].setText(temp);

                            String tempB = matrizA[i][1].getText();
                            matrizA[i][1].setText(matrizA[j][1].getText());
                            matrizA[j][1].setText(tempB);
                            
                        }

                }
            
            }
            
            jpan2.removeAll();
            
            matrizB = new JTextField[pesoLim + 4][articulos + 1];
            
            for(int i = 0; i<pesoLim + 4;i++){
                
                for(int j = 0; j<articulos + 1;j++){
                    
                    if (j == 0 && i == 0){
                    
                        matrizB[i][j] = new JTextField("Artic.");
                        
                    } else if (j == 0 && i == 1){
                        
                        matrizB[i][j] = new JTextField("Peso");
                    
                    } else if (j == 0 && i == 2){
                    
                        matrizB[i][j] = new JTextField("Costo");
                        
                    } else if (j == 0 && i > 2){
                        
                        matrizB[i][j] = new JTextField(Integer.toString(i-3));
                        
                    } else if (i == 0 && j > 0){
                        
                         matrizB[i][j] = new JTextField(Integer.toString(j));
                        
                    } else if (i == 1 && j > 0){
                        
                         matrizB[i][j] = new JTextField(matrizA[j-1][0].getText());
                        
                    } else if (i == 2 && j > 0){
                        
                          matrizB[i][j] = new JTextField(matrizA[j-1][1].getText());
                        
                    } else if (j == 1 && i >2) {
                    
                        if(Integer.parseInt(matrizB[1][1].getText()) > pesoLim){
                            
                            valorOptimo = hallarMax(0,0);
                            
                        } else {
                            
                            valorOptimo = hallarMax(0,Integer.parseInt(matrizB[2][1].getText()));
                            
                        }
                        
                        matrizB[i][j] = new JTextField(Integer.toString(valorOptimo));
                        
                    } else {
                        
                        
                        if((j - Integer.parseInt(matrizB[i][1].getText()))>= 0){
                            
                            valorOptimo = hallarMax(Integer.parseInt(matrizB[i-1][j].getText()) , (j - Integer.parseInt(matrizB[i][1].getText())) + Integer.parseInt(matrizB[i][2].getText()));
                            
                        } else {
                            
                            
                            valorOptimo = hallarMax(Integer.parseInt(matrizB[i-1][j].getText()) , 0);
                            
                        }
                        
                        
                        
                        matrizB[i][j] = new JTextField(Integer.toString(valorOptimo));
                        
                    }
                    
                    matrizB[i][j].setBounds(i*50,j*50,40,40);
                    jpan2.add(matrizB[i][j]);
                    
                }
                
            }
            
            
        }
        
    }
    
    int hallarMax( int a, int b){
        
        if(a>b){
            
            return a; 
            
        } else {
            
            return b;
            
        }
        
    }
    
}
