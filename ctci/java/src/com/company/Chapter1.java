package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Chapter1 {

    //exercise 1.1
    public static boolean isUniqueString(String str){
        StringBuilder seenChars = new StringBuilder();
        for (int i = 0 ; i < str.length(); i++){
            char c = str.charAt(i);
            if (seenChars.toString().indexOf(c) != -1) return false;
            seenChars.append(c);
        }
        return true;
    }

    //exercise 1.2
    public static boolean isPermutation(String s1, String s2){
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return false;

        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();

        for (char c : s1.toCharArray()){
            int count = charCount.getOrDefault(c, 0);
            charCount.put(c, count + 1);
        }

        for (char c : s2.toCharArray()){
            int count = charCount.getOrDefault(c, 0);
            if (count == 0) return false;
            charCount.put(c, count - 1);
        }

        for (int count : charCount.values()){
            if (count != 0) return false;
        }

        return true;
    }

    //exercise 1.3
    public static boolean isPalindromePermutation(String str){
        str = str.trim();
        str = str.replaceAll("\\s", "");
        str = str.toLowerCase();

        int oneCount = 0;

        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();

        for (char c : str.toCharArray()){
            int count = charCount.getOrDefault(c, 0);
            charCount.put(c, count + 1);
        }

        for (int count : charCount.values()){
            if (count == 1){
                if (oneCount == 1) return false;
                oneCount++;
                continue;
            }
            if (count % 2 != 0) return false;
        }
        return true;
    }

    //exercise 1.6
    public static String basicCompression(String str){
        boolean allOnes = true;
        int sameCharCount = 0;
        Character prevChar = null;
        Character curChar = null;
        StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i < str.length() + 1; i++){
            if (i != str.length()) curChar = Character.valueOf(str.charAt(i));
            else {curChar = null;}

            if ((curChar == prevChar || prevChar == null)) {
                sameCharCount++;
            } else {
                strBuilder.append(prevChar);
                strBuilder.append(sameCharCount);
                if (sameCharCount != 1 && allOnes) allOnes = false;
                sameCharCount = 1;
            }
            prevChar = curChar;
        }

        if (allOnes) return str;
        else return strBuilder.toString();
    }

    //exercise  1.7
    public static int[][] rotateMatrix(int[][] mtx){
        if (mtx.length == 0 || mtx.length != mtx[0].length) return mtx;
        int n = mtx.length;

        for(int layer = 0; layer < n / 2; layer++){
            int first = layer;
            int last = n - 1 - layer;

            for(int i = first; i < last; i++){
                int offset = i - first;
                int top = mtx[first][i];

                mtx[first][i] = mtx[last - offset][first];
                mtx[last-offset][first] = mtx[last][last-offset];
                mtx[last][last-offset] = mtx[i][last];
                mtx[i][last] = top;
            }
        }
        return mtx;
    }

    //exercise 1.8
    public static int[][] zeroMatrix(int[][] mtx){
        ArrayList<int[]> coords = new ArrayList<int[]>();

        for(int i = 0; i < mtx.length; i++){
            int[] row = mtx[i];
            for(int j = 0; j < row.length; j++){
                if (row[j] == 0){
                    int[] arr = {i, j};
                    coords.add(arr);
                }
            }
        }

        for (int[] coord : coords){
            int yPos = coord[0];
            int xPos = coord[1];

            for (int i = 0; i < mtx.length; i++){
                mtx[yPos][i] = 0;
            }

            for (int i = 0; i < mtx[0].length; i++){
                mtx[i][xPos] = 0;
            }
        }
        return mtx;
    }

    //exercise 1.9
    public static boolean isStringRotated(String str1, String str2){
        if (str1.length() != str2.length()) return false;

        char firstCharS1 = str1.charAt(0);
        int segmentIdx = 0;
        boolean segmentFound = false;

        for (int i = 0; i < str2.length(); i++){
            if (str2.charAt(i) == firstCharS1){
                if (i == 0) return false; //if exact string does not work if double char
                String firstSegmentS2 = str2.substring(0, i);
                if (!str1.contains(firstSegmentS2)) return false;
                segmentFound = true;
                segmentIdx = i;
            }
            if (segmentFound){
                int offsetIdx = i - segmentIdx;
                if (str1.charAt(offsetIdx) != str2.charAt(i)) return false;
            }
        }
        return true;
    }
}
