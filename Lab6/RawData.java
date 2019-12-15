import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class RawData implements Collection, Serializable {
	private ArrayList<String> data;
	
	public RawData(ArrayList<String> data) {
		this.data = data;
	}
	
	public ArrayList<String> getData() {
		return data;
	}
	
	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	
	@Override
	public Iterator<String> gerIterator() {
		return new DataIterator();
	}
	
	private class DataIterator<String> implements Iterator{
		
		int index;
		
		@Override
		public boolean hasNext() {
			if(index < data.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return data.get(index++);
		}
		
		@Override
		public void remove() {
			data.remove(--index);
		}
		
	}
	
	public String toString() {
		StringBuffer ret = new StringBuffer();
		for(int i = 0; i < this.data.size(); i++) {
			ret.append(data.get(i));
		}
		return ret.toString();
	}
	
	public void add(String str) {
		this.data.add(str);
	}
	
	public void clear() {
		data.clear();
	}
	
	public boolean remove(String string) {
		DataIterator iterator = new DataIterator();
		while(iterator.hasNext()) {
			if(iterator.next().equals(string)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}
	
	public Object[] toArray() {
		return this.data.toArray();
	}
	
	public int size() {
		return this.data.size();
	}
	
	public boolean contains(String string) {
		DataIterator iterator = new DataIterator();
		while(iterator.hasNext()) {
			if(iterator.next().toString().equals(string)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAll(RawData container) {
		return this.getData().equals(container.getData());
	}

	public void sort(){
		Collections.sort(this.data, String.CASE_INSENSITIVE_ORDER);
	}

	public int find(String str){
		int index = 0;
		Iterator iterator = this.gerIterator();
		while (iterator.hasNext()){
			index++;
			if (iterator.next().equals(str)){
				return index;
			}
		}
		return -1;
	}
}
