package one.digitalinnovation.gof.singleton;
/**
 * Singleton "Lazy Holder".
 *
 * @see <a href="https://stackoverflow.com/a/24018148">Referencia</a>
 *
 * @author guilhermeag
 */
public class SingletonLazyHolder {

  private static class InstanceHolder {
    private  static SingletonLazyHolder instancia =  new SingletonLazyHolder();
  }

  private SingletonLazyHolder() {
    super();
  }

  public static SingletonLazyHolder getInstancia() {
    return InstanceHolder.instancia;
  }
}
