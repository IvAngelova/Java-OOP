package T04_InterfacesAndAbstraction.exercise.telephony;

import java.util.List;

public class Smartphone implements Browsable, Callable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        for (String url : this.urls) {
            boolean containsDigits = false;
            for (int index = 0; index < url.length(); index++) {
                if (Character.isDigit(url.charAt(index))) {
                    containsDigits = true;
                    break;
                }
            }
            if (!containsDigits) {
                sb.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());
            } else {
                sb.append("Invalid URL!").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        for (String number : this.numbers) {
            boolean containsOnlyDigits = true;
            for (int index = 0; index < number.length(); index++) {
                if (!Character.isDigit(number.charAt(index))) {
                    containsOnlyDigits = false;
                    break;
                }
            }
            if (containsOnlyDigits) {
                sb.append(String.format("Calling... %s", number)).append(System.lineSeparator());
            } else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return sb.toString();

    }
}
