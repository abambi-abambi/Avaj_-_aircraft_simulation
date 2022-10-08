package ro.academyplus.avaj.simulator;

public class MyCustomNumberFormatException extends NumberFormatException {
	static private final String ANSI_RED = "\u001B[31m";
	static private final String ANSI_RESET = "\u001B[0m";

	public MyCustomNumberFormatException(String message) {
		super(ANSI_RED + "\n>>> Avaj-launcher's error: " + message + " <<<" + ANSI_RESET);
	}
}
