package model.bean;

import javax.swing.*;

public class Aeronave {
    private int id;
    private float x;
    private float y;
    private float raio;
    private float angulo;
    private float velocidade;
    private float direcao;

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
        System.out.println(coefA);
        this.angulo = (float) Math.toDegrees(Math.atan(coefA));

    }

}
