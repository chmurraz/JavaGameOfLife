package SwingGUI;

import java.util.EventObject;

public class UserEvent extends EventObject
{
	private String text;
	public UserEvent(Object source, String text)
	{
		super(source);
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
}
