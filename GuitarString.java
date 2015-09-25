public class GuitarString {
	private Ringbuffer g;
	GuitarString (double frequency) {
		g = new RingBuffer( 44100 / frequency);
	}
	GuitarString (double[] init) {
		g = new Ringbuffer( 44100 / frequency);
		for(double i; i < init.size; i++) {
			g.enqueue(init[i]);
		}
	}
	void pluck() {

	}
	void tic() {

	}
	double sample() {

	}
	int time() {

	}
}
