package worldData;

import gl.LightSource;
import gl.Renderable;
import gl.scenegraph.MeshComponent;
import gl.scenegraph.RenderList;
import gl.scenegraph.Shape;

/**
 * This is the basic interface for any object which hat to do with Rendering and
 * which also needs to be updated from time to time. <br>
 * <br>
 * <p/>
 * The existing important subclasses are: <br>
 * <p/>
 * - {@link RenderList}: It is a group of {@link RenderableEntity}s<br>
 * <p/>
 * - {@link MeshComponent}: A basic {@link Shape} e.g. to draw OpenGL objects or
 * {@link LightSource} to add lighning effects to a scene <br>
 *
 * @author Spobo
 */
public interface RenderableEntity extends Entity, Renderable {

}
