package ro.academyplus.avaj.simulator;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

class Simulator {
	static private int weather_launches;
	static private AircraftFactory factory = new AircraftFactory();
	static {
		System.out.println("\n::: New AircraftFactory is created...");
	}
	static private String line;
	static private String[] parts;
	static private int height;
	static private int latitude;
	static private int longitude;

	private static boolean checkArgIsvalid(String[] args) throws MyCustomException {
		if (args.length != 1)
			throw new MyCustomException("Wrong number of args!");
  		if (new File(args[0]).isFile() == false)
			throw new MyCustomException("Not valid pathname / filename!");
		int x = args[0].lastIndexOf('.');
		if (x > 0) {
    		String extension = args[0].substring(x + 1);
			if (extension.equals("txt") == false)
				throw new MyCustomException("Wrong file extension for a file:\n\t" + args[0] + "\nonly *.txt files are accepted");
		}
		if (new File(args[0]).canRead() == false)
			throw new MyCustomException("Wrong file permissions for a file:\n\t" + args[0] + ".\nSwitch on the readable permission");
		return (true);
	}

	public static void main(String[] args) throws MyCustomException {

		checkArgIsvalid(args);
		WeatherProvider	weatherProvider = WeatherProvider.getProvider();
		WeatherTower	weatherTower = WeatherTower.getWeatherTower();

		try (FileReader freader = new FileReader(args[0]);
			BufferedReader breader = new BufferedReader(freader)) {
			try {
				weather_launches = Integer.parseInt(breader.readLine());
				System.out.println("::: Number of weather changes: " + weather_launches + "\n");
			}
			catch (NumberFormatException e) {
				throw new MyCustomNumberFormatException("Wrong format of 1-st line of file ('int' is required)");
			}

			while ((line = breader.readLine()) != null)
				readAllLines(breader);

			breader.close();
			freader.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println("~~~ Starting the simulation...");

		PrintStream standard = System.out;
		try { PrintStream out = new PrintStream(new FileOutputStream("../../../simulation.txt", true));
    		System.setOut(out);

			for (int i = 0; i < weather_launches; i++) {
				System.out.println("\n ::: #" + (i + 1));
				weatherTower.changeWeather();
			}

			out.close();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		}

		System.setOut(standard);
		System.out.println("\n\t...\n\n~~~ All info is written to 'simulation.txt' file. Stopping the simulation...");
	}

	private static String MD5(String md5) throws MyCustomException {
	try {
			java.security.MessageDigest	md = java.security.MessageDigest.getInstance("MD5");
			byte[]						digest = md.digest(md5.getBytes());
			java.math.BigInteger		bigInt = new java.math.BigInteger(1, digest);

        	return bigInt.toString(16);
		} catch (java.security.NoSuchAlgorithmException e) {
			throw new MyCustomException("Errors while md5 encryption :(");
		}
	}

	private static void readAllLines(BufferedReader breader) throws MyCustomException {
		parts = line.split(" ");
		if (parts.length != 5) {
			throw new MyCustomException("Wrong format of of file:\n\t- all lines from the 2-nd should consist of 5 args separated by 1 whitespace;\n\t- forbidden empty lines in file;\n");
		}
/* type */ /* https://decodeit.ru/md5 */
		if ((parts[0].equals("Baloon") || parts[0].equals("Helicopter") || parts[0].equals("JetPlane") ||
			parts[0].equals(MD5("Baloon")) || parts[0].equals(MD5("Helicopter")) || parts[0].equals(MD5("JetPlane"))) == false) {
			throw new MyCustomException("Wrong type of an aircraft:\n\t" + parts[0] + "\n- required names are: Baloon / Helicopter / JetPlane or their md5 encryption");
		}
/* Longitude */
		try { longitude = Integer.parseInt(parts[2]);
		} catch (NumberFormatException e) { throw new MyCustomNumberFormatException("\n\tWrong format of the 3-th argument in some line of input file ('int' is required)\n");
		}
		if (longitude < 0)
			throw new MyCustomException("\n\tWrong type of 3-rd arg in some line of input file (the positive number is required)\n");		
		if (longitude > 100)
			longitude = 100;
/* Latitude */
		try { latitude = Integer.parseInt(parts[3]);
		} catch (NumberFormatException e) { throw new MyCustomNumberFormatException("\n\tWrong format of the 4-th argument in some line of input file ('int' is required)\n");
		}
		if (latitude < 0)
			throw new MyCustomException("\n\tWrong type of 4-th arg in some file line (the positive number is required)\n");
		if (latitude > 100)
			latitude = 100;
/* Height */
		try { height = Integer.parseInt(parts[4]);
		} catch (NumberFormatException e) { throw new MyCustomNumberFormatException("\n\tWrong format of the 5-th argument in some line of input file ('int' is required)\n");
		}
		if (height < 0)
			throw new MyCustomException("\n\tWrong type of 5-th arg in some file line (the positive number is required)\n");
		if (height > 100)
			height = 100;
/* new Aircraft */
		PrintStream standard = System.out;
		try { PrintStream out = new PrintStream(new FileOutputStream("../../../simulation.txt", true));
    		System.setOut(out);

			Flyable	craft1 = factory.newAircraft(parts[0], parts[1], longitude, latitude, height);

			out.close();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
		System.setOut(standard);
	}
}
