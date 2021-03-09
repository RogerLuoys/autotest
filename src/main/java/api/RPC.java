package api;

public interface RPC {
    void dubboService();

    Object getService();

    Object getService(String url, String InterfacePath);

    <T> T getService(String url, Class<T> clazz);
}
