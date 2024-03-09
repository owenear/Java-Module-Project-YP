import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Определение количества человек
        int personCount = getPersonCount(scanner);

        // Ввод товаров в калькулятор
        Calculator calculator = new Calculator(personCount);
        addProductsToCalculator(scanner, calculator);
        scanner.close();

        // Вывод данных
        System.out.println("Добавленные товары:");
        for (Product product : calculator.products) {
            System.out.printf("%s: %.2f%n",product.name, product.price);
        }
        System.out.println("-------------------");
        System.out.printf("Общая сумма: %s%n%n", formatSum(calculator.productSum));
        System.out.println("Каждый должен заплатить по " + formatSum(calculator.getSumPerPerson()));
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

    static String formatSum(double value) {
        String currency = switch ((int) value % 10) {
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
        return String.format("%.2f %s",value,currency);
    }

}