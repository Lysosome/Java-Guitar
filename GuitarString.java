import java.util.Random;
public class GuitarString {
	private RingBuffer g;
	GuitarString (double frequency) {
		g = new RingBuffer(44100/(int)frequency);
	}
	GuitarString (double[] init) {
		g = new RingBuffer(init.length);
		for(int i; i < init.length; i++) {
			g.enqueue(init[i]);
		}
	}
	void pluck() {
		for(int i; i < init.length; i++)
			g.dequeue();
		Random rand = new Random();
		for(int i; i < init.length; i++)
			g.enqueue(Random.nextDouble() - 0.5);
	}
	void tic() {
		tic += 1;
	}
	double sample() {

	}
	int time() {

	}
}
