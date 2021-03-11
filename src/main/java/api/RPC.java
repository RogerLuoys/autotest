package api;

public interface RPC {

    @Deprecated
    Object getService(String url, String InterfacePath);

    <T> T getService(String url, Class<T> clazz);
}
