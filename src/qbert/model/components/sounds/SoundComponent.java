package qbert.model.components.sounds;

/**
 * This interface models a generic set of sound related to the game or to a specific character. 
 */
public interface SoundComponent {

    /**
     * This method adds the sound effect associated with the virtual insertion of the coin to the queue.
     */
   void setCoinSound();

   /**
    * This method adds the sound effect associated with the start of the game to the queue.
    */
   void setGameStartSound();

   /**
    * This method notifies the {@link Controller} the presence of a sound effect to be played.
    */
   void notifyPlaySound();
}
