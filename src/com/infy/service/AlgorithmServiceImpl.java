package com.infy.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.Algorithm2DAO;
import com.infy.dao.AlgorithmDAO;
import com.infy.model.Assets;
import com.infy.model.Clients;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Service("algoService")
@Transactional(readOnly = true)
public class AlgorithmServiceImpl implements AlgorithmService{
       @Autowired
       private AlgorithmDAO algodao;
       @Autowired
       private Algorithm2DAO algo2DAO;
       public Double futureValue(Integer time,Float rate,Float compound)
       {
                   return ((Math.pow((1+(rate/compound)),(compound*time)))-1)/(rate/compound);//      PMT × {[(1 + r/n)(nt) - 1] / (r/n)}
                   
       }
       @Override
       public Double individualCar(Clients client,Integer time,Integer price,Float percent,Integer resale) throws Exception {
              
              Integer i2=algodao.getNetAssets(client);
              Integer i=algodao.getAnnualSavings(client)*time;
              
              System.out.println("net worth"+i2);
              System.out.println("net saving"+i);
              Integer netWorth=i+i2;
              Double arr;
              
              Integer netWorthPercent=Math.round((netWorth*percent)/100);
              System.out.println("nwp"+netWorthPercent);
              Double inflatedPrice = (1.0489*price)-resale; //inflation rate for vechicles in india is 4.89% for 2019
              
              if(inflatedPrice<=netWorthPercent)
                     return null;
              
              else
              {
                     if(netWorth>0)
                           {
                               inflatedPrice=inflatedPrice-netWorthPercent;
                           }
                     
                     double equityPrinciple = 0.4*inflatedPrice/futureValue(time, 0.2f, 12.0f);
                     double debtPrinciple = 0.6*inflatedPrice/futureValue(time, 0.12f, 12.0f);
                     arr= equityPrinciple+debtPrinciple;
              }
              return arr;
       }
     @Override
     public Double individualHome(Clients client, Integer time, Integer price, Float percent, Integer resale) throws Exception {
            
                 Integer i2=algodao.getNetAssets(client);
         Integer i=algodao.getAnnualSavings(client)*time;
         
         System.out.println("net worth"+i2);
         System.out.println("net saving"+i);
         Integer netWorth=i+i2;
         Double arr;
         
         Integer netWorthPercent=Math.round((netWorth*percent)/100);
         System.out.println("nwp"+netWorthPercent);
         Double inflatedPrice = (1.057*price)-resale; //inflation rate predicted for properties in india is 5.70% for future
         
         if(inflatedPrice<=netWorthPercent)
                return null;
         
         else
         {
                if(netWorth>0)
                      {
                               inflatedPrice=inflatedPrice-netWorthPercent;
                      }
                
                double equityPrinciple = 0.4*inflatedPrice/futureValue(time, 0.2f, 12.0f);
                double debtPrinciple = 0.6*inflatedPrice/futureValue(time, 0.12f, 12.0f);
                arr= equityPrinciple+debtPrinciple;
         }
         return arr;  
     }
     @Override
    public Double individualEdu(Clients client, Integer age, Integer fee, Float percent) throws Exception {
            

                 Integer i2=algodao.getNetAssets(client),time=18-age;
         Integer i=algodao.getAnnualSavings(client)*time;
         
         System.out.println("net worth"+i2);
         System.out.println("net saving"+i);
         Integer netWorth=i+i2;
         Double arr;
         
         Integer netWorthPercent=Math.round((netWorth*percent)/100);
         System.out.println("nwp"+netWorthPercent);
         Double inflatedPrice = fee*(Math.pow((1+(0.05/time)),time))*4; //inflation rate predicted for college fees in india is 5.00%         
         if(inflatedPrice<=netWorthPercent)
                return null;
         
         else
         {
                if(netWorth>0)
                      {
                               inflatedPrice=inflatedPrice-netWorthPercent;
                      }
                
                double equityPrinciple = 0.4*inflatedPrice/futureValue(time, 0.2f, 12.0f);
                double debtPrinciple = 0.6*inflatedPrice/futureValue(time, 0.12f, 12.0f);
                arr= equityPrinciple+debtPrinciple;
         }
         return arr;  
     }
     @Override
     public Double individualRetire(Clients client) throws Exception {
            

                 
         Integer i[]=algodao.getRetireSavings(client);
         System.out.println(i[0]);
         Double arr;
         Integer age=client.getCdate().until(LocalDate.now()).getYears();
         Integer time=60-age;
         Integer retirementFund = i[0]*20; //inflation rate predicted for college fees in india is 5.00%         
         System.out.println(age);
                
                double equityPrinciple = 0.4*retirementFund/futureValue(time, 0.2f, 12.0f);
                double debtPrinciple = 0.6*retirementFund/futureValue(time, 0.12f, 12.0f);
                arr= equityPrinciple+debtPrinciple;
         
         return arr;  
     }
     

