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
/*		 
 * Copyright (C) 2002-2013 Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.doubles;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted maps.
 *
 * @see java.util.Collections
 */
public class Double2ObjectSortedMaps {
 private Double2ObjectSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Double, ?>> entryComparator( final DoubleComparator comparator ) {
  return new Comparator<Map.Entry<Double, ?>>() {
   public int compare( Map.Entry<Double, ?> x, Map.Entry<Double, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap <V> extends Double2ObjectMaps.EmptyMap <V> implements Double2ObjectSortedMap <V>, java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public DoubleComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Double2ObjectMap.Entry <V> > double2ObjectEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Double, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public DoubleSortedSet keySet() { return DoubleSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Double2ObjectSortedMap <V> subMap( final double from, final double to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Double2ObjectSortedMap <V> headMap( final double to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Double2ObjectSortedMap <V> tailMap( final double from ) { return EMPTY_MAP; }
  public double firstDoubleKey() { throw new NoSuchElementException(); }
  public double lastDoubleKey() { throw new NoSuchElementException(); }
  public Double2ObjectSortedMap <V> headMap( Double oto ) { return headMap( ((oto).doubleValue()) ); }
  public Double2ObjectSortedMap <V> tailMap( Double ofrom ) { return tailMap( ((ofrom).doubleValue()) ); }
  public Double2ObjectSortedMap <V> subMap( Double ofrom, Double oto ) { return subMap( ((ofrom).doubleValue()), ((oto).doubleValue()) ); }
  public Double firstKey() { return (Double.valueOf(firstDoubleKey())); }
  public Double lastKey() { return (Double.valueOf(lastDoubleKey())); }
 }


 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton <V> extends Double2ObjectMaps.Singleton <V> implements Double2ObjectSortedMap <V>, java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final DoubleComparator comparator;

  protected Singleton( final double key, final V value, DoubleComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final double key, final V value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final double k1, final double k2 ) {
   return comparator == null ? ( Double.compare((k1),(k2)) ) : comparator.compare( k1, k2 );
  }

  public DoubleComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Double2ObjectMap.Entry <V> > double2ObjectEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Double2ObjectMap.Entry <V>)new SingletonEntry(), (Comparator<? super Double2ObjectMap.Entry <V> >)entryComparator( comparator ) ); return (ObjectSortedSet<Double2ObjectMap.Entry <V> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Double, V>> entrySet() { return (ObjectSortedSet)double2ObjectEntrySet(); }

  public DoubleSortedSet keySet() { if ( keys == null ) keys = DoubleSortedSets.singleton( key, comparator ); return (DoubleSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Double2ObjectSortedMap <V> subMap( final double from, final double to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Double2ObjectSortedMap <V> headMap( final double to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Double2ObjectSortedMap <V> tailMap( final double from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public double firstDoubleKey() { return key; }
  public double lastDoubleKey() { return key; }


  public Double2ObjectSortedMap <V> headMap( Double oto ) { return headMap( ((oto).doubleValue()) ); }
  public Double2ObjectSortedMap <V> tailMap( Double ofrom ) { return tailMap( ((ofrom).doubleValue()) ); }
  public Double2ObjectSortedMap <V> subMap( Double ofrom, Double oto ) { return subMap( ((ofrom).doubleValue()), ((oto).doubleValue()) ); }

  public Double firstKey() { return (Double.valueOf(firstDoubleKey())); }
  public Double lastKey() { return (Double.valueOf(lastDoubleKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static <V> Double2ObjectSortedMap <V> singleton( final Double key, V value ) {
  return new Singleton <V>( ((key).doubleValue()), (value) );
 }

 /** RETURNS a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static <V> Double2ObjectSortedMap <V> singleton( final Double key, V value, DoubleComparator comparator ) {
  return new Singleton <V>( ((key).doubleValue()), (value), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static <V> Double2ObjectSortedMap <V> singleton( final double key, final V value ) {
  return new Singleton <V>( key, value );
 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static <V> Double2ObjectSortedMap <V> singleton( final double key, final V value, DoubleComparator comparator ) {
  return new Singleton <V>( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap <V> extends Double2ObjectMaps.SynchronizedMap <V> implements Double2ObjectSortedMap <V>, java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Double2ObjectSortedMap <V> sortedMap;

  protected SynchronizedSortedMap( final Double2ObjectSortedMap <V> m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Double2ObjectSortedMap <V> m ) {
   super( m );
   sortedMap = m;
  }

  public DoubleComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Double2ObjectMap.Entry <V> > double2ObjectEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.double2ObjectEntrySet(), sync ); return (ObjectSortedSet<Double2ObjectMap.Entry <V> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Double, V>> entrySet() { return (ObjectSortedSet)double2ObjectEntrySet(); }
  public DoubleSortedSet keySet() { if ( keys == null ) keys = DoubleSortedSets.synchronize( sortedMap.keySet(), sync ); return (DoubleSortedSet )keys; }

  public Double2ObjectSortedMap <V> subMap( final double from, final double to ) { return new SynchronizedSortedMap <V>( sortedMap.subMap( from, to ), sync ); }
  public Double2ObjectSortedMap <V> headMap( final double to ) { return new SynchronizedSortedMap <V>( sortedMap.headMap( to ), sync ); }
  public Double2ObjectSortedMap <V> tailMap( final double from ) { return new SynchronizedSortedMap <V>( sortedMap.tailMap( from ), sync ); }

  public double firstDoubleKey() { synchronized( sync ) { return sortedMap.firstDoubleKey(); } }
  public double lastDoubleKey() { synchronized( sync ) { return sortedMap.lastDoubleKey(); } }


  public Double firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Double lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Double2ObjectSortedMap <V> subMap( final Double from, final Double to ) { return new SynchronizedSortedMap <V>( sortedMap.subMap( from, to ), sync ); }
  public Double2ObjectSortedMap <V> headMap( final Double to ) { return new SynchronizedSortedMap <V>( sortedMap.headMap( to ), sync ); }
  public Double2ObjectSortedMap <V> tailMap( final Double from ) { return new SynchronizedSortedMap <V>( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static <V> Double2ObjectSortedMap <V> synchronize( final Double2ObjectSortedMap <V> m ) { return new SynchronizedSortedMap <V>( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static <V> Double2ObjectSortedMap <V> synchronize( final Double2ObjectSortedMap <V> m, final Object sync ) { return new SynchronizedSortedMap <V>( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap <V> extends Double2ObjectMaps.UnmodifiableMap <V> implements Double2ObjectSortedMap <V>, java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Double2ObjectSortedMap <V> sortedMap;

  protected UnmodifiableSortedMap( final Double2ObjectSortedMap <V> m ) {
   super( m );
   sortedMap = m;
  }

  public DoubleComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Double2ObjectMap.Entry <V> > double2ObjectEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.double2ObjectEntrySet() ); return (ObjectSortedSet<Double2ObjectMap.Entry <V> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Double, V>> entrySet() { return (ObjectSortedSet)double2ObjectEntrySet(); }
  public DoubleSortedSet keySet() { if ( keys == null ) keys = DoubleSortedSets.unmodifiable( sortedMap.keySet() ); return (DoubleSortedSet )keys; }

  public Double2ObjectSortedMap <V> subMap( final double from, final double to ) { return new UnmodifiableSortedMap <V>( sortedMap.subMap( from, to ) ); }
  public Double2ObjectSortedMap <V> headMap( final double to ) { return new UnmodifiableSortedMap <V>( sortedMap.headMap( to ) ); }
  public Double2ObjectSortedMap <V> tailMap( final double from ) { return new UnmodifiableSortedMap <V>( sortedMap.tailMap( from ) ); }

  public double firstDoubleKey() { return sortedMap.firstDoubleKey(); }
  public double lastDoubleKey() { return sortedMap.lastDoubleKey(); }


  public Double firstKey() { return sortedMap.firstKey(); }
  public Double lastKey() { return sortedMap.lastKey(); }

  public Double2ObjectSortedMap <V> subMap( final Double from, final Double to ) { return new UnmodifiableSortedMap <V>( sortedMap.subMap( from, to ) ); }
  public Double2ObjectSortedMap <V> headMap( final Double to ) { return new UnmodifiableSortedMap <V>( sortedMap.headMap( to ) ); }
  public Double2ObjectSortedMap <V> tailMap( final Double from ) { return new UnmodifiableSortedMap <V>( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static <V> Double2ObjectSortedMap <V> unmodifiable( final Double2ObjectSortedMap <V> m ) { return new UnmodifiableSortedMap <V>( m ); }
}
