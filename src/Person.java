import java.util.Date;

public class Person extends ClassicalEntity<Integer> {

	private String name;
	private Date birthdate;
	private Boolean blueEyed;
	
	public Person(Integer id, String name, Date birthdate, Boolean blueEyed) {
		super(id);
		this.name = name;
		this.birthdate = birthdate;
		this.blueEyed = blueEyed;
	}
	
	public String name() {
		return name;
	}
	
	public Date birthdate() {
		return birthdate;
	}
	
	public Boolean isBlueEyed() {
		return blueEyed;
	}
}
