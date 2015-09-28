import java.util.Random;
public class GuitarString {
	private RingBuffer g;
	private int tickTock;
	private static final double ENERGY_DECAY = 0.994;
	GuitarString (double frequency) {
		g = new RingBuffer(44100/(int)frequency);
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
			g.enqueue(rand.nextDouble() - 0.5);
	}
	void tic() {
		g.dequeue();
		g.enqueue((g.peek(0) + g.peek(1)) * ENERGY_DECAY);
		tickTock += 1;
	}
	double sample() {
		return g.peek(0);
	}
	int time() {
		return tickTock;
	}
}
