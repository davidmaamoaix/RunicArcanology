package cn.davidma.runicarcanology.capability;

public interface IActivatable {

	// Turn on.
	public void activate();
	
	// Turn off.
	public void deactivate();
	
	// State (on/off).
	public boolean isWorking();
}
