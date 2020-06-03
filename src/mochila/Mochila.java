
package mochila;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
    JTextArea matrizB[][];
    JTextField matrizF[][];

    JButton ingresar = new JButton("Ingresar");
    JButton calcular = new JButton("Calcular");

    JPanel jpan1 = new JPanel(); 
       
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    JScrollPane scrollPane2 = new JScrollPane();
    JScrollPane scrollPane3 = new JScrollPane();
    
    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
    
    int valorOptimo;
    
    int pesoLim,articulos,filasF,columF;

    int contador;
    
    public static void main(String[] args) {

        Mochila mochila = new Mochila();
        mochila.add(new JScrollPane(), BorderLayout.CENTER);
        mochila.setSize(1350, 710);
        mochila.setTitle("Problema de la mochila");
        mochila.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mochila.setVisible(true); 
        
    }

    Mochila(){
        
        Container c = getContentPane();
        c.setLayout(null);
        
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
      
        c.add(jpan1);
        
        c.add(texto1);
        c.add(texto2);
        c.add(texto3);
        c.add(texto4);
        c.add(texto5);
        
        c.add(fieldPeso);
        c.add(fieldArt);
        
        c.add(ingresar);
        c.add(calcular);
        
        c.add(scrollPane1);
        c.add(scrollPane3);
        
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
        
        scrollPane.setBounds(10, 370, 2000, 400);
        scrollPane.setPreferredSize(new Dimension(2000, 400));  
        
        scrollPane1.setBounds(10, 370, 1150, 270);
        scrollPane1.setPreferredSize(new Dimension(1150, 270)); 
        
        scrollPane2.setBounds(350, 120, 200, 600);
        scrollPane2.setPreferredSize(new Dimension(200, 600));  
        
        scrollPane3.setBounds(350, 120, 220, 220);
        scrollPane3.setPreferredSize(new Dimension(220, 220));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                
       
        if(e.getSource()==ingresar){
        
            pesoLim = Integer.parseInt(fieldPeso.getText());    
            
            articulos = Integer.parseInt(fieldArt.getText());   
            
            matrizA = new JTextField[2][articulos];
            
            lPesos = new JLabel("Digite pesos:");
            lPesos.setBounds(0,0,100,20);
            
            lValores = new JLabel("Digite valores:");
            lValores.setBounds(0,100,100,20);
            
            jpan1.add(lPesos);
            jpan1.add(lValores);
            
            jpan1.repaint();
            
            int x = 6;
            int y = 10;
            
            for(int i = 0; i<2;i++){
                
                for(int j = 0; j<articulos;j++){
                    
                    if(i==0){
                    
                        matrizA[i][j] = new JTextField(Integer.toString(x));
                        matrizA[i][j].setBounds(j*30,30,20,20);
                        jpan1.add(matrizA[i][j]);
                    
                        x = x - 2;
                        
                    } else {
                        
                        matrizA[i][j] = new JTextField(Integer.toString(y));
                        matrizA[i][j].setBounds(j*30,120,20,20);
                        jpan1.add(matrizA[i][j]); 
                        
                        y = y - 3;
                        
                    }
                }
                
            }
            
        } else if (e.getSource()==calcular){
        
            for(int i=0;i < articulos - 1 ; i++) {
            
                for(int j=i+1; j < articulos; j++) {

                    if(Integer.parseInt(matrizA[0][i].getText()) > Integer.parseInt(matrizA[0][j].getText())) {

                            String temp = matrizA[0][i].getText();
                            matrizA[0][i].setText(matrizA[0][j].getText());
                            matrizA[0][j].setText(temp);

                            String tempB = matrizA[1][i].getText();
                            matrizA[1][i].setText(matrizA[1][j].getText());
                            matrizA[1][j].setText(tempB);
                            
                        }

                }
            
            }
            
            scrollPane.removeAll();
            
            matrizB = new JTextArea[articulos + 1][pesoLim + 4];
            
            for(int i = 0; i<articulos + 1;i++){
                
                for(int j = 0; j<pesoLim + 4;j++){
                    
                    if (i == 0 && j == 0){
                    
                        matrizB[i][j] = new JTextArea("Artic.");
                        
                    } else if (i == 0 && j == 1){
                        
                        matrizB[i][j] = new JTextArea("Peso");
                    
                    } else if (i == 0 && j == 2){
                    
                        matrizB[i][j] = new JTextArea("Valor");
                        
                    } else if (i == 0 && j > 2){
                        
                        matrizB[i][j] = new JTextArea(Integer.toString(j-3));
                        
                    } else if (i > 0 && j == 0){
                        
                         matrizB[i][j] = new JTextArea(Integer.toString(i));
                        
                    } else if (i > 0 && j == 1){
                        
                         matrizB[i][j] = new JTextArea(matrizA[0][i-1].getText());
                        
                    } else if (i > 0 && j == 2){
                        
                          matrizB[i][j] = new JTextArea(matrizA[1][i-1].getText());
                        
                    } else {
                        
                        int pesoTemporal = Integer.parseInt(matrizA[0][i-1].getText());
                        int espacioTemporal = Integer.parseInt(matrizB[0][j].getText());
                        int valorTemporal = Integer.parseInt(matrizA[1][i-1].getText());
                        
                        if(i == 1){
                        
                            if(espacioTemporal < pesoTemporal){
                                
                                matrizB[i][j] = new JTextArea(Integer.toString(0) + " \n 0:0, ");
                                
                            } else {
                                
                                matrizB[i][j] = new JTextArea(Integer.toString(valorTemporal) + " \n" + Integer.toString(valorTemporal)+":1, ");
                                
                            }
                            
                        } else {
                            
                            String digitosA = ""; 
                            String rutaA = "";
                            String rutaB = "";
                            boolean esNumeroA = true;
                            
                            for(int k = 0; k < matrizB[i-1][j].getText().length(); k++){
                            
                                if( matrizB[i-1][j].getText().charAt(k) != ' ' && esNumeroA){
                                
                                    digitosA = digitosA + String.valueOf(matrizB[i-1][j].getText().charAt(k));
                                    
                                } else {
                                    
                                    rutaA = rutaA + String.valueOf(matrizB[i-1][j].getText().charAt(k));
                                    esNumeroA = false;
                                    
                                }
                                
                            }
                            
                            int a = Integer.parseInt(digitosA), b;
                            
                            if( (espacioTemporal - pesoTemporal) < 0){
                            
                               b = 0;
                                
                            } else {
                                
                               String digitosB = "";
                               boolean esNumeroB = true;
                            
                                for(int k = 0; k < matrizB[i-1][j - pesoTemporal].getText().length(); k++){

                                    if( matrizB[i-1][j - pesoTemporal].getText().charAt(k) != ' ' && esNumeroB){

                                        digitosB = digitosB + String.valueOf(matrizB[i-1][j - pesoTemporal].getText().charAt(k));
                                        
                                    } else{

                                        rutaB = rutaB + String.valueOf(matrizB[i-1][j - pesoTemporal].getText().charAt(k));
                                        esNumeroB = false;
                                    }

                                }
                                
                                b = Integer.parseInt(digitosB) + valorTemporal;
                                
                            }
                           
        
                            if(a>=b){

                                matrizB[i][j] = new JTextArea(Integer.toString(a)+ rutaA + ", ");
                                
                            } else {

                                matrizB[i][j] = new JTextArea(Integer.toString(b)+ rutaB + Integer.toString(valorTemporal) + ":" + Integer.toString(i)+", ");

                            }
                            
                        }
  
                    }
                    
                    matrizB[i][j].setBorder(border);
                    matrizB[i][j].setBounds(j*90 + 5 ,i*50 + 5,80,40);
                    scrollPane.add(matrizB[i][j]);
                    
                }
                
                scrollPane.repaint();
                
            }
         
            String cadenaOptima = matrizB[articulos][pesoLim + 3].getText();
            
            scrollPane2.removeAll();
            
            matrizF = new JTextField [articulos + 2][3];
         
            int pesoFinal = 0 , valorFinal = 0;
            
            for(int i = 0; i< articulos+2; i++){
                
                for(int j = 0; j<3; j++){
                    
                    if (i == 0 && j == 0){
                    
                        matrizF[i][j] = new JTextField("Artic.");
                        
                    } else if (i == 0 && j == 1){
                        
                        matrizF[i][j] = new JTextField("Peso");
                    
                    } else if (i == 0 && j == 2){
                    
                        matrizF[i][j] = new JTextField("Valor");
                        
                    } else if(i>0 && j == 0 && i<articulos+1) {
                        
                        matrizF[i][j] = new JTextField(Integer.toString(i));
                        
                    } else {
                        
                        if(cadenaOptima.contains(":" + Integer.toString(i))){
                            
                            pesoFinal = pesoFinal + Integer.parseInt(matrizA[0][i-1].getText());
                            valorFinal = valorFinal + Integer.parseInt(matrizA[1][i-1].getText());
                            
                            matrizF[i][1] = new JTextField(matrizA[0][i-1].getText());
                            matrizF[i][2] = new JTextField(matrizA[1][i-1].getText());
                            
                            
                            
                        } else if(i==articulos+1){

                            matrizF[i][0] = new JTextField(" ");
                            matrizF[i][1] = new JTextField(Integer.toString(pesoFinal/2));
                            matrizF[i][2] = new JTextField(Integer.toString(valorFinal/2));
                            
                        }else {
                            
                            matrizF[i][j] = new JTextField("-");
                            
                        }                       
                                               
                    }

                    matrizF[i][j].setBorder(border);
                    matrizF[i][j].setBounds(j*40 + 5 ,i*40 + 5,35,35);
                    
                    scrollPane2.add(matrizF[i][j]);                    
                    scrollPane2.repaint();
                    
                } 
                
                scrollPane1.setViewportView(scrollPane);
                scrollPane3.setViewportView(scrollPane2);
                
            }

        }
        
    }
    
}
