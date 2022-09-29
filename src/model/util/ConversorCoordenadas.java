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
        return 200+(y*(-1))-(25/2);
    }


    public static int polarX(int raio, int angulo){
        int x = (int) (Math.cos(angulo)*raio);
        System.out.println("X: ");
        System.out.println(x);
        System.out.println(converteX(x));
        return converteX(x);
    }

    public static int polarY(int raio, int angulo){
        int y = (int) (Math.sin(angulo)*raio);
        System.out.println("Y: ");
        System.out.println(y);
        System.out.println(converteY(y));
        return converteY(y);
    }



}
