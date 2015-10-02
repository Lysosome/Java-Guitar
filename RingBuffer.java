package javaguitar;

public class RingBuffer
{
   //instance variables
   private int capacity;
   private double[] arr;
   private int first;
   private int last;
   
   public RingBuffer(int capacity)
   {
      this.capacity=capacity;
      arr=new double[capacity];
      first=0;
      last=0;
   }
   
   public int size()
   {
      if(last>=first)
         return last-first;
      return (last+capacity)-first;
   }
   
   public boolean isEmpty()
   {
      if(last==first)
         return true;
      return false;
   }
   
   public boolean isFull()
   {
      if(first-1==last || (first+capacity)-1==last)
         return true;
      return false;
   }
   
   public void enqueue(double x)
   {
      if(isFull()){}
      else if(last==capacity)
      {
         last=1;
         arr[0]=x;
      }
      else
      {
         arr[last]=x;
         last++;
      }
   }
   
   public double dequeue()
   {
      double ret=arr[first];
      if(first==capacity-1)
         first=0;
      else
         first++;
      return ret;
   }
   
   public double peek(int loc)
   {
      if(first+loc>=capacity)
         return arr[first+loc-capacity];
      return arr[first+loc];
   }
}