package gsar.sarajevocloud;

import android.app.Activity;

import actions.ActionMoveCameraBuffered;
import actions.ActionRotateCameraBuffered;
import actions.ActionWASDMovement;
import commands.Command;
import geo.GeoObj;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
import gui.GuiSetup;
import gui.InfoScreenSettings;
import system.EventManager;
import system.Setup;
import util.Vec;
import util.Wrapper;
import worldData.Obj;
import worldData.SystemUpdater;
import worldData.World;

/**
 * Created by user1 on 12/16/15.
 */
public class CustomSetup extends Setup {

    private GLCamera _mainCamera;
    private World _mainWorld;

    private Wrapper _placeObjectWrapper;


    @Override
    public void _a_initFieldsIfNecessary() {
        _placeObjectWrapper = new Wrapper();
    }

    @Override
    public void _b_addWorldsToRenderer(GL1Renderer glRenderer, GLFactory objectFactory,
                                       GeoObj currentPosition) {
        _mainCamera = new GLCamera();
        _mainWorld = new World(_mainCamera);

        Obj placerContainer = new Obj();
        placerContainer.setComp(objectFactory.newArrow());
        _mainWorld.add(placerContainer);

        _placeObjectWrapper.setTo(placerContainer);

        glRenderer.addRenderElement(_mainWorld);

        _mainWorld.add(objectFactory.newSolarSystem(new Vec(10, 0, 1)));
    }

    @Override
    public void _c_addActionsToEvents(EventManager eventManager, CustomGLSurfaceView arView, SystemUpdater updater) {
        eventManager.addOnLocationChangedAction(new ActionMoveCameraBuffered(_mainCamera, 25, 5));
        eventManager.addOnOrientationChangedAction(new ActionRotateCameraBuffered(_mainCamera));
        eventManager.addOnTrackballAction(new ActionMoveCameraBuffered(_mainCamera, 25, 0));
    }

    @Override
    public void _d_addElementsToUpdateThread(SystemUpdater updater) {
        updater.addObjectToUpdateCycle(_mainWorld);
    }

    @Override
    public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity activity) {
        guiSetup.addButtonToTopView(new Command() {
            @Override
            public boolean execute() {
                return false;
            }
        }, "Bijelo Dugme");
    }

    @Override
    public void _f_addInfoScreen(InfoScreenSettings infoScreenData) {
        super._f_addInfoScreen(infoScreenData);
        infoScreenData.addText("Pozdrav svima, ovo je pokazna poruka.");
    }
}
