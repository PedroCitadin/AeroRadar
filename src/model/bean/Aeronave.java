package model.bean;

import model.util.ConversorCoordenadas;

import javax.swing.*;
import java.util.*;

public class Aeronave {
    private int id;
    private float x;
    private float y;
    private float raio;
    private float angulo;
    private float velocidade;
    private float direcao;
    private boolean selecionado;

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    private JLabel label;

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRaio() {
        return raio;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }

    public float getAngulo() {
        return angulo;
    }

    public void setAngulo(float angulo) {
        this.angulo = angulo;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public float getDirecao() {
        return direcao;
    }

    public void setDirecao(float direcao) {
        this.direcao = direcao;
    }


    public void convertePolarCartesiano(){
        this.x = (float) (Math.cos(Math.toRadians(this.angulo))*this.raio);
        this.y = (float) (Math.sin(Math.toRadians(this.angulo))*this.raio);

    }

    public void converteCartesianoPolar(){
        this.raio = (float) Math.sqrt((this.x*this.x)+(this.y*this.y));
        var coefA = this.y/this.x;

        this.angulo = (float) Math.toDegrees(Math.atan(coefA));

    }
    public static List<Aeronave> calculaBase(Collection<Aeronave> lista, float distancia){
        List<Aeronave> listraproximos = new ArrayList<Aeronave>();
        for (Aeronave aero: lista){
            if (aero.raio<=distancia){
                listraproximos.add(aero);
            }
        }


        return listraproximos;
    }

    public static List<String> calculaDistAvioes(Collection<Aeronave> lista, float distancia){
        List<String> listaproximos = new ArrayList<String>();
        HashMap<String, Float> mapAux = new HashMap<String, Float>();
        for (Aeronave a1: lista){
            for (Aeronave a2: lista){
                if (a1.getId()!=a2.getId()){
                    if (a1.getId()<a2.getId()){
                        if (!mapAux.containsKey(a1.getId()+"-"+a2.getId())){
                            mapAux.put(a1.getId()+"-"+a2.getId(), calculaDist(a1, a2));
                            if (calculaDist(a1, a2)<=distancia){
                                listaproximos.add("Aviões "+a1.getId()+" e "+a2.getId()+" estão proximos");
                            }
                        }
                    }else{
                        if (!mapAux.containsKey(a2.getId()+"-"+a1.getId())){
                            mapAux.put(a2.getId()+"-"+a1.getId(), calculaDist(a1, a2));
                            if (calculaDist(a1, a2)<=distancia){
                                listaproximos.add("Aviões "+a1.getId()+" e "+a2.getId()+" estão proximos");
                            }
                        }
                    }
                }
            }
        }


        return listaproximos;

    }

    public static float calculaDist(Aeronave a1, Aeronave a2){
        float dx, dy;
        if (a1.getX()*a2.getX()>=0){
            dx = Math.abs(a1.getX()) - Math.abs(a2.getX());
        }else{
            dx = Math.abs(a1.getX()) + Math.abs(a2.getX());
        }
        if (a1.getY()*a2.getY()>=0){
            dy = Math.abs(a1.getY()) - Math.abs(a2.getY());
        }else{
            dy = Math.abs(a1.getY()) + Math.abs(a2.getY());
        }

        return (float) Math.sqrt((dx*dx)+(dy*dy));
    }
    public void alteraPosicao(int x, int y){
        this.x = x;
        this.y = y;
        this.converteCartesianoPolar();
        this.label.setBounds(ConversorCoordenadas.converteX(x), ConversorCoordenadas.converteY(y), this.label.getWidth(), this.label.getHeight());
    }
    public static void translandar(Aeronave a, int x, int y){
        int novoX = (int) (a.x+x);
        int novoY = (int) (a.y+y);
        a.alteraPosicao(novoX, novoY);


    }
    public static void rotacionar(Aeronave a, int x, int y, float angulo){
        var cos = Math.cos(Math.toRadians(angulo));

        var sin = Math.sin(Math.toRadians(angulo));
        var x_relativo = a.getX() - x;
        var y_relativo = a.getY() - y;
        int x2 = (int) (x_relativo * cos - y_relativo * sin);
        int y2 = (int) (y_relativo * cos + x_relativo * sin);
        int x_final = (int) (x2+x_relativo);
        int y_final = (int) (y2+y_relativo);
        a.alteraPosicao(x_final, y_final);

    }
    public static void escalonar(Aeronave a, int x, int y){
        int novoX = (int) (a.getX()*(x/100));
        int novoY = (int) (a.getY()*(y/100));
        a.alteraPosicao(novoX, novoY);

    }
}
