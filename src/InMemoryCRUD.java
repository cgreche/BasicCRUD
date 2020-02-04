import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCRUD<T extends ClassicalEntity<K>, K> implements CRUD<T, K> {
	
	Map<K, T> data = new HashMap<>();

	@Override
	public void create(T t) {
		data.put(t.getKey(), t);
	}

	@Override
	public List<T> read() {
		List<T> _data = new ArrayList<>();
		for (Map.Entry<K, T> entry : data.entrySet()) {
			_data.add(entry.getValue());
		}
		return _data;
	}

	@Override
	public T read(K k) {
		return data.get(k);
	}

	@Override
	public T update(T t) {
		return data.put(t.getKey(), t);
	}

	@Override
	public void delete(T t) {
		data.remove(t.getKey());
	}

	@Override
	public void deleteById(K k) {
		data.remove(k);
	}
	
}
