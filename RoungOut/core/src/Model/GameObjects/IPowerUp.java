package Model.GameObjects;

/**
 * Created by DukeA on 2017-05-12.
 */
public interface IPowerUp {

    void pSpeedUP();
    void pSpeedDown();
    void effectOver();

    float getPspeedUp();
    float getPspeedDown();
}
