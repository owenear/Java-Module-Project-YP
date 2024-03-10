import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Локаль для ввода/вывода вещественных чисел c разделителем точкой
        Locale LOCALE = Locale.ENGLISH;

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(LOCALE);

        // Определение количества человек
        int personCount = getPersonCount(scanner);

        // Ввод товаров в калькулятор
        Calculator calculator = new Calculator(personCount);
        addProductsToCalculator(scanner, calculator);
        scanner.close();

        // Вывод данных
        System.out.println("Добавленные товары:");
        for (Product product : calculator.products) {
            System.out.printf(LOCALE,"%s: %.2f%n",product.name, product.price);
        }
        System.out.println("-------------------");
        System.out.printf("Общая сумма: %s%n", formatSum(LOCALE, calculator.productSum));
        System.out.printf("Сумма на каждого: %s", formatSum(LOCALE, calculator.getSumPerPerson()));
    }

     static int getPersonCount(Scanner scanner){
        int personCount;
        while (true) {
            System.out.println("На скольких человек необходимо разделить счёт?");
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: количество человек должно быть целым числом");
                scanner.nextLine();
                continue;
            }
            personCount = scanner.nextInt();
            if (personCount <= 1) {
                System.out.println("Ошибка: количество человек должно быть > 1");
                scanner.nextLine();
                continue;
            }
            return personCount;
        }
    }

    static void addProductsToCalculator(Scanner scanner, Calculator calculator){
        while (true) {
            System.out.println("Укажите название и стоимость товара в формате рубли.копейки:");
            String productName = scanner.next();
            if (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: стоимость товара должна быть числом");
                scanner.nextLine();
                continue;
            }
            double productPrice = scanner.nextDouble();
            if (productPrice < 0) {
                System.out.println("Ошибка: стоимость товара не может быть отрицательной");
                scanner.nextLine();
                continue;
            }
            calculator.addProduct(new Product(productName, productPrice));
            System.out.println("Товар успешно добавлен в калькулятор!");
            System.out.println("Хотите добавить еще один товар? (введите 'ЗАВЕРШИТЬ' для выхода)");
            if (scanner.next().equalsIgnoreCase("завершить")) {
                break;
            }
        }
    }

    static String formatSum(Locale locale, double value) {
        int valueRnd = (int) value;
        String currency = switch (valueRnd % 10) {
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
        if (valueRnd % 100 > 10 && valueRnd % 100 < 20) {
            currency = "рублей";
        }
        return String.format(locale,"%.2f %s",value,currency);
    }

}