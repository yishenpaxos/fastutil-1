/* Generic definitions */




/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/* Primitive-type-only definitions (values) */
/*		 
 * Copyright (C) 2007-2013 Sebastiano Vigna 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package it.unimi.dsi.fastutil.shorts;
import java.util.Map;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanCollections;
import it.unimi.dsi.fastutil.booleans.BooleanArraySet;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/** A simple, brute-force implementation of a map based on two parallel backing arrays. 
 * 
 * <p>The main purpose of this
 * implementation is that of wrapping cleanly the brute-force approach to the storage of a very 
 * small number of pairs: just put them into two parallel arrays and scan linearly to find an item.
 */
public class Short2BooleanArrayMap extends AbstractShort2BooleanMap implements java.io.Serializable, Cloneable {
 private static final long serialVersionUID = 1L;
 /** The keys (valid up to {@link #size}, excluded). */
 private transient short[] key;
 /** The values (parallel to {@link #key}). */
 private transient boolean[] value;
 /** The number of valid entries in {@link #key} and {@link #value}. */
 private int size;
 /** Creates a new empty array map with given key and value backing arrays. The resulting map will have as many entries as the given arrays.
	 * 
	 * <p>It is responsibility of the caller that the elements of <code>key</code> are distinct.
	 * 
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 */
 public Short2BooleanArrayMap( final short[] key, final boolean[] value ) {
  this.key = key;
  this.value = value;
  size = key.length;
  if( key.length != value.length ) throw new IllegalArgumentException( "Keys and values have different lengths (" + key.length + ", " + value.length + ")" );
 }
 /** Creates a new empty array map.
	 */
 public Short2BooleanArrayMap() {
  this.key = ShortArrays.EMPTY_ARRAY;
  this.value = BooleanArrays.EMPTY_ARRAY;
 }
 /** Creates a new empty array map of given capacity.
	 *
	 * @param capacity the initial capacity.
	 */
 public Short2BooleanArrayMap( final int capacity ) {
  this.key = new short[ capacity ];
  this.value = new boolean[ capacity ];
 }
 /** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
 public Short2BooleanArrayMap( final Short2BooleanMap m ) {
  this( m.size() );
  putAll( m );
 }
 /** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
 public Short2BooleanArrayMap( final Map<? extends Short, ? extends Boolean> m ) {
  this( m.size() );
  putAll( m );
 }
 /** Creates a new array map with given key and value backing arrays, using the given number of elements.
	 * 
	 * <p>It is responsibility of the caller that the first <code>size</code> elements of <code>key</code> are distinct.
	 * 
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 * @param size the number of valid elements in <code>key</code> and <code>value</code>.
	 */
 public Short2BooleanArrayMap( final short[] key, final boolean[] value, final int size ) {
  this.key = key;
  this.value = value;
  this.size = size;
  if( key.length != value.length ) throw new IllegalArgumentException( "Keys and values have different lengths (" + key.length + ", " + value.length + ")" );
  if ( size > key.length ) throw new IllegalArgumentException( "The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")" );
 }
 private final class EntrySet extends AbstractObjectSet<Short2BooleanMap.Entry > implements FastEntrySet {
  @Override
  public ObjectIterator<Short2BooleanMap.Entry > iterator() {
   return new AbstractObjectIterator<Short2BooleanMap.Entry >() {
    int next = 0;
    public boolean hasNext() {
     return next < size;
    }
    @SuppressWarnings("unchecked")
    public Entry next() {
     if ( ! hasNext() ) throw new NoSuchElementException();
     return new AbstractShort2BooleanMap.BasicEntry ( key[ next ], value[ next++ ] );
    }
   };
  }
  public ObjectIterator<Short2BooleanMap.Entry > fastIterator() {
   return new AbstractObjectIterator<Short2BooleanMap.Entry >() {
    int next = 0;
    final BasicEntry entry = new BasicEntry ( ((short)0), (false) );
    public boolean hasNext() {
     return next < size;
    }
    @SuppressWarnings("unchecked")
    public Entry next() {
     if ( ! hasNext() ) throw new NoSuchElementException();
     entry.key = key[ next ];
     entry.value = value[ next++ ];
     return entry;
    }
   };
  }
  public int size() {
   return size;
  }
  @SuppressWarnings("unchecked")
  public boolean contains( Object o ) {
   if ( ! ( o instanceof Map.Entry ) ) return false;
   final Map.Entry<Short, Boolean> e = (Map.Entry<Short, Boolean>)o;
   final short k = ((e.getKey()).shortValue());
   return Short2BooleanArrayMap.this.containsKey( k ) && ( (Short2BooleanArrayMap.this.get( k )) == (((e.getValue()).booleanValue())) );
  }
 }
 public FastEntrySet short2BooleanEntrySet() {
  return new EntrySet();
 }
 private int findKey( final short k ) {
  final short[] key = this.key;
  for( int i = size; i-- != 0; ) if ( ( (key[ i ]) == (k) ) ) return i;
  return -1;
 }

