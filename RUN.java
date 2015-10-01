package javaguitar;
import java.util.ArrayList;
public class RUN {
   public static void main (String[] args) {
      Keyboard threaded = new Keyboard();
      threaded.start();
        // number of line segments to plot
        int N = 100;

        // the function y = sin(4x) + sin(20x), sampled at N points
        // between x = 0 and x = pi
        double[] x = new double[N+1];
        double[] y = new double[N+1];
        for (int i = 0; i <= N; i++) {
            x[i] = Math.PI * i / N;
            y[i] = Math.sin(4*x[i]) + Math.sin(20*x[i]);
        }

        // rescale the coordinate system
        StdDraw.setXscale(0, Math.PI);
        StdDraw.setYscale(-2.0, +2.0);

        // plot the approximation to the function
        for (int i = 0; i < N; i++) {
            StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
        }
   }
}
class Keyboard extends Thread {
   public void run() {
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
