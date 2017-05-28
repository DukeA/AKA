package Controller;

/**
 * @author Alex
 * Created on 2017-05-12.
 */
 interface IHandler {
    /**
     * This method sets the input processor to the first controller in the controller list that matches the enum
     * @param index enum that defines a controller
     */
    void callSetNewInput (EnumIndexes index);
}
