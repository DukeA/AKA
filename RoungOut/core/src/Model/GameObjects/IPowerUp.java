package Model.GameObjects;

/**
 * Created by DukeA on 2017-05-12.
 */
public interface IPowerUp {

    float speedUpvalue;
    float speedDownvalue;

    void PSpeedUP();
    void PSpeedDown();
    void EffectOver();

}
