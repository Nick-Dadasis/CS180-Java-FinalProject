/* Nick Dadasis
Final Project */

public class Car {
	private int year;
	private String make;
	private String model;
	private char option;
	private double price;
	private boolean available;
	
	public Car(int y, String mk, String md, char o, double p, boolean a) {
		year = y;
		make = mk;
		model = md;
		option = o;
		price = p;
		available = a;
	}
	
	public Car() {
		year = 0;
		make = "no make";
		model = "no model";
		option = ' ';
		price = 0.0;
		available = false;
	}
	
	public void setYear(int y) {
		year = y;
	}
	
	public void setMake(String mk) {
		make = mk;
	}
	
	public void setModel(String md) {
		model = md;
	}
	
	public void setOption(char o) {
		option = o;
	}
	
	public void setPrice(double p) {
		price = p;
	}
	
	public void setAvailability(boolean a) {
		available = a;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public char getOption() {
		return option;
	}
	
	public double getPrice() {
		return price;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public boolean equals(Car car2) {
		if((year == car2.year) && (make == car2.make) && (model == car2.model) && (option == car2.option) && (price == car2.price) && (available == car2.available))
			return (true);
		else
			return (false);
	}
	
	public String toString() {
		return String.format("%d  %-10s %-10s %c %10.2f %s", year, make, model, option, price, available);
	}
}