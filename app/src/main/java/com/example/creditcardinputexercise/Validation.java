package com.example.creditcardinputexercise;

public class Validation {
    private Validation() {
    }

    public static int isCardNumberValid(String num) {
        if (!checkLuhn(num)) return 0;

            //master card
        if (num.matches("^(?:5[1-5]|2(?!2([01]|20)|7(2[1-9]|3))[2-7])\\d{14}$")) return 1;

            //american exe
        else if (num.matches("^3[47][0-9]{13}$")) return 2;

            // discover
        else if (num.matches("^6(?:011|[45][0-9]{2})[0-9]{12}$")) return 1;

            //visa
        else if (num.matches("^4[0-9]{12}(?:[0-9]{3}){0,2}$")) return 1;

        else return 0;

    }

    public static boolean isMMYYValid(String mmyy) {
        return mmyy.matches("\\d\\d/\\d\\d");
    }

    public static boolean isS_CodeValid(String s_code, int cardType) {
        if (cardType == 1)
            return s_code.matches("\\d\\d\\d");
        else
            return s_code.matches("\\d\\d\\d\\d");
    }

    public static boolean isFirstNameValid(String firstName) {
        return firstName.matches("[A-Za-z]+");
    }

    public static boolean isLastNameValid(String lastName) {
        return lastName.matches("[A-Za-z]+");
    }

    private static boolean checkLuhn(String num) {
        int[] arr = new int[num.length()];
        int sum = 0;

        for (int i = 0; i < num.length(); i++)
            arr[i] = Integer.parseInt(num.charAt(i) + "");

        for (int i = arr.length - 2; i >= 0; i -= 2) {
            int n = arr[i] * 2;
            if (n > 9)
                n = (n % 10) + (n / 10);
            arr[i] = n;
        }

        for (int temp : arr)
            sum += temp;

        return (sum % 10 == 0);
    }
}
