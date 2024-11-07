package petadoption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class adoptionrecord {
    
public static void arrecord() {
        
        
           Scanner sc = new Scanner(System.in);
           String response;
           do{
        
         System.out.println("\nAdoption Record");
            System.out.println("1. Add Record");
            System.out.println("2. View Record");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
       
     System.out.print("Enter action: ");
            int action = sc.nextInt();
            adoptionrecord ar = new adoptionrecord ();
            switch(action){
                
                case 1:
                    ar.addarecord();
                    ar.viewarecord();
                    break;
                    
                case 2:
                    
                    break;
             
                    
                case 3:
                    
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
                   
           
            }
             System.out.println("Do you want to continue? (yes/no): ");
             response = sc.next();
                    
           }while(response.equalsIgnoreCase("yes"));
           System.out.println("Thank You! ");
    }
    public Object[] arid;

    public void addarecord(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        pet p = new pet();
        p.viewpet();
        
        System.out.println("Enter the ID of the pet: ");
        int pid = sc.nextInt();
        
        String sql = "SELECT p_id FROM tbl_pet WHERE p_id = ?";  
            while(conf.getSingleValue(sql, pid) == 0) {
            System.out.print("Selected ID doesn't exist! ");
        pid = sc.nextInt();
        
        
   }
            
            
        fosterparent fp = new fosterparent();
        fp.viewfparent();
        
        System.out.println("Enter the ID of the fparent: ");
        int fpid = sc.nextInt();
        
        String psql = "SELECT fp_id FROM tbl_fosterparent WHERE fp_id = ?";  
            while(conf.getSingleValue(psql, fpid) == 0) {
            System.out.print("Selected ID doesn't exist! ");
            fpid = sc.nextInt();
            
     }  
            
            
            LocalDate currdate = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = currdate.format(format);
            
            
            String status = "ADOPTED"
                    + "WAITING FOR APPROVAL"
                    + "AVAILABLE";
            
            
            String adoprionrecordqry = "INSERT INTO tbl_adoptionrecord(p_id, fp_id, a_date, a_status)"
                 + "VALUES(?, ?, ?, ?)";
            
            
            conf.addRecord(adoprionrecordqry, pid, fpid, date, status);
                                          
            
   }
    
    public void viewarecord(){
        String qry = "SELECT \"ar_id\", \"name\", \"lname\", \"breed\", \"address\", \"contact\", \"a_date\", \"a_status\" FROM tbl_adoptionrecord\n" 
            + "LEFT JOIN tbl_pet ON tbl_pet.p_id = tbl_adoptionrecord.p_id "
            + "LEFT JOIN tbl_fosterparent ON tbl_fosterparent.fp_id = tbl_adoptionrecord.fp_id";
               
                
        String[] hrds = {"ar_id", "Pet", "fparent", "breed", "address", "contact", "date", "status"};
        String[] clm = {"ar_id", "name", "lname", "breed", "address", "contact", "a_date", "a_status"};       
        config conf = new config();   
        conf.viewRecords(qry, hrds, clm);
       
    }

   
        }