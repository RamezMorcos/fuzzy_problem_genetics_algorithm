import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class fuzzy {
ArrayList<fuzzy_set>fuzz=new ArrayList<fuzzy_set>();
fuzzy_set predict_output=new fuzzy_set();
Scanner s=new Scanner(System.in);
inference_rule ir = new inference_rule();
    fuzzy(int x){
        for(int i=0;i<x;i++){
            fuzzy_set f=new fuzzy_set();
            System.out.println("fuzzy name");
            f.fuzzy_name=s.next();
            System.out.println("fuzzy position");
            f.position=s.nextDouble();
            System.out.println("number of shapes");
            int shapes_number=s.nextInt();
            for(int j=0;j<shapes_number;j++)
            {
                shape sha=new shape();
                System.out.println("name of shape");
                sha.name=s.next();
                System.out.println("kind of shape triangle/trapezoidal");
                String trap_or_triangle=s.next();
                if(trap_or_triangle.equals("trapezoidal")){
                    System.out.print("(");
                    double d=s.nextDouble();
                    sha.p.add(new points(d,0));
                    d=s.nextDouble();
                    sha.p.add(new points(d,1));
                    d=s.nextDouble();
                    sha.p.add(new points(d,1));
                    d=s.nextDouble();
                    sha.p.add(new points(d,0));
                    System.out.print(")");
                    sha.is_trab=true;
                }
                else{
                    System.out.print("(");
                    double d=s.nextDouble();
                    sha.p.add(new points(d,0));
                     d=s.nextDouble();
                    sha.p.add(new points(d,1));
                     d=s.nextDouble();
                    sha.p.add(new points(d,0));

                    sha.is_trab=false;
                }
               f.sh.add(sha);
            }
            fuzz.add(f);
        }

       System.out.println("name of the predicted fuzzy:");
            predict_output.fuzzy_name=s.next();
            int shapes_num=s.nextInt();
            for(int i=0;i<shapes_num;i++){
                shape sha=new shape();
                sha.name=s.next();
                String trap_or_triangle=s.next();
                if(trap_or_triangle.equals("trapezoidal")){
                    System.out.print("(");
                    double d=s.nextDouble();
                    sha.p.add(new points(d,0));
                    d=s.nextDouble();
                    sha.p.add(new points(d,1));
                    d=s.nextDouble();
                    sha.p.add(new points(d,1));
                    d=s.nextDouble();
                    sha.p.add(new points(d,0));
                    System.out.print(")");
                    sha.is_trab=true;
                }
                else{
                    System.out.print("(");
                    double d=s.nextDouble();
                    sha.p.add(new points(d,0));
                    d=s.nextDouble();
                    sha.p.add(new points(d,1));
                    d=s.nextDouble();
                    sha.p.add(new points(d,0));

                    sha.is_trab=false;
                }
               predict_output.sh.add(sha);
            }


    }

public double intersection(shape s,double x){
 for(int i=0;i<s.p.size()-1;i++){
if(Math.min(s.p.get(i).x, s.p.get(i+1).x)<=x && Math.max(s.p.get(i).x,s.p.get(i+1).x)>=x ){

 double y = ( ( (x-s.p.get(i).x)*(s.p.get(i+1).y-s.p.get(i).y) ) / (s.p.get(i+1).x-s.p.get(i).x)) + s.p.get(i).y ;
                return y ;
            }
        }
        return 0;
}
public void fuzzification (){
    for(int i=0;i<fuzz.size();i++){
                System.out.println(fuzz.get(i).fuzzy_name+" :");
        for(int j=0;j<fuzz.get(i).sh.size();j++){
                fuzz.get(i).sh.get(j).prob=intersection(fuzz.get(i).sh.get(j),fuzz.get(i).position);
                System.out.println(fuzz.get(i).sh.get(j).name+" = "+fuzz.get(i).sh.get(j).prob);
        }
    }
}

public void inference(fuzzy f){
    int numOfRules = s.nextInt();
    String rule;
    int numberOfStatements;
    for (int i = 0; i < numOfRules; i++){
        numberOfStatements = s.nextInt();
        ir.numOfStatements.add(numberOfStatements);
        rule = s.next();
        ir.rules.add(rule);
    }
   // ir.splitRules();
    ir.hhp( f);
}
public double defuzzification (){
    double sum=0;
    double result=0;
    for(int i=0;i<ir.pair.size();i++){

for(int j=0;j<predict_output.sh.size();j++){
if(ir.pair.get(i).predicted.equals(predict_output.sh.get(j).name)){
    System.out.println(ir.pair.get(i).predicted+"*"+predict_output.sh.get(j).prob);
    result+=ir.pair.get(i).number*predict_output.sh.get(j).centroid();
    sum+=ir.pair.get(i).number;
}
}
    }
return result/sum;
}

    public static void main(String[] args) {
        System.out.println("num of inputs");
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        fuzzy f=new fuzzy(x);
        f.fuzzification();
        f.inference(f);
        double sol=f.defuzzification();
        System.out.println(sol);
    }
}
