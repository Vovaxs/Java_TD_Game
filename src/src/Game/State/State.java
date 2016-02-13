package src.Game.State;

import src.Game.Frame;

/**
 *
 * @author Vovaxs
 */
public interface State {
    
    public void init(Frame frame);

    public void update();

    public boolean isInitialized();
    
}
