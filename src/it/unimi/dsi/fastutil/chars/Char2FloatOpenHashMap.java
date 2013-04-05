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
package it.unimi.dsi.fastutil.chars;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import static it.unimi.dsi.fastutil.HashCommon.arraySize;
import static it.unimi.dsi.fastutil.HashCommon.maxFill;
import java.util.Map;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
/** A type-specific hash map with a fast, small-footprint implementation.
 *
 * <P>Instances of this class use a hash table to represent a map. The table is
 * enlarged as needed by doubling its size when new entries are created, but it is <em>never</em> made
 * smaller (even on a {@link #clear()}). A family of {@linkplain #trim() trimming
 * methods} lets you control the size of the table; this is particularly useful
 * if you reuse instances of this class.
 *
 * <p><strong>Warning:</strong> The implementation of this class has significantly
 * changed in <code>fastutil</code> 6.1.0. Please read the
 * comments about this issue in the section &ldquo;Faster Hash Tables&rdquo; of the <a href="../../../../../overview-summary.html">overview</a>.
 *
 * @see Hash
 * @see HashCommon
 */
public class Char2FloatOpenHashMap extends AbstractChar2FloatMap implements java.io.Serializable, Cloneable, Hash {
    private static final long serialVersionUID = 0L;
 private static final boolean ASSERTS = false;
 /** The array of keys. */
 protected transient char key[];
 /** The array of values. */
 protected transient float value[];
 /** The array telling whether a position is used. */
 protected transient boolean used[];
 /** The acceptable load factor. */
 protected final float f;
 /** The current table size. */
 protected transient int n;
 /** Threshold after which we rehash. It must be the table size times {@link #f}. */
 protected transient int maxFill;
 /** The mask for wrapping a position counter. */
 protected transient int mask;
 /** Number of entries in the set. */
 protected int size;
 /** Cached set of entries. */
 protected transient volatile FastEntrySet entries;
 /** Cached set of keys. */
 protected transient volatile CharSet keys;
 /** Cached collection of values. */
 protected transient volatile FloatCollection values;
 /** Creates a new hash map.
	 *
	 * <p>The actual table size will be the least power of two greater than <code>expected</code>/<code>f</code>.
	 *
	 * @param expected the expected number of elements in the hash set. 
	 * @param f the load factor.
	 */
 @SuppressWarnings("unchecked")
 public Char2FloatOpenHashMap( final int expected, final float f ) {
  if ( f <= 0 || f > 1 ) throw new IllegalArgumentException( "Load factor must be greater than 0 and smaller than or equal to 1" );
  if ( expected < 0 ) throw new IllegalArgumentException( "The expected number of elements must be nonnegative" );
  this.f = f;
  n = arraySize( expected, f );
  mask = n - 1;
  maxFill = maxFill( n, f );
  key = new char[ n ];
  value = new float[ n ];
  used = new boolean[ n ];
 }
 /** Creates a new hash map with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor.
	 *
	 * @param expected the expected number of elements in the hash map.
	 */
 public Char2FloatOpenHashMap( final int expected ) {
  this( expected, DEFAULT_LOAD_FACTOR );
 }
 /** Creates a new hash map with initial expected {@link Hash#DEFAULT_INITIAL_SIZE} entries
	 * and {@link Hash#DEFAULT_LOAD_FACTOR} as load factor.
	 */
 public Char2FloatOpenHashMap() {
  this( DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR );
 }
 /** Creates a new hash map copying a given one.
	 *
	 * @param m a {@link Map} to be copied into the new hash map. 
	 * @param f the load factor.
	 */
 public Char2FloatOpenHashMap( final Map<? extends Character, ? extends Float> m, final float f ) {
  this( m.size(), f );
  putAll( m );
 }
 /** Creates a new hash map with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor copying a given one.
	 *
	 * @param m a {@link Map} to be copied into the new hash map. 
	 */
 public Char2FloatOpenHashMap( final Map<? extends Character, ? extends Float> m ) {
  this( m, DEFAULT_LOAD_FACTOR );
 }
 /** Creates a new hash map copying a given type-specific one.
	 *
	 * @param m a type-specific map to be copied into the new hash map. 
	 * @param f the load factor.
	 */
 public Char2FloatOpenHashMap( final Char2FloatMap m, final float f ) {
  this( m.size(), f );
  putAll( m );
 }
 /** Creates a new hash map with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor copying a given type-specific one.
	 *
	 * @param m a type-specific map to be copied into the new hash map. 
	 */
 public Char2FloatOpenHashMap( final Char2FloatMap m ) {
  this( m, DEFAULT_LOAD_FACTOR );
 }
 /** Creates a new hash map using the elements of two parallel arrays.
	 *
	 * @param k the array of keys of the new hash map.
	 * @param v the array of corresponding values in the new hash map.
	 * @param f the load factor.
	 * @throws IllegalArgumentException if <code>k</code> and <code>v</code> have different lengths.
	 */
 public Char2FloatOpenHashMap( final char[] k, final float v[], final float f ) {
  this( k.length, f );
  if ( k.length != v.length ) throw new IllegalArgumentException( "The key array and the value array have different lengths (" + k.length + " and " + v.length + ")" );
  for( int i = 0; i < k.length; i++ ) this.put( k[ i ], v[ i ] );
 }
 /** Creates a new hash map with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor using the elements of two parallel arrays.
	 *
	 * @param k the array of keys of the new hash map.
	 * @param v the array of corresponding values in the new hash map.
	 * @throws IllegalArgumentException if <code>k</code> and <code>v</code> have different lengths.
	 */
 public Char2FloatOpenHashMap( final char[] k, final float v[] ) {
  this( k, v, DEFAULT_LOAD_FACTOR );
 }
 /*
	 * The following methods implements some basic building blocks used by
	 * all accessors. They are (and should be maintained) identical to those used in OpenHashSet.drv.
	 */
 public float put(final char k, final float v) {
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) {
    final float oldValue = value[ pos ];
    value[ pos ] = v;
    return oldValue;
   }
   pos = ( pos + 1 ) & mask;
  }
  used[ pos ] = true;
  key[ pos ] = k;
  value[ pos ] = v;
  if ( ++size >= maxFill ) rehash( arraySize( size + 1, f ) );
  if ( ASSERTS ) checkTable();
  return defRetValue;
 }
 public Float put( final Character ok, final Float ov ) {
  final float v = ((ov).floatValue());
  final char k = ((ok).charValue());
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) {
    final Float oldValue = (Float.valueOf(value[ pos ]));
    value[ pos ] = v;
    return oldValue;
   }
   pos = ( pos + 1 ) & mask;
  }
  used[ pos ] = true;
  key[ pos ] = k;
  value[ pos ] = v;
  if ( ++size >= maxFill ) rehash( arraySize( size + 1, f ) );
  if ( ASSERTS ) checkTable();
  return (null);
 }
 /** Adds an increment to value currently associated with a key.
	 *
	 * @param k the key.
	 * @param incr the increment.
	 * @return the old value, or the {@linkplain #defaultReturnValue() default return value} if no value was present for the given key.
	 * @deprecated use <code>addTo()</code> instead; having the same name of a {@link java.util.Set} method turned out to be a recipe for disaster.
	 */
 @Deprecated
 public float add(final char k, final float incr) {
  return addTo( k, incr );
 }
 /** Adds an increment to value currently associated with a key.
	 *
	 * <P>Note that this method respects the {@linkplain #defaultReturnValue() default return value} semantics: when
	 * called with a key that does not currently appears in the map, the key
	 * will be associated with the default return value plus
	 * the given increment.
	 *
	 * @param k the key.
	 * @param incr the increment.
	 * @return the old value, or the {@linkplain #defaultReturnValue() default return value} if no value was present for the given key.
	 */
 public float addTo(final char k, final float incr) {
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) {
    final float oldValue = value[ pos ];
    value[ pos ] += incr;
    return oldValue;
   }
   pos = ( pos + 1 ) & mask;
  }
  used[ pos ] = true;
  key[ pos ] = k;
  value[ pos ] = defRetValue + incr;
  if ( ++size >= maxFill ) rehash( arraySize( size + 1, f ) );
  if ( ASSERTS ) checkTable();
  return defRetValue;
 }
 /** Shifts left entries with the specified hash code, starting at the specified position,
	 * and empties the resulting free entry.
	 *
	 * @param pos a starting position.
	 * @return the position cleared by the shifting process.
	 */
 protected final int shiftKeys( int pos ) {
  // Shift entries with the same hash.
  int last, slot;
  for(;;) {
   pos = ( ( last = pos ) + 1 ) & mask;
   while( used[ pos ] ) {
    slot = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (key[ pos ]) ) ) & mask;
    if ( last <= pos ? last >= slot || slot > pos : last >= slot && slot > pos ) break;
    pos = ( pos + 1 ) & mask;
   }
   if ( ! used[ pos ] ) break;
   key[ last ] = key[ pos ];
   value[ last ] = value[ pos ];
  }
  used[ last ] = false;
  return last;
 }
 @SuppressWarnings("unchecked")
 public float remove( final char k ) {
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) {
    size--;
    final float v = value[ pos ];
    shiftKeys( pos );
    return v;
   }
   pos = ( pos + 1 ) & mask;
  }
  return defRetValue;
 }
 @SuppressWarnings("unchecked")
 public Float remove( final Object ok ) {
  final char k = ((((Character)(ok)).charValue()));
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) {
    size--;
    final float v = value[ pos ];
    shiftKeys( pos );
    return (Float.valueOf(v));
   }
   pos = ( pos + 1 ) & mask;
  }
  return (null);
 }
 public Float get( final Character ok ) {
  final char k = ((ok).charValue());
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( ( k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == ( k) ) ) return (Float.valueOf(value[ pos ]));
   pos = ( pos + 1 ) & mask;
  }
  return (null);
 }
 @SuppressWarnings("unchecked")
 public float get( final char k ) {
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) return value[ pos ];
   pos = ( pos + 1 ) & mask;
  }
  return defRetValue;
 }
 @SuppressWarnings("unchecked")
 public boolean containsKey( final char k ) {
  // The starting point.
  int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
  // There's always an unused entry.
  while( used[ pos ] ) {
   if ( ( (key[ pos ]) == (k) ) ) return true;
   pos = ( pos + 1 ) & mask;
  }
  return false;
 }
 public boolean containsValue( final float v ) {
  final float value[] = this.value;
  final boolean used[] = this.used;
  for( int i = n; i-- != 0; ) if ( used[ i ] && ( (value[ i ]) == (v) ) ) return true;
  return false;
 }
 /* Removes all elements from this map.
	 *
	 * <P>To increase object reuse, this method does not change the table size.
	 * If you want to reduce the table size, you must use {@link #trim()}.
	 *
	 */
 public void clear() {
  if ( size == 0 ) return;
  size = 0;
  BooleanArrays.fill( used, false );
  // We null all object entries so that the garbage collector can do its work.
 }
 public int size() {
  return size;
 }
 public boolean isEmpty() {
  return size == 0;
 }
 /** A no-op for backward compatibility.
	 * 
	 * @param growthFactor unused.
	 * @deprecated Since <code>fastutil</code> 6.1.0, hash tables are doubled when they are too full.
	 */
 @Deprecated
 public void growthFactor( int growthFactor ) {}
 /** Gets the growth factor (2).
	 *
	 * @return the growth factor of this set, which is fixed (2).
	 * @see #growthFactor(int)
	 * @deprecated Since <code>fastutil</code> 6.1.0, hash tables are doubled when they are too full.
	 */
 @Deprecated
 public int growthFactor() {
  return 16;
 }
 /** The entry class for a hash map does not record key and value, but
	 * rather the position in the hash table of the corresponding entry. This
	 * is necessary so that calls to {@link java.util.Map.Entry#setValue(Object)} are reflected in
	 * the map */
 private final class MapEntry implements Char2FloatMap.Entry , Map.Entry<Character, Float> {
  // The table index this entry refers to, or -1 if this entry has been deleted.
  private int index;
  MapEntry( final int index ) {
   this.index = index;
  }
  public Character getKey() {
   return (Character.valueOf(key[ index ]));
  }
  public char getCharKey() {
      return key[ index ];
  }
  public Float getValue() {
   return (Float.valueOf(value[ index ]));
  }
  public float getFloatValue() {
   return value[ index ];
  }
  public float setValue( final float v ) {
   final float oldValue = value[ index ];
   value[ index ] = v;
   return oldValue;
  }
  public Float setValue( final Float v ) {
   return (Float.valueOf(setValue( ((v).floatValue()) )));
  }
  @SuppressWarnings("unchecked")
  public boolean equals( final Object o ) {
   if (!(o instanceof Map.Entry)) return false;
   Map.Entry<Character, Float> e = (Map.Entry<Character, Float>)o;
   return ( (key[ index ]) == (((e.getKey()).charValue())) ) && ( (value[ index ]) == (((e.getValue()).floatValue())) );
  }
  public int hashCode() {
   return (key[ index ]) ^ it.unimi.dsi.fastutil.HashCommon.float2int(value[ index ]);
  }
  public String toString() {
   return key[ index ] + "=>" + value[ index ];
  }
 }
 /** An iterator over a hash map. */
 private class MapIterator {
  /** The index of the next entry to be returned, if positive or zero. If negative, the next entry to be
			returned, if any, is that of index -pos -2 from the {@link #wrapped} list. */
  int pos = Char2FloatOpenHashMap.this.n;
  /** The index of the last entry that has been returned. It is -1 if either
			we did not return an entry yet, or the last returned entry has been removed. */
  int last = -1;
  /** A downward counter measuring how many entries must still be returned. */
  int c = size;
  /** A lazily allocated list containing the keys of elements that have wrapped around the table because of removals; such elements
			would not be enumerated (other elements would be usually enumerated twice in their place). */
  CharArrayList wrapped;
  {
   final boolean used[] = Char2FloatOpenHashMap.this.used;
   if ( c != 0 ) while( ! used[ --pos ] );
  }
  public boolean hasNext() {
   return c != 0;
  }
  public int nextEntry() {
   if ( ! hasNext() ) throw new NoSuchElementException();
   c--;
   // We are just enumerating elements from the wrapped list.
   if ( pos < 0 ) {
    final char k = wrapped.getChar( - ( last = --pos ) - 2 );
    // The starting point.
    int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
    // There's always an unused entry.
    while( used[ pos ] ) {
     if ( ( (key[ pos ]) == (k) ) ) return pos;
     pos = ( pos + 1 ) & mask;
    }
   }
   last = pos;
   //System.err.println( "Count: " + c );
   if ( c != 0 ) {
    final boolean used[] = Char2FloatOpenHashMap.this.used;
    while ( pos-- != 0 && !used[ pos ] );
    // When here pos < 0 there are no more elements to be enumerated by scanning, but wrapped might be nonempty.
   }
   return last;
  }
  /** Shifts left entries with the specified hash code, starting at the specified position,
		 * and empties the resulting free entry. If any entry wraps around the table, instantiates
		 * lazily {@link #wrapped} and stores the entry key.
		 *
		 * @param pos a starting position.
		 * @return the position cleared by the shifting process.
		 */
  protected final int shiftKeys( int pos ) {
   // Shift entries with the same hash.
   int last, slot;
   for(;;) {
    pos = ( ( last = pos ) + 1 ) & mask;
    while( used[ pos ] ) {
     slot = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (key[ pos ]) ) ) & mask;
     if ( last <= pos ? last >= slot || slot > pos : last >= slot && slot > pos ) break;
     pos = ( pos + 1 ) & mask;
    }
    if ( ! used[ pos ] ) break;
    if ( pos < last ) {
     // Wrapped entry.
     if ( wrapped == null ) wrapped = new CharArrayList ();
     wrapped.add( key[ pos ] );
    }
    key[ last ] = key[ pos ];
    value[ last ] = value[ pos ];
   }
   used[ last ] = false;
   return last;
  }
  @SuppressWarnings("unchecked")
  public void remove() {
   if ( last == -1 ) throw new IllegalStateException();
   if ( pos < -1 ) {
    // We're removing wrapped entries.
    Char2FloatOpenHashMap.this.remove( wrapped.getChar( - pos - 2 ) );
    last = -1;
    return;
   }
   size--;
   if ( shiftKeys( last ) == pos && c > 0 ) {
    c++;
    nextEntry();
   }
   last = -1; // You can no longer remove this entry.
   if ( ASSERTS ) checkTable();
  }
  public int skip( final int n ) {
   int i = n;
   while( i-- != 0 && hasNext() ) nextEntry();
   return n - i - 1;
  }
 }
 private class EntryIterator extends MapIterator implements ObjectIterator<Char2FloatMap.Entry > {
  private MapEntry entry;
  public Char2FloatMap.Entry next() {
   return entry = new MapEntry( nextEntry() );
  }
  @Override
  public void remove() {
   super.remove();
   entry.index = -1; // You cannot use a deleted entry.
  }
 }
 private class FastEntryIterator extends MapIterator implements ObjectIterator<Char2FloatMap.Entry > {
  final BasicEntry entry = new BasicEntry ( ((char)0), (0) );
  public BasicEntry next() {
   final int e = nextEntry();
   entry.key = key[ e ];
   entry.value = value[ e ];
   return entry;
  }
 }
 private final class MapEntrySet extends AbstractObjectSet<Char2FloatMap.Entry > implements FastEntrySet {
  public ObjectIterator<Char2FloatMap.Entry > iterator() {
   return new EntryIterator();
  }
  public ObjectIterator<Char2FloatMap.Entry > fastIterator() {
   return new FastEntryIterator();
  }
  @SuppressWarnings("unchecked")
  public boolean contains( final Object o ) {
   if ( !( o instanceof Map.Entry ) ) return false;
   final Map.Entry<Character, Float> e = (Map.Entry<Character, Float>)o;
   final char k = ((e.getKey()).charValue());
   // The starting point.
   int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
   // There's always an unused entry.
   while( used[ pos ] ) {
    if ( ( (key[ pos ]) == (k) ) ) return ( (value[ pos ]) == (((e.getValue()).floatValue())) );
    pos = ( pos + 1 ) & mask;
   }
   return false;
  }
  @SuppressWarnings("unchecked")
  public boolean remove( final Object o ) {
   if ( !( o instanceof Map.Entry ) ) return false;
   final Map.Entry<Character, Float> e = (Map.Entry<Character, Float>)o;
   final char k = ((e.getKey()).charValue());
   // The starting point.
   int pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
   // There's always an unused entry.
   while( used[ pos ] ) {
    if ( ( (key[ pos ]) == (k) ) ) {
     Char2FloatOpenHashMap.this.remove( e.getKey() );
     return true;
    }
    pos = ( pos + 1 ) & mask;
   }
   return false;
  }
  public int size() {
   return size;
  }
  public void clear() {
   Char2FloatOpenHashMap.this.clear();
  }
 }
 public FastEntrySet char2FloatEntrySet() {
  if ( entries == null ) entries = new MapEntrySet();
  return entries;
 }
 /** An iterator on keys.
	 *
	 * <P>We simply override the {@link java.util.ListIterator#next()}/{@link java.util.ListIterator#previous()} methods
	 * (and possibly their type-specific counterparts) so that they return keys
	 * instead of entries.
	 */
 private final class KeyIterator extends MapIterator implements CharIterator {
  public KeyIterator() { super(); }
  public char nextChar() { return key[ nextEntry() ]; }
  public Character next() { return (Character.valueOf(key[ nextEntry() ])); }
 }
 private final class KeySet extends AbstractCharSet {
  public CharIterator iterator() {
   return new KeyIterator();
  }
  public int size() {
   return size;
  }
  public boolean contains( char k ) {
   return containsKey( k );
  }
  public boolean remove( char k ) {
   final int oldSize = size;
   Char2FloatOpenHashMap.this.remove( k );
   return size != oldSize;
  }
  public void clear() {
   Char2FloatOpenHashMap.this.clear();
  }
 }
 public CharSet keySet() {
  if ( keys == null ) keys = new KeySet();
  return keys;
 }
 /** An iterator on values.
	 *
	 * <P>We simply override the {@link java.util.ListIterator#next()}/{@link java.util.ListIterator#previous()} methods
	 * (and possibly their type-specific counterparts) so that they return values
	 * instead of entries.
	 */
 private final class ValueIterator extends MapIterator implements FloatIterator {
  public ValueIterator() { super(); }
  public float nextFloat() { return value[ nextEntry() ]; }
  public Float next() { return (Float.valueOf(value[ nextEntry() ])); }
 }
 public FloatCollection values() {
  if ( values == null ) values = new AbstractFloatCollection () {
    public FloatIterator iterator() {
     return new ValueIterator();
    }
    public int size() {
     return size;
    }
    public boolean contains( float v ) {
     return containsValue( v );
    }
    public void clear() {
     Char2FloatOpenHashMap.this.clear();
    }
   };
  return values;
 }
 /** A no-op for backward compatibility. The kind of tables implemented by
	 * this class never need rehashing.
	 *
	 * <P>If you need to reduce the table size to fit exactly
	 * this set, use {@link #trim()}.
	 *
	 * @return true.
	 * @see #trim()
	 * @deprecated A no-op.
	 */
 @Deprecated
 public boolean rehash() {
  return true;
 }
 /** Rehashes the map, making the table as small as possible.
	 * 
	 * <P>This method rehashes the table to the smallest size satisfying the
	 * load factor. It can be used when the set will not be changed anymore, so
	 * to optimize access speed and size.
	 *
	 * <P>If the table size is already the minimum possible, this method
	 * does nothing. 
	 *
	 * @return true if there was enough memory to trim the map.
	 * @see #trim(int)
	 */
 public boolean trim() {
  final int l = arraySize( size, f );
  if ( l >= n ) return true;
  try {
   rehash( l );
  }
  catch(OutOfMemoryError cantDoIt) { return false; }
  return true;
 }
 /** Rehashes this map if the table is too large.
	 * 
	 * <P>Let <var>N</var> be the smallest table size that can hold
	 * <code>max(n,{@link #size()})</code> entries, still satisfying the load factor. If the current
	 * table size is smaller than or equal to <var>N</var>, this method does
	 * nothing. Otherwise, it rehashes this map in a table of size
	 * <var>N</var>.
	 *
	 * <P>This method is useful when reusing maps.  {@linkplain #clear() Clearing a
	 * map} leaves the table size untouched. If you are reusing a map
	 * many times, you can call this method with a typical
	 * size to avoid keeping around a very large table just
	 * because of a few large transient maps.
	 *
	 * @param n the threshold for the trimming.
	 * @return true if there was enough memory to trim the map.
	 * @see #trim()
	 */
 public boolean trim( final int n ) {
  final int l = HashCommon.nextPowerOfTwo( (int)Math.ceil( n / f ) );
  if ( this.n <= l ) return true;
  try {
   rehash( l );
  }
  catch( OutOfMemoryError cantDoIt ) { return false; }
  return true;
 }
 /** Resizes the map.
	 *
	 * <P>This method implements the basic rehashing strategy, and may be
	 * overriden by subclasses implementing different rehashing strategies (e.g.,
	 * disk-based rehashing). However, you should not override this method
	 * unless you understand the internal workings of this class.
	 *
	 * @param newN the new size
	 */
 @SuppressWarnings("unchecked")
 protected void rehash( final int newN ) {
  int i = 0, pos;
  final boolean used[] = this.used;
  char k;
  final char key[] = this.key;
  final float value[] = this.value;
  final int newMask = newN - 1;
  final char newKey[] = new char[ newN ];
  final float newValue[] = new float[newN];
  final boolean newUsed[] = new boolean[ newN ];
  for( int j = size; j-- != 0; ) {
   while( ! used[ i ] ) i++;
   k = key[ i ];
   pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & newMask;
   while ( newUsed[ pos ] ) pos = ( pos + 1 ) & newMask;
   newUsed[ pos ] = true;
   newKey[ pos ] = k;
   newValue[ pos ] = value[ i ];
   i++;
  }
  n = newN;
  mask = newMask;
  maxFill = maxFill( n, f );
  this.key = newKey;
  this.value = newValue;
  this.used = newUsed;
 }
 /** Returns a deep copy of this map. 
	 *
	 * <P>This method performs a deep copy of this hash map; the data stored in the
	 * map, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this map.
	 */
 @SuppressWarnings("unchecked")
 public Char2FloatOpenHashMap clone() {
  Char2FloatOpenHashMap c;
  try {
   c = (Char2FloatOpenHashMap )super.clone();
  }
  catch(CloneNotSupportedException cantHappen) {
   throw new InternalError();
  }
  c.keys = null;
  c.values = null;
  c.entries = null;
  c.key = key.clone();
  c.value = value.clone();
  c.used = used.clone();
  return c;
 }
 /** Returns a hash code for this map.
	 *
	 * This method overrides the generic method provided by the superclass. 
	 * Since <code>equals()</code> is not overriden, it is important
	 * that the value returned by this method is the same value as
	 * the one returned by the overriden method.
	 *
	 * @return a hash code for this map.
	 */
 public int hashCode() {
  int h = 0;
  for( int j = size, i = 0, t = 0; j-- != 0; ) {
   while( ! used[ i ] ) i++;
    t = (key[ i ]);
    t ^= it.unimi.dsi.fastutil.HashCommon.float2int(value[ i ]);
   h += t;
   i++;
  }
  return h;
 }
 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
  final char key[] = this.key;
  final float value[] = this.value;
  final MapIterator i = new MapIterator();
  s.defaultWriteObject();
  for( int j = size, e; j-- != 0; ) {
   e = i.nextEntry();
   s.writeChar( key[ e ] );
   s.writeFloat( value[ e ] );
  }
 }
 @SuppressWarnings("unchecked")
 private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
  s.defaultReadObject();
  n = arraySize( size, f );
  maxFill = maxFill( n, f );
  mask = n - 1;
  final char key[] = this.key = new char[ n ];
  final float value[] = this.value = new float[ n ];
  final boolean used[] = this.used = new boolean[ n ];
  char k;
  float v;
  for( int i = size, pos = 0; i-- != 0; ) {
   k = s.readChar();
   v = s.readFloat();
   pos = ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (k) ) ) & mask;
   while ( used[ pos ] ) pos = ( pos + 1 ) & mask;
   used[ pos ] = true;
   key[ pos ] = k;
   value[ pos ] = v;
  }
  if ( ASSERTS ) checkTable();
 }
 private void checkTable() {}
}