package ro.academyplus.avaj.simulator;

class Aircraft {
	protected long			id;
	private String			type;
	protected String		name;
	protected Coordinates	coordinates;
	private static long		idCounter = 0;

	protected	Aircraft(String name, Coordinates coordinates) {
		this.id = nextId();
		this.type = "Aircraft";
		this.name = name;
		this.coordinates = coordinates;
	}

	private long	nextId() {
		idCounter++;
		return (idCounter);
	}
}
