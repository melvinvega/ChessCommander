package com.capstone.chesscommander.chesscommander;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sujith on 19/5/14.
 */
public class Soundex {

private static Map<String, List<Character>> soundexMap = new HashMap<String, List<Character>>();

static {
    soundexMap.put("soundex_en", Arrays.asList('0', '1', '2', '3', '0', '1', '2', '0', '0', '2', '2', '4', '5', '5', '0', '1', '2', '6', '2', '3', '0', '1', '0', '2', '0', '2'));
}

public static String soundex(String s) {
        char[] x = s.toUpperCase().toCharArray();
        char firstLetter = x[0];

        // convert letters to numeric code
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {

                case 'B':
                case 'F':
                case 'P':
                case 'V':
                    x[i] = '1';
                    break;

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z':
                    x[i] = '2';
                    break;

                case 'D':
                case 'T':
                    x[i] = '3';
                    break;

                case 'L':
                    x[i] = '4';
                    break;

                case 'M':
                case 'N':
                    x[i] = '5';
                    break;

                case 'R':
                    x[i] = '6';
                    break;

                default:
                    x[i] = '0';
                    break;
            }
        }

        // remove duplicates
        String output = "" + firstLetter;
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i-1] && x[i] != '0')
                output += x[i];

        // pad with 0's or truncate
        output = output + "0000";
        return output.substring(0, 4);
    }

/**
 * Compare soundex of given strings
 * This function checks if 2 given strings are phonetically
 * sounds same by doing soundex code comparison
 *
 * @param string1 First string for comparison
 * @param string2 Second string for comparison
 * @return Returns 0 if both strings are same, 1 if strings
 * sound phonetically same, 2 if strings are
 * phonetically not same. We can't perform English
 * cross language comparision if English string is
 * passed as one function will return -1.
 */
public int compare(String string1, String string2) {
    if (string1 == null || string2 == null || string1.length() == 0 || string2.length() == 0) {
        return -1;
    }

    if (string1.equals(string2)) {
        return 0;
    }

    String soundex1 = soundex(string1);
    String soundex2 = soundex(string2);

    if (soundex1.substring(1).equals(soundex2.substring(1))) {
        return 1;
    }
    return 2;
}
}