import java.util.List;

public interface CRUD<T extends ClassicalEntity<K>, K> {
	void create(T t);
	List<T> read();
	T read(K k);
	T update(T t);
	void delete(T t);
	void deleteById(K k);
}
