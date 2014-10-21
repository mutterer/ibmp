package src.org.micromanager.serialPortHandling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 

import java.util.Enumeration;
import java.util.Observable;

import src.org.micromanager.plugin.ArdWindow;


public class SerialReader extends Observable implements SerialPortEventListener{
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAME[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
                        "/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM3", // Windows
	};
	private BufferedReader input;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	
	private String serialInput;

	private Thread listenerThread;
	
	
	public void initialize() {
		

		//Identifies Ports
		CommPortIdentifier portId = null;
		//Getting together all the different Ports
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAME) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			ArdWindow.println("Could not find port.");
			return;
		}
		
		ArdWindow.println("3.Initializing SerialReader");

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

			// add this as an event listener - Eventhandling is below
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		
		//Start listenerThread
		listenerThread=new Thread() {
			public void run() {
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		listenerThread.start();
		
		notifyObservers(serialInput);
		
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
		if(listenerThread.isAlive()){
			listenerThread.interrupt();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	@Override
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
					serialInput=input.readLine();
					System.out.println(serialInput);
					ArdWindow.print("Sending.");
					ArdWindow.print(". ");
					setChanged();
			} catch (Exception e) {
				ArdWindow.println(e.toString());
			}
		}
		notifyObservers(serialInput);
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	
	/*/**Not necessary?
	public static void main(String[] args) throws Exception {
		SerialReader main = new SerialReader();
		main.initialize();
		Thread listenerThread=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		listenerThread.start();
		System.out.println("Started");
	}*/
}
