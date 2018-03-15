package basicpackage;

import java.io.*;

public class foodTruck {

	public String getData(String id, String lastpurchasedate, String amount, String it, String in) {
		String fileContent = "Id: " + id + ". Ingredient Name: " + in + ". Ingredient Type: " + it + ". Amount: " + amount + ". Last Purchased Date: " + lastpurchasedate;
		String result = "";
		try {
			File f = new File("/users/vini/Desktop/Java.txt");
			if (f.createNewFile()){
				result = "File is created!";
		      }else{
		    	  result = "File is creation failed!";
		      }
		}
		catch(IOException e) {}
		
		String filename = "/users/vini/Desktop/Java.txt";
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(fileContent);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
		return result;
	}
}