                static HashMap<String, Integer> nLargest(HashMap<String, Integer> map, int n) { //map and n largest values to search for
                
                    Integer value;
                    ArrayList<String> keys = new ArrayList<>(n); //to store keys of the n largest values
                    ArrayList<Integer> values = new ArrayList<>(n); //to store n largest values (same index as keys)
                    int index;
                    for (String key : map.keySet()) { //iterate on all the keys (i.e. on all the values)
                        value = map.get(key); //get the corresponding value
                        index = keys.size() - 1; //initialize to search the right place to insert (in a sorted order) current value within the n largest values
                        while (index >= 0 && value > values.get(index)) { //we traverse the array of largest values from smallest to biggest
                            index--; //until we found the right place to insert the current value
                        }
                        index = index + 1; //adapt the index (come back by one)
                        values.add(index, value); //insert the current value in the right place
                        keys.add(index, key); //and also the corresponding key
                        if (values.size() > n) { //if we have already found enough number of largest values
                            values.remove(n); //we remove the last largest value (i.e. the smallest within the largest)
                            keys.remove(n); //actually we store at most n+1 largest values and therefore we can discard just the last one (smallest)
                        }
                    }
                    HashMap<String, Integer> result = new HashMap<>(values.size());
                    for (int i = 0; i < values.size(); i++) { //copy keys and value into an HashMap
                        result.put(keys.get(i), values.get(i));
                    }
                    return result;
                }

                private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
                
