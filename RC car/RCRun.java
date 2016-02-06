import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import java.util.*;
import oscP5.*;
public class RCRun
{
	public static class NestedTest {

		final static GpioController gpio = GpioFactory.getInstance();
        final static GpioPinDigitalOutput PinFour = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "pin20",
                PinState.HIGH);
        final static GpioPinDigitalOutput PinFive = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "pin21",
                PinState.HIGH);

        final static GpioPinDigitalOutput PinTwo = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "pin27",
                PinState.HIGH);
        final static GpioPinDigitalOutput PinThree = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "pin25",
                PinState.HIGH);
        final static int port = 12001;
		final static OscP5 oscP5 = new OscP5(new RCRun.NestedTest(), port);
		static String data = "";

		public static void oscEvent(OscMessage theMess) throws InterruptedException {
			data = theMess.get(0).stringValue();
			 if (data.equals("forward"))
            {
                forward();
            }
            else if (data.equals("backward"))
            {
                backward();
            }
            else if (data.equals("forward-left"))
            {
                leftTurn();
            }
            else if (data.equals("forward-right"))
            {
                rightTurn();
            }
            else if (data.equals("backward-left"))
            {
                leftBackTurn();
            }
            else if (data.equals("backward-right"))
            {
                rightBackTurn();
            }

		}

		public static void main(String[] args) throws InterruptedException
		{
			Gpio.wiringPiSetup();
			SoftPwm.softPwmCreate(4,0,100);
			SoftPwm.softPwmCreate(5,0,100);
			SoftPwm.softPwmCreate(25,0,100);
			SoftPwm.softPwmCreate(27,0,100);
			String command = "";
			while (true)
			{
			}
		}
		public static void forward() throws InterruptedException
		{
			SoftPwm.softPwmWrite(4,100);
			SoftPwm.softPwmWrite(25,100);
			PinFour.high();
			PinTwo.high();
			PinFive.low();
			PinThree.low();
			Thread.sleep(15);
			PinFour.low();
			PinTwo.low();
			PinFive.low();
			PinThree.low();
			SoftPwm.softPwmWrite(4,0);
			SoftPwm.softPwmWrite(25,0);
		}

		public static void backward() throws InterruptedException
		{
			SoftPwm.softPwmWrite(5,100);
			SoftPwm.softPwmWrite(27,100);
			PinFive.high();
			PinThree.high();
			PinFour.low();
			PinTwo.low();
			Thread.sleep(15);
			PinFive.low();
			PinThree.low();
			PinFour.low();
			PinTwo.low();
			SoftPwm.softPwmWrite(5,0);
       	 	SoftPwm.softPwmWrite(27,0);
		}

		public static void leftTurn() throws InterruptedException
		{
			SoftPwm.softPwmWrite(4,50);
			SoftPwm.softPwmWrite(25,100);
			PinFour.high();
			PinTwo.high();
			PinFive.low();
			PinThree.low();
			Thread.sleep(15);
			PinFour.low();
			PinTwo.low();
			PinFive.low();
        	PinThree.low();
			SoftPwm.softPwmWrite(4,0);
        	SoftPwm.softPwmWrite(25,0);
		}

		public static void rightTurn() throws InterruptedException
		{
			SoftPwm.softPwmWrite(25,50);
        	SoftPwm.softPwmWrite(4,100);
        	PinFour.high();
        	PinTwo.high();
        	PinFive.low();
        	PinThree.low();
        	Thread.sleep(15);
        	PinFour.low();
        	PinTwo.low();
        	PinFive.low();
        	PinThree.low();
			SoftPwm.softPwmWrite(4,0);
        	SoftPwm.softPwmWrite(25,0);
		}

		public static void rightBackTurn() throws InterruptedException
		{
			SoftPwm.softPwmWrite(27,50);
			SoftPwm.softPwmWrite(5,100);
			PinFive.high();
			PinThree.high();
			PinFour.low();
			PinTwo.low();
			Thread.sleep(15);
			PinFive.low();
			PinThree.low();
			PinFour.low();
			PinTwo.low();
			SoftPwm.softPwmWrite(5,0);
			SoftPwm.softPwmWrite(27,0);
		}

		public static void leftBackTurn() throws InterruptedException
    	{	
        	SoftPwm.softPwmWrite(5,50);
        	SoftPwm.softPwmWrite(27,100);
        	PinFive.high();
        	PinThree.high();
        	PinFour.low();
        	PinTwo.low();
        	Thread.sleep(15);
        	PinFive.low();
        	PinThree.low();
        	PinFour.low();
        	PinTwo.low();
        	SoftPwm.softPwmWrite(5,0);
        	SoftPwm.softPwmWrite(27,0);
    	}
	}

	public static void main(String[] args) throws InterruptedException {
		new NestedTest().main(args);
	}

}
