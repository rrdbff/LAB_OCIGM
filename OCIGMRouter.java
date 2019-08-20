package routing;

import java.util.ArrayList;
import java.util.List;

import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;

public class OCIGMRouter extends ActiveRouter {
	/** identifier for the initial number of copies setting ({@value})*/ 
	public static final String NROF_COPIES = "nrofCopies";
	/** identifier for the binary-mode setting ({@value})*/ 
	public static final String OCIGM_NS = "OCIGM";
	/** Message property key */
	public static final String MSG_COUNT_PROPERTY = OCIGM_NS + "." +
		"copies";
	
	/** Threshold for Delivery ratio*/
	public static final String ALPHA1 = OCIGM_NS + "." +"a1"; 
	/** Threshold for Overhead ratio*/
	public static final String ALPHA2 = OCIGM_NS + "." +"a2";
	/** Threshold for Drop ratio*/
	public static final String ALPHA3 = OCIGM_NS + "." +"a3";
	
	public class Status
	{
		public double mDelivery=0.0;
		public double mOverhead=0.0;
		public double mDrop=0.0;
	}

	protected int initialNrofCopies;
	protected double alpha1;
	protected double alpha2;
	protected double alpha3;
	public OCIGMRouter(Settings s) {
		super(s);
		Settings ocigmSettings = new Settings(OCIGM_NS);
		
		initialNrofCopies = ocigmSettings.getInt(NROF_COPIES);
		alpha1 = ocigmSettings.getDouble(ALPHA1);
		alpha2 = ocigmSettings.getDouble(ALPHA2);
		alpha3 = ocigmSettings.getDouble(ALPHA3);
	}
	
	/**
	 * Copy constructor.
	 * @param r The router prototype where setting values are copied from
	 */
	protected OCIGMRouter(OCIGMRouter r) {
		super(r);
		this.initialNrofCopies = r.initialNrofCopies;
	} 
	
	public int CntlCopies(Status OCI,int nrofCopies)
	{
		int afternCopies = 0;
		afternCopies = (int)Math.round((alpha1/ OCI.mDelivery)*nrofCopies);
		nrofCopies = afternCopies;
		afternCopies = (int)Math.round((alpha2/ OCI.mOverhead)*nrofCopies);
		nrofCopies = afternCopies;
		afternCopies = (int)Math.round((alpha3/ OCI.mDrop)*nrofCopies);
		nrofCopies = afternCopies;
		
		return nrofCopies;
	}
	
	public Status MkmdSituation(Status mdSituation)
	{
		
		return mdSituation;
	}
	
	
	
/*	@Override
	public int receiveMessage(Message m, DTNHost from) {
		return super.receiveMessage(m, from);
	}
	

	
	@Override
	public Message messageTransferred(String id, DTNHost from) {
		Message msg = super.messageTransferred(id, from);
		Integer nrofCopies = (Integer)msg.getProperty(MSG_COUNT_PROPERTY);
		
		assert nrofCopies != null : "Not a ocigm message: " + msg;
		
			 in standard S'n'W the receiving node gets only single copy 
			nrofCopies = 1;
				
		msg.updateProperty(MSG_COUNT_PROPERTY, nrofCopies);
		return msg;
	}
	
	@Override 
	public boolean createNewMessage(Message msg) {
		makeRoomForNewMessage(msg.getSize());

		msg.setTtl(this.msgTtl);
		msg.addProperty(MSG_COUNT_PROPERTY, new Integer(initialNrofCopies));
		addToMessages(msg, true);
		return true;
	}
	
	@Override
	public void update() {
		super.update();
		if (!canStartTransfer() || isTransferring()) {
			return; // nothing to transfer or is currently transferring 
		}

		 try messages that could be delivered to final recipient 
		if (exchangeDeliverableMessages() != null) {
			return;
		}
		
		 create a list of SAWMessages that have copies left to distribute 
		@SuppressWarnings(value = "unchecked")
		List<Message> copiesLeft = sortByQueueMode(getMessagesWithCopiesLeft());
		
		if (copiesLeft.size() > 0) {
			 try to send those messages 
			this.tryMessagesToConnections(copiesLeft, getConnections());
		}
	}
	
	*//**
	 * Creates and returns a list of messages this router is currently
	 * carrying and still has copies left to distribute (nrof copies > 1).
	 * @return A list of messages that have copies left
	 *//*
	protected List<Message> getMessagesWithCopiesLeft() {
		List<Message> list = new ArrayList<Message>();

		for (Message m : getMessageCollection()) {
			Integer nrofCopies = (Integer)m.getProperty(MSG_COUNT_PROPERTY);
			assert nrofCopies != null : "ocigm message " + m + " didn't have " + 
				"nrof copies property!";
			if (nrofCopies > 1) {
				list.add(m);
			}
		}
		
		return list;
	}
	
	*//**
	 * Called just before a transfer is finalized (by 
	 * {@link ActiveRouter#update()}).
	 * Reduces the number of copies we have left for a message. 
	 * In binary Spray and Wait, sending host is left with floor(n/2) copies,
	 * but in standard mode, nrof copies left is reduced by one. 
	 *//*
	@Override
	protected void transferDone(Connection con) {
		Integer nrofCopies;
		String msgId = con.getMessage().getId();
		 get this router's copy of the message 
		Message msg = getMessage(msgId);

		if (msg == null) { // message has been dropped from the buffer after..
			return; // ..start of transfer -> no need to reduce amount of copies
		}
		
		 reduce the amount of copies left 
		nrofCopies = (Integer)msg.getProperty(MSG_COUNT_PROPERTY);

			nrofCopies /= 2;
		

		msg.updateProperty(MSG_COUNT_PROPERTY, nrofCopies);
	}
	
	@Override
	public OCIGMRouter replicate() {
		return new OCIGMRouter(this);
	}
}
*/
