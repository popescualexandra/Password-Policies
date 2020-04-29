/*  FORMATELE REGULILOR
length <min_length> - parola trebuie să aibă min_length caractere (inclusiv); 0 < min_length
length <min_length> <max_length> - parola trebuie să aibă între min_length și max_length caractere (inclusiv); 0 < min_length <= max_length
class <min_class_count> - parola trebuie să aibă minim min_class_count tipuri de caractere diferite (literă mică, literă mare, cifră și alte caractere); 0 < min_class_count < 5
include <class> - parola trebuie obligatoriu să includă cel puțin un caracter din clasa specificată; class poate fi a, A, 0 sau $, caractere ce denotă clasa dorită
ninclude <class> - parola trebuie obligatoriu să nu includă niciun caracter din clasa specificată; class urmează aceleași reguli de mai sus
repetition <max_count> - același caracter se poate repeta pe poziții consecutive de maxim max_count ori; 0 < max_count
consecutive <max_count> - parola poate avea max_count caractere consecutive în secvență; 0 < max_count

INPUT EXAMPLE
4
length 6
include 0
include A
repetition 2
PimpMyPassword
HoldMyB33r
OnceUponAtimeinKansas
1234
*/

import java.util.*;

public class Main{
    
    public static class Length{
        String parola;
        int min;
        Length(){
        }
        public boolean getLength(){
            String [] s = parola.split("");
            if(s.length >= this.min) {
                return true;
            }else{
                return false;
            }
        }
    }
    
    public static class LengthD extends Length{
        int max;
        LengthD(){
        }
        @Override
        public boolean getLength(){
            String [] s = parola.split("");
            if(s.length >= this.min && s.length <= this.max) {
                return true;
            }else{
                return false;
            }
        }
    }
    
    public static class Clase{
        String parola;
        int min;
        int lc=0, uc=0, d=0, o=0;
        char ch;
        Clase(){
        }
        public int calc(){
            for(int i=0 ; i<parola.length() ; i++){
                ch = parola.charAt(i);
                if(Character.isLowerCase(ch)){
                    lc = 1;
                }else if(Character.isUpperCase(ch)){
                    uc = 1;
                }else if(Character.isDigit(ch)){
                    d = 1;
                }else{
                    o = 1;
                }
            }
            return lc + uc + d + o;
        }
        
        public boolean getClase(){
            lc = 0;
            uc = 0;
            d = 0;
            o = 0;
            if(this.calc() >= this.min){
                return true;
            }else{
                return false;
            }
        }
    }
    
    public static class MustInclude{
        String parola;
        String caracter;
        int lc=0, uc=0, d=0, o=0;
        char ch;
        MustInclude(){
        }
        public boolean getInclude(){
            for(int i=0 ; i<parola.length() ; i++){
                ch = parola.charAt(i);
                if(Character.isLowerCase(ch)){
                    lc = 1;
                }else if(Character.isUpperCase(ch)){
                    uc = 1;
                }else if(Character.isDigit(ch)){
                    d = 1;
                }else{
                    o = 1;
                }
            }
            if((this.caracter.equals("a") && lc == 1) || (this.caracter.equals("A") && uc == 1) || 
            (this.caracter.equals("0") && d == 1) || (this.caracter.equals("$") && o == 1)){
                lc=0;
                uc=0;
                d=0;
                o=0;
                return true;
            }else{
                lc=0;
                uc=0;
                d=0;
                o=0;
                return false;
            }
            
        } 
    }
    
    public static class MustNotInclude{
        String parola;
        String caracter;
        int lc=0, uc=0, d=0, o=0;
        char ch;
        MustNotInclude(){
        }
        public boolean getNotInclude(){
            for(int i=0 ; i<parola.length() ; i++){
                ch = parola.charAt(i);
                if(Character.isLowerCase(ch)){
                    lc = 1;
                }else if(Character.isUpperCase(ch)){
                    uc = 1;
                }else if(Character.isDigit(ch)){
                    d = 1;
                }else{
                    o = 1;
                }
            }
            if((this.caracter.equals("a") && lc == 0) || (this.caracter.equals("A") && uc == 0) || 
            (this.caracter.equals("0") && d == 0) || (this.caracter.equals("$") && o == 0)){
                lc=0;
                uc=0;
                d=0;
                o=0;
                return true;
            }else{
                lc=0;
                uc=0;
                d=0;
                o=0;
                return false;
            }
        } 
    }
    
    public static class Repetition{
        String parola;
        int max;
        int count = 1, ok = 1;
        Repetition(){
        }
        public boolean getRepetition(){
            String [] s = parola.split("");
            for(int i=0 ; i<s.length-1 ; i++){
                if(s[i].equals(s[i+1])){
                    count++;
                    if(count > this.max){
                        ok = 0;
                    }
                }else{
                    count = 1;
                }
            }
            if(ok == 0){
                ok=1;
                return false;
            }else{
                ok=1;
                return true;
            }
        }
    }
    
