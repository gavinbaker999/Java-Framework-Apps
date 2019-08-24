	public class Tuple3<A, B, C> {

	    public final A a;
	    public final B b;
	    public final C c;

	    public Tuple3(A a, B b,C c) {
	        this.a = a;
	        this.b = b;
	        this.c = c;
	    }
	    public A getA() {return a;}
	    public B getB() {return b;}
	    public C getC() {return c;}
	    
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Tuple3<?, ?, ?> tuple = (Tuple3<?, ?, ?>) o;
	        if (!a.equals(tuple.a)) return false;
	        if (!b.equals(tuple.b)) return false;
	        return c.equals(tuple.c);
	    }

	    @Override
	    public int hashCode() {
	        int result = a.hashCode();
	        result = 31 * result + b.hashCode() + c.hashCode();
	        return result;
	    }
	}
