package model.util;

public class ConversorCoordenadas {
    private int x;
    private int y;
    private float raio;
    private int angulo;



    public static int converteX(int x){

        return x+200-(25/2);
    }
    public static int converteY(int y){

        return (y*(-1))+200-(25/2);
    }


    public static int polarX(int raio, int angulo){
        int x = (int) (Math.cos(Math.toRadians(angulo))*raio);

        return converteX(x);
    }

    public static int polarY(int raio, int angulo){
        int y = (int) (Math.sin(Math.toRadians(angulo))*raio);

        return converteY(y);
    }



}
