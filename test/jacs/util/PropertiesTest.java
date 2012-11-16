package jacs.util;

public class PropertiesTest {
	public static void main(String[] args) {
		String ver = PropertyManager.getProperty("persistence.exceedvote");
		System.out.println("version is " + ver);
	}
}
