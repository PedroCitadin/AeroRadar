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
    public static float calculaDistPonto(Aeronave a1, float x, float y){

        float dx, dy;
        if (a1.getX()*x>=0){
            dx = Math.abs(a1.getX()) - Math.abs(x);
        }else{
            dx = Math.abs(a1.getX()) + Math.abs(x);
        }
        if (a1.getY()*y>=0){
            dy = Math.abs(a1.getY()) - Math.abs(y);
        }else{
            dy = Math.abs(a1.getY()) + Math.abs(y);
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
        var angulo_tot = 0.0;

        angulo_tot = a.getAngulo()+angulo;
       var xr = a.getX()-x;
       var yr = a.getY()-y;
       var x_final = xr*Math.cos(Math.toRadians(angulo))- yr*Math.sin(Math.toRadians(angulo));
       var y_final = yr*Math.cos(Math.toRadians(angulo))+ xr*Math.sin(Math.toRadians(angulo));
        x_final+=x;
        y_final+=y;
        a.alteraPosicao((int) x_final,(int) y_final);

    }
    public static void escalonar(Aeronave a, int x, int y){
        float xf = x;
        float yf= y;
        var novoX = a.getX()*(xf/100);
        var novoY =  a.getY()*(yf/100);

        a.alteraPosicao((int) novoX, (int) novoY);

    }

    public static List<String> rotaColisao(Collection<Aeronave> lista, int tempo){
        List<String> listaproximos = new ArrayList<String>();
        HashMap<String, Float> mapAux = new HashMap<String, Float>();
        for (Aeronave a1: lista){
            for (Aeronave a2: lista){
                if (a1.getId()!=a2.getId()){
                    if (a1.getId()<a2.getId()){
                        if (!mapAux.containsKey(a1.getId()+"-"+a2.getId())){
                            mapAux.put(a1.getId()+"-"+a2.getId(), calculaDist(a1, a2));
                            HashMap<String, Float> pontos = Aeronave.calculaPontoEncontro(a1, a2);
                            if (pontos.containsKey("IMPOSSIVEL")){
                                listaproximos.add("Colisão impossivel entre os aviões "+a1.getId()+" e "+a2.getId())   ;
                            }else if(pontos.containsKey("TODOS")){
                                if (a1.getVelocidade()==a2.getVelocidade()){
                                    listaproximos.add("Colisão impossivel entre os aviões "+a1.getId()+" e "+a2.getId())   ;
                                }else{
                                   if (Aeronave.maisRapidoFrente(a1, a2)){
                                       listaproximos.add("Colisão impossivel entre os aviões "+a1.getId()+" e "+a2.getId())   ;
                                   }else{
                                       float v1, v2;
                                       if (a1.getVelocidade()>a2.getVelocidade()){
                                           v1 = a1.getVelocidade();
                                           v2 = a2.getVelocidade();
                                       }else{
                                           v1 = a2.getVelocidade();
                                           v2 = a1.getVelocidade();
                                       }

                                       var distancia = Aeronave.calculaDist(a1, a2);
                                       var tempoColi = ((distancia*1000)/(v1-v2));
                                       if (tempoColi<=tempo){
                                           listaproximos.add("Colisão entre os aviões "+a1.getId()+" e "+a2.getId()+" em "+tempoColi+" segundos");
                                       }
                                   }
                                }
                            }else{
                                var distanciaA1 = (Aeronave.calculaDistPonto(a1, pontos.get("X"), pontos.get("Y")));

                                var distanciaA2 = (Aeronave.calculaDistPonto(a2, pontos.get("X"), pontos.get("Y")));

                                var tempoA1 = distanciaA1/a1.getVelocidade();
                                var tempoA2 = distanciaA2/a2.getVelocidade();
                                var tempofinal = Math.abs(tempoA1 - tempoA2)*3600;
                               if (tempofinal<=tempo){
                                   listaproximos.add("Colisão entre os aviões "+a1.getId()+" e "+a2.getId()+" em "+tempofinal+" segundos");
                               }
                            }
                        }
                    }else{
                        if (!mapAux.containsKey(a2.getId()+"-"+a1.getId())){
                            mapAux.put(a2.getId()+"-"+a1.getId(), calculaDist(a1, a2));

                        }
                    }
                }
            }
        }
        return listaproximos;
    }


    public static HashMap<String, Float> calculaPontoEncontro(Aeronave a1, Aeronave a2){
        HashMap<String, Float> pontos = new HashMap<String, Float>();
        ////função a1
        var coefAngular1 = Math.tan(Math.toRadians(a1.getDirecao()));
        var coefLinear1 = (coefAngular1*a1.getX()-a1.getY())*(-1);


        ///função a2
        var coefAngular2 = Math.tan(Math.toRadians(a2.getDirecao()));
        var coefLinear2 = (coefAngular2*a2.getX()-a2.getY())*(-1);



        if (coefAngular1==coefAngular2&&coefLinear1!=coefLinear2){
            System.out.println("colisão impossivel");
            pontos.put("IMPOSSIVEL", 0F);
        }else{
            if (coefAngular1==coefAngular2&&coefLinear1==coefLinear2){
                pontos.put("TODOS", 0F);
            }else{
                double x = Aeronave.retornaX(coefAngular1, coefAngular2, coefLinear1, coefLinear2);
                double y = coefAngular1*x+coefLinear1;

                pontos.put("X", (float) x);
                pontos.put("Y", (float) y);

            }
        }

        return pontos;
    }
    public static double retornaX(double a1, double a2, double b1, double b2){

        var x = ((b2*-1)-(b1*-1))/(a2-a1);

        return x;
    }
    public static boolean maisRapidoFrente(Aeronave a1, Aeronave a2){
        var direcao = a1.getDirecao();

        if (direcao==0){
               if (a1.getX()>a2.getX()){
                   if (a1.getVelocidade()>a2.getVelocidade()){
                       return true;
                   }
               }else{
                   if (a2.getVelocidade()>a1.getVelocidade()){
                       return true;
                   }
               }
        }else if(direcao>0&&direcao<90){
            if (a1.getX()>a2.getX()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao==90){
            if (a1.getY()>a2.getY()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao>90&&direcao<180){
            if (a1.getX()<a2.getX()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao==180) {
            if (a1.getX()<a2.getX()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao>180&&direcao<270){
            if (a1.getX()<a2.getX()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao==270){
            if (a1.getY()<a2.getY()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao>270&&direcao<360){
            if (a1.getX()>a2.getX()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }else if(direcao==360){
            if (a1.getX()>a2.getX()){
                if (a1.getVelocidade()>a2.getVelocidade()){
                    return true;
                }
            }else{
                if (a2.getVelocidade()>a1.getVelocidade()){
                    return true;
                }
            }
        }



        return false;
    }


    public static boolean doisNaMesmaPosicao(Aeronave a1, Collection<Aeronave> lista){

        for (Aeronave a : lista){
            if (a.getX()==a1.getX()&&a.getY()==a1.getY()){
                return true;
            }
        }
        return false;
    }
}
