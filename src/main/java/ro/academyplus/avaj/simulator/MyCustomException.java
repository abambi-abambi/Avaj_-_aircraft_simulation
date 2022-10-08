package ro.academyplus.avaj.simulator;

public class MyCustomException extends Exception {
	static private final String ANSI_PURPLE = "\u001B[35m";
	static private final String ANSI_RESET = "\u001B[0m";

	MyCustomException(String message) {
		super(ANSI_PURPLE + "\n>>> Avaj-launcher's error: " + message + " <<<" + ANSI_RESET);
	}

}