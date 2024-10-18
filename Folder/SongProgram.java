/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package songprogram;
import java.util.ArrayList;
import java.util.Scanner;


public class SongProgram  {
    private ArrayList songs;
    private Song song;
    private Scanner input;
    
    //defining constructor here 
    public SongProgram(){
        songs =new ArrayList();
        song=new Song();
        input=new Scanner (System.in);
    }

    public static void main(String[]args){
        SongProgram program= new SongProgram();
        //It calls run method
        program.run();  
    }
    public void run(){
        int choice;
        do{
        displayMenu();
        choice=input.nextInt();
        input.nextLine();
        processChoice(choice);
    }
        while(choice!=4);
        
    }
    
    
    public int displayMenu(){
      // declaring variable to hold choice 
         int choice=0;
        System.out.println("1.Display all songs");
        System.out.println("2.Gets song By its Id");
        System.out.println("3.Updates song By its ID");
        System.out.println("4.Deletes songs By its ID");
        System.out.println("5.Exit");
        System.out.println(" Enter your Choice:");
        System.out.println("-----------------");
        System.out.println("\n");
       
        return choice;
        
    }
    public void processChoice(int choice){
        if (choice==1){
            displayAllSongs();
        }
        else if(choice==2){
            getSongById();
        }
        else if(choice==3){
            updateSongById();
        }
        else if(choice==4){
            deleteSongById();
        }
        else if(choice==5){
            System.out.println("Exiting program  brooooo");
            System.out.println("----------------");
        
    }
        else{
            System.out.println("This is a invalid choice buddy!!!");
            System.out.println("================");
        }
    
    }
    public void displayAllSongs(){
      SongDAO dblayer= new SongDAO();
      ArrayList songs=dblayer.getSongs();
      for(int i=0;i<songs.size();i++){
        Song s=(Song)songs.get(i);
          System.out.println("ID:"+s.getId());
          System.out.println("Title:"+s.getTitile());
          System.out.println("Artist:"+s.getArtist());
          System.out.println("Release Year:"+s.getReleaseYear());
          System.out.println("Genre ID:"+s.getGenreId());
          System.out.println("Album:"+s.getAlbum());
          System.out.println("---------------");
          System.out.println("\n");
      }        
      }
      
    
       
    public void getSongById(){
        //Gives the user for the song ID
        System.out.println("Enter songs By its ID:");
        int id =input.nextInt();
        input.nextLine();//It gets newline left  from nextInt().....
        //Just creating a instance of the Song DAO class 
        SongDAO songDAO=new SongDAO();
        //Brings the songs with given ID
        Song song=songDAO.getSongsByID(id);
        //Just making sure if a song was returned and displays its details ,otherwise we gonna indicate not found..
        if(song!=null){
            System.out.println("Eventually song found::");
            System.out.println("ID:"+song.getId());
            System.out.println("Titile:"+song.getTitile());
            System.out.println("Artist:"+song.getArtist());
            System.out.println("Release Year:"+song.getReleaseYear());
            System.out.println("Genre ID:"+song.getGenreId());
            System.out.println("Album:"+song.getAlbum());
            System.out.println("\n");
            
        }
        else{
            System.out.println("Sorry We did not find that song with this ID: "+" "+id);
            
            
        }              
        
    }
    public void updateSongById() {
        SongDAO updateSong = new SongDAO();
         input = new Scanner(System.in); 

        System.out.println("Enter the Song ID Number you want to Update:");
        int songId = input.nextInt();
        Song s = updateSong.getSongsByID(songId);

        if (s != null && s.getId() == songId) {
            System.out.println("ID:" + s.getId());
            System.out.println("Title:" + s.getTitile()); 
            System.out.println("Artist:" + s.getArtist());
            System.out.println("Release Year:" + s.getReleaseYear());
            System.out.println("Genre Id:" + s.getGenreId());
            System.out.println("Album:" + s.getAlbum());
            System.out.println("===================");

            int choice = 0;
            while (choice != 6) {
                System.out.println("1.Update Title");
                System.out.println("2.Update Artist");
                System.out.println("3.Update Release Year");
                System.out.println("4.Update Genre Id");
                System.out.println("5.Update Album");
                System.out.println("6. Going to back menu");
                System.out.println("Enter Your choice");
                System.out.println("===================");
                choice = input.nextInt();

                if (choice == 1) {
                    System.out.println("Enter the New Title:");
                    s.setTitile(input.next()); // 
                    System.out.println("Updated new Song Title");
                } else if (choice == 2) {
                    System.out.println("Enter the New Artist:");
                    s.setArtist(input.next());
                    System.out.println("Updated new Song Artist");
                } else if (choice == 3) {
                    System.out.println("Enter the New Release Year:");
                    s.setReleaseYear(input.nextInt());
                    System.out.println("Updated new Song Release Year");
                } else if (choice == 4) {
                    System.out.println("Enter the New Genre Id:");
                    s.setGenreId(input.nextInt());
                    System.out.println("Updated new Song Genre Id");
                } else if (choice == 5) {
                    System.out.println("Enter the New Album:");
                    s.setAlbum(input.next());
                    System.out.println("Updated new Song Album");
                } else if (choice == 6) {
                    System.out.println("Thank you!!!");
                }
            }
                   updateSong.updateSongById(s); 
        } else {
            System.out.println(" Sorry we did not find Song ID..");
        }
    }
    public void deleteSongById(){
      System.out.println("Enter the song ID you want to Delete::");
        int id=input.nextInt();
        input.nextLine();
        SongDAO suzal= new SongDAO();
        Song song=suzal.getSongsByID(id);
        if(song!=null){
            suzal.deleteSong(id);
            System.out.println("Song Deleted with Song ID:"+id);
            
        }
        else{
            String sorry="Sorry";
            System.out.println("There was error while deleting a song."+sorry);
            
        }
        
    }       
        
    
}   
         
        
  

