package SwingGUI;

import java.util.EventListener;

public interface UserListener extends EventListener
{
	public void detailEventOccured(UserEvent event);
}
