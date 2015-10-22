package com.neusoft.smsplatform.message.back;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2009-3-31 下午08:46:29
 */
public class MessageBackMap extends ConcurrentHashMap<String, MessageBack> {

    private static final long serialVersionUID = 5636658645097193353L;

    private static final Logger log = LoggerFactory.getLogger(MessageBackMap.class);

    private static final MessageBackMap backmap = new MessageBackMap();
    
    private LinkedList<MessageBack> backlist = new LinkedList<MessageBack>();

    public static MessageBackMap getInstance() {
        return backmap;
    }

    @Override
    public MessageBack get(Object backId) {
        return super.get(backId);
    }

    @Override
    public MessageBack put(String backId, MessageBack back) {
        log.info("the back " + back.getAddress() + " has been put into the back map.");
        return super.put(backId, back);
    }

    @Override
    public MessageBack remove(Object key) {
        log.info("the back " + key + " has been removed from the back map.");
        return super.remove(key);
    }
    
    public synchronized MessageBack getList() {
    	MessageBack back = backlist.poll();
    	backlist.add(back);
		return back;
	}
	
	public LinkedList<MessageBack> getBacklist() {
		return backlist;
	}

	public synchronized void setList(MessageBack back){
		backlist.add(back);
	}

	public void setBacklist(LinkedList<MessageBack> backlist) {
		this.backlist = backlist;
	}
}
