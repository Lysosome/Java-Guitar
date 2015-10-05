package javaguitar;
import java.util.ArrayList;
import java.awt.Color;
import java.util.concurrent.*;

public class GuitarHeroine {

   protected static Double sampleVal = 0.0;
   
   private static Integer pos = 0;
   private static double[] x = {0,0};
   private static double[] y = {-1,1};
   private static double lastSample = 0;
   private final static int X_SCALE = 500;
   private final static double Y_SCALE = 0.25;

	public static void main (String[] args) {
		Keyboard threaded = new Keyboard(sampleVal);
		threaded.start();
		
      /*
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
      */
      
		//Pseudocode for visualizer
		/*
		   Make a GUI and then update the current pixel in accordance with the current value of sample.
		   -1 is at the bottom of the screen, 1 is at the top, 0 is in the middle.
		   Then advance to the next pixel and loop when you reach the end of the screen.
		   */
		
      StdDraw.setXscale(0, X_SCALE);
      StdDraw.setYscale(-Y_SCALE, +Y_SCALE);
      
      
      final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
      executorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            myTask(threaded);
        }
    }, 0, 20, TimeUnit.MILLISECONDS);
      
      
      /*while(true)
      {
			if(pos>X_SCALE)
         {
            pos-=X_SCALE;
         }
         
         StdDraw.setPenColor(Color.WHITE);
         x[0]=pos;
         x[1]=pos+7;
         StdDraw.polygon(x,y);
         StdDraw.setPenColor();
         
         StdDraw.line(pos, threaded.sampleVal(), pos+1, threaded.sampleVal());
         pos++;
      }
      */
	}
   public static void myTask(Keyboard threaded)
   {
      if(pos>X_SCALE)
         {
            pos-=X_SCALE;
         }
         
         StdDraw.setPenColor(Color.WHITE);
         x[0]=pos;
         x[1]=pos+14;
         StdDraw.polygon(x,y);
         StdDraw.setPenColor();
         
         StdDraw.line(pos, lastSample, pos+1, threaded.sampleVal());
         pos++;
         lastSample=threaded.sampleVal();

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
