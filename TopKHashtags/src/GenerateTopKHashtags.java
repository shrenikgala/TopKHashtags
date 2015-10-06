


import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class GenerateTopKHashtags {
	TwitterStream twitterStream; 
	public  LinkedList<HashtagEntity[]> l= new LinkedList<HashtagEntity[]>();
	public  ArrayList<HashtagEntity[]> tweets= new ArrayList<HashtagEntity[]>();
    public  HashMap<String,Integer> map = new HashMap<String,Integer>(); 
    
    //public static int k,wsize;
    /**
     * Main entry of this application.
     *
     * @param args arguments doesn't take effect with this example
     */
   // public static void SetValues(int value,int size)
   // {
    //	k=value
    	
   // }
    
	public Map<String, Integer> SortByCount(HashMap<String, Integer> map) 
	{
		ValueComparator vc =  new ValueComparator(map);
		Map<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(map);
		return sortedMap;
	}
	
    public void SetStream(final JTextField f,final JTextArea j,final int k,final int wsize) {
    	twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.setOAuthConsumer("XXXXXX", "XXXXXXX");
        twitterStream.setOAuthAccessToken(new AccessToken("XXXXXXX", "XXXXXXXXXXXXXXXX"));
       
       
       StatusListener listener = new StatusListener() {
        	
            @Override
            public void onStatus(Status status) {
                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
       
            	
            	int len=tweets.size();
            	 f.setText(String.valueOf(len));
            		
            	 HashtagEntity[] hte = status.getHashtagEntities();
            	 if(hte.length>0)
            	 {
            		 for(int i=0;i < hte.length;i++)
            		 {
            			 if(map.containsKey(hte[i].getText()))
            			 {
            				 int val=map.get(hte[i].getText());
            				 map.put(hte[i].getText(), ++val);
            			 }
            			else
            				 map.put(hte[i].getText(), 1);
            		 }
            		 
            		 l.add(hte)	;
            		 tweets.add(hte);
            	 }
            	 
            	 
            	 if(l.size()==wsize){
            		 Map<String,Integer> sortedMap = SortByCount(map);
            			
            		 Printkmapvalues(sortedMap,j,k);
            			HashtagEntity[] h=l.poll();
            			for(int i=0;i<h.length;i++)
            			{
            				if(map.containsKey(h[i].getText()))
               			 {
               				 int val=map.get(h[i].getText());
               				 if(val >1)
               					 map.put(h[i].getText(), --val);
               			     else
               			    	 map.remove(h[i].getText());
               
            			}
            			}
            		 
            	 }
                
            }

            @Override
         public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               // System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                //System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
       
            
        };
        
    
      twitterStream.addListener(listener);
     
      twitterStream.sample("en");
  
   }
   
    public void StopStream(JTextArea j,JTextField f1,JTextField f2,JTextField f3){
    	twitterStream.shutdown();
    	f1.setText("");
    	f2.setText("");
    	f3.setText("0");
    	j.setText("");
    	tweets.clear();
    	map.clear();
    	l.clear();
    
    }
    
   
        	
        
    
    public void Printkmapvalues(Map m,JTextArea j,int k)
    {
    	
    	Set  s=m.entrySet();
    	Iterator i=s.iterator();
    	int c=0;
    	String s1="";
    	while(i.hasNext() && c<k)
    	{
    		Map.Entry me = (Map.Entry)i.next();
            //System.out.println(me.getKey()+ " "+ me.getValue());
    		s1=s1+"#"+me.getKey()+ " "+ "   "+"Count:"+me.getValue()+"\n\n";
           
    		c++;
          
    	}
    	j.setText(s1);
    	
    	
    }
}
class ValueComparator implements Comparator<String> {
	 
    Map<String, Integer> map;
 
    public ValueComparator(Map<String, Integer> base) {
        this.map = base;
    }
 
    public int compare(String a, String b) {
        if (map.get(a) >= map.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys 
    }
}
