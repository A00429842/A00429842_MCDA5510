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
//        Total execution time – Total number of valid
//        rows – Total number of skipped rows
	public static int validNum = 0,
			skippedNum = 0;
	public static FileWriter writer = null;

    public String getRelativePath()
    {
	
	String relativePath = System.getProperty("user.dir");
	int lastIndex = relativePath.lastIndexOf("Assignment1")+"Assignment1".length();	
	relativePath = relativePath.substring(0, lastIndex);
	return relativePath;	
    }

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();



		try {
			if (list == null)
			{
			    throw new IOException("FILE NOT FOUND");
			}
			String relativePath = getRelativePath();
			String csvFile = relativePath + "/Output/result.csv";
			System.out.println(csvFile);
			
			String[] arr = {"First Name","Last Name","Street Number","Street","City","Province","Postal Code","Country","Phone Number","email Address"};
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
	
	            }
	            else if(f.getName().indexOf(".csv") > -1){
	                String str = f.getParent();
	                int index = str.lastIndexOf("Sample Data/")+"Sample Data/".length();
	                //System.out.println(str.substring(index));
	                
	                Reader in;
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
	            }
	           
	        }
	        
	        
	        
	   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("Main01").log(Level.SEVERE, e.getMessage());

			e.printStackTrace();
		}
        
    }

    public static void main(String[] args) {
	final long startTime = System.currentTimeMillis();
    	DirWalker fw = new DirWalker();
	String relativePath = fw.getRelativePath();
	String logPath = relativePath + "/logging.properties";
	System.setProperty("java.util.logging.config.file",
	        			logPath);
	System.out.println(relativePath + "/Sample Data");
        fw.walk(relativePath + "/Sample Data" );
		try {
			fw.writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("Main01").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			Logger.getLogger("Main01").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}

		final long endTime = System.currentTimeMillis();
		Logger.getLogger("Main01").log(Level.INFO,"Total execution time: " + (endTime - startTime) +" ms" + "\n" + "valid rows number:" + validNum + "\n skipped rows number:" + skippedNum);

		System.out.println("Total execution time: " + (endTime - startTime) +" ms" + "\n" + "valid rows number:" + validNum + "\n skipped rows number:" + skippedNum);
    }

}







