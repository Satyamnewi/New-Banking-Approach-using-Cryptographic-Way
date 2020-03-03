import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class DateAndTime4
{
	String datetime()
     {
			 //DateFormat df = new SimpleDateFormat("HH:mm");   //24 hr format
			 DateFormat df = new SimpleDateFormat("hh:mm");     //12 hr format
			 Calendar calobj = Calendar.getInstance();
			 String s = df.format(calobj.getTime());
			 return s;
	 }
	void extract()
	{
		/*-------------Dividing substring-----------------*/
		String s2=datetime();
		int prt1=Integer.parseInt(s2.substring(0,2));            //0,4    
		int prt2=Integer.parseInt(s2.substring(3,5));            //5,7
		System.out.println("Time Stamps : "+prt1+" "+prt2);
		/*------------------------------------------------*/
		
		/*---------Now generate random account number------*/
		Random rng = new Random();
		long first14 = (rng.nextLong() % 100000000000000L) + 5200000000000000L; //My Company code will be 52 means starting two digits will be 52
		System.out.println("Account Number : "+first14);
		String s4=Long.toString(first14);
		/*-------------------------------------------------*/
		
		/*---------Now generate random cvv number------*/
        Random rand = new Random();
        int randomNum = rand.nextInt((999 - 100) + 1) + 100;
        System.out.println ("CVV Number : "+randomNum);
        String s3=Integer.toString(randomNum);
		/*-------------------------------------------------*/
	    
        /*----------------shifting cvv digits for Cards--------------*/
        char a[]=new char[3];
        a[0]=s3.charAt(0);
        a[1]=s3.charAt(1);
        a[2]=s3.charAt(2);
        char t=' ';
        for(int j=1;j<=prt2;j++)
        {
            t=a[0];
            for(int i=0;i<a.length-1;i++)
            {
                a[i]=a[i+1];
            }
            a[2]=t;
        }
        String cvvEncrypt = a[0]+""+a[1]+""+a[2];
        System.out.println ("Encrypted CVV number in Cards : "+cvvEncrypt);
    /*-------------------------------------------------*/
	
    /*----------------shifting account numbers digits for Cards--------------*/
    t=' ';
    String accEncrypt="";
    char b[]=new char[16];
    for(int i=0;i<16;i++)
    	b[i]=s4.charAt(i);
    for(int j=1;j<=prt1;j++)
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
    System.out.println ("Encrypted Account Number in Cards : "+accEncrypt);
    /*--------------------------------------------------------------------------*/
    /*----------------shifting cvv digits for Database--------------*/
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
    System.out.println ("Encrypted CVV number for Database : "+cvvEncryptDB);
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
System.out.println ("Encrypted Account Number for Database: "+accEncryptDB);
/*--------------------------------------------------------------------------*/
}
    public static void main(String args[])
     {
    	 DateAndTime4 ob = new DateAndTime4();
    	 ob.datetime();
    	 ob.extract();
     }
}