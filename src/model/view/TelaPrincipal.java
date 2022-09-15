
package model.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Pedro Citadin Coelho <pedro_citadin@outlook.com>
 */
public class TelaPrincipal extends JFrame{
    private Font fonte_principal;
    private JLabel lblEntrada;
    private JLabel lblRadar;
    private JLabel lblDataGrid;
    private JLabel lblFuncTrans;
    private JLabel lblFuncRast;
    private JLabel lblRelatorio;
    private JPanel pnlEntrada;
    private JPanel pnlFuncTrans1;
    private JPanel pnlFuncTrans2;
    private JPanel pnlFuncTrans3;
    private JPanel pnlFuncRast1;
    private JPanel pnlFuncRast2;
    private JPanel pnlFuncRast3;
    private JPanel pnlRadar;
    private JPanel pnlRelatorio;
    private JPanel pnlDataGrid;

    ///entrada de dados
    private JTextField txtXentrada;
    private JTextField txtYentrada;
    private JTextField txtRaioentrada;
    private JTextField txtAnguloentrada;
    private JTextField txtVelocidadeentrada;
    private JTextField txtDirecaoentrada;
    private JLabel lblXentrada;
    private JLabel lblYentrada;
    private JLabel lblRaioentrada;
    private JLabel lblAnguloentrada;
    private JLabel lblVelocidadeentrada;
    private JLabel lblDirecaoentrada;
    private JButton btnInserir;






    public TelaPrincipal(){
        this.setSize(1200, 800);
        setResizable(false);
        setTitle("AeroRadar");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        criaComponentes();
    }
    