                @Override
                public String[] individual(Clients client, Integer time, Integer family,Integer dependents,Integer age) throws Exception {
                                
        Integer cAge=client.getCdate().until(LocalDate.now()).getYears();
        
        System.out.println(LocalDate.now());
        
        Assets arr= algo2DAO.individualAssets(client.getUserId());
                                
                                Integer home=0,edu=0,medical=0,gold=0,mutual=0,car=0,insurence=0,retirement=0;
                                
                                Integer arr2=algo2DAO.individualIncome(client.getUserId());
                                
                                if(arr.getMedicalInsurance().equals("false"))
                                                medical+=10;
                                if(arr.getCashOfLifeInsurance()<(dependents*arr2*5))
                                                insurence+=8;
                                if(cAge<30)
                                {	
                                	if(time>5)
                                		{
                                			mutual+=7;
                                			gold+=4;
                                		}
                                	else
                                		gold+=4;
                                }
                                else if(cAge<45) 
                                {              
	                                if(time>5)
	                                {
	                                    if(arr.getProperties()<(arr2*4))
	                                    	home+=3;
	                                    if(family>3){
                                            home+=3;
                            }
	                                    if(arr.getNoOfVehicles()<2)
	                                    {
	                                                    car+=4;
	                                    }
	                                    home+=4;
	                                    if(arr.getRetirement()<(arr2*15))
	                                    {
	                                                    retirement+=4;
	                                    }
	                                    if(age>0)
	                                                    edu+=6;
	                                    mutual+=5;
	                                }
	                                else
	                                {
	                                	if(arr.getRetirement()<(arr2*15))
	                                	{
	                                		retirement+=4;
	                                	}
	                                    gold+=4;
	                                }
                                }
                                else if(cAge<60)
                                {System.out.println("aagya");
                                                retirement+=6;
                                                if(arr.getRetirement()<(arr2*15))
                                                {
                                                                retirement+=4;
                                                }
                                }
                                HashMap<String,Integer> mp=new HashMap<String,Integer>();

                                mp.put("Home", home);
                                mp.put("Child Education",edu );
                                mp.put("Medical Insurance", medical);
                                mp.put("Gold", gold);
                                mp.put("Retirement Planning", retirement);
                                mp.put("Mutual Funds & Equity", mutual);
                                mp.put("Car", car);
                                mp.put("Life Insurance", insurence);
                                
                                mp=nLargest(mp, 3);
                                String[] str=new String[3];
                                int i=0;
                                Map<String, Integer> sortedMapDesc = sortByComparator(mp, false);
                                for(String s:sortedMapDesc.keySet())    
                                                 {
                                                               str[i]=s;
                                                               i++;
                                                }
                                return str;
                }
                @Override
                public String[] corporate(Clients client, Integer time,Integer noOfEmployees, boolean medical)throws Exception 
                {
                                Integer cAge=client.getCdate().until(LocalDate.now()).getYears();
                                Integer recession=0,
                                                                unwantedIncidents=0,
                                                                ventures=0,
                                                                shares=0,
                                                                empMedical=0,
                                                                recruitment=0,
                                                                ad=0,
                                                                expansion=0;
                                Integer[] arr=algodao.getNetProfit(client.getUserId());
                                System.out.println(arr[0]+"           "+noOfEmployees);
                                Integer profitPerEmployee=arr[0]/noOfEmployees;
                                
                                if(!medical)
                                                empMedical+=6;
                                if(time<=10)
                                {              
                                                if(cAge>10)
                                                	ad+=4;
                                                else
                                                    ad+=6;
                                                shares+=4;
                                                unwantedIncidents+=4;
                                                if(profitPerEmployee>200000){
                                                    ventures+=5;
                                                    recruitment+=4;
                                                }
                                                else if(profitPerEmployee>100000){
                                                    recruitment+=4;
                                                }
                                }
                                else 
                                {
                                                if(cAge>10)
	                                                {
	                                                if(arr[1]<(arr[0]*1.5)&&profitPerEmployee>150000)
	                                                                expansion+=10;
	                                                recession+=2;
	                                                empMedical+=2;
	                                                }
                                                unwantedIncidents+=6;
                                                shares+=2;
                                                recession+=4;
                                                if(profitPerEmployee>200000)
                                                {
                                                                ventures+=6;
                                                                recruitment+=4;
                                                                recession+=2;
                                                }
                                                else if(profitPerEmployee>100000)
                                                {
                                                                recruitment+=5;
                                                                recession+=1;
                                                }                              
                                }
                                
                                HashMap<String,Integer> mp=new HashMap<String,Integer>();

                                mp.put("Recession Period", recession);
                                mp.put("Insurance for Unwanted Incidents",unwantedIncidents);
                                mp.put("New Ventures", ventures);
                                mp.put("Stock Market", shares);
                                mp.put("Medical Insurance for Employees", empMedical);
                                mp.put("Fresh Recruitment", recruitment);
                                mp.put("Advertisements & Marketing", ad);
                                mp.put("Business Expansion", expansion);
                                mp=nLargest(mp,3);
                                
                                 String[] str=new String[3];
                                int i=0;
                                Map<String, Integer> sortedMapDesc = sortByComparator(mp, false);
                                for(String s:sortedMapDesc.keySet())    
                                                 {             
                                                               str[i]=s;
                                                               i++;
                                                }
                                return str;
                }
                @Override
                public String[] medium(Clients client, Integer time, Integer noOfEmployees,boolean medical) throws Exception {
                                Integer cAge=client.getCdate().until(LocalDate.now()).getYears();
                                Integer recession=0,
                                                                unwantedIncidents=0,
                                                                ventures=0,
                                                                shares=0,
                                                                empMedical=0,
                                                                recruitment=0,
                                                                ad=0,
                                                                expansion=0;
                                Integer[] arr=algodao.getNetProfit(client.getUserId());
                                System.out.println(arr[0]+"           "+noOfEmployees);
                                Integer profitPerEmployee=arr[0]/noOfEmployees;
                                
                                if(!medical)
                                                empMedical+=6;
                                if(time<=10)
                                {              
                                                if(cAge>10)
                                                                                ad+=4;
                                                else
                                                                ad+=6;
                                                shares+=4;
                                                unwantedIncidents+=5;
                                                if(profitPerEmployee>20000){
                                                                ventures+=5;
                                                                recruitment+=4;
                                                }
                                                else if(profitPerEmployee>10000){
                                                                recruitment+=4;
                                                }
                                }
                                else 
                                {
                                                if(cAge>10)
                                                                {
                                                                if(arr[1]<(arr[0]*2.5)&&profitPerEmployee>15000)
                                                                                expansion+=10;
                                                                recession+=2;
                                                                empMedical+=2;
                                                                }
                                                unwantedIncidents+=6;
                                                shares+=2;
                                                recession+=4;
                                                if(profitPerEmployee>20000)
                                                {
                                                                ventures+=6;
                                                                recruitment+=4;
                                                                recession+=2;
                                                }
                                                else if(profitPerEmployee>10000)
                                                {
                                                                recruitment+=5;
                                                                recession+=1;
                                                }                              
                                }
                                
                                HashMap<String,Integer> mp=new HashMap<String,Integer>();

                                mp.put("Recession Period", recession);
                                mp.put("Insurance for Unwanted Incidents",unwantedIncidents);
                                mp.put("New Ventures", ventures);
                                mp.put("Stock Market", shares);
                                mp.put("Medical Insurance for Employees", empMedical);
                                mp.put("Fresh Recruitment", recruitment);
                                mp.put("Advertisements & Marketing", ad);
                                mp.put("Business Expansion", expansion);
                                mp=nLargest(mp,3);
                                
                                 String[] str=new String[3];
                                int i=0;
                                Map<String, Integer> sortedMapDesc = sortByComparator(mp, false);
                                for(String s:sortedMapDesc.keySet())    
                                                 {             
                                                               str[i]=s;
                                                               i++;
                                                }
                                return str;
                }
                @Override
                public void pdfGenration(String[] report,Clients client) throws Exception {
                                
                                HashMap<String,String> map= new HashMap<String,String>();
                                map.put("Home", "According to our Algorithm and based on the details provided by you, we suggest you to invest in Real Estate. Your age and financial status is apt for this investment. The market trends also show that it is the right time to invest in Real Estate. To plan for your investment, you can use our dedicated investment planner. ");
                                map.put("Child Education","We have observed that there is high rate of inflation in the education sector in the last decade. So it is the perfect time for you to start planning for your child’s future. You can figure out the expenses and the savings required (per month) for the same using our Investment planner for education." );
                                map.put("Medical Insurance", "Your Data shows that currently you have not opted for any medical insurance. We highly recommend that you get the medical insurance and policies done for the wellbeing of yourself and your family in case of any medical emergencies. There are a wide range of good policies to choose from as per your needs. Contact your bank for further details regarding the policy.");
                                map.put("Retirement Planning", "Planning for your Retirement is very important in order to keep your life comfortable even after you stop earning and its very important that you start early. As per our analysis it’s a perfect time for you to start with your retirement planning. The amount that you have currently saved for your retirement is inadequate. We suggest you to dedicate a retirement account if it’s not already there and save for the same.");
                                map.put("Mutual Funds & Equity", "Since you are ready to invest for long term its highly recommended that you go for mutual funds. Your financial status shows that you are ready to take the risks involved in the market. So in order to achieve financial success quickly and climb up the ladder. Mutual funds seem to be a great option for you.");
                                map.put("Car", "As per our analysis, you financial planning seems to be on the right track. Owning a good vehicle is much needed in our fast paced lives. So we suggest you to purchase/upgrade your vehicle. Our Investment planner has some really good options which will help you with the savings.");
                                map.put("Life Insurance", "Life Insurance is a financial cover for a contingency linked with human life, like death, disability, accident, retirement etc. Human life is subject to risks of death and disability due to natural and accidental causes. When human life is lost or a person is disabled permanently or temporarily, there is loss of income to the household. Though human life cannot be valued, a monetary sum could be determined based on the loss of income in future years. The funds that you have dedicated for life insurance seem to be inadequate. Hence we suggest you to browse policies and invest in the same.");
                                map.put("Gold", "Gold has historically been an excellent hedge against inflation, because its price tends to rise when the cost of living increases. Over the past 50 years’ investors have seen gold prices soar and the stock market plunge during high-inflation years. According to the time frame of your investment we recommend you to invest in gold which will be very helpful for you in future and it is also less risky.");
                                map.put("Recession Period", "According to our analysis and calculations you are in a good position to invest for the recession period. Leaders may understandably be reluctant to take major actions until they see clear evidence that they are affected by economic headwinds. However, we found that the companies that proactively recognized the threat—by discussing the possibility of a downturn in their earnings calls before the economic recession officially began in Dec. 2007—achieved 6 percentage points better Total Shareholder Return (defined as total returns to investors including capital gains and dividends) in the downturn than companies that did not address the challenge early.");
                                map.put("Insurance for Unwanted Incidents","If a hacker breached your network today, what would you do? What if a company employee unwittingly infected your systems with ransomware? What if attackers defaced your website or launched a distributed denial of service (DDoS) attack? And What if there is some physical damages in infrastructure in your company? All these situations are unfortunately quite common for companies like yours. As you are doing good in savings hence its best to be prepared for unwanted incidents from now itself.");
                                map.put("New Ventures", "Data shows that starting a new venture is the best way to increase profits for any company but it comes with risk of failure. Your company’s financial status is good to deal with this risk and hence you should go for it. Your company is making good profits per employee and hence it’s the best time to start and succeed with the new venture too.");
                                map.put("Stock Market", "According to our analysis it is good to invest in stock market. Your company is in good state so it can take risk to invest in stock market. Stock market is a good way for company to make capital. This is a good investment option for both short term and long term period of investment.");
                                map.put("Medical Insurance for Employees", "For a company like yours it’s very important to take care of your employees and one of the mandatory things for that is to invest in medical insurance of your employees. You have currently not opted for any medical insurance policy hence it’s the right time to address your employees needs and browse good medical insurance plans.");
                                map.put("Fresh Recruitment", "A company’s success always depends on the quality of its recruits. It is very important for a company to meticulously plan and spend time and money for recruitments. Data shows that difference between companies which grow very quickly and those which don’t is the simple factor of how important they took the process of hiring. As per our detailed analysis we found that it’s the best that you invest in planning for fresh recruitments which will help you boost your success.");
                                map.put("Advertisements & Marketing", "Marketing is must for any company to grow and increase their market value. It is important to invest in advertising because it is the lifeline of a business. It helps people get interested in what the businessman is doing. If you want your message to hit on the day a product launches or event is about to happen, this is the only vehicle you control completely. when you match a very personal message to a very select audience you get far greater connection. Hence it is recommended to invest in advertisement.");
                                map.put("Business Expansion", "A good business must operate in more than one location. Company which has diversity in terms of location and other factors, tends to perform better than others. Your company has a wonderful   opportunity to expand its business, which can be a game changer in today’s competitive market.");
                                
                                try {
               Rectangle pageSize = new Rectangle(720, 1080);
               pageSize.setBackgroundColor(new BaseColor(194, 204, 197));
               LineSeparator ls=new LineSeparator();            
               pageSize.setBorder(Rectangle.BOX);
               pageSize.setBorderWidth(2);

               Document document = new Document(pageSize,25f,25f,10f,10f);
           		OutputStream file = new FileOutputStream(new File("C:\\Users\\yadvendra.trn\\Downloads\\"+client.getName()+".pdf"));
           		PdfWriter.getInstance(document, file);
                          
              
              //Inserting Image in PDF
                                                     Image image = Image.getInstance ("C:\\Users\\yadvendra.trn\\workspace\\IA_BackEnd\\src\\favicon.png");
                                                     image.scaleAbsolute(120f, 100f);//image width,height              
                                                     image.setAlignment(Element.ALIGN_CENTER);
                                                     
//                                            //Title
                                                     Float fntSize = 19f,lineSpacing = 20f,fntSize2=16f,fntSize3=14f;
                                                     Paragraph p=new Paragraph(new Phrase(lineSpacing,"INVESTO AVENUE",
                                                     FontFactory.getFont(FontFactory.COURIER_BOLD, fntSize)));
                                                     p.setAlignment(Element.ALIGN_CENTER);
                                                //Inserting Table in PDF
                                                     PdfPTable table=new PdfPTable(3);
                                                     
                                     PdfPCell cell = new PdfPCell (new Paragraph(new Phrase(lineSpacing,"Recommended Investment Options",
                                                                    FontFactory.getFont(FontFactory.COURIER_BOLD, fntSize2))));

                                                                      cell.setColspan (3);
                                                                      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell.setPadding (10.0f);
                                                                      cell.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                                                                                    
                                                                      
                                                                      
                                                                      PdfPCell cell2 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,"Priority",
                                                                    FontFactory.getFont(FontFactory.COURIER_BOLD, fntSize3))));
                                                                      
                                                                      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell2.setPadding (10.0f);
                                                                      cell2.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                             
                                                                      
                                                                      PdfPCell cell3 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,"Option",
                                                                    FontFactory.getFont(FontFactory.COURIER_BOLD, fntSize3))));
                                                                      cell3.setColspan (2);
                                                                      cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell3.setPadding (10.0f);
                                                                      cell3.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                             
                                                                      
                                                                      PdfPCell cell4 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,"          1.",
                                                                    FontFactory.getFont(FontFactory.COURIER, fntSize3))));
                                                                      cell4.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell4.setPadding (10.0f);
                                                                      cell4.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                             
                                                                      
                                                                      PdfPCell cell5 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,report[0],
                                                                    FontFactory.getFont(FontFactory.COURIER, fntSize3))));
                                                                      cell5.setColspan (2);
                                                                      cell5.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell5.setPadding (10.0f);
                                                                      cell5.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                             
                                                                      
                                                                      PdfPCell cell6 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,"          2.",
                                                                    FontFactory.getFont(FontFactory.COURIER, fntSize3))));
                                                                      cell6.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell6.setPadding (10.0f);
                                                                      cell6.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                             
                                                                      
                                                                      PdfPCell cell7 =new PdfPCell (new Paragraph(new Phrase(lineSpacing,report[1],
                                                                    FontFactory.getFont(FontFactory.COURIER, fntSize3))));
                                                                      cell7.setColspan (2);
                                                                      cell7.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell7.setPadding (10.0f);
                                                                      cell7.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));
                                                                      
                                                                      PdfPCell cell8 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,"          3.",
                                                                    FontFactory.getFont(FontFactory.COURIER, fntSize3))));
                                                                      cell8.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell8.setPadding (10.0f);
                                                                      cell8.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));                                                                                             
                                                                      
                                                                      PdfPCell cell9 = new PdfPCell (new Paragraph(new Phrase(lineSpacing,report[2],
                                                                    FontFactory.getFont(FontFactory.COURIER, fntSize3))));
                                                                      cell9.setColspan (2);
                                                                      cell9.setHorizontalAlignment (Element.ALIGN_CENTER);
                                                                      cell9.setPadding (10.0f);
                                                                      cell9.setBackgroundColor (new BaseColor (0xFF, 0xFF, 0xDE));
                                                                      
                                                                      table.addCell(cell);                                                                                                  
                                                                      table.addCell(cell2);
                                                                      table.addCell(cell3);
                                                                      table.addCell(cell4);
                                                                      table.addCell(cell5);
                                                                      table.addCell(cell6);
                      table.addCell(cell7);
                                                                      table.addCell(cell8);
                                                                      table.addCell(cell9);
                                                                      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
                                                                      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS                                                                                                                                        

                                                //Text formating in PDF
