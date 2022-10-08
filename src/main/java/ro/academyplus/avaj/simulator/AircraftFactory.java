package ro.academyplus.avaj.simulator;

class AircraftFactory {

	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
	{
		if (type.equals("Baloon") || type.equals("994736b4f0aec72f6e5ae580051d012f"))
		{
			return new Baloon(name, new Coordinates(longitude, latitude, height));
		}
		else if (type.equals("Helicopter") || type.equals("2ab8b43468e8b92b0fc5c81e70e35a2d"))
		{
			return new Helicopter(name, new Coordinates(longitude, latitude, height));
		}
		else if (type.equals("JetPlane")  || type.equals("554cd647d6b135f7e36ab1214c5e816a"))
		{
			return new JetPlane(name, new Coordinates(longitude, latitude, height));
		}
		else
		{
			System.out.println("Unknown aircraft...");
			return null;
		}
	}
}
