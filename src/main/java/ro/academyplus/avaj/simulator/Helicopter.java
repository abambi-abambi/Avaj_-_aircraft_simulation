package ro.academyplus.avaj.simulator;

class Helicopter extends Aircraft implements Flyable {
	private String			type;
	private WeatherTower	weatherTower = WeatherTower.getWeatherTower();
	private boolean			registered = false;
	private boolean			landed = false;

	Helicopter(String name, Coordinates coordinates)
	{
		super(name, coordinates);

		this.type = "Helicopter";

		registerTower(weatherTower);
	}

	@Override
	public void	updateConditions() {
		String point = weatherTower.getWeather(coordinates);
		// System.out.println(landed);
		if (landed == true && 
			point == "$") {
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): Ready for flight. Starting a take-off...");
			landed = false;
		}
		else if (landed == true && 
			(point == "F" || point == "R" || point == "S")) {
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): I'm still on the ground. Waiting for a good weather...");
			return ;
		}
		if (point == "F")
		{
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): It's so foggy.");
			if (coordinates.getLongitude() + 1 > 100)
				this.coordinates = new Coordinates(100, coordinates.getLatitude(), coordinates.getHeight());
			else
				this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
		}
		else if (point == "R")
		{
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): It's raining.");
			if (coordinates.getLongitude() + 5 > 100)
				this.coordinates = new Coordinates(100, coordinates.getLatitude(), coordinates.getHeight());
			else
				this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
		}
		else if (point == "S")
		{
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): Winter is coming!");
			if (coordinates.getHeight() - 12 < 0)
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 0);
			else
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
		}
		else if (point == "$")
		{
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): It's sunny.");
			if (coordinates.getLongitude() + 10 > 100)
				this.coordinates = new Coordinates(100, coordinates.getLatitude(), coordinates.getHeight());
			else
				this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight());
			if (coordinates.getHeight() + 10 > 100)
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
			else
				this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() + 10);
		}
		else
			System.out.println("Uknown weather");
		if (coordinates.getHeight() == 0 && 
			registered == true && 
			landed == false) {
			System.out.println(this.type + "#" + this.name + "(" + this.id + "): Just landed. My current coordinates: Latitude [" + coordinates.getLatitude() + 
			"], Longitude [" + coordinates.getLongitude() + "], Height [0].");
			landed = true;
			registerTower(weatherTower);
			registerTower(weatherTower);
		}
	}

	@Override
	public void	registerTower(WeatherTower weatherTower) {
		registered = !registered;
		if (registered == true)
		{
			weatherTower.register(this);
			System.out.println(this.type + "#" + this.name + "(" + this.id + ") registered to weather tower.");
		}
		else
		{
			weatherTower.unregister(this);
			System.out.println(this.type + "#" + this.name + "(" + this.id + ") unregistered from weather tower.");
		}
	}
}
