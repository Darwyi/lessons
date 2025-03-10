package org.example;

import java.util.Scanner;

public class TA_27022025 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Введыть будь-який текст щоб порахувати в ньому англійські голосні");
        String userinput = s.nextLine();

        System.out.println("Кількість голосних в веденному вами рядку: " + count_golosni(userinput));
    }

    public static int count_golosni( String text) {
        int kilkist_i = 0;
        int kilkist_e = 0;
        int kilkist_o = 0;
        int kilkist_a = 0;
        int kilkist_u = 0;

        for ( int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'i') {
                kilkist_i++;
            }
            if (text.charAt(i) == 'e') {
                kilkist_e++;
            }
            if (text.charAt(i) == 'o') {
                kilkist_o++;
            }
            if (text.charAt(i) == 'a') {
                kilkist_a++;
            }
            if (text.charAt(i) == 'u') {
                kilkist_u++;
            }

        }

        return kilkist_a + kilkist_e + kilkist_i + kilkist_u + kilkist_o;
    }
}
