package javaguitar;
import java.util.ArrayList;

class keyboard extends Thread {
   public static void run() {
	   final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
      final double CONCERT_A = 440.0;
      
      // create array list of all the Guitar Strings on our keyboard
      ArrayList<GuitarString> keys = new ArrayList<GuitarString>(37);
      for(int i =0; i<37; i++)
      {
         keys.add(new GuitarString(CONCERT_A * Math.pow(1.05956, i-24)));
      }
      
   
      while (true)
      {
         // check if the user has typed a key; if so, process it   
         if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if(KEYBOARD.indexOf(key)>-1)
            {
               keys.get(KEYBOARD.indexOf(key)).pluck();
            }
               
         }
   
         // compute the superposition of samples
         double sample = 0;
         for(int i =0; i<keys.size(); i++)
            sample+=keys.get(i).sample();
     
         // play the sample on standard audio
         StdAudio.play(sample);
     
         // advance the simulation of each guitar string by one step   
         for(int i =0; i<keys.size(); i++)
         {
            keys.get(i).tic();     
         }
         
         
         //Pseudocode for visualizer
         /*
            Make a GUI and then update the current pixel in accordance with the current value of sample.
            -1 is at the bottom of the screen, 1 is at the top, 0 is in the middle.
            Then advance to the next pixel and loop when you reach the end of the screen.
         */
         
         //visualizer
         /*try{
            Thread.sleep(25);
         } catch(InterruptedException ex){Thread.currentThread().interrupt();}
         */      
      }
   }
}
