import java.util.ArrayList;

public class shape {
    String name;
    ArrayList<points> p=new ArrayList<>();
    boolean is_trab;
    double prob=0;

    public double area(){

        double sum=0.0 ;

        for( int i=0 ; i<p.size()-1 ; i++ ){

            sum += ( (p.get(i).x * p.get(i+1).y) - (p.get(i+1).x * p.get(i).y) ) ;
        }

        sum /= 2.0 ;

        return Math.abs(sum) ;
    }

    public double centroid(){

        double a = area()*6.0 ;

        double sum = 0.0 ;

        for( int i=0 ; i<p.size()-1 ; i++ ){

            sum += ( (p.get(i).x+p.get(i+1).x) * ( (p.get(i).x*p.get(i+1).y) - (p.get(i+1).x*p.get(i).y) ) ) ;
        }

        if( a==0 ){

            return a ;
        }

        return Math.abs(sum/a) ;
    }

}