    public static class Consecutive{
        String parola;
        int max;
        char ch,ch1;
        int count = 1, ok = 1;
        Consecutive(){
        }
        public boolean getConsecutive(){
            for(int i=0 ; i<parola.length()-1 ; i++){
                ch = parola.charAt(i);
                ch1 = parola.charAt(i+1);
                if(ch==ch1-1){
                    count++;
                    if(count > this.max){
                        ok = 0;
                    }
                }else{
                    count = 1;
                }
            }
            if(ok == 0){
                ok=1;
                return false;
            }else{
                ok=1;
                return true;
            }
        }
    }
    
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Length l = new Length();
        LengthD ld = new LengthD();
        Clase c = new Clase();
        MustInclude mi1 = new MustInclude();
        MustInclude mi2 = new MustInclude();
        MustInclude mi3 = new MustInclude();
        MustInclude mi4 = new MustInclude();
        MustNotInclude mni1 = new MustNotInclude();
        MustNotInclude mni2 = new MustNotInclude();
        MustNotInclude mni3 = new MustNotInclude();
        MustNotInclude mni4 = new MustNotInclude();
        Repetition r = new Repetition();
        Consecutive co = new Consecutive();
        int len = 0, lenD = 0, cla = 0, inc = 0, ninc = 0, rep = 0, con = 0, ok = 1;
        int n = Integer.parseInt(sc.nextLine());
        for(int i=0 ; i<n ; i++){
            String linie = sc.nextLine();
            String [] s = linie.split(" ");
            switch(s[0]){
                case "length":
                    if(s.length == 2){
                        l.min = Integer.parseInt(s[1]);
                        len=1;
                    }else{
                        ld.min = Integer.parseInt(s[1]);
                        ld.max = Integer.parseInt(s[2]);
                        lenD = 1;
                    }
                break;
                case "class":
                    c.min = Integer.parseInt(s[1]);
                    cla = 1;
                break;
                case "include":
                    if(inc == 0){
                        mi1.caracter = s[1];
                        inc++;
                    }else if(inc == 1){
                        mi2.caracter = s[1];
                        inc++;
                    }else if(inc == 2){
                        mi3.caracter = s[1];
                        inc++;
                    }else{
                        mi4.caracter = s[1];
                    }
                break;
                case "ninclude":
                    if(ninc == 0){
                        mni1.caracter = s[1];
                        ninc++;
                    }else if(ninc == 1){
                        mni2.caracter = s[1];
                        ninc++;
                    }else if(ninc == 2){
                        mni3.caracter = s[1];
                        ninc++;
                    }else{
                        mni4.caracter = s[1];
                    }
                break;
                case "repetition":
                    r.max = Integer.parseInt(s[1]);
                    rep = 1;
                break;
                case "consecutive":
                    co.max = Integer.parseInt(s[1]);
                    con = 1;
                break;
            }
        }
        while(sc.hasNext()==true){
            String parola = sc.nextLine();
            System.out.println(parola);
            if(len == 1){
                l.parola = parola;
                if(l.getLength()==false){
                    ok = 0;
                    System.out.println("Numar prea mic de caractere");
                }
            }
            if(lenD == 1){
                ld.parola = parola;
                if(ld.getLength()==false){
                    ok = 0;
                    System.out.println("Numar prea mic sau prea mare de caractere");
                }
            }
            if(cla == 1){
                c.parola = parola;
                if(c.getClase()==false){
                    ok = 0;
                    System.out.println("Parola nu are destule tipuri de caractere diferite");
                }
            }
            if(inc == 1){
                mi1.parola = parola;
                if(mi1.getInclude()==false){
                    ok = 0;
                    System.out.println("Parola nu are caracterul specificat");
                }
            }
            if(inc==2){
                mi1.parola = parola;
                mi2.parola = parola;
                if(mi1.getInclude()==false || mi2.getInclude()==false){
                    ok = 0;
                    System.out.println("Parola nu are unul din caracterele specificate");
                }
            }
            if(inc==3){
                mi1.parola = parola;
                mi2.parola = parola;
                mi3.parola = parola;
                if(mi1.getInclude()==false || mi2.getInclude()==false || mi3.getInclude()==false){
                    ok = 0;
                    System.out.println("Parola nu are unul din caracterele specificate");
                }
            }
            if(inc==4){
                mi1.parola = parola;
                mi2.parola = parola;
                mi3.parola = parola;
                mi4.parola = parola;
                if(mi1.getInclude()==false || mi2.getInclude()==false || mi3.getInclude()==false || mi4.getInclude()==false){
                    ok = 0;
                    System.out.println("Parola nu are unul din caracterele specificate");
                }
            }
            if(ninc == 1){
                mni1.parola = parola;
                if(mni1.getNotInclude()==false){
                    ok = 0;
                    System.out.println("Parola are contine un caracter care nu trebuie");
                }
            }
            if(ninc==2){
                mni1.parola = parola;
                mni2.parola = parola;
                if(mni1.getNotInclude()==false || mni2.getNotInclude()==false){
                    ok = 0;
                    System.out.println("Parola are contine caractere care nu trebuie");
                }
            }
            if(ninc==3){
                mni1.parola = parola;
                mni2.parola = parola;
                mni3.parola = parola;
                if(mni1.getNotInclude()==false || mni2.getNotInclude()==false || mni3.getNotInclude()==false){
                    ok = 0;
                    System.out.println("Parola are contine caractere care nu trebuie");
                }
            }
            if(ninc==4){
                mni1.parola = parola;
                mni2.parola = parola;
                mni3.parola = parola;
                mni4.parola = parola;
                if(mni1.getNotInclude()==false || mni2.getNotInclude()==false || mni3.getNotInclude()==false || mni4.getNotInclude()==false){
                    ok = 0;
                    System.out.println("Parola are contine caractere care nu trebuie");
                }
            }
            if(rep == 1){
                r.parola = parola;
                if(r.getRepetition()==false){
                    ok = 0;
                    System.out.println("Parola contine un caracter care se repeta de prea multe ori");
                }
            }
            if(con == 1){
                co.parola = parola;
                if(co.getConsecutive()==false){
                    ok = 0;
                    System.out.println("Parola are contine prea multe caractere la rand");
                }
            }
            if(ok == 1){
                System.out.println("OK");
            }else{
                System.out.println("NOK"); 
            }
            ok = 1;
        }
    }
}
