import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class pharmacy_counting {

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		
		// input & output file addresses
		String path = args[0];
		String outPath = args[1];

		// Create a scanner object
		Scanner scan = new Scanner(new File(path));
		// Skip the header
		scan.nextLine();
		
		//HashMap, where the K=prescriberID (last+first); V=cost in cent 
		//HashMap<String, Integer> prescriber_map = new HashMap<>();
		
		//HashMap, where the K=Drug name; V=prescriber_map 
		HashMap<String, HashMap<String, Long>> drug_prescriber_map = new HashMap<>();
		
		int i = 0;
		// while reading the whole csv file. creat a map for each drug, where the value is each drugs prescriber<id, cost>
		while(scan.hasNextLine()) {
			String l = scan.nextLine();
			
			// ref https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
			// handle the case like 1962514471,ABAD,JORGE,"PANCRELIPASE 5,000",669.89
			String [] info = l.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			String id = info[0];
			String prescriber_last_name = info[1].trim().toLowerCase();
			String prescriber_first_name = info[2].trim().toLowerCase();
			String drug_name = info[3].trim();
			String drug_cost = info[4].trim();
			
			long drug_cost_cent = (long) (Float.parseFloat(drug_cost) * 100);
			
			String prescriberID = prescriber_last_name + "," + prescriber_first_name;
			
			if (!drug_prescriber_map.containsKey(drug_name)) {
				
				//HashMap, where the K=prescriberID (last+first); V=cost in cent
				HashMap<String, Long> prescriber_map = new HashMap<>();
				prescriber_map.put(prescriberID, drug_cost_cent);
				
				drug_prescriber_map.put(drug_name, prescriber_map);
			} else {
				
				HashMap<String, Long> prescriber_map = drug_prescriber_map.get(drug_name);				
				if (!prescriber_map.containsKey(prescriberID)) {
					prescriber_map.put(prescriberID, drug_cost_cent);
				} else {
					long updatedTotalCost = prescriber_map.get(prescriberID) + drug_cost_cent;
					prescriber_map.put(prescriberID, updatedTotalCost);				
				}
				
				drug_prescriber_map.put(drug_name, prescriber_map);
			}
			
		}
		
		scan.close();
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("----------------------------------------------------");
		System.out.println(totalTime);
				
//		HashMap<String, Integer> drug_num_cost_map = new HashMap<>();
		// a tree map to aggregrate & sort total cost
		TreeMap<Long, ArrayList<String>> cost_drug_num_map = new TreeMap<>(Collections.reverseOrder());		
		for (String k:drug_prescriber_map.keySet()) {
			String info = get_num_prescriber_and_cost(drug_prescriber_map.get(k));
			
			String unique_num_prescriber = info.split(",")[0];
			String total_cost = info.split(",")[1];
			
			long total_cost_int = Long.parseLong(total_cost);
			String drug_num = k + "," + unique_num_prescriber;
			
			if(!cost_drug_num_map.containsKey(total_cost_int)) {
				ArrayList<String> drug_num_list = new ArrayList<>();
				drug_num_list.add(drug_num);
				cost_drug_num_map.put(total_cost_int, drug_num_list);
			} else {
				
				ArrayList<String> drug_num_list = cost_drug_num_map.get(total_cost_int);
				drug_num_list.add(drug_num);
				cost_drug_num_map.put(total_cost_int, drug_num_list);
			}
		}
		
		
		//output result from the sorted tree map
		FileWriter fw = new FileWriter(outPath);
		fw.write("drug_name,num_prescriber,total_cost\n");
		for (Long cost:cost_drug_num_map.keySet()) {
			ArrayList<String> drug_num_list = cost_drug_num_map.get(cost);
			
			// if there is a tie of total cost, order drug name
			Collections.sort(drug_num_list);
			for (String s:drug_num_list) {
				String result = "";
				if(cost%100 == 0) {
					result = s + "," + cost/100;
				} else {
					result = s + "," + cost/100.0;
				}
				
				fw.write(result + "\n");
				System.out.println(result);
			}		
		}
		fw.close();
		
		endTime   = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println(totalTime);
	}
	

	/**
	 * private method to get the unique number of prescriber and total cost
	 * @param HashMap of prescriber_map associated with each Drug Name
	 * @return
	 */
	private static String get_num_prescriber_and_cost(HashMap<String, Long> hmap) {
		int num_prescriber = hmap.size();
		long totalCost = 0;
		for (String key : hmap.keySet()) {
			long keyCost = hmap.get(key);
			totalCost = totalCost + keyCost;
		}
		
		String result = num_prescriber + "," + totalCost;
		return result;
	}
		
}
