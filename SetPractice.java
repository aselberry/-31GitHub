import java.util.Scanner; 
import java.util.HashSet; 

class SetPractice{
    public static void main(String args[]){
        HashSet<String> names = new HashSet<String>();
        Scanner scanner = new Scanner(System.in);
        Scanner opt = new Scanner(System.in);
        String searchedName = "";
        System.out.println("Enter the name of the participant: ");
        for(int i=0; i<6; i++){
            
            names.add(scanner.nextLine().toLowerCase());
        }

        System.out.println("Do you want to chacek the presence of your name? (Y/N)?");
        String option = (opt.nextLine()).toLowerCase();
        System.out.println(option);
        if(option.equals("y")){
            System.out.println("Enter the name: ");
            searchedName = opt.nextLine().toLowerCase(); 
            if(names.contains(searchedName)){
                System.out.println("Yes, the person is in the list!");
            }else{
                System.out.println("No, he/she is not incuded in the list. ");
            }
        }else{
            System.out.println("It does not work");
        }
    }
}