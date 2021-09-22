package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.CANADA);
		Scanner read = new Scanner(System.in);
		List<Product> products = new ArrayList<>();

		System.out.print("Enter the number of products: ");
		int n = read.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.println("Product #" + i + ": ");
			System.out.print("Common, used or imported (c/u/i)? ");
			char type = read.next().charAt(0);

			if (type == 'c' || type == 'u' || type == 'i') {
				System.out.print("Name: ");
				read.nextLine();
				String name = read.nextLine();

				System.out.print("Price: ");
				Double price = read.nextDouble();
				if (type == 'i') {
					System.out.print("Customs fee: ");
					Double customsFee = read.nextDouble();
					products.add(new ImportedProduct(name, price, customsFee));

				} else if (type == 'u') {
					System.out.print("Manufacture date (dd/mm/yyyy): ");
					Date manufactureDate = sdf.parse(read.next());
					products.add(new UsedProduct(name, price, manufactureDate));

				} else {
					products.add(new Product(name, price));
				}
			} else {
				System.err.println("Error, expected value \'c\', \'u\' or \'i\'!");
				break;
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("PRICE TAGS:");
		for (Product product : products) {
			System.out.println(product);
		}

		read.close();
	}
}
