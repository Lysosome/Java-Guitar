package javaguitar;

import java.util.Random;


public class GuitarString {
	private RingBuffer g;
	private int tickTock;
   //Very large sample rates have a delay effect (around 1,000,000)
   //Medium large sample rates are deeper in pitch (around 100,000)
   //Regular sample rate is 44100
   //Higher sample rates have higher pitch
   private final int SAMPLE_RATE = 44100;
   
   //Regular decay is 0.994
   //Higher decay (but still less than 1) makes the sound carry longer
	private static final double ENERGY_DECAY = 0.994;
	GuitarString (double frequency) {
		g = new RingBuffer(SAMPLE_RATE/(int)frequency);
	}
	GuitarString (double[] init) {
		g = new RingBuffer(init.length);
		for(int i=0; i < init.length; i++) {
			g.enqueue(init[i]);
		}
	}
	void pluck() {
		for(int i=0; i < g.size(); i++)
			g.dequeue();
		
      Random rand = new Random();
		for(int i=0; i < g.size(); i++)
      {
			//Normal Guitar Sound
         //g.enqueue(rand.nextDouble() - 0.5);
         
         //Squarewave Guitar
         g.enqueue(-0.5+i/g.size());
         
         //Also squarewave
         //g.enqueue(0.5);
         
      }
	}
	void tic() {
		g.dequeue();
		g.enqueue((g.peek(0) + g.peek(1))* 0.5 * ENERGY_DECAY);
		tickTock += 1;
	}
	double sample() {
		return g.peek(0);
	}
	int time() {
		return tickTock;
	}
}
