import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static CRUD<Person,Integer> crud = new InMemoryCRUD<Person,Integer>(); 
	
	private static String PERSON_INFO_STR = "ID: %d, Name: %s, Birthdate: %s, Blue eyed: %s";
	
	private static Integer incrementalId = 0;
	
	private static Date str2Date(String dateStr) {
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	private static String date2Str(Date date) {
		return sdf.format(date);
	}
	
	private static void showPerson(Person person) {
		System.out.println(String.format(PERSON_INFO_STR,
				person.getKey(),
				person.name(),
				date2Str(person.birthdate()),
				person.isBlueEyed() ? "yes" : "no"));
	}
	
	private static void showAllPersons() {
		System.out.println("Existing persons: ");
		List<Person> persons = crud.read();
		persons.forEach(person -> showPerson(person));
	}
	
	private static int showOptions() {
		System.out.println("Options:\n"
							+ "[0] Exit\n"
							+ "[1] Create Person\n"
							+ "[2] Read Person(s)\n"
							+ "[3] Update Person\n"
							+ "[4] Delete Person");
		
		Scanner scanner = new Scanner(System.in);
		return  scanner.nextInt();
	}
	
	
	private static void processCreateOption() {
		Person person = getPersonFromUserInput();
		crud.create(person);
	}

	private static void processReadOption() {
		System.out.println("Which person to read (leave blank to read all):");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		
		if(id == null || "".equals(id))
			showAllPersons();
		else {
			Person person = crud.read(Integer.parseInt(id));
			if(person == null)
				System.out.println("Invalid person ID");
			else
				showPerson(person);
		}
	}
	
	private static Person getPersonFromUserInput() {
		Scanner scanner = new Scanner(System.in);
		String name;
		Date birthdate;
		Boolean blueEyed;
		
		System.out.println("Input person info:");
		
		System.out.print("Name: ");
		name = scanner.nextLine();
		
		System.out.print("Birthdate (dd/MM/yyyy): ");
		birthdate = str2Date(scanner.next());
		
		System.out.print("Does this person has blue eyes? (y/n): ");
		blueEyed = "y".equals(scanner.next()) ? true : false;
		
		return new Person(incrementalId++,name,birthdate,blueEyed);
	}

	private static void processUpdateOption() {
		System.out.println("Which person to update?");
		
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();
		
		Person person = crud.read(id);
		if(person == null) {
			System.out.println("Invalid person ID");
			return;
		}
		
		person = getPersonFromUserInput();
		Person oldPerson = crud.update(person);
		System.out.println("Old person:");
		showPerson(oldPerson);
		System.out.println("New person:");
		showPerson(person);
	}

	private static void processDeleteOption() {
		System.out.println("Which person to delete?");
		
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();
		
		Person person = crud.read(id);
		if(person == null) {
			System.out.println("Invalid person ID");
			return;
		}
		
		crud.deleteById(id);
		
	}

	public static void main(String [] args) {
				
		Person person1 = new Person(incrementalId++,"Cesar",str2Date("31/10/1989"),false);
		Person person2 = new Person(incrementalId++,"Pitt",str2Date("31/10/1989"),true);
		Person person3 = new Person(incrementalId++,"Isabely",str2Date("31/10/1989"),true);
		
		crud.create(person1);
		crud.create(person2);
		crud.create(person3);
		
		showAllPersons();
		
		System.out.println("");
		
		int choice;
		do {
			choice = showOptions();
			switch(choice) {
			case 1:
				processCreateOption();
				break;
			case 2:
				processReadOption();
				break;
			case 3:
				processUpdateOption();
				break;
			case 4:
				processDeleteOption();
				break;
			}
		}while(choice != 0);
		
	}
}
