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
 * Copyright (C) 2003-2013 Paolo Boldi and Sebastiano Vigna 
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
/** A class providing static methods and objects that do useful things with heaps.
 *
 * <P>The static methods of this class allow to treat arrays as 0-based heaps. They
 * are used in the implementation of heap-based queues, but they may be also used
 * directly.
 *
 */
public class CharHeaps {
 private CharHeaps() {}
 /** Moves the given element down into the heap until it reaches the lowest possible position.
	 *
	 * @param heap the heap (starting at 0).
	 * @param size the number of elements in the heap.
	 * @param i the index of the element that must be moved down.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the new position of the element of index <code>i</code>.
	 */
 @SuppressWarnings("unchecked")
 public static int downHeap( final char[] heap, final int size, int i, final CharComparator c ) {
  if ( i >= size ) throw new IllegalArgumentException( "Heap position (" + i + ") is larger than or equal to heap size (" + size + ")" );
  final char e = heap[ i ];
  int child;
  if ( c == null )
   while ( ( child = 2 * i + 1 ) < size ) {
    if ( child + 1 < size && ( (heap[ child + 1 ]) < (heap[ child ]) ) ) child++;
    if ( ( (e) <= (heap[ child ]) ) ) break;
    heap[ i ] = heap[ child ];
    i = child;
   }
  else
   while ( ( child = 2 * i + 1 ) < size ) {
    if ( child + 1 < size && c.compare( heap[ child + 1 ], heap[ child ] ) < 0 ) child++;
    if ( c.compare( e, heap[ child ] ) <= 0 ) break;
    heap[ i ] = heap[ child ];
    i = child;
   }
  heap[ i ] = e;
  return i;
 }
 /** Moves the given element up in the heap until it reaches the highest possible position.
	 *
	 * @param heap the heap (starting at 0).
	 * @param size the number of elements in the heap.
	 * @param i the index of the element that must be moved up.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the new position of the element of index <code>i</code>.
	 */
 @SuppressWarnings("unchecked")
 public static int upHeap( final char[] heap, final int size, int i, final CharComparator c ) {
  if ( i >= size ) throw new IllegalArgumentException( "Heap position (" + i + ") is larger than or equal to heap size (" + size + ")" );
  final char e = heap[ i ];
  int parent;
  if ( c == null )
   while ( i != 0 && ( parent = ( i - 1 ) / 2 ) >= 0 ) {
    if ( ( (heap[ parent ]) <= (e) ) ) break;
    heap[ i ] = heap[ parent ];
    i = parent;
   }
  else
   while ( i != 0 && ( parent = ( i - 1 ) / 2 ) >= 0 ) {
    if ( c.compare( heap[ parent ], e ) <= 0 ) break;
    heap[ i ] = heap[ parent ];
    i = parent;
   }
  heap[ i ] = e;
  return i;
 }
 /** Makes an array into a heap.
	 *
	 * @param heap the heap (starting at 0).
	 * @param size the number of elements in the heap.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 */
 public static void makeHeap( final char[] heap, final int size, final CharComparator c ) {
  int i = size / 2;
  while( i-- != 0 ) downHeap( heap, size, i, c );
 }
}
