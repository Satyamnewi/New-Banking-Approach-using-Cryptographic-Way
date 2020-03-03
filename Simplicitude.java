import java.awt.*;
import java.applet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class Simplicitude extends Canvas
{
	static String accEncryptfin="",cvvEncrypt="",yearmonth="",name="";
	public void paint(Graphics g)
    {     
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("CardPics/Slide3.jpg");
        g.drawImage(i,200,300,700,350,this);                              //570,50,700,350,this
        g.drawRect(200,300,700,350);
        g.setFont(new Font("Bahnschrift Light SemiCondensed", Font.PLAIN,40));
        g.drawString(accEncryptfin,340,500);
        i=t.getImage("CardPics/Slide2.jpg");
        g.drawImage(i,1000,300,700,350,this);                             //570,520,700,350,this
        g.drawRect(1000,300,700,350);                                     //570,520,700,350
        g.setFont(new Font("Bahnschrift Light SemiCondensed", Font.PLAIN,32));
        g.drawString(yearmonth,480,580);
        g.setFont(new Font("AlternateGothic2 BT", Font.PLAIN,32));
        g.drawString(name,300,635);
        g.setFont(new Font("AlternateGothic2 BT", Font.ITALIC,32));
        g.drawString(cvvEncrypt,1435,428);
    }
    public static void main(String args[])
     {
    	 	Scanner in = new Scanner(System.in);
    	 	System.out.println("Enter the name to be displayed on the card : ");
    	 	name=in.nextLine();
    	 	name = name.toUpperCase();
    	 	
    	 	DateFormat df = new SimpleDateFormat("HH:mm");   //24 hr format
    	 	//DateFormat df = new SimpleDateFormat("hh:mm");    //12 hr format
    	 	Calendar calobj = Calendar.getInstance();
    	 	String s2 = df.format(calobj.getTime());
    	 	
    	 	df = new SimpleDateFormat("dd/MM/yyyy");
    	 	calobj = Calendar.getInstance();
    	 	String todayDate = df.format(calobj.getTime());
    	 	
    	 	df = new SimpleDateFormat("MM/yyyy");
    	 	calobj = Calendar.getInstance();
    	 	yearmonth = df.format(calobj.getTime());
    	 	String month=yearmonth.substring(0,2);                
    		int year=Integer.parseInt(yearmonth.substring(5,7))+5;
    		yearmonth=month+" / "+Integer.toString(year);
    		
			/*-------------Dividing substring-----------------*/
			int prt1=Integer.parseInt(s2.substring(0,2));
			int prt2=Integer.parseInt(s2.substring(3,5));
			String timeStamp = prt1+""+prt2;
			if(timeStamp.length()==3)
				timeStamp="0"+timeStamp;
			/*------------------------------------------------*/
			
			/*---------Now generate random account number------*/
			Random rng = new Random();
			long first14 = (rng.nextLong() % 100000000000000L) + 5200000000000000L; //My Company code will be 52 means starting two digits will be 52
			System.out.println("Original Card Number : "+first14);
			String s4=Long.toString(first14);
			/*-------------------------------------------------*/
			
			/*---------Now generate random cvv number------*/
	        Random rand = new Random();
	        int randomNum = rand.nextInt((999 - 100) + 1) + 100;
	        System.out.println ("Original CVV Number : "+randomNum);
	        String s3=Integer.toString(randomNum);
			/*-------------------------------------------------*/
		    
	        /*----------------shifting cvv digits for Cards--------------*/
	        char a[]=new char[3];
	        a[0]=s3.charAt(0);
	        a[1]=s3.charAt(1);
	        a[2]=s3.charAt(2);
	        char t=' ';
	        for(int j=1;j<=prt2%3;j++)
	        {
	            t=a[0];
	            for(int i=0;i<a.length-1;i++)
	            {
	                a[i]=a[i+1];
	            }
	            a[2]=t;
	        }
	        cvvEncrypt = a[0]+""+a[1]+""+a[2];
	        /*-------------------------------------------------*/
		
	        /*----------------shifting account numbers digits for Cards--------------*/
	        t=' ';
	        String accEncrypt="";
	        char b[]=new char[16];
	        for(int i=0;i<16;i++)
	        	b[i]=s4.charAt(i);
	        for(int j=1;j<=prt1%16;j++)
	        {
	        	t=b[0];
	        	for(int i=0;i<b.length-1;i++)
	        	{
	        		b[i]=b[i+1];
	        	}
	        	b[15]=t;
	        }
	        for(int i=0;i<16;i++)
	        	accEncrypt = accEncrypt+b[i];
	        for(int i=0;i<accEncrypt.length();i++)
	        {
	        	t=accEncrypt.charAt(i);
	        	if(i==4||i==8||i==12)
	        		accEncryptfin = accEncryptfin+"   "+t;
	        	else
	        		accEncryptfin = accEncryptfin +t;
	        }
	    /*------------------------------------------------------------------------------------------*/
	    /*--------------------------------shifting cvv digits for Database--------------------------*/
	       
	        String cvvEncryptDB="";
	        a[0]=s3.charAt(0);
	        a[1]=s3.charAt(1);
	        a[2]=s3.charAt(2);
	        t=' ';
	        for(int j=1;j<=prt1;j++)
	        {
	        	t=a[0];
	        	for(int i=0;i<a.length-1;i++)
	        	{
	        		a[i]=a[i+1];
	        	}
	        	a[2]=t;
	        }
	        cvvEncryptDB = a[0]+""+a[1]+""+a[2];
	    /*-------------------------------------------------*/

	    /*----------------shifting account numbers digits for Database--------------*/
	    
	        t=' ';
	        String accEncryptDB="";
	        for(int i=0;i<16;i++)
	        	b[i]=s4.charAt(i);
	        for(int j=1;j<=prt2;j++)
	        {
	        	t=b[0];
	        	for(int i=0;i<b.length-1;i++)
	        	{
	        		b[i]=b[i+1];
	        	}
	        	b[15]=t;
	        }
	        for(int i=0;i<16;i++)
	        	accEncryptDB = accEncryptDB+b[i];
	/*---------------------------------------------------------------------------------------------------*/
	        
	/*-------------------------------------------Database Storage----------------------------------------*/
	        Simplicitude m=new Simplicitude();  
	        JFrame f=new JFrame();  
	        f.add(m);  
	        f.setSize(1920,1080);
	        f.setVisible(true);
	        String excelFilePath = "CardHolders.xlsx";
	        try 
	        {
	        	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	        	Workbook workbook = WorkbookFactory.create(inputStream);
	        	Sheet sheet1 = workbook.getSheetAt(0);
	        	Object[][] bookData = 
	        	{
                     {name,accEncryptDB,cvvEncryptDB,todayDate,timeStamp},
	        	};
	        	int rowCount = sheet1.getLastRowNum();
	        	for (Object[] aBook : bookData) 
	        	{
	        		Row row = sheet1.createRow(++rowCount); 
	        		int columnCount = 0;
	        		Cell cell = row.createCell(columnCount); 
	        		cell.setCellValue(rowCount);
	        		for (Object field : aBook) 
	        		{
	        			cell = row.createCell(++columnCount);
	        			if (field instanceof String) 
	        			{
	        				cell.setCellValue((String) field);
	        			} 
	        			else if (field instanceof Integer) 
	        			{
	        				cell.setCellValue((Integer) field);
	        			}
	        		}
	        	}
             inputStream.close();
             FileOutputStream outputStream = new FileOutputStream("CardHolders.xlsx");
             System.out.println("Account details added successfully !!");
             workbook.write(outputStream);
             workbook.close();
             outputStream.close();
         } 
         catch (Exception ex) 
	     {
             ex.printStackTrace();
         }
     }
}