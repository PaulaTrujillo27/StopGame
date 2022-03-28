package model;


public class Answer {
	
	public String type = "Answer";
	private String name;
	private String lastName;
	private String animal;
	private String country;
	private String city;
	private String thing;
	
	public Answer(String name, String lastName, String animal, String country, String city, String thing){
		this.name = name;
		this.lastName = lastName;
		this.animal = animal;
		this.country = country;
		this.city = city;
		this.thing= thing;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	
	
}

