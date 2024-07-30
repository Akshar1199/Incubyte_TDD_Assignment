package com.incubyte.calculator;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        if (numbers.startsWith("//")) {
            Matcher m = Pattern.compile("//(.)\n(.*)").matcher(numbers);
            if (m.matches()) {
                delimiter = Pattern.quote(m.group(1));
                numbers = m.group(2);
            }
        }

        String[] numberArray = numbers.split(delimiter);
        List<Integer> numberList = Arrays.stream(numberArray).map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> negativeNumbers = numberList.stream().filter(n -> n < 0).collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negativeNumbers);
        }

        return numberList.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputBuilder = new StringBuilder();

        System.out.println("Enter the string of numbers (comma-separated or newline-separated). Type 'DONE' on a new line to finish:");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("DONE")) {
                break;
            }
            inputBuilder.append(line).append("\n");
        }

        String input = inputBuilder.toString().trim();

        try {
            int result = calculator.add(input);
            System.out.println("The sum is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
