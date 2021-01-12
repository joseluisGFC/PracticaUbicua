package mqtt;

public class MQTTBroker 
{		
	private static int qos = 2;
	private static String broker = "tcp://185.239.202.139:1883";
	private static String clientId = "BasuraUAH";
		
	public MQTTBroker()
	{
	}

	public static int getQos() {
		return qos;
	}

	public static String getBroker() {
		return broker;
	}

	public static String getClientId() {
		return clientId;
	}			
}


