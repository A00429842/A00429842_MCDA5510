import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class DirWalker {
	public static int validNum = 0,
			skippedNum = 0;
	public static FileWriter writer = null;

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;
//        Total execution time – Total number of valid
//        rows – Total number of skipped rows
        String csvFile = "/Users/shaunyan/Documents/GitHub/A00429842_MCDA5510/Assignment1/Output/result.csv";
        File outputCsvFile = new File(csvFile);
        
        String[] arr = {"First Name","Last Name","Street Number","Street","City","Province","Postal Code","Country","Phone Number","email Address"};


		try {
			if(writer == null)
			{
			
				writer = new FileWriter(csvFile);

			
		
		        for (int i=0; i < arr.length; i++)
				{
					writer.append(arr[i]);    
			        writer.append(',');
				}
		        writer.append("date");
		        writer.append("\n");

			}
	        
	
	        for ( File f : list ) {
	            if ( f.isDirectory() ) {
	                walk( f.getAbsolutePath() );
	                
	                //System.out.println( "Dir:" + f.getAbsoluteFile() );
	
	            }
	            else if(f.getName().indexOf(".csv") > -1){
	                String str = f.getParent();
	                int index = str.lastIndexOf("Sample Data/")+"Sample Data/".length();
	                //System.out.println(str.substring(index));
	                
	                Reader in;
	        		try {
	        			//System.out.println(f.getAbsoluteFile());
	        			in = new FileReader(f.getAbsoluteFile());
	        			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
	        			
	        			for (CSVRecord record : records) {
	        				break;
	        			}		
	        			
	        			
	        	        for (CSVRecord record : records) {
	        	        	int i = 0,
	        	        		condition = 1;
	        	        	String rowData = "";
	        	        	int len = record.size();
	        				if(len == arr.length)
	        				{
	        					for (i = 0; i < arr.length; i++)
		         				{
	        					
	        						if(record.get(i).length() == 0)
	        						{
	        							condition = 0;
	        							break;
	        						}
	        						rowData += record.get(i)+",";		
		         				}
	        					if(condition == 1)
	        					{
	        						writer.append(rowData);
	        						writer.append(str.substring(index));
	        						
			         		        writer.append('\n');
	                 		        validNum++;


	        					}else {
	        						skippedNum++;
	        					}

	        				}else {
        						skippedNum++;

	        				}
	        				
	        				
	        			    //System.out.println("Data: "+ id+" : "+refID);
	        			}		
	        		} catch ( IOException e) {
	        			e.printStackTrace();
	        		}
	            }
	           
	        }
	        
	        
	        
	   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		System.setProperty("java.util.logging.config.file",
	              "./logging.properties");
    	DirWalker fw = new DirWalker();
        fw.walk("/Users/shaunyan/Documents/GitHub/A00429842_MCDA5510/Assignment1/Sample Data" );
		try {
			fw.writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final long endTime = System.currentTimeMillis();
		Logger.getLogger("Main01").log(Level.INFO,"Total execution time: " + (endTime - startTime) +" ms" + "\n" + "valid rows number:" + validNum + "\n skipped rows number:" + skippedNum);

		System.out.println("Total execution time: " + (endTime - startTime) +" ms" + "\n" + "valid rows number:" + validNum + "\n skipped rows number:" + skippedNum);
    }

}






