package javaguitar;
import java.util.ArrayList;
import java.awt.Color;
import java.util.concurrent.*;
import java.util.Collections;

public class GuitarHeroineRing {

   protected static Double sampleVal = 0.0;
   
   private static Integer pos = 0;
   private static double[] x = {0,0};
   private static double lastSample = 0;
   private final static int X_SCALE = 1000;
   private final static double Y_SCALE = 0.4;
   private static double[] y = {-Y_SCALE,Y_SCALE};
   private static ArrayList<Double> sampleArr = new ArrayList<Double>(Collections.nCopies(X_SCALE, 0.0));

	public static void main (String[] args) {
		Keyboard threaded = new Keyboard(sampleVal);
		threaded.start();
		      		
      StdDraw.setXscale(0, X_SCALE);
      StdDraw.setYscale(-Y_SCALE, +Y_SCALE);
      
      /*
      final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
      executorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            myTask(threaded);
        }
    }, 0, 20, TimeUnit.MICROSECONDS);
      */
      
      int i=0;
      int j;
      for(j=0; j<X_SCALE*0.8; j++)
      {
         StdDraw.point(j,threaded.sampleVal());
      }
      
      while(true)
      {
         StdDraw.point(i+j,threaded.sampleVal());
			i++;
         StdDraw.setXscale(i, X_SCALE+i);
      }
      
	}
   public static void myTask()
   {
      
         for(int i=0; i<X_SCALE; i++)
         {
            StdDraw.point(i,sampleArr.get(i));
            System.out.println(sampleArr.get(i));
         }
         
   }
}

class Keyboard extends Thread {
	private static double sample = 0;
   protected Double sampleObj;
   
   public Keyboard(Double theSample)
   {
      sampleObj=theSample;
   }

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
			sample = 0;
			for(int i =0; i<keys.size(); i++)
				sample+=keys.get(i).sample();
			sampleObj=sample;
         
         // play the sample on standard audio
			StdAudio.play(sample);
         
			// advance the simulation of each guitar string by one step
			for(int i =0; i<keys.size(); i++)
			{
				keys.get(i).tic();
			}

		}
      
      
	}
   public Double sampleVal(){return sampleObj;}
}
