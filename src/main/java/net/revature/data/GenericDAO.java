package net.revature.data;

import java.util.List;

public interface GenericDAO <T> {
	public List<T> getAll(); // read all
	public void update(T updatedObj);
	public void delete(T objToDelete);
	// new method that deletes an object by just an id
	public int getById(int id);
	public int create(T newObj);
}