//                            Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
//                                                                            chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
//                                                                            Chunk chunk1=new Chunk("Php4s.com");
//                                                                            chunk1.setUnderline(+4f,-8f);
//                                                                            chunk1.setBackground(new BaseColor (17, 46, 193));      
                                                                                
                                                                                Paragraph intro= new Paragraph("Thanks for trusting us with your investment planning. We are higly committed towards helping you achive your financial goals. Our highly efficient analytical algorithm has come up with three best areas for you to invest in.",FontFactory.getFont(FontFactory.TIMES_ITALIC, fntSize3));
                                                //Now Insert Every Thing Into PDF Document
                                         document.open();//PDF document opened........
                                         
                          
                                                 
                                                               
                                               document.add(Chunk.NEWLINE);
                                                                                document.add(image);
                                                                                document.add(Chunk.NEWLINE);
                                                                                document.add(p);
                                                                                document.addTitle("Client Report");
                                                                                document.addAuthor("INVESTO AVENUE");
                                                                                
                                                                                document.add(Chunk.NEWLINE);   //Something like in HTML 
                                                                                document.add(new Chunk(ls));                                                            
                    document.add(new Paragraph("Dear "+client.getName()+",",FontFactory.getFont(FontFactory.TIMES_ITALIC, fntSize3)));
                    document.add(Chunk.NEWLINE);
                    document.add(intro);
                                                                                document.add(table);
                                                                
                                                                                for(int i=0;i<3;i++)
                                                                                {              
                                                                                                Paragraph para=new Paragraph(new Phrase(lineSpacing,map.get(report[i]),
                                                                    FontFactory.getFont(FontFactory.TIMES, fntSize3)));
                                                                                                Phrase c =new Phrase(lineSpacing,report[i],
                                                                    FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, fntSize2));
                                                                                                
                                                                                                document.add(Chunk.NEWLINE);
                                                                                                document.add(c);
                                                                                                document.add(Chunk.NEWLINE);
                                                                                                document.add(Chunk.NEWLINE);
                                                                                                document.add(para);
                                                                                                document.add(Chunk.NEWLINE);
                                                                                                
                                                                                }
//                                                                            document.add(chunk);
//                                                                            document.add(chunk1);

                                                                                document.add(Chunk.NEWLINE);   //Something like in HTML                                                                                            

//                                                           document.newPage();            //Opened new page
                                                                                document.add(new Chunk(ls));
                                                                                document.add(new Paragraph(new Phrase(lineSpacing,"INVESTO AVENUE Report Generated On - "+new Date().toString(),
                                                                                                     FontFactory.getFont(FontFactory.HELVETICA, 8.7f)))); 
                                                                
                                         document.close();

                                                             file.close();

            System.out.println("Pdf created successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
                                
}
                
                
