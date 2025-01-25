package model;

public class Doctor {
	private int id;
	private String name;
	private String specialization;
	private String contact;
	private String availability;
	
	public Doctor(int id, String name, String specialization, String contact, String availability) {
		this.id = id;
		this.name = name;
	    this.specialization = specialization;
	    this.contact = contact;
	    this.availability = availability;
	}
	
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getSpecialization() { return specialization; }
	public void setSpecialization(String specialization) { this.specialization = specialization; }
	
	public String getContact() { return contact; }
	public void setContact(String contact) { this.contact = contact; }
	
	public String getAvailability() { return availability; }
	public void setAvailability(String availability) { this.availability = availability; }
}
