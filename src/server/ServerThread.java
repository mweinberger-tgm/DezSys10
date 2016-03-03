package server;

import java.math.BigDecimal;

public class ServerThread implements Runnable
{
	int digits;
	BigDecimal out;
	
	public ServerThread(int digits) {
		this.digits = digits;
	}
	
	public BigDecimal getOut() {
		return out;
	}
	
	public void run()
	{
		PiCalculator calc = new PiCalculator();
		out = calc.pi(digits);
		//System.out.println(out.toString());
	}
}