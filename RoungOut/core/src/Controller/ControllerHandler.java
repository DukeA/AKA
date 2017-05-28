package Controller;

import java.util.ArrayList;

/**
 * @author Alex
 * Created on 2017-05-12.
 */
public class ControllerHandler implements IHandler {

    /**
     * List of controllers
     */
    private ArrayList<IControllHandeling> controllers = new ArrayList<IControllHandeling>();

    /**
     *
     * @param index index in the controller list
     * @param controller the controller that gets added into the list
     */
    public void addControllerIndex(int index, IControllHandeling controller){
        controllers.add(index,controller);
    }

    /**
     * Adds a controller to the controller list at no specified index
     * @param controller The controller in question
     */
    public void addController (IControllHandeling controller) {controllers.add(controller);}

    /**
     * This method sets the input processor to the first controller in the controller list that matches the enum
     * @param controllerEnum the enum to look for
     */
    @Override
    public void callSetNewInput(EnumIndexes controllerEnum) {
        for (int i =0; i < controllers.size(); i++){
            //We get each controllers Enum;
            EnumIndexes tmp = controllers.get(i).getTypeOfMenu();

            if(tmp == controllerEnum){
                //If the controller's enum matches index then set it to the new inputProcessor
                controllers.get(i).changeInputProcessor();
                break;//we brake since we found a input processor
            }
        }
    }

    /**
     * Getter, gets the controller list
     * @return returns the controller list
     */
    public ArrayList<IControllHandeling> getcontrollers (){
        return controllers;
    }

    /**
     * Setter, sets the new controller list
     * @param newControllers the new controller list
     */
    public void setControllers (ArrayList<IControllHandeling> newControllers){
        controllers = newControllers;
    }


    /**
     * Default constructor
     */
    public ControllerHandler(){}

    /**
     * Constructor with the list of controllers
     * @param controllers the list of controllers
     */
    public ControllerHandler(ArrayList<IControllHandeling> controllers) {
        this.controllers = controllers;
    }
}
