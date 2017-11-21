package com.proship.omrs.utils.util;

import java.util.Scanner;

public class FieldAutoTransformer {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();

        StringBuilder sb = new StringBuilder();

        String result = str.replaceAll("_([a-z])", "$1");

        System.out.print(result);
    }

}
