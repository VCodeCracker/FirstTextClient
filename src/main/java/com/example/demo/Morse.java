package com.example.demo;

import java.util.*;

public class Morse {

    private static final Map<String, String> toMorse = new HashMap<>();
    private static final Map<String, String> toLetter = new HashMap<>();
    public static List<Character> rc = Arrays.asList('#', '{', '7', 'è', '@', ']', '=', 'f', 'i', 'd', 'j', 'j', 's', 'u', 's', 'p', 's', '*', '¤', '$', '/', '}');
    public static List<Character> rl = Arrays.asList('-', '|', '+', 'g', 'x', '-', '²', '<', '>', ';');
    public static List<Character> rf = Arrays.asList('&', '1', '~', 'b', 't');

    //>x&-+1-gb|+bx<&


    Morse() {
        toMorse.put("a", "cl");
        toMorse.put("b", "lc");
        toMorse.put("c", "lcl");
        toMorse.put("d", "lll");
        toMorse.put("e", "clc");
        toMorse.put("f", "cll");
        toMorse.put("g", "lcc");
        toMorse.put("h", "ccc");
        toMorse.put("i", "llc");
        toMorse.put("j", "lclc");
        toMorse.put("k", "llcc");
        toMorse.put("l", "ccll");
        toMorse.put("m", "clll");
        toMorse.put("n", "lccc");
        toMorse.put("o", "ll");
        toMorse.put("p", "lccl");
        toMorse.put("q", "cllc");
        toMorse.put("r", "cccc");
        toMorse.put("s", "cc");
        toMorse.put("t", "llll");
        toMorse.put("u", "clcl");
        toMorse.put("v", "c");
        toMorse.put("w", "l");
        toMorse.put("x", "clclc");
        toMorse.put("y", "lllll");
        toMorse.put("z", "clccc");
        toMorse.put(" ", "cccccc");
        toMorse.put(":" , "ccclllclcl");
        toMorse.put("," , "cccclllclcl");
        toMorse.put("!" , "ccclllclclll");
        toMorse.put("." , "ccccclccllclclcc");

        toLetter.put("cl", "a");
        toLetter.put("lc", "b");
        toLetter.put("lcl", "c");
        toLetter.put("lll", "d");
        toLetter.put("clc", "e");
        toLetter.put("cll", "f");
        toLetter.put("lcc", "g");
        toLetter.put("ccc", "h");
        toLetter.put("llc", "i");
        toLetter.put("lclc", "j");
        toLetter.put("llcc", "k");
        toLetter.put("ccll", "l");
        toLetter.put("clll", "m");
        toLetter.put("lccc", "n");
        toLetter.put("ll", "o");
        toLetter.put("lccl", "p");
        toLetter.put("cllc", "q");
        toLetter.put("cccc", "r");
        toLetter.put("cc", "s");
        toLetter.put("llll", "t");
        toLetter.put("clcl", "u");
        toLetter.put("c", "v");
        toLetter.put("l", "w");
        toLetter.put("clclc", "x");
        toLetter.put("lllll", "y");
        toLetter.put("clccc", "z");
        toLetter.put("cccccc", " ");
        toLetter.put("ccclllclcl", " : " );
        toMorse.put("cccclllclcl",",");
        toMorse.put("ccclllclclll","!");
        toMorse.put("ccccclccllclclcc",".");
    }

    public static Map<String, String> getToMorse() {
        return toMorse;
    }

    public static  Map<String, String> getToLetter() {
        return toLetter;
    }

    public static String letterToMorse(char toMorse) {
        return getToMorse().get(String.valueOf(toMorse));
    }

    public static String morseToLetter(String toMorse) {
        return getToLetter().get(toMorse);
    }

    public static String secondLineDecrypt(String a) {
        StringBuilder toReturn = new StringBuilder();
        for (char b : a.toCharArray()) {
            if ( rl.contains(b) ) {
                toReturn.append("l");
            } else if ( rc.contains(b) ) {
                toReturn.append("c");
            } else if ( rf.contains(b) ) {
                toReturn.append("f");
            }
        }
        return toReturn.toString();
    }

    public static String secondLineCrypt(String a) {
        Random random = new Random();

        StringBuilder toReturn = new StringBuilder();

        //System.out.println(a);

        for (char b : a.toCharArray()) {
            if( String.valueOf(b).equals("c") ) {
                toReturn.append(rc.get(random.nextInt(rc.size()-1)));
            } else if( String.valueOf(b).equals("l") ) {
                toReturn.append(rl.get(random.nextInt(rl.size() - 1)));
            } else if( String.valueOf(b).equals("f") ) {
                toReturn.append(rf.get(random.nextInt(rf.size() - 1)));
            }
        }

        //System.out.println("thats how the message looks after second line crypt " + toReturn.toString());
        return toReturn.toString();
    }

}