    public void criaComponentes(){
        fonte_principal = new Font("Arial", Font.PLAIN, 20);
        Font fonte_secundaria = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("Label.font", fonte_principal);
        Border borda = BorderFactory.createLineBorder(Color.black);

        //-------------------Coluna 1 -----------------------
        lblEntrada = new JLabel();
        lblEntrada.setText("Entrada de dados");
        lblEntrada.setBounds(86, 12, 180, 24);
        this.getContentPane().add(lblEntrada);

        pnlEntrada = new JPanel();
        pnlEntrada.setBounds(26, 43, 300, 230);
        pnlEntrada.setBorder(borda);


        lblXentrada = new JLabel();
        lblXentrada.setText("X:");
        lblXentrada.setBounds(104, 65, 19,14);
        lblXentrada.setFont(fonte_secundaria);
        getContentPane().add(lblXentrada);


        txtXentrada = new JTextField();
        txtXentrada.setBounds(123, 58, 55,29);
        getContentPane().add(txtXentrada);

        lblYentrada = new JLabel();
        lblYentrada.setText("Y:");
        lblYentrada.setBounds(231, 65, 19,14);
        lblYentrada.setFont(fonte_secundaria);
        getContentPane().add(lblYentrada);


        txtYentrada = new JTextField();
        txtYentrada.setBounds(250, 58, 55,29);
        getContentPane().add(txtYentrada);

        lblRaioentrada = new JLabel();
        lblRaioentrada.setText("Raio:");
        lblRaioentrada.setBounds(83, 126, 53,16);
        lblRaioentrada.setFont(fonte_secundaria);
        getContentPane().add(lblRaioentrada);


        txtRaioentrada = new JTextField();
        txtRaioentrada.setBounds(123, 119, 55,29);
        getContentPane().add(txtRaioentrada);

        lblAnguloentrada = new JLabel();
        lblAnguloentrada.setText("Angulo:");
        lblAnguloentrada.setBounds(192, 126, 67,16);
        lblAnguloentrada.setFont(fonte_secundaria);
        getContentPane().add(lblAnguloentrada);


        txtAnguloentrada = new JTextField();
        txtAnguloentrada.setBounds(250, 119, 55,29);
        getContentPane().add(txtAnguloentrada);


        lblVelocidadeentrada = new JLabel();
        lblVelocidadeentrada.setText("Velocidade:");
        lblVelocidadeentrada.setBounds(34, 177, 89,16);
        lblVelocidadeentrada.setFont(fonte_secundaria);
        getContentPane().add(lblVelocidadeentrada);


        txtVelocidadeentrada = new JTextField();
        txtVelocidadeentrada.setBounds(123, 170, 55,29);
        getContentPane().add(txtVelocidadeentrada);

        lblDirecaoentrada = new JLabel();
        lblDirecaoentrada.setText("Direção:");
        lblDirecaoentrada.setBounds(187, 177, 67,16);
        lblDirecaoentrada.setFont(fonte_secundaria);
        getContentPane().add(lblDirecaoentrada);


        txtAnguloentrada = new JTextField();
        txtAnguloentrada.setBounds(250, 170, 55,29);
        getContentPane().add(txtAnguloentrada);

        btnInserir = new JButton();
        btnInserir.setText("Inserir");
        btnInserir.setBounds(181, 224, 124, 41);
        btnInserir.setBackground(Color.orange);
        getContentPane().add(btnInserir);



        getContentPane().add(pnlEntrada);

        lblFuncTrans = new JLabel();
        lblFuncTrans.setText("Funções de Transformação");
        lblFuncTrans.setBounds(48, 299, 257, 24);
        this.getContentPane().add(lblFuncTrans);

        pnlFuncTrans1 = new JPanel();
        pnlFuncTrans1.setBounds(26, 329, 142,115);
        pnlFuncTrans1.setBorder(borda);
        getContentPane().add(pnlFuncTrans1);

        pnlFuncTrans2 = new JPanel();
        pnlFuncTrans2.setBounds(183, 329, 142,115);
        pnlFuncTrans2.setBorder(borda);
        getContentPane().add(pnlFuncTrans2);

        pnlFuncTrans3 = new JPanel();
        pnlFuncTrans3.setBounds(26, 471, 300,115);
        pnlFuncTrans3.setBorder(borda);
        getContentPane().add(pnlFuncTrans3);

        lblFuncRast = new JLabel();
        lblFuncRast.setText("Funções de Rastreamento");
        lblFuncRast.setBounds(52, 600, 257, 24);
        this.getContentPane().add(lblFuncRast);

        pnlFuncRast1 = new JPanel();
        pnlFuncRast1.setBounds(26, 634, 300,115);
        pnlFuncRast1.setBorder(borda);
        getContentPane().add(pnlFuncRast1);




    ///////////---------------- Coluna 2---------------
        lblRadar = new JLabel();
        lblRadar.setText("Radar");
        lblRadar.setBounds(521, 12, 56, 24);
        getContentPane().add(lblRadar);


        pnlRadar = new JPanel();
        pnlRadar.setBounds(349, 43, 400, 400);
        pnlRadar.setBorder(borda);
        getContentPane().add(pnlRadar);


        pnlFuncRast2 = new JPanel();
        pnlFuncRast2.setBounds(349, 634, 166, 115);
        pnlFuncRast2. setBorder(borda);
        getContentPane().add(pnlFuncRast2);

        pnlFuncRast3 = new JPanel();
        pnlFuncRast3.setBounds(582, 634, 166, 115);
        pnlFuncRast3. setBorder(borda);
        getContentPane().add(pnlFuncRast3);



        //----------------------coluna 3
        lblDataGrid = new JLabel();
        lblDataGrid.setText("DataGrid");
        lblDataGrid.setBounds(926, 12, 86, 24);
        getContentPane().add(lblDataGrid);

        pnlDataGrid = new JPanel();
        pnlDataGrid.setBounds(772, 43, 394, 293);
        pnlDataGrid.setBorder(borda);
        getContentPane().add(pnlDataGrid);

        lblRelatorio = new JLabel();
        lblRelatorio.setText("Relatório");
        lblRelatorio.setBounds(926, 358, 86,24);
        getContentPane().add(lblRelatorio);

        pnlRelatorio = new JPanel();
        pnlRelatorio.setBounds(772, 388, 394, 361);
        pnlRelatorio.setBorder(borda);
        getContentPane().add(pnlRelatorio);

    }
    
    
    
}
