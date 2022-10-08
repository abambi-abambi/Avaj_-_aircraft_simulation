package ro.academyplus.avaj.simulator;

class WeatherProvider {
	private static WeatherProvider	weatherProvider;
	private static String[]			weather = new String[] {"F", "R", "S", "$"};
	private static char[][][]		arrc = new char[101][101][101];
	private WeatherProvider() {}

	public static	WeatherProvider getProvider() {
		if (weatherProvider == null) {
			synchronized (WeatherProvider.class) {
				if (weatherProvider == null) {
					weatherProvider = new WeatherProvider();
					System.out.println("::: New WeatherProvider is created...");
				}
			}
		}
		createRandomWeather();
		return (weatherProvider);
	}

	private static void createRandomWeather() {
		for (int z = 0; z < 101; z++)
			for (int y = 0; y < 101; y++)
				for (int x = 0; x < 101; x++)
					arrc[z][y][x] = (char)((int)((Math.random() * 10) % 4));
	}

	public String	getCurrentWeather(Coordinates coordinates) {
		int x = coordinates.getLatitude();
		int y = coordinates.getLongitude();
		int z = coordinates.getHeight();
		char c = arrc[z][y][x];

		return weather[(int)c];
	}
}
