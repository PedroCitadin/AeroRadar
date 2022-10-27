
package model.view;

import model.bean.Aeronave;
import model.util.ConversorCoordenadas;
import model.util.DGTabelModel;
import model.util.ImagemRoda;
import model.util.NumeredBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.HashMap;
import java.util.List;

/**
 * @author Pedro Citadin Coelho <pedro_citadin@outlook.com>
 */
public class TelaPrincipal extends JFrame {
    private HashMap<Integer, Integer> contaID = new HashMap<Integer, Integer>();
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
    private JLabel lblPlanoCartesiano;

    private JTextArea txtRelatorio;
    private JScrollPane pnlRelatorio;
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


    ///// Funções de transformação
    private JTextField txtFT1X;
    private JTextField txtFT1Y;
    private JLabel lblFT1X;
    private JLabel lblFT1Y;
    private JButton btnFT1Transformar;

    private JTextField txtFT2X;
    private JTextField txtFT2Y;
    private JLabel lblFT2X;
    private JLabel lblFT2Y;
    private JButton btnFT2Transformar;

    private JTextField txtFT3X;
    private JTextField txtFT3Y;
    private JLabel lblFT3X;
    private JLabel lblFT3Y;
    private JTextField txtFT3Angulo;
    private JLabel lblFT3Angulo;
    private JLabel lblFT3CR;
    private JButton btnFT3Transformar;


    /////Funções de Rastreamento
    private JLabel lblFR1DM;
    private JTextField txtFR1DM;
    private JButton btnFR1Rastrear;


    private JLabel lblFR2DM;
    private JTextField txtFR2DM;
    private JButton btnFR2Rastrear;


    private JLabel lblFR3TM;
    private JTextField txtFR3TM;
    private JButton btnFR3Rastrear;


    /////////Datagrid
    private JScrollPane sPaneDG;
    private JTable tabelaDG;

    private HashMap<Integer, Aeronave> lista_avioes = new HashMap<Integer, Aeronave>();
    private JLabel lblDescricao;

    private JButton deletaAvião;

    public TelaPrincipal() {
        this.setSize(1200, 800);
        setResizable(false);
        setTitle("AeroRadar");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        criaComponentes();
    }

