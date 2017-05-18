import java.util.Comparator;

public class MyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        int compa=String.valueOf(str1.charAt(1)).compareTo(String.valueOf(str2.charAt(1)));

        if(compa==0){
            compa=String.valueOf(str1.charAt(0)).compareTo(String.valueOf(str2.charAt(0)));
        }
        if(compa==0){
            compa=String.valueOf(str1.charAt(2)).compareTo(String.valueOf(str2.charAt(2)));
        }
        return compa;
    }
}