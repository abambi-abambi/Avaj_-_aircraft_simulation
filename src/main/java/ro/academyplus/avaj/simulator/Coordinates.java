package ro.academyplus.avaj.simulator;

class Coordinates {
	private int	longitude;
	private int	latitude;
	private int	height;

	Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	// public void	setLongitude(int longitude) { // public is not allowed
	// 	this.longitude = longitude;
	// }

	// public void	setLatitude(int latitude) { // public is not allowed
	// 	this.latitude = latitude;
	// }

	// public void	setHeight(int height) { // public is not allowed
	// 	this.height = height;
	// }

	public int	getLongitude() {
		return this.longitude;
	}

	public int	getLatitude() {
		return this.latitude;
	}

	public int	getHeight() {
		return this.height;
	}
}