import java.util.ArrayList;
import java.util.Vector;

public class inference_rule {
    ArrayList<String>rules=new ArrayList<>();
    ArrayList<Integer> numOfStatements = new ArrayList<>();
    String predicted_val="";
    Vector vector = new Vector<>();
    ArrayList<pair>pair=new ArrayList<>();


    public ArrayList<String> splitRules(String s){
        String []divide=s.split("#");
        ArrayList<String>str=new ArrayList<>();
        for(int i=0;i<divide.length;i++) {
            str.add(divide[i]);

        }
        return str;

    }

    public void hhp(fuzzy f){
        for(int k=0;k<rules.size();k++) {
            String str=rules.get(k);
            ArrayList<String>split_Rules=splitRules(str);
            for (int i = 0; i < split_Rules.size() - 1; i += 2) {
                String[] divide = split_Rules.get(i).split("=");
                for (int j = 0; j < f.fuzz.size(); j++) {

                    if (divide[0].equals(f.fuzz.get(j).fuzzy_name)) {
                        for (int l = 0; l < f.fuzz.get(j).sh.size(); l++) {
                            if (divide[1].equals(f.fuzz.get(j).sh.get(l).name)) {
                                double x = f.fuzz.get(j).sh.get(l).prob;
                                vector.add(x);
                                if(i!=split_Rules.size()-2){
                                vector.add(split_Rules.get(i + 1));}
                                break;
                            }
                        }
                    }

                }
            }
            String []final_div=split_Rules.get(split_Rules.size()-1).split("=");
            predicted_val=final_div[1];
            show_print();

        }


    }
    public void show_print(){
        for(int i=0;i<vector.size()-1;i++){
            if(vector.get(i).equals("AND")){
                System.out.println("Min ( "+vector.get(i-1)+" , "+ vector.get(i+1));
               double x = Math.min((double)vector.get(i-1),(double)vector.get(i+1));
               System.out.println(x+" "+vector.get(vector.size()-1));
                vector.remove(i+1);
                vector.remove(i-1);
                vector.set(i-1,x);

            }
        }
        for(int i=0;i<vector.size()-1;i++){
            if(vector.get(i).equals("OR")){
                System.out.println("Max ( "+vector.get(i-1)+" , "+ vector.get(i+1)+")");
                double x = Math.max((double)vector.get(i-1),(double)vector.get(i+1));
                System.out.println(x+" "+vector.get(vector.size()-1));
                vector.remove(i+1);
                vector.remove(i-1);
                vector.set(i-1,x);

            }
        }
        pair p=new pair();
        p.number=(double)vector.get(0);
        p.predicted=predicted_val;
        System.out.println("name: "+p.predicted+" prob: "+p.number);
        pair.add(p);
        vector.clear();

    }
}