    public void criaComponentes() {

        fonte_principal = new Font("Arial", Font.PLAIN, 20);
        Font fonte_secundaria = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("Label.font", fonte_principal);
        Border borda = BorderFactory.createLineBorder(Color.black);

        //----------------------coluna 3
        lblDataGrid = new JLabel();
        lblDataGrid.setText("DataGrid");
        lblDataGrid.setBounds(926, 12, 86, 24);


        getContentPane().add(lblDataGrid);


        pnlDataGrid = new JPanel();
        pnlDataGrid.setBounds(772, 43, 394, 293);
        pnlDataGrid.setBorder(borda);

        DGTabelModel dtm = new DGTabelModel();
        tabelaDG = new JTable(dtm);
        tabelaDG.setBounds(772, 43, 394, 293);
        tabelaDG.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {

                    if ( JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir esses aviões?", "Aviso",JOptionPane.YES_NO_OPTION)==0){
                        for (int i = 0; i < dtm.getRowCount(); i++) {
                            if ((boolean) dtm.getValueAt(i, 0)) {

                                var id = Integer.parseInt(dtm.getValueAt(i, 1).toString());
                                pnlRadar.remove(lista_avioes.get(id).getLabel());
                                lista_avioes.remove(id);
                                dtm.removeUsuario(i);


                            }
                        }
                        pnlRadar.updateUI();
                        tabelaDG.updateUI();
                    }
                }
            }
        });
        sPaneDG = new JScrollPane(tabelaDG);
        sPaneDG.setBounds(772, 43, 394, 293);
        getContentPane().add(sPaneDG);


        getContentPane().add(pnlDataGrid);

        lblRelatorio = new JLabel();
        lblRelatorio.setText("Relatório");
        lblRelatorio.setBounds(926, 358, 86, 24);
        getContentPane().add(lblRelatorio);

        txtRelatorio = new JTextArea();
        txtRelatorio.setBounds(772, 388, 389, 361);
        txtRelatorio.setBorder(new NumeredBorder());
        txtRelatorio.setEditable(false);

        pnlRelatorio = new JScrollPane(txtRelatorio);
        pnlRelatorio.setBounds(772, 388, 394, 361);
        pnlRelatorio.setBorder(borda);
        getContentPane().add(pnlRelatorio);


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
        lblXentrada.setBounds(104, 65, 19, 14);
        lblXentrada.setFont(fonte_secundaria);
        getContentPane().add(lblXentrada);


        txtXentrada = new JTextField();
        txtXentrada.setBounds(123, 58, 55, 29);
        getContentPane().add(txtXentrada);

        lblYentrada = new JLabel();
        lblYentrada.setText("Y:");
        lblYentrada.setBounds(231, 65, 19, 14);
        lblYentrada.setFont(fonte_secundaria);
        getContentPane().add(lblYentrada);


        txtYentrada = new JTextField();
        txtYentrada.setBounds(250, 58, 55, 29);
        getContentPane().add(txtYentrada);

        lblRaioentrada = new JLabel();
        lblRaioentrada.setText("Raio:");
        lblRaioentrada.setBounds(83, 126, 53, 16);
        lblRaioentrada.setFont(fonte_secundaria);
        getContentPane().add(lblRaioentrada);


        txtRaioentrada = new JTextField();
        txtRaioentrada.setBounds(123, 119, 55, 29);
        getContentPane().add(txtRaioentrada);

        lblAnguloentrada = new JLabel();
        lblAnguloentrada.setText("Angulo:");
        lblAnguloentrada.setBounds(192, 126, 67, 16);
        lblAnguloentrada.setFont(fonte_secundaria);
        getContentPane().add(lblAnguloentrada);


        txtAnguloentrada = new JTextField();
        txtAnguloentrada.setBounds(250, 119, 55, 29);
        getContentPane().add(txtAnguloentrada);


        lblVelocidadeentrada = new JLabel();
        lblVelocidadeentrada.setText("Velocidade:");
        lblVelocidadeentrada.setBounds(34, 177, 89, 16);
        lblVelocidadeentrada.setFont(fonte_secundaria);
        getContentPane().add(lblVelocidadeentrada);


        txtVelocidadeentrada = new JTextField();
        txtVelocidadeentrada.setBounds(123, 170, 55, 29);
        getContentPane().add(txtVelocidadeentrada);

        lblDirecaoentrada = new JLabel();
        lblDirecaoentrada.setText("Direção:");
        lblDirecaoentrada.setBounds(187, 177, 67, 16);
        lblDirecaoentrada.setFont(fonte_secundaria);
        getContentPane().add(lblDirecaoentrada);


        txtDirecaoentrada = new JTextField();
        txtDirecaoentrada.setBounds(250, 170, 55, 29);
        getContentPane().add(txtDirecaoentrada);

        btnInserir = new JButton();
        btnInserir.setText("Inserir");
        btnInserir.setBounds(181, 224, 124, 41);
        btnInserir.setBackground(Color.orange);
        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtXentrada.getText().equalsIgnoreCase("") && !txtYentrada.getText().equalsIgnoreCase("")) {
                    Aeronave aviaoOBJ = new Aeronave();
                    JLabel aviao = new JLabel();
                    aviao.setLocation(ConversorCoordenadas.converteX(Integer.parseInt(txtXentrada.getText())), ConversorCoordenadas.converteY(Integer.parseInt(txtYentrada.getText())));
                    aviao.setVisible(true);

                    aviao.setIcon(ImagemRoda.giraAviao(Integer.parseInt(txtDirecaoentrada.getText())));
                    aviao.setSize(25, 25);
                    aviaoOBJ.setX(Float.parseFloat(txtXentrada.getText()));
                    aviaoOBJ.setY(Float.parseFloat(txtYentrada.getText()));
                    aviaoOBJ.setVelocidade(Float.parseFloat(txtVelocidadeentrada.getText()));
                    aviaoOBJ.setDirecao(Float.parseFloat(txtDirecaoentrada.getText()));
                    aviaoOBJ.setLabel(aviao);
                    aviaoOBJ.setId(geraId());
                    aviaoOBJ.converteCartesianoPolar();
                    lista_avioes.put(aviaoOBJ.getId(), aviaoOBJ);
                    dtm.addAeronave(aviaoOBJ);
                    pnlRadar.add(aviao);

                    pnlRadar.updateUI();
                } else if (!txtRaioentrada.getText().equalsIgnoreCase("") && !txtAnguloentrada.getText().equalsIgnoreCase("")) {
                    JLabel aviao = new JLabel();
                    Aeronave aviaoOBJ = new Aeronave();
                    aviao.setLocation(ConversorCoordenadas.polarX(Integer.parseInt(txtRaioentrada.getText()), Integer.parseInt(txtAnguloentrada.getText())), ConversorCoordenadas.polarY(Integer.parseInt(txtRaioentrada.getText()), Integer.parseInt(txtAnguloentrada.getText())));
                    aviao.setVisible(true);

                    aviao.setIcon(ImagemRoda.giraAviao(Integer.parseInt(txtDirecaoentrada.getText())));
                    aviao.setSize(25, 25);
                    aviaoOBJ.setRaio(Float.parseFloat(txtRaioentrada.getText()));
                    aviaoOBJ.setAngulo(Float.parseFloat(txtAnguloentrada.getText()));
                    aviaoOBJ.setVelocidade(Float.parseFloat(txtVelocidadeentrada.getText()));
                    aviaoOBJ.setDirecao(Float.parseFloat(txtDirecaoentrada.getText()));
                    aviaoOBJ.setLabel(aviao);
                    aviaoOBJ.setId(geraId());
                    aviaoOBJ.convertePolarCartesiano();
                    lista_avioes.put(aviaoOBJ.getId(), aviaoOBJ);
                    dtm.addAeronave(aviaoOBJ);
                    pnlRadar.add(aviao);
                    pnlRadar.updateUI();
                } else {
                    System.out.println("erro");
                }


            }
        });
        getContentPane().add(btnInserir);


        getContentPane().add(pnlEntrada);

        lblFuncTrans = new JLabel();
        lblFuncTrans.setText("Funções de Transformação");
        lblFuncTrans.setBounds(48, 299, 257, 24);
        this.getContentPane().add(lblFuncTrans);

        pnlFuncTrans1 = new JPanel();
        pnlFuncTrans1.setBounds(26, 329, 142, 115);
        pnlFuncTrans1.setBorder(borda);

        lblFT1X = new JLabel();
        lblFT1X.setText("X:");
        lblFT1X.setFont(fonte_secundaria);
        lblFT1X.setBounds(29, 357, 19, 14);
        getContentPane().add(lblFT1X);

        txtFT1X = new JTextField();
        txtFT1X.setBounds(48, 350, 45, 29);
        getContentPane().add(txtFT1X);

        lblFT1Y = new JLabel();
        lblFT1Y.setText("Y:");
        lblFT1Y.setFont(fonte_secundaria);
        lblFT1Y.setBounds(96, 357, 19, 14);
        getContentPane().add(lblFT1Y);

        txtFT1Y = new JTextField();
        txtFT1Y.setBounds(115, 350, 45, 29);
        getContentPane().add(txtFT1Y);

        btnFT1Transformar = new JButton();
        btnFT1Transformar.setText("Translandar");
        btnFT1Transformar.setBackground(Color.CYAN);
        btnFT1Transformar.setBounds(38, 397, 124, 41);
        btnFT1Transformar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    if ((boolean) dtm.getValueAt(i, 0)) {
                        Aeronave.translandar(dtm.getAeronave(i), (Integer.parseInt(txtFT1X.getText())), Integer.parseInt(txtFT1Y.getText()));


                    }
                }
                pnlRadar.updateUI();
                tabelaDG.updateUI();
            }
        });
        getContentPane().add(btnFT1Transformar);


        getContentPane().add(pnlFuncTrans1);

        pnlFuncTrans2 = new JPanel();
        pnlFuncTrans2.setBounds(183, 329, 142, 115);
        pnlFuncTrans2.setBorder(borda);


        lblFT2X = new JLabel();
        lblFT2X.setText("X:");
        lblFT2X.setFont(fonte_secundaria);
        lblFT2X.setBounds(186, 357, 19, 14);
        getContentPane().add(lblFT2X);

        txtFT2X = new JTextField();
        txtFT2X.setBounds(205, 350, 45, 29);
        getContentPane().add(txtFT2X);

        lblFT2Y = new JLabel();
        lblFT2Y.setText("Y:");
        lblFT2Y.setFont(fonte_secundaria);
        lblFT2Y.setBounds(253, 357, 19, 14);
        getContentPane().add(lblFT2Y);

        txtFT2Y = new JTextField();
        txtFT2Y.setBounds(272, 350, 45, 29);
        getContentPane().add(txtFT2Y);

        btnFT2Transformar = new JButton();
        btnFT2Transformar.setText("Escalonar");
        btnFT2Transformar.setBackground(Color.CYAN);
        btnFT2Transformar.setBounds(195, 397, 124, 41);
        btnFT2Transformar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    if ((boolean) dtm.getValueAt(i, 0)) {
                        Aeronave.escalonar(dtm.getAeronave(i), (Integer.parseInt(txtFT2X.getText())), Integer.parseInt(txtFT2Y.getText()));


                    }
                }
                pnlRadar.updateUI();
                tabelaDG.updateUI();
            }
        });
        getContentPane().add(btnFT2Transformar);


        getContentPane().add(pnlFuncTrans2);

        pnlFuncTrans3 = new JPanel();
        pnlFuncTrans3.setBounds(26, 471, 300, 115);
        pnlFuncTrans3.setBorder(borda);


        lblFT3X = new JLabel();
        lblFT3X.setText("X:");
        lblFT3X.setFont(fonte_secundaria);
        lblFT3X.setBounds(169, 546, 19, 14);
        getContentPane().add(lblFT3X);

        txtFT3X = new JTextField();
        txtFT3X.setBounds(188, 539, 45, 29);
        getContentPane().add(txtFT3X);

        lblFT3Y = new JLabel();
        lblFT3Y.setText("Y:");
        lblFT3Y.setFont(fonte_secundaria);
        lblFT3Y.setBounds(236, 545, 19, 14);
        getContentPane().add(lblFT3Y);

        txtFT3Y = new JTextField();
        txtFT3Y.setBounds(255, 539, 45, 29);
        getContentPane().add(txtFT3Y);

        btnFT3Transformar = new JButton();
        btnFT3Transformar.setText("Rotacionar");
        btnFT3Transformar.setBackground(Color.CYAN);
        btnFT3Transformar.setBounds(36, 535, 124, 41);
        btnFT3Transformar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    if ((boolean) dtm.getValueAt(i, 0)) {
                        Aeronave.rotacionar(dtm.getAeronave(i), (Integer.parseInt(txtFT3X.getText())), Integer.parseInt(txtFT3Y.getText()), Float.parseFloat(txtFT3Angulo.getText()));


                    }
                }
                pnlRadar.updateUI();
                tabelaDG.updateUI();
            }
        });
        getContentPane().add(btnFT3Transformar);

        lblFT3Angulo = new JLabel();
        lblFT3Angulo.setText("Angulo:");
        lblFT3Angulo.setFont(fonte_secundaria);
        lblFT3Angulo.setBounds(45, 495, 67, 16);
        getContentPane().add(lblFT3Angulo);

        txtFT3Angulo = new JTextField();
        txtFT3Angulo.setBounds(105, 489, 55, 26);
        getContentPane().add(txtFT3Angulo);

        lblFT3CR = new JLabel();
        lblFT3CR.setText("Centro de Rotação:");
        lblFT3CR.setFont(fonte_secundaria);
        lblFT3CR.setBounds(169, 494, 157, 16);
        getContentPane().add(lblFT3CR);


        getContentPane().add(pnlFuncTrans3);

        lblFuncRast = new JLabel();
        lblFuncRast.setText("Funções de Rastreamento");
        lblFuncRast.setBounds(52, 600, 257, 24);
        this.getContentPane().add(lblFuncRast);

        pnlFuncRast1 = new JPanel();
        pnlFuncRast1.setBounds(26, 634, 300, 115);
        pnlFuncRast1.setBorder(borda);

        lblFR1DM = new JLabel();
        lblFR1DM.setText("Distância mínima:");
        lblFR1DM.setFont(fonte_secundaria);
        lblFR1DM.setBounds(36, 657, 140, 14);
        getContentPane().add(lblFR1DM);

        txtFR1DM = new JTextField();
        txtFR1DM.setBounds(176, 652, 96, 29);
        getContentPane().add(txtFR1DM);

        btnFR1Rastrear = new JButton();
        btnFR1Rastrear.setText("Aviões próximos ao Aeroporto");
        btnFR1Rastrear.setBackground(Color.red);
        btnFR1Rastrear.setBounds(36, 702, 236, 41);
        btnFR1Rastrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float distancia = Float.valueOf(txtFR1DM.getText());
                for (Aeronave a : Aeronave.calculaBase(lista_avioes.values(), distancia)) {
                    txtRelatorio.setText(txtRelatorio.getText() + "Avião " + a.getId() + " está próximo da base \n");
                }
            }
        });
        getContentPane().add(btnFR1Rastrear);


        getContentPane().add(pnlFuncRast1);


        ///////////---------------- Coluna 2---------------
        lblRadar = new JLabel();
        lblRadar.setText("Radar");
        lblRadar.setBounds(521, 12, 56, 24);
        getContentPane().add(lblRadar);

        lblPlanoCartesiano = new JLabel();
        try {


            lblPlanoCartesiano.setIcon(new ImageIcon(ImageIO.read(new File("src\\model\\media\\plano.png"))));
        } catch (Exception e) {
            System.out.println(e);
        }
        lblPlanoCartesiano.setLocation(0, 0);
        lblPlanoCartesiano.setSize(400, 400);

        pnlRadar = new JPanel();
        pnlRadar.setLayout(null);
        pnlRadar.setBounds(349, 43, 400, 400);
        pnlRadar.setBorder(borda);
        pnlRadar.add(lblPlanoCartesiano);
        pnlRadar.updateUI();
        getContentPane().add(pnlRadar);

        lblDescricao = new JLabel();
        lblDescricao.setBounds(349, 471, 400, 119);
        try {
            lblDescricao.setIcon(new ImageIcon(ImageIO.read(new File("src\\model\\media\\descricao.png"))));
        } catch (Exception e) {
            System.out.println(e);
        }
        getContentPane().add(lblDescricao);


        pnlFuncRast2 = new JPanel();
        pnlFuncRast2.setBounds(349, 634, 166, 115);
        pnlFuncRast2.setBorder(borda);


        lblFR2DM = new JLabel();
        lblFR2DM.setText("Distância mín:");
        lblFR2DM.setFont(fonte_secundaria);
        lblFR2DM.setBounds(356, 657, 106, 14);
        getContentPane().add(lblFR2DM);

        txtFR2DM = new JTextField();
        txtFR2DM.setBounds(462, 652, 48, 29);
        getContentPane().add(txtFR2DM);

        btnFR2Rastrear = new JButton();
        btnFR2Rastrear.setText("Aviões Próximo");
        btnFR2Rastrear.setBackground(Color.red);
        btnFR2Rastrear.setBounds(356, 702, 154, 41);
        btnFR2Rastrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float distancia = Float.valueOf(txtFR2DM.getText());
                for (String s : Aeronave.calculaDistAvioes(lista_avioes.values(), distancia)) {
                    txtRelatorio.setText(txtRelatorio.getText() + s + " \n");
                }
            }
        });
        getContentPane().add(btnFR2Rastrear);


        getContentPane().add(pnlFuncRast2);

        pnlFuncRast3 = new JPanel();
        pnlFuncRast3.setBounds(582, 634, 166, 115);
        pnlFuncRast3.setBorder(borda);


        lblFR3TM = new JLabel();
        lblFR3TM.setText("Tempo míni:");
        lblFR3TM.setFont(fonte_secundaria);
        lblFR3TM.setBounds(588, 657, 95, 14);
        getContentPane().add(lblFR3TM);

        txtFR3TM = new JTextField();
        txtFR3TM.setBounds(683, 652, 59, 29);
        getContentPane().add(txtFR3TM);

        btnFR3Rastrear = new JButton();
        btnFR3Rastrear.setText("Em rota de colisão");
        btnFR3Rastrear.setBackground(Color.red);
        btnFR3Rastrear.setBounds(588, 702, 154, 41);
        btnFR3Rastrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : Aeronave.rotaColisao(lista_avioes.values(), Integer.parseInt(txtFR3TM.getText()))) {
                    txtRelatorio.setText(txtRelatorio.getText() + s + " \n");
                }
            }
        });
        getContentPane().add(btnFR3Rastrear);


        getContentPane().add(pnlFuncRast3);


    }

    protected int geraId() {
        int id = 1;
        boolean verificador = false;
        while (!verificador) {
            if (contaID.containsKey(id)) {
                id++;
            } else {
                contaID.put(id, id);
                verificador = true;
            }
        }

        return id;

    }

}
