package Projek.GoRide;
import java.util.HashMap;
public class Main 
{
    public static void main( String[] args )
    {

        HashMap<String, Person> personMap = new HashMap<String, Person>();
        
        personMap.put("STU001", new User(1, "Darla", 8954227, "MiraTeslaNungkin@gmail.com "));
        personMap.put("STU002", new Driver(2, "Rafi", 8113991 , "denikalkulus@gmail.com", "ER012D", "Motor", "Yamaha"));
        
        for(String personId : personMap.keySet()) {
        	Person person = personMap.get(personId);
            System.out.println("\n==========================================");
        	System.out.print(personId + " - ");
            
            System.out.print(" Role ");
        	System.out.println(person.getRole());
        	
            System.out.print("- Id: ");
        	System.out.println(person.getId());
        	
            System.out.print("- Nama: ");
        	System.out.println(person.getName());
        	
            System.out.print("- PhoneNumber: ");
        	System.out.println(person.getPhone());
            
            System.out.print("- Email: ");
        	System.out.println(person.getEmail());
            
            System.out.println(person.toString());
        }


    }
}