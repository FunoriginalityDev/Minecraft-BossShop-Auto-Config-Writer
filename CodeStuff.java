
import java.io.*;
import static java.lang.System.*;
import java.util.Scanner; 
import java.lang.Math;
public class CodeStuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arrayLength; 
	    String txtN; 
	    String OSN; 
	    String DOSN; 
	    
	    Scanner scan = new Scanner(System.in); 
	    System.out.println("Hello! This is an expieremental, probably broken attempt at creating a config writer for the Minecraft BossShop Plugin!");
	    System.out.println("\nWhat do you want the exported TXT to be called?");
	    txtN = scan.nextLine(); 
	    System.out.println("What do you want this set of shops to be called?");
	    OSN = scan.nextLine(); 
	    System.out.println("What do you want this set of shops to be displayed as (including color codes)");
	    DOSN = scan.nextLine(); 
	    System.out.println("\nLet's get this started, how many items would you like to add to this shop?");
	    arrayLength = scan.nextInt();
	    //Setting arrays (God I wish I had just used ArrayLists)

	    String[] SN = new String[arrayLength];
	    String[] DSN = new String[arrayLength]; 
	    int[] SA1 = new int[arrayLength]; 
	    int[] SA2 = new int[arrayLength]; 
	    String[] IIDN = new String[arrayLength];
	    double[] SB = new double[arrayLength]; 
	    double[] SS = new double[arrayLength]; 

	    for(int i = 0; i <= SN.length-1; i++) //I'm not traversing this correctly...
	    {
	      Scanner test = new Scanner(System.in); 
	      System.out.println("What is the #" + (i + 1) + " shop name? (For the config)");
	      SN[i] = test.nextLine();  
	      System.out.println("Now the Displayname of the item (with color codes)"); 
	      DSN[i] = test.nextLine(); 
	      System.out.println("Now input your first, then second click amounts (left and right click)");
	      SA1[i] = test.nextInt();
	      SA2[i] = test.nextInt(); 
	      System.out.println("Now the name ID of the item (EX REPEATER)");
	      IIDN[i] = test.next(); 
	      System.out.println("Finally, plug in your Buy, then sell price");
	      SB[i] = scan.nextDouble(); 
	      SS[i] = test.nextDouble(); 
	    }
	    String Config = Shop(OSN, DOSN, SN, DSN, SA1, SA2, IIDN, SB, SS); 
	    System.out.println(Config); 
	    
	    //File Writing
	    try {
	    	 File myObj = new File("C:\\Users\\obx31\\Downloads\\" + txtN + ".yml");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
	        FileWriter myWriter = new FileWriter("C:\\Users\\obx31\\Downloads\\" + txtN + ".yml");
	        myWriter.write(Config);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	     
	  }

	    public static String Shop(String OSN, String DOSN, String[] SN, String[] DSN, int[] SA1, int[] SA2, String[] IIDN, double[] SB, double[] SS)
	    {
	      //SN = Shop names
	      //DSN = Displayed Shop Name
	      //SA1 = Shop Amount 1
	      //SA2 = Shop Amount 2
	      //IIDN = Item ID Name
	      //SB = Shop Buy (Per Singular Item)
	      //SS = Shop sell (Per singular Item)
	      String ConfigR = " "; 
	      String ConfigTemp = "ShopName: ";
	      ConfigTemp = "ShopName: " + OSN + "\nDisplayName: '" + DOSN + "'\nsigns:\n  text: '[BuyShop]'\n  NeedPermissionToCreateSign: true\nshop:"; 
	      for(int i = 0; i <= SN.length - 1; i++)
	      {
	        ConfigTemp = ConfigTemp + "\n  " + SN[i] + ":\n    MenuItem:\n    - 'lore1: &eClick &2left &eto buy &c%reward% &efor &c%price%&e.'\n    - 'lore2: &eClick &2right &eto buy &c%reward_right% &efor &c%price_right%&e.'\n    - 'lore3: &eClick &2middle mouse button &eto sell &c%price_middle% &efor &c%reward_middle%&e.'"; //Beginning to Lore

	        ConfigTemp = ConfigTemp + "\n    - name:" + DSN[i] + "\n    - amount:" + SA1[i] + "\n    - type:" + IIDN[i] + "\n    RewardType: ITEM\n    Reward:\n    - - amount:" + SA1[i] + "\n      - type:" + IIDN[i]; //name to Reward1

	        ConfigTemp = ConfigTemp + "\n    PriceType: MONEY\n    Price: " + (SB[i] * SA1[i]) + "\n    Message: '&eYou bought %reward%! Money left: &c%left%'\n    RewardType_right: ITEM\n    Reward_right:\n    - - amount:" + SA2[i] + "\n      - type:" + IIDN[i] + "\n    PriceType_right: MONEY\n    Price_right: " + (SB[i] * SA2[i]) + "\n    Message_right: '&eYou bought %reward%! Money left: &c%left%'\n    RewardType_middle: MONEY\n    Reward_middle: " + SS[i];//PriceType1 to SellAmt

	        ConfigTemp = ConfigTemp + "\n    PriceType_middle: ITEMALL\n    Price_middle:\n    - type:" + IIDN[i] + "\n    - amount:1\n    Message_middle: '&eYou just sold %price_middle%! Your current balance is %balance%'\n    ExtraPermission: ''\n    InventoryLocation: 0";

	        ConfigR = ConfigR + "\n" + ConfigTemp; 
	        ConfigTemp = ""; 
	      }
	      ConfigR = ConfigR + "\n  back:\n    MenuItem:\n    - lore:&8Back to Menu\n    - name:&cBack\n    - amount:1\n    - type:ARROW\n    RewardType: SHOP\n    Reward: menu\n    PriceType: NOTHING\n    Message: '&6Returning to menu...'\n    ExtraPermission: ''\n    InventoryLocation: 27";
	      return ConfigR; 
	    }
	}
