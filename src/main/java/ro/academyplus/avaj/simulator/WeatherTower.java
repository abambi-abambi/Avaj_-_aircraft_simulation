package ro.academyplus.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

class WeatherTower extends Tower {
	private  List<Flyable> observers = new ArrayList<>();
	private static WeatherTower	weatherTower;
	private WeatherTower() {};

	static WeatherTower getWeatherTower() {
		if (weatherTower == null) {
			synchronized (WeatherTower.class) {
				if (weatherTower == null) {
					weatherTower = new WeatherTower();
					System.out.println("::: New weatherTower is created...\n");
				}
			}
		}
		return (weatherTower);
    }

	public String getWeather(Coordinates coordinates) {
		WeatherProvider	weatherProvider = WeatherProvider.getProvider();
		return (weatherProvider.getCurrentWeather(coordinates));
	}

	void changeWeather() {
		WeatherProvider	weatherProvider = WeatherProvider.getProvider();
		conditionsChanged();
	}

	@Override
	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}

	@Override
	public void register(Flyable flyable) {
		observers.add(0, flyable);

		System.out.print("Tower says: ");
	}

	@Override
	public void unregister(Flyable flyable) {
		observers.remove(flyable);

		System.out.print("Tower says: ");
	}

}