package model.util;

public class ConversorCoordenadas {
    private int x;
    private int y;
    private float raio;
    private int angulo;



    public static int converteX(float x){

        return (int) x+200-(25/2);
    }
    public static int converteY(float y){

        return (int) (y*(-1))+200-(25/2);
    }


    public static int polarX(float raio, float angulo){
        float x = (float) (Math.cos(Math.toRadians(angulo))*raio);

        return converteX(x);
    }

    public static int polarY(float raio, float angulo){
        float y = (float) (Math.sin(Math.toRadians(angulo))*raio);

        return converteY(y);
    }



}