 @SuppressWarnings("unchecked")

 public boolean get( final short k ) {



  final short[] key = this.key;
  for( int i = size; i-- != 0; ) if ( ( (key[ i ]) == (k) ) ) return value[ i ];
  return defRetValue;
 }

 public int size() {
  return size;
 }

 @Override
 public void clear() {
  size = 0;
 }
 @Override
 public boolean containsKey( final short k ) {
  return findKey( k ) != -1;
 }
 @Override
 @SuppressWarnings("unchecked")
 public boolean containsValue( boolean v ) {
  for( int i = size; i-- != 0; ) if ( ( (value[ i ]) == (v) ) ) return true;
  return false;
 }
 @Override
 public boolean isEmpty() {
  return size == 0;
 }
 @Override
 @SuppressWarnings("unchecked")
 public boolean put( short k, boolean v ) {
  final int oldKey = findKey( k );
  if ( oldKey != -1 ) {
   final boolean oldValue = value[ oldKey ];
   value[ oldKey ] = v;
   return oldValue;
  }
  if ( size == key.length ) {
   final short[] newKey = new short[ size == 0 ? 2 : size * 2 ];
   final boolean[] newValue = new boolean[ size == 0 ? 2 : size * 2 ];
   for( int i = size; i-- != 0; ) {
    newKey[ i ] = key[ i ];
    newValue[ i ] = value[ i ];
   }
   key = newKey;
   value = newValue;
  }
  key[ size ] = k;
  value[ size ] = v;
  size++;
  return defRetValue;
 }
 @Override
 @SuppressWarnings("unchecked")
 public boolean remove( final short k ) {
  final int oldPos = findKey( k );
  if ( oldPos == -1 ) return defRetValue;
  final boolean oldValue = value[ oldPos ];
  final int tail = size - oldPos - 1;
  for( int i = 0; i < tail; i++ ) {
   key[ oldPos + i ] = key[ oldPos + i + 1 ];
   value[ oldPos + i ] = value[ oldPos + i + 1 ];
  }
  size--;
  return oldValue;
 }
 @Override
 @SuppressWarnings("unchecked")
 public ShortSet keySet() {
  return new ShortArraySet ( key, size );
 }
 @Override
 public BooleanCollection values() {
  return BooleanCollections.unmodifiable( new BooleanArraySet ( value, size ) );
 }
 /** Returns a deep copy of this map. 
	 *
	 * <P>This method performs a deep copy of this hash map; the data stored in the
	 * map, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this map.
	 */
 @SuppressWarnings("unchecked")
 public Short2BooleanArrayMap clone() {
  Short2BooleanArrayMap c;
  try {
   c = (Short2BooleanArrayMap )super.clone();
  }
  catch(CloneNotSupportedException cantHappen) {
   throw new InternalError();
  }
  c.key = key.clone();
  c.value = value.clone();
  return c;
 }
 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
  s.defaultWriteObject();
  for( int i = 0; i < size; i++ ) {
   s.writeShort( key[ i ] );
   s.writeBoolean( value[ i ] );
  }
 }
 @SuppressWarnings("unchecked")
 private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
  s.defaultReadObject();
  key = new short[ size ];
  value = new boolean[ size ];
  for( int i = 0; i < size; i++ ) {
   key[ i ] = s.readShort();
   value[ i ] = s.readBoolean();
  }
 }
